package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chess.Road.RoadType;



public class RoadsComponent extends JComponent  {
	public static RoadsComponent instance = new RoadsComponent();

	public static RoadsComponent getInstance() {
		return instance;
	}
	RoadsComponent(){}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		ArrayList<Road> roads = InitRoads.init();
		for(Road road: roads){
			Coordinate start = road.getStart();
			Coordinate end = road.getEnd();
			g2.setPaint(Color.black);
			if (RoadType.ROAD == road.getType()){
				Line2D line = new Line2D.Double(start.getPoint(), end.getPoint());
				g2.draw(line);
			} else if (RoadType.HORIZONTAL_RAILWAY == road.getType()){
				Line2D line = new Line2D.Double(start.getPointX(),start.getPointY() - 2,
						end.getPointX() , end.getPointY() - 2);
				g2.draw(line);
				line = new Line2D.Double(start.getPointX(),start.getPointY() + 2,
						end.getPointX(), end.getPointY() + 2 );
				g2.draw(line);
			} else if (RoadType.VERTICAL_RAILWAY == road.getType()){
				Line2D line = new Line2D.Double(start.getPointX() -2 ,start.getPointY(),
						end.getPointX() -2 , end.getPointY());
				g2.draw(line);
				line = new Line2D.Double(start.getPointX() + 2,start.getPointY(),
						end.getPointX() + 2, end.getPointY());
				g2.draw(line);
			} else if (RoadType.CROOKED_RAILWAY == road.getType()){
				Line2D line = new Line2D.Double(start.getPointX() - 2,start.getPointY(),
						end.getPointX() - 2, end.getPointY());
				g2.draw(line);
				line = new Line2D.Double(start.getPointX() + 2,start.getPointY(),
						end.getPointX() + 2, end.getPointY());
				g2.draw(line);
			}
			if (RoadType.HORIZONTAL_RAILWAY == road.getType() || 
					RoadType.VERTICAL_RAILWAY == road.getType() || 
					RoadType.CROOKED_RAILWAY == road.getType()){
				g2.setPaint(Color.green);
				Line2D line = new Line2D.Double(start.getPointX(),start.getPointY(),
						end.getPointX(), end.getPointY());
				g2.draw(line);
			}
		}
		g2.setPaint(Color.black);
		
	}

	
}

