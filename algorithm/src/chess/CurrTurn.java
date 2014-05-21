package chess;

import java.io.Serializable;

public class CurrTurn implements Serializable{
	private String currTurn = "";

	public CurrTurn(String currTurn) {
		this.currTurn = currTurn;
	}
	
	public String getCurrTurn() {
		return currTurn;
	}

	public void setCurrTurn(String currTurn) {
		this.currTurn = currTurn;
	}
	
}
