package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import chess.Chessman.Soldier;
import chess.OperationButton.TheOperation;
import chess.Player.Located;
import chess.Road.RoadType;




public class MainPanel extends JPanel{
	public static MainPanel mp;
	private static final long serialVersionUID = 7;
	public static  TransBeanB transBeanB;
	public static  LostPlayer lostPlayer;
	
	public static Map<String, ChessmanFigure> figureMap = new HashMap<String, ChessmanFigure>();
	public static Map<String, ChessmanFigure> figureFinalMap = new HashMap<String, ChessmanFigure>();
	public static Map<String, ChessmanFigure> tmpMap = new HashMap<String, ChessmanFigure>();
	
	public static List<StationFigure> stationFigureList = new ArrayList<StationFigure>();
	
	public static  MainFrame frame = MainFrame.me;
	
	public static boolean initFlag;
	public static boolean first;
	public static int second ;
	public static boolean three ;
	public static boolean four;
	public static boolean five;
	public static boolean opponentFlag;
	
	private JButton buttonCompleteLineup = new OperationButton(TheOperation.COMPLETE_LINEUP);
	private JButton buttonCallinLineup = new OperationButton(TheOperation.CALLIN_LINEUP);
	private JButton buttonSaveLineup = new OperationButton(TheOperation.SAVE_LINEUP);
	
	public Player p;
	public Player p2;
	public Player p3;
	public Player p4;
	
	static{
		initFlag = false;
		first = true;
		second = 0;
		three = false;
		four = false;
		five = false;
		opponentFlag = false;
	}
	
	public MainPanel(){
		super();
		setLayout(new BorderLayout());
		//setLayout(null);
		mp =this;
	}
	
	@Override
	synchronized
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(!initFlag){
			return;
		}
		
		if(first){
			
			drawButton();
			drawChessman();
			drawStation();
			
		}
		first = false;
		
		if(frame.lineupBean != null && opponentFlag){
			removeStation();
			
			drawOpponent();
			drawStation();
			opponentFlag = false;
			
		}
		
		if(four == true && transBeanB != null){
			
			ChessFight fight = ChessFight.getInstance();
			fight.setFirst(transBeanB.getFirst());
			fight.setSecond(transBeanB.getSecond());
			
			ReceiveThread t2 = new ReceiveThread();
			t2.start();
			
			four = false;
		}


		if(five == true && lostPlayer != null){
			
			String side = lostPlayer.getSide();
			List<String> delList = new ArrayList<String>();
			
			for(String key : figureFinalMap.keySet()){
				if(key.contains(side)){
					delList.add(key);
					remove(figureFinalMap.get(key));
				}
			}
			
			for(String key : delList){
				figureFinalMap.remove(key);
			}
			
			
			five = false;
		}
		
		//drawRoads(g);
		
		add(RoadsComponent.getInstance());
	}

	void removeStation() {
		for(StationFigure sf : stationFigureList){
			remove(sf);
		}
	}

	private void drawOpponent() {
		for(String key :frame.lineupBean.getMap().keySet()){
			ChessmanFigure figure = frame.lineupBean.getMap().get(key);

			if(Const.SERVER.equals(frame.lineupBean.getSide())){
				ChessmanFigure f = new ChessmanFigure(new Player(Located.SOUTH), figure.getChessman(),false);
				f.setKey(figure.getKey());
				f.setPosition(figure.getPosition());
				add(f);
				figureFinalMap.put(f.getKey(), f);
			}else if(Const.CLIENT_A.equals(frame.lineupBean.getSide())){
				ChessmanFigure f = new ChessmanFigure(new Player(Located.NORTH), figure.getChessman(),false);
				f.setKey(figure.getKey());
				f.setPosition(figure.getPosition());
				add(f);
				figureFinalMap.put(f.getKey(), f);
			}else if(Const.CLIENT_B.equals(frame.lineupBean.getSide())){
				ChessmanFigure f = new ChessmanFigure(new Player(Located.WEST), figure.getChessman(),false);
				f.setKey(figure.getKey());
				f.setPosition(figure.getPosition());
				add(f);
				figureFinalMap.put(f.getKey(), f);
			}else if(Const.CLIENT_C.equals(frame.lineupBean.getSide())){
				ChessmanFigure f = new ChessmanFigure(new Player(Located.EAST), figure.getChessman(),false);
				f.setKey(figure.getKey());
				f.setPosition(figure.getPosition());
				add(f);
				figureFinalMap.put(f.getKey(), f);
			}
			
		}
	}

	private void drawButton() {
		add(buttonCompleteLineup);
		buttonCompleteLineup.setVisible(false);
		add(buttonCallinLineup);
		buttonCallinLineup.setVisible(true);
		add(buttonSaveLineup);
		buttonSaveLineup.setVisible(true);
	}

	void drawChessman() {
		try {
			if(Const.SERVER.equals(frame.direction)){
				List<Chessman> list = InitPlayers.initOnePlayer(Located.SOUTH, null).getChessmans();
				p = new Player(Located.SOUTH);
				for(Chessman c : list){
					ChessmanFigure f = new ChessmanFigure(p,c,frame.direction);
					add(f);
					figureMap.put(f.getKey(), f);
					figureFinalMap.put(f.getKey(), f);
				}
			}else if(Const.CLIENT_A.equals(frame.direction)){
				List<Chessman> list2 = InitPlayers.initOnePlayer(Located.SOUTH, null).getChessmans();
				p2 = new Player(Located.NORTH);
				for(Chessman c : list2){
					ChessmanFigure f = new ChessmanFigure(p2,c, frame.direction);
					add(f);
					figureMap.put(f.getKey(), f);
					figureFinalMap.put(f.getKey(), f);
				}
			}else if(Const.CLIENT_B.equals(frame.direction)){
				List<Chessman> list3 = InitPlayers.initOnePlayer(Located.SOUTH, null).getChessmans();
				p3 = new Player(Located.WEST);
				for(Chessman c : list3){
					ChessmanFigure f = new ChessmanFigure(p3,c, frame.direction);
					add(f);
					figureMap.put(f.getKey(), f);
					figureFinalMap.put(f.getKey(), f);
				}
			}else if(Const.CLIENT_C.equals(frame.direction)){
				List<Chessman> list4 = InitPlayers.initOnePlayer(Located.SOUTH, null).getChessmans();
				p4 = new Player(Located.EAST);
				for(Chessman c : list4){
					ChessmanFigure f = new ChessmanFigure(p4,c, frame.direction);
					add(f);
					figureMap.put(f.getKey(), f);
					figureFinalMap.put(f.getKey(), f);
				}
			}
			
		} catch(LineupException le){
			MainFrame.me.mp.setMessage(Const.msg14);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*private void testData1() {
		Chessman chessman = new Chessman();
		chessman.setSoldier(Soldier.SILING);
		chessman.setCoordinate(new Coordinate(9, 12));
		chessman.setDisplay(true);
		add(new ChessmanFigure(chessman));
		
		Chessman chessman2 = new Chessman();
		chessman2.setSoldier(Soldier.ZHADAN);
		chessman2.setCoordinate(new Coordinate(11, 15));
		chessman2.setDisplay(true);
		add(new ChessmanFigure(chessman2));
		
		Chessman chessman3 = new Chessman();
		chessman3.setSoldier(Soldier.SHIZHANG);
		chessman3.setCoordinate(new Coordinate(7, 12));
		chessman3.setDisplay(true);
		add(new ChessmanFigure(chessman3));
		
	}*/

	public void drawStation() {
		ArrayList<StationPoint> stationPointList = InitStationPoints.init();
		for(StationPoint point : stationPointList){
			StationFigure sf = new StationFigure(point);
			add(sf);
			stationFigureList.add(sf);
		}
	}

	public void drawRoads(Graphics g) {
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
	public JButton getButtonCompleteLineup() {
		return buttonCompleteLineup;
	}

	public JButton getButtonCallinLineup() {
		return buttonCallinLineup;
	}

	public JButton getButtonSaveLineup() {
		return buttonSaveLineup;
	}
	
	
}
