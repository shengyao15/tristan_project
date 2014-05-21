package chess;

import java.awt.Color;

public class ChessFight {
	private ChessmanFigure first;
	private Object second;
	private static ChessFight instance = null;
	private MainFrame main = MainFrame.me;
	
	private ChessFight(){}
	
	public static ChessFight getInstance(){
		if(instance == null){
			instance = new ChessFight();
		}
		return instance;
	}
	
	public void setFirst(ChessmanFigure first) {
		this.first = first;
	}

	public void setSecond(Object second) {
		this.second = second;
	}

	public void addChess(Object o) {

			if (first == null && o instanceof ChessmanFigure) {
				first = (ChessmanFigure)o;
				second = null;
//				if (!(main.getCurrentPlayer() == 0 && first.getBackground().equals(Color.orange ) ||
//							main.getCurrentPlayer() == 1 && first.getBackground().equals(Color.green ))){
//					first = null;
//				}
			}else if (first !=null && second == null){
				if (o instanceof StationFigure){
					second = o;
				}else if (o instanceof ChessmanFigure){
					ChessmanFigure chmFigure = (ChessmanFigure) o;
					if (first.getBackground().equals(chmFigure.getBackground())){
						first = chmFigure;
					}else{
						second = chmFigure;
					}
				}else{
				}
			}
	}

	public ChessmanFigure getFirst() {
		return first;
	}

	public Object getSecond() {
		return second;
	}

	public boolean needTwinkleLineup(){
		return first != null && second ==null;
	}

	public void clearFirstSecond() {
		first = null;
		second =null;
		
	}

	public boolean canMove (){
		return first !=null && second !=null && second instanceof StationFigure;
	}

	public boolean canFight (){
		return first !=null && second !=null && second instanceof ChessmanFigure && isEnemy();
	}
	
	public boolean isEnemy(){
		String p1 = first.getPosition();
		String p2 = "";
		
		if(second instanceof ChessmanFigure){
			p2 = ((ChessmanFigure)second).getPosition();
		}else{
			return false;
		}
		
		if(Const.SERVER.equals(p1) && !Const.CLIENT_A.equals(p2)){
			return true;
		}else if(Const.CLIENT_A.equals(p1) && !Const.SERVER.equals(p2)){
			return true;
		}else if(Const.CLIENT_B.equals(p1) && !Const.CLIENT_C.equals(p2)){
			return true;
		}else if(Const.CLIENT_C.equals(p1) && !Const.CLIENT_B.equals(p2)){
			return true;
		}
		return false;
	}
	
}
