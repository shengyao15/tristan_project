/**
 * Author by metaphy
 * Date Feb 26, 2008
 * All Rights Reserved.
 */
package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import chess.Player.Located;


public class OperationCallinLineup implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		MainFrame parent = MainFrame.me;
		if (parent.getGameState() == 0 || parent.getGameState() == 1) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("c:\\"));
			chooser.setFileFilter(new LineupFileFilter());
			chooser.showOpenDialog(parent);

			File f = chooser.getSelectedFile();
			if (f != null) {
				try {
					
					for(String key : MainPanel.figureMap.keySet()){
						ChessmanFigure cf = MainPanel.figureMap.get(key);
						MainPanel.mp.remove(cf);
					}
					
					MainPanel.figureMap =  new HashMap<String, ChessmanFigure>();
					MainPanel.figureFinalMap =  new HashMap<String, ChessmanFigure>();
					MainPanel.mp.removeStation();
					
					if(Const.SERVER.equals(MainFrame.direction)){
						List<Chessman> list = InitPlayers.initOnePlayer(Located.SOUTH, f).getChessmans();
						Player p = new Player(Located.SOUTH);
						for(Chessman c : list){
							ChessmanFigure f1 = new ChessmanFigure(p,c,MainFrame.direction);
							MainPanel.mp.add(f1);
							MainPanel.figureMap.put(f1.getKey(), f1);
							MainPanel.figureFinalMap.put(f1.getKey(), f1);
						}
					}else if(Const.CLIENT_A.equals(MainFrame.direction)){
						List<Chessman> list2 = InitPlayers.initOnePlayer(Located.SOUTH, f).getChessmans();
						Player p2 = new Player(Located.NORTH);
						for(Chessman c : list2){
							ChessmanFigure f2 = new ChessmanFigure(p2,c, MainFrame.direction);
							MainPanel.mp.add(f2);
							MainPanel.figureMap.put(f2.getKey(), f2);
							MainPanel.figureFinalMap.put(f2.getKey(), f2);
						}
					}else if(Const.CLIENT_B.equals(MainFrame.direction)){
						List<Chessman> list2 = InitPlayers.initOnePlayer(Located.SOUTH, f).getChessmans();
						Player p2 = new Player(Located.WEST);
						for(Chessman c : list2){
							ChessmanFigure f2 = new ChessmanFigure(p2,c, MainFrame.direction);
							MainPanel.mp.add(f2);
							MainPanel.figureMap.put(f2.getKey(), f2);
							MainPanel.figureFinalMap.put(f2.getKey(), f2);
						}
					}else if(Const.CLIENT_C.equals(MainFrame.direction)){
						List<Chessman> list2 = InitPlayers.initOnePlayer(Located.SOUTH, f).getChessmans();
						Player p2 = new Player(Located.EAST);
						for(Chessman c : list2){
							ChessmanFigure f2 = new ChessmanFigure(p2,c, MainFrame.direction);
							MainPanel.mp.add(f2);
							MainPanel.figureMap.put(f2.getKey(), f2);
							MainPanel.figureFinalMap.put(f2.getKey(), f2);
						}
					}
					
					MainPanel.mp.drawStation();
					//MainPanel.mp.repaint();
					
					MainFrame.me.mp.setMessage(Const.msg15);
					MainPanel.mp.add(RoadsComponent.getInstance());		
					
				} catch(LineupException le){
					MainFrame.me.mp.setMessage(Const.msg14);
					MainPanel.mp.drawChessman();
					MainPanel.mp.drawStation();
					MainPanel.mp.add(RoadsComponent.getInstance());
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private class LineupFileFilter extends FileFilter {
		@Override
		public boolean accept(File f) {
			return f.getName().toLowerCase().endsWith(".military") || f.isDirectory();
		}

		@Override
		public String getDescription() {
			return null;
		}
	}
}
