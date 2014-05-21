/**
 * Author by metaphy
 * Date Dec 24, 2007
 * All Rights Reserved.
 */
package chess;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
	public enum Located {
		SOUTH,NORTH,WEST,EAST
	}
	private Located located ;
	private int team ;
	private ArrayList<Chessman> chessmans = new ArrayList<Chessman>();
	
	public Player(){}
	public Player(Located loc) {
		super();
		this.located = loc;
	}
	
	public Player(Located loc, int team) {
		super();
		this.located = loc;
		this.team = team;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public Located getLocated() {
		return located;
	}
	public void setLocated(Located located) {
		this.located = located;
	}
	public ArrayList<Chessman> getChessmans() {
		return chessmans;
	}
	public void setChessmans(ArrayList<Chessman> chessmans) {
		this.chessmans = chessmans;
	}
	public void addChessman(Chessman chm){
		this.chessmans.add(chm);
	}
	public void removeChessman(Chessman chm){
		this.chessmans.remove(chm);
	}
	
	public boolean containChessman(Chessman chm){
		for (Chessman chessman : chessmans){
			if (chessman.equals(chm))
				return true;
		}
		return false;
	}
	
	public boolean equals(Player p){
		return p.getLocated() == located;
	}
}

