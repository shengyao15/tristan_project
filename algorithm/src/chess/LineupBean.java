package chess;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import chess.Player.Located;


public class LineupBean implements Serializable{
	Map<String, ChessmanFigure> map = new HashMap<String, ChessmanFigure>();
	String side = "";
	
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public Map<String, ChessmanFigure> getMap() {
		return map;
	}
	public void setMap(Map<String, ChessmanFigure> map) {
		this.map = map;
	}
	
	
}
