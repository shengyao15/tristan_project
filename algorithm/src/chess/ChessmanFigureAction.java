package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chess.Chessman.Soldier;



public class ChessmanFigureAction implements ActionListener {
	private ChessmanFigure chessmanFigure = null;
	private MainFrame frame = MainFrame.me;

	public ChessmanFigureAction(ChessmanFigure chessmanFigure) {
		super();
		this.chessmanFigure = chessmanFigure;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (frame.getGameState() == 2) {

			if (!frame.currTurn.equals(frame.direction)) {
				return;
			}
			
			ChessFight fight = ChessFight.getInstance();

			if (fight.getFirst() == null && chessmanFigure.getChessman().isDisplay() == false) {
				return;
			}

			fight.addChess(chessmanFigure);

			if (fight.canFight()) {
				fight(fight);
			}
			
		} else {
			ChessLineup lineup = ChessLineup.getInstance();
			lineup.addChess(chessmanFigure);

			if (lineup.canExchange()) {
				exchange(lineup);
			}
		}

	}

	private void fight(ChessFight fight) {
		
		SendThread t = new SendThread();
		t.start();

	}

	private void exchange(ChessLineup lineup) {
		ChessmanFigure firstFigure = lineup.getFirst();
		Chessman first = firstFigure.getChessman();
		ChessmanFigure secondFigure = (ChessmanFigure) lineup.getSecond();
		Chessman second = secondFigure.getChessman();

		if(!checkRules(first, second, lineup)){
			return;
		}
		
		Coordinate tmp = first.getCoordinate();
		first.setCoordinate(second.getCoordinate());
		second.setCoordinate(tmp);
		
		firstFigure.reSet();
		secondFigure.reSet();
		lineup.clearFirstSecond();

	}

	private boolean checkRules(Chessman first, Chessman second, ChessLineup lineup) {
		if(first.getSoldier() == Soldier.ZHADAN){
			if(second.getCoordinate().getY() == 12 || 
					second.getCoordinate().getY() == 6){
				frame.mp.setMessage(Const.msg2);
				lineup.clearFirstSecond();
				return false;
			}
		}else if(second.getSoldier() == Soldier.ZHADAN){
			if(first.getCoordinate().getY() == 12 || 
					first.getCoordinate().getY() == 6){
				frame.mp.setMessage(Const.msg2);
				lineup.clearFirstSecond();
				return false;
			}
		}
		
		if(first.getSoldier() == Soldier.DILEI){
			if(second.getCoordinate().getY() != 16 &&
					second.getCoordinate().getY() != 17 && 
					second.getCoordinate().getY() != 1 && 
					second.getCoordinate().getY() != 2 ){
				frame.mp.setMessage(Const.msg3);
				lineup.clearFirstSecond();
				return false;
			}
		}else if(second.getSoldier() == Soldier.DILEI){
			if(first.getCoordinate().getY() != 16 &&
					first.getCoordinate().getY() != 17 &&
					first.getCoordinate().getY() != 1 &&
					first.getCoordinate().getY() != 2 ){
				frame.mp.setMessage(Const.msg3);
				lineup.clearFirstSecond();
				return false;
			}
		}
		
		if(first.getSoldier() == Soldier.JUNQI){
			if(!second.getCoordinate().inHeaderquaracter()){
				frame.mp.setMessage(Const.msg4);
				lineup.clearFirstSecond();
				return false;
			}
		}
		
		if(second.getSoldier() == Soldier.JUNQI){
			if(!first.getCoordinate().inHeaderquaracter()){
				frame.mp.setMessage(Const.msg4);
				lineup.clearFirstSecond();
				return false;
			}
		}
		
		return true;
	}


}
