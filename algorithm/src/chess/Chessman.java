/**
 * Author by metaphy
 * Date Dec 24, 2007
 * All Rights Reserved.
 */
package chess;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;


public class Chessman implements Serializable{
	public enum Soldier {
		SILING, JUNZHANG, SHIZHANG, LVZHANG, TUANZHANG, YINGZHANG, LIANZHANG, PAIZHANG, GONGBING, ZHADAN, DILEI, JUNQI
	}

	private int id;
	private Soldier soldier;
	private Coordinate coordinate;
	private boolean display; // Show the caption or not
	private boolean visible;
//	private Player player;
//	private ChessmanFigure figure;
	
	public Chessman() {
	}

	public Chessman(Soldier soldier) {
		super();
		this.soldier = soldier;
	}

	public Chessman(Soldier soldier, Coordinate coordinate) {
		super();
		this.soldier = soldier;
		this.coordinate = coordinate;
	}

	public Chessman(Soldier soldier, int coordinate) {
		super();
		this.soldier = soldier;
		this.coordinate = new Coordinate(coordinate);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Soldier getSoldier() {
		return soldier;
	}

	public void setSoldier(Soldier soldier) {
		this.soldier = soldier;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}

	public boolean isVisible() {
		return visible;
	}

//	public ChessmanFigure getFigure() {
//		return figure;
//	}
//
//	public void setFigure(ChessmanFigure figure) {
//		this.figure = figure;
//	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getCaption(){
		if (this.soldier == null) return null;
		return getCaptionStatic(this.soldier);
	}
//	public Player getPlayer() {
//		return player;
//	}
//	public void setPlayer(Player player) {
//		this.player = player;
//	}
//	
	/**
	 * Fight against the enemy
	 * fail: 0 ; equal: 1; beat: 2; Win: 9
	 * @param enemy
	 * @return
	 */
	public int attack(Chessman enemy){
		int attk = -1;
		if (enemy.getSoldier() == Soldier.JUNQI){
			attk = 9;
		} else if (enemy.getSoldier() == Soldier.DILEI){
			if (this.getSoldier() == Soldier.GONGBING){
				attk = 2;
			}else if (this.getSoldier() == Soldier.ZHADAN){
				attk = 1;
			}else{
				attk = 0;
			}
		} else if (enemy.getSoldier() == Soldier.ZHADAN || this.getSoldier() == Soldier.ZHADAN){
			attk = 1;
		} else {
			if (this.getValue() > enemy.getValue())
				attk = 2;
			else if(this.getValue() == enemy.getValue())
				attk = 1;
			else
				attk = 0;
		}
		return attk ;
	}
	/*
	 * The value 
	 */
	public int getValue() {
		int value = -1;
		switch (soldier) {
		case SILING:
			value = 40;
			break;
		case JUNZHANG:
			value = 39;
			break;
		case SHIZHANG:
			value = 38;
			break;
		case LVZHANG:
			value = 37;
			break;
		case TUANZHANG:
			value = 36;
			break;
		case YINGZHANG:
			value = 35;
			break;
		case LIANZHANG:
			value = 34;
			break;
		case PAIZHANG:
			value = 33;
			break;
		case GONGBING:
			value = 32;
			break;
		case ZHADAN:
			value = 31;
			break;
		case DILEI:
			value = 30;
			break;
		case JUNQI:
			value = 29;
			break;
		default:
		}
		return value ;
	}
	/*
	 *value tle
	 */
	public static String getCaptionStatic(Soldier soldier) {
		String caption = null;
		switch (soldier) {
		case SILING:
			caption = "司令";
			break;
		case JUNZHANG:
			caption = "军长";
			break;
		case SHIZHANG:
			caption = "师长";
			break;
		case LVZHANG:
			caption = "旅长";
			break;
		case TUANZHANG:
			caption = "团长";
			break;
		case YINGZHANG:
			caption = "营长";
			break;
		case LIANZHANG:
			caption = "连长";
			break;
		case PAIZHANG:
			caption = "排长";
			break;
		case GONGBING:
			caption = "工兵";
			break;
		case ZHADAN:
			caption = "炸弹";
			break;
		case DILEI:
			caption = "地雷";
			break;
		case JUNQI:
			caption = "军旗";
			break;
		default:
			caption = "Error";
		}
		return caption;
	}

	public static Soldier getSoldier(String caption) {
		Soldier soldier = null;
		if (caption.equals("司令")) {
			soldier = Soldier.SILING;
		} else if (caption.equals("军长")) {
			soldier = Soldier.JUNZHANG;
		} else if (caption.equals("师长")) {
			soldier = Soldier.SHIZHANG;
		} else if (caption.equals("旅长")) {
			soldier = Soldier.LVZHANG;
		} else if (caption.equals("团长")) {
			soldier = Soldier.TUANZHANG;
		} else if (caption.equals("营长")) {
			soldier = Soldier.YINGZHANG;
		} else if (caption.equals("连长")) {
			soldier = Soldier.LIANZHANG;
		} else if (caption.equals("排长")) {
			soldier = Soldier.PAIZHANG;
		} else if (caption.equals("工兵")) {
			soldier = Soldier.GONGBING;
		} else if (caption.equals("炸弹")) {
			soldier = Soldier.ZHADAN;
		} else if (caption.equals("地雷")) {
			soldier = Soldier.DILEI;
		} else if (caption.equals("军旗")) {
			soldier = Soldier.JUNQI;
		}
		return soldier;
	}
	
	public String toString(){
		return "Soldier:"+ soldier + "; Coordinate:" + coordinate.getCoordinateValue(); 
	}
}
