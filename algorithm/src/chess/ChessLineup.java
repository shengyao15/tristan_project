package chess;


public class ChessLineup {
	private ChessmanFigure first;
	private Object second;
	private static ChessLineup instance = null;
	private ChessLineup(){}
	
	public static ChessLineup getInstance(){
		if(instance == null){
			instance = new ChessLineup();
		}
		return instance;
	}
	
	public void addChess(Object o) {

		if (first == null && o instanceof ChessmanFigure){
			first = (ChessmanFigure)o;
			second = null;
		}else if (first !=null && second == null){
			if (o instanceof StationFigure){
				// Do nothing...
			}else if (o instanceof ChessmanFigure){
				ChessmanFigure chmFigure = (ChessmanFigure) o;
				if (first.getBackground().equals(chmFigure.getBackground())){
					second = chmFigure;
				}else{
					first = null;
					second = null;
				}
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
	 
	public boolean canExchange (){
		return first !=null && second !=null;
	}

	public void clearFirstSecond() {
		first = null;
		second =null;
		
	}

}
