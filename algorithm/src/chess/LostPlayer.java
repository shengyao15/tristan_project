package chess;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import chess.Player.Located;


public class LostPlayer implements Serializable{
	String side = "";
	String msg;
	String currTurn;
	
	
	
	public String getCurrTurn() {
		return currTurn;
	}
	public void setCurrTurn(String currTurn) {
		this.currTurn = currTurn;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	
}
