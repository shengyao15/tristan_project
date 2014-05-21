/**
 * Author by metaphy
 * Date Dec 26, 2007
 * All Rights Reserved.
 */
package chess;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Map;

public class ReceiveThread extends Thread {
	private static final int CHESSMAN_MOVE_STANDSTILL_TIME = 32;
	private Component component = MainFrame.me.getPanel();

	public void run() {
		ChessFight fight = ChessFight.getInstance();
		ChessmanFigure f1 = fight.getFirst();
		Object tmp = fight.getSecond();

		ChessmanFigure firstFigure = MainPanel.figureFinalMap.get(f1.getKey());
		Chessman first = firstFigure.getChessman();
		ChessmanFigure secondFigure = null;
		Chessman second = null;
		ChessmanFigure f2 = null;

		MainPanel panel = (MainPanel) component;

		ArrayList<Integer> path;
		try {
			Coordinate start = first.getCoordinate();
			Coordinate end = null;

			if (tmp instanceof ChessmanFigure) {
				f2 = (ChessmanFigure) tmp;
				secondFigure = MainPanel.figureFinalMap.get(f2.getKey());
				second = secondFigure.getChessman();
				end = second.getCoordinate();
			} else if (tmp instanceof StationFigure) {
				Coordinate c = ((StationFigure) tmp).getSp().getCoordinate();
				for (StationFigure sf : MainPanel.stationFigureList) {
					if (sf.getSp().getCoordinate().equals(c)) {
						end = sf.getSp().getCoordinate();
						break;
					}
				}
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

								//MainPanel.figureFinalMap = (Map<String, ChessmanFigure>) Utils.clone(MainPanel.tmpMap);
								//MainPanel.tmpMap = null;

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
			}

			fight.clearFirstSecond();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
