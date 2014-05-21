package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chess.Chessman.Soldier;



public class StationFigureAction implements MouseListener {
	private StationFigure stationFigure = null;
	private MainFrame frame = MainFrame.me;

	public StationFigureAction(StationFigure stationFigure) {
		super();
		this.stationFigure = stationFigure;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (frame.getGameState() == 2) {
			ChessFight fight = ChessFight.getInstance();
			fight.addChess(stationFigure);

			if (fight.canMove()) {
				move(fight);
			}

		}
	}

	private void move(ChessFight fight) {
		
		SendThread t = new SendThread();
		t.start();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
