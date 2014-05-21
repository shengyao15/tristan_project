/**
 * Author by metaphy
 * Date Dec 27, 2007
 * All Rights Reserved.
 */
package chess;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class ChessmanFigure extends JButton implements Serializable{
	private static final long serialVersionUID = 1L;
	private Chessman chessman;
	private static int id = 0;
	private String key;
	private String position;
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ChessmanFigure() {
		super();
	}

	public ChessmanFigure(Player player,Chessman chessman, boolean flag) {
		super();
		id++;
		this.chessman = chessman;
		setAutoscrolls(false);
		
		if(flag == false){
			chessman.setDisplay(false);
			setText("");
		}

		switch (player.getLocated()) {
		case SOUTH:
			setBackground(Color.orange);
			break;
		case NORTH:
			setBackground(Color.green);
			break;
		case WEST:
			setBackground(Color.blue);
			break;
		case EAST:
			setBackground(Color.cyan);
			break;
		}
		setOpaque(true);
		Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED,
				Color.white,Color.white,Color.gray,	Color.gray);
		setBorder (border);
		Rectangle rec = new Rectangle(chessman.getCoordinate().getPointX()
				- Const.CHESSMAN_SIDE_WIDTH / 2, chessman.getCoordinate().getPointY()
				- Const.CHESSMAN_SIDE_WIDTH / 2, Const.CHESSMAN_SIDE_WIDTH,
				Const.CHESSMAN_SIDE_WIDTH);
		setBounds(rec);
		setFocusable(false);
		addActionListener(new ChessmanFigureAction(this));
	}
	
	public ChessmanFigure(Player player,Chessman chessman, String direction) {
		this(player, chessman);
		position = direction;
		key = id + direction;
	}
	
	public ChessmanFigure(Player player,Chessman chessman) {
		super();
		id++;
		this.chessman = chessman;
		setAutoscrolls(false);
		if (chessman.isDisplay()){
			setText(chessman.getCaption());
		}else{
			setText("");
		}
		switch (player.getLocated()) {
		case SOUTH:
			setBackground(Color.orange);
			break;
		case NORTH:
			setBackground(Color.green);
			break;
		case WEST:
			setBackground(Color.blue);
			break;
		case EAST:
			setBackground(Color.cyan);
			break;
		}
		setOpaque(true);
		Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED,
				Color.white,Color.white,Color.gray,	Color.gray);
		setBorder (border);
		Rectangle rec = new Rectangle(chessman.getCoordinate().getPointX()
				- Const.CHESSMAN_SIDE_WIDTH / 2, chessman.getCoordinate().getPointY()
				- Const.CHESSMAN_SIDE_WIDTH / 2, Const.CHESSMAN_SIDE_WIDTH,
				Const.CHESSMAN_SIDE_WIDTH);
		setBounds(rec);
		setFocusable(false);
		addActionListener(new ChessmanFigureAction(this));
	}

	public void reLocation() {
		Point loc = new Point(chessman.getCoordinate().getPointX()
				- Const.CHESSMAN_SIDE_WIDTH / 2, chessman.getCoordinate().getPointY()
				- Const.CHESSMAN_SIDE_WIDTH / 2);
		setLocation(loc);
	}

	public void reSet(){
		if (chessman.isDisplay()){
			setText(chessman.getCaption());
		}else{
			setText("");
		}
		reLocation();
	}
	
	public Chessman getChessman() {
		return chessman;
	}

	public void setChessman(Chessman chessman) {
		this.chessman = chessman;
	}

}
