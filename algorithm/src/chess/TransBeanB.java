package chess;

import java.io.Serializable;

public class TransBeanB implements Serializable{
	private ChessmanFigure first;
	private Object second;
	private String side;
	private boolean live = true;
	private String opponent = "";
	
	public String getOpponent() {
		return opponent;
	}
	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public ChessmanFigure getFirst() {
		return first;
	}
	public void setFirst(ChessmanFigure first) {
		this.first = first;
	}
	public Object getSecond() {
		return second;
	}
	public void setSecond(Object second) {
		this.second = second;
	}
	
}
