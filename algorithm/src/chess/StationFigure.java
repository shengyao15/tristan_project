package chess;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import chess.StationPoint.StationPointType;




public class StationFigure extends JLabel {
	private StationPoint sp;
	
	public StationFigure() {
		// TODO Auto-generated constructor stub
	}
	
	public StationFigure(StationPoint sp) {
		this.sp = sp;
		setOpaque(true); 
	//	setAutoscrolls(false);
		setBorder(new LineBorder(Color.BLACK));
		
		if(StationPointType.CAMP == sp.getType()){
			setBackground(Color.LIGHT_GRAY);
		} else if(StationPointType.HEADQUARTERS == sp.getType()){
			setBackground(Color.LIGHT_GRAY);
		} else if(StationPointType.STATION == sp.getType()){
			setBackground(Color.white);
		}
		
		Rectangle rec = new Rectangle(
				sp.getCoordinate().getPointX() - Const.SP_SIDE_WIDTH /2 ,
				sp.getCoordinate().getPointY() - Const.SP_SIDE_WIDTH /2 ,
				Const.SP_SIDE_WIDTH,Const.SP_SIDE_WIDTH);
		setBounds(rec);
		
		addMouseListener(new StationFigureAction(this));
	}

	public StationPoint getSp() {
		return sp;
	}

	public void setSp(StationPoint sp) {
		this.sp = sp;
	}
	
	
}
