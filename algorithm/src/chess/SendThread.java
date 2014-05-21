/**
 * Author by metaphy
 * Date Dec 26, 2007
 * All Rights Reserved.
 */
package chess;

import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;

import chess.Chessman.Soldier;


public class SendThread extends Thread {
	private static final int CHESSMAN_MOVE_STANDSTILL_TIME = 32;
	private Component component = MainFrame.me.getPanel();

	public void run() {
		ChessFight fight = ChessFight.getInstance();
		ChessmanFigure firstFigure = fight.getFirst();
		Chessman first = firstFigure.getChessman();
		ChessmanFigure secondFigure = null;
		Chessman second =  null;
		MainPanel panel = (MainPanel)component;
		boolean finish = false;
		String opponent = "";
		
		if (Soldier.JUNQI == first.getSoldier() || Soldier.DILEI == first.getSoldier()) {
			MainFrame.me.mp.setMessage(Const.msg8);
			fight.clearFirstSecond();
			return;
		}
		
		ArrayList<Integer> path;
		try {
			Coordinate start = first.getCoordinate();
			Coordinate end = null;

			Object obj = fight.getSecond();
			if (obj instanceof ChessmanFigure) {
				secondFigure = (ChessmanFigure) obj;
				second = secondFigure.getChessman();
				end = second.getCoordinate();
			} else if (obj instanceof StationFigure) {
				end = ((StationFigure) obj).getSp().getCoordinate();
			}
			
			PathFinding pf = new PathFinding();
				path = pf.tryToFind(start, end, first, second); // Start,end
															// included
			if (path != null && path.size() > 1) {
				for (int i = 0; i < path.size(); i++) {
					if (i == path.size() - 1) { // The last step
						if (second != null) {
							switch (first.attack(second)) {
							case 0:
								SoundPlayer.play(GameResources.getAudio("DIE"));
								panel.remove(firstFigure);
								Utils.showJunQi(firstFigure);
								MainPanel.figureFinalMap.remove(firstFigure.getKey());
								break;
							case 1:
								SoundPlayer.play(GameResources.getAudio("EQUAL"));
								panel.remove(firstFigure);
								panel.remove(secondFigure);
								Utils.showJunQi(firstFigure);
								Utils.showJunQi(secondFigure);
								MainPanel.figureFinalMap.remove(firstFigure.getKey());
								MainPanel.figureFinalMap.remove(secondFigure.getKey());
								break;
							case 2:
								SoundPlayer.play(GameResources.getAudio("EAT"));
								first.setCoordinate(second.getCoordinate());
								firstFigure.reSet();
								panel.remove(secondFigure);
								Utils.showJunQi(secondFigure);
								MainPanel.figureFinalMap.remove(secondFigure.getKey());
								break;
							case 9:
								SoundPlayer.play(GameResources.getAudio("CAPTURE"));
								first.setCoordinate(second.getCoordinate());
								firstFigure.reSet();
								panel.remove(secondFigure);
								Utils.removePlayer(secondFigure);
								MainPanel.figureFinalMap.remove(secondFigure.getKey());
								String pos = secondFigure.getPosition();
								if(Const.SERVER.equals(pos)){
									opponent = Const.SERVER;
									MainFrame.liveS = false;
								}else if(Const.CLIENT_A.equals(pos)){
									opponent = Const.CLIENT_A;
									MainFrame.liveA = false;
								}else if(Const.CLIENT_B.equals(pos)){
									opponent = Const.CLIENT_B;
									MainFrame.liveB = false;
								}else if(Const.CLIENT_C.equals(pos)){
									opponent = Const.CLIENT_C;
									MainFrame.liveC = false;
								}
								
								finish = true;
								
//								MainPanel.figureFinalMap = (Map<String, ChessmanFigure>) Utils.clone(MainPanel.tmpMap);
//								MainPanel.tmpMap = null;
								break;
							}
						} else {
							SoundPlayer.play(GameResources.getAudio("MOVE"));
						}
					} else {
						if (i != 0) {
							SoundPlayer.play(GameResources.getAudio("MOVE"));
						}
					}
					// Clear the path
					if (i == 0) {
						for (StationFigure sf : MainPanel.stationFigureList) {
							sf.setText("");
						}
					}
					// Display the path
					if (i != path.size() - 1) {
						Coordinate c = new Coordinate(path.get(i));
						for (StationFigure sf : MainPanel.stationFigureList) {
							if (sf.getSp().getCoordinate().equals(c)) {
								sf.setText(" >");
							}
						}
					}

					if (i >= 1) {
						first.setCoordinate(new Coordinate(path.get(i).intValue()));
						firstFigure.setChessman(first);
						firstFigure.reLocation();
						panel.repaint();
						try {
							Thread.sleep(CHESSMAN_MOVE_STANDSTILL_TIME);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}else{
				MainFrame.me.mp.setMessage(Const.msg9);
				fight.clearFirstSecond();
				return;
			}
			
			if(Const.SERVER.equals(MainFrame.me.direction)){
				TransBeanB beanB = new TransBeanB();
				beanB.setSide(Const.SERVER);
				ChessmanFigure cf1 = (ChessmanFigure)Utils.clone(fight.getFirst());
				cf1.getChessman().setCoordinate(Coordinate.symmetry(cf1.getChessman().getCoordinate()));
				beanB.setFirst(cf1);
				
				if(fight.getSecond() instanceof ChessmanFigure){
					ChessmanFigure secondCF = (ChessmanFigure)Utils.clone(fight.getSecond());
					secondCF.getChessman().setCoordinate(Coordinate.symmetry(secondCF.getChessman().getCoordinate()));
					beanB.setSecond(secondCF);
				}else{
					StationFigure secondSF = (StationFigure)Utils.clone(fight.getSecond());
					secondSF.getSp().setCoordinate(Coordinate.symmetry(secondSF.getSp().getCoordinate()));
					beanB.setSecond(secondSF);
				}
			
				
				//-----------------------
				TransBeanB beanB2 = new TransBeanB();
				beanB2.setSide(Const.SERVER);
				ChessmanFigure cf2 = (ChessmanFigure)Utils.clone(fight.getFirst());
				cf2.getChessman().setCoordinate(Coordinate.steer90(cf2.getChessman().getCoordinate()));
				beanB2.setFirst(cf2);
				
				if(fight.getSecond() instanceof ChessmanFigure){
					ChessmanFigure secondCF = (ChessmanFigure)Utils.clone(fight.getSecond());
					secondCF.getChessman().setCoordinate(Coordinate.steer90(secondCF.getChessman().getCoordinate()));
					beanB2.setSecond(secondCF);
				}else{
					StationFigure secondSF = (StationFigure)Utils.clone(fight.getSecond());
					secondSF.getSp().setCoordinate(Coordinate.steer90(secondSF.getSp().getCoordinate()));
					beanB2.setSecond(secondSF);
				}
			
				
				//--------------------------
				TransBeanB beanB3 = new TransBeanB();
				beanB3.setSide(Const.SERVER);
				ChessmanFigure cf3 = (ChessmanFigure)Utils.clone(fight.getFirst());
				cf3.getChessman().setCoordinate(Coordinate.steerMinus90(cf3.getChessman().getCoordinate()));
				beanB3.setFirst(cf3);
				
				if(fight.getSecond() instanceof ChessmanFigure){
					ChessmanFigure secondCF = (ChessmanFigure)Utils.clone(fight.getSecond());
					secondCF.getChessman().setCoordinate(Coordinate.steerMinus90(secondCF.getChessman().getCoordinate()));
					beanB3.setSecond(secondCF);
				}else{
					StationFigure secondSF = (StationFigure)Utils.clone(fight.getSecond());
					secondSF.getSp().setCoordinate(Coordinate.steerMinus90(secondSF.getSp().getCoordinate()));
					beanB3.setSecond(secondSF);
				}
			
				Utils.sendBeanServer(beanB, beanB2, beanB3);
					
			}else if(Const.CLIENT_A.equals(MainFrame.me.direction)){
				TransBeanB beanB = new TransBeanB();
				beanB.setSide(Const.CLIENT_A);
				ChessmanFigure cf1 = (ChessmanFigure)Utils.clone(fight.getFirst());
				cf1.getChessman().setCoordinate(Coordinate.symmetry(cf1.getChessman().getCoordinate()));
				beanB.setFirst(cf1);
				
				if(fight.getSecond() instanceof ChessmanFigure){
					ChessmanFigure secondCF = (ChessmanFigure)Utils.clone(fight.getSecond());
					secondCF.getChessman().setCoordinate(Coordinate.symmetry(secondCF.getChessman().getCoordinate()));
					beanB.setSecond(secondCF);
				}else{
					StationFigure secondSF = (StationFigure)Utils.clone(fight.getSecond());
					secondSF.getSp().setCoordinate(Coordinate.symmetry(secondSF.getSp().getCoordinate()));
					beanB.setSecond(secondSF);
				}
				beanB.setOpponent(opponent);
				Utils.sendBeanClientA(beanB, finish);
				MainFrame.currTurn = "-";
			}else if(Const.CLIENT_B.equals(MainFrame.me.direction)){
				TransBeanB beanB = new TransBeanB();
				beanB.setSide(Const.CLIENT_B);
				ChessmanFigure cf1 = (ChessmanFigure)Utils.clone(fight.getFirst());
				cf1.getChessman().setCoordinate(Coordinate.steerMinus90(cf1.getChessman().getCoordinate()));
				beanB.setFirst(cf1);
				
				if(fight.getSecond() instanceof ChessmanFigure){
					ChessmanFigure secondCF = (ChessmanFigure)Utils.clone(fight.getSecond());
					secondCF.getChessman().setCoordinate(Coordinate.steerMinus90(secondCF.getChessman().getCoordinate()));
					beanB.setSecond(secondCF);
				}else{
					StationFigure secondSF = (StationFigure)Utils.clone(fight.getSecond());
					secondSF.getSp().setCoordinate(Coordinate.steerMinus90(secondSF.getSp().getCoordinate()));
					beanB.setSecond(secondSF);
				}
				beanB.setOpponent(opponent);
				Utils.sendBeanClientB(beanB, finish);
				MainFrame.currTurn = "-";
				
			}else if(Const.CLIENT_C.equals(MainFrame.me.direction)){
				TransBeanB beanB = new TransBeanB();
				beanB.setSide(Const.CLIENT_C);
				ChessmanFigure cf1 = (ChessmanFigure)Utils.clone(fight.getFirst());
				cf1.getChessman().setCoordinate(Coordinate.steer90(cf1.getChessman().getCoordinate()));
				beanB.setFirst(cf1);
				
				if(fight.getSecond() instanceof ChessmanFigure){
					ChessmanFigure secondCF = (ChessmanFigure)Utils.clone(fight.getSecond());
					secondCF.getChessman().setCoordinate(Coordinate.steer90(secondCF.getChessman().getCoordinate()));
					beanB.setSecond(secondCF);
				}else{
					StationFigure secondSF = (StationFigure)Utils.clone(fight.getSecond());
					secondSF.getSp().setCoordinate(Coordinate.steer90(secondSF.getSp().getCoordinate()));
					beanB.setSecond(secondSF);
				}
				beanB.setOpponent(opponent);
				Utils.sendBeanClientC(beanB, finish);
				MainFrame.currTurn = "-";
			}
			
			
			fight.clearFirstSecond();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
