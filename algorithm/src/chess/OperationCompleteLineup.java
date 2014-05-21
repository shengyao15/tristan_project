/**
 * Author by metaphy
 * Date Feb 26, 2008
 * All Rights Reserved.
 */
package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class OperationCompleteLineup implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		MainFrame frame = MainFrame.me;
		MainPanel panel = frame.getPanel();

		frame.setGameState(2);
		panel.remove(frame.getPanel().getButtonCompleteLineup());
		panel.remove(frame.getPanel().getButtonCallinLineup());
		panel.remove(frame.getPanel().getButtonSaveLineup());

		panel.repaint();
		
		try {
			// 深拷贝
			Map<String, ChessmanFigure> mapDeep = (Map<String, ChessmanFigure>) Utils.clone(panel.figureMap);
			Map<String, ChessmanFigure> mapDeep2 = (Map<String, ChessmanFigure>) Utils.clone(panel.figureMap);
			Map<String, ChessmanFigure> mapDeep3 = (Map<String, ChessmanFigure>) Utils.clone(panel.figureMap);
			LineupBean lineupBean = new LineupBean();
			LineupBean lineupBean2 = new LineupBean();
			LineupBean lineupBean3 = new LineupBean();

			if (Const.SERVER.equals(MainFrame.me.direction)) {
				for (String key : mapDeep.keySet()) {
					mapDeep.get(key).getChessman()
							.setCoordinate(Coordinate.symmetry(mapDeep.get(key).getChessman().getCoordinate()));
				}

				lineupBean.setMap(mapDeep);
				lineupBean.setSide(Const.SERVER);

				if (frame.oos1 != null) {
					frame.oos1.writeObject(lineupBean);
					frame.oos1.flush();
				}
				//	-----------
				for (String key : mapDeep2.keySet()) {
					mapDeep2.get(key).getChessman()
							.setCoordinate(Coordinate.steer90(mapDeep2.get(key).getChessman().getCoordinate()));
				}

				lineupBean2.setMap(mapDeep2);
				lineupBean2.setSide(Const.SERVER);

				if (frame.oos2 != null) {
					frame.oos2.writeObject(lineupBean2);
					frame.oos2.flush();
				}
				// --------------
				for (String key : mapDeep3.keySet()) {
					mapDeep3.get(key).getChessman()
							.setCoordinate(Coordinate.steerMinus90(mapDeep3.get(key).getChessman().getCoordinate()));
				}

				lineupBean3.setMap(mapDeep3);
				lineupBean3.setSide(Const.SERVER);

				if (frame.oos3 != null) {
					frame.oos3.writeObject(lineupBean3);
					frame.oos3.flush();
				}
				// -----------
				MainFrame.serverQueue2.put(1);

			} else if (Const.CLIENT_A.equals(MainFrame.me.direction)) {
				for (String key : mapDeep.keySet()) {
					mapDeep.get(key).getChessman()
							.setCoordinate(Coordinate.symmetry(mapDeep.get(key).getChessman().getCoordinate()));
				}
				lineupBean.setMap(mapDeep);
				lineupBean.setSide(Const.CLIENT_A);
				Utils.writeObject(Const.CLIENT_A, lineupBean);
			} else if (Const.CLIENT_B.equals(MainFrame.me.direction)) {
				for (String key : mapDeep.keySet()) {
					mapDeep.get(key).getChessman()
							.setCoordinate(Coordinate.steerMinus90(mapDeep.get(key).getChessman().getCoordinate()));
				}
				lineupBean.setMap(mapDeep);
				lineupBean.setSide(Const.CLIENT_B);
				Utils.writeObject(Const.CLIENT_B, lineupBean);
			} else if (Const.CLIENT_C.equals(MainFrame.me.direction)) {
				for (String key : mapDeep.keySet()) {
					mapDeep.get(key).getChessman()
							.setCoordinate(Coordinate.steer90(mapDeep.get(key).getChessman().getCoordinate()));
				}
				lineupBean.setMap(mapDeep);
				lineupBean.setSide(Const.CLIENT_C);
				Utils.writeObject(Const.CLIENT_C, lineupBean);
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		frame.finishBujuSelf = true;

		//		if (true) {
		//			frame.mp.setMessage("开始游戏");
		//		} else {
		//			frame.mp.setMessage("等待对方布局");
		//		}

	}
}
