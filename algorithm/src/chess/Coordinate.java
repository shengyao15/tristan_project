package chess;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class Coordinate implements Serializable{
	private int coordinateValue ;
	private int x ;
	private int y ;
	private Coordinate parent;
	
	public Coordinate(int coordinate) {
		this.x = coordinate % 100;
		this.y = coordinate / 100;
		this.coordinateValue = coordinate;
	}
	public Coordinate(int x ,int y) {
		this.x = x;
		this.y = y;
		this.coordinateValue = 100 * y + x;
	}
	public int getCoordinateValue() {
		return coordinateValue;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Coordinate getParent() {
		return parent;
	}
	public Integer getInteger(){
		return new Integer(coordinateValue);
	}
	public void setParent(Coordinate parent) {
		this.parent = parent;
	}
	public boolean equals(Coordinate coor){
		if (coor.getCoordinateValue() == coordinateValue){
			return true;
		}
		return false; 
	}
	/**
	 *  Judge the coordinate is valid or not
	 */
	public boolean isValidCoordinate(int c){
		return c >= 107 && c <= 111 ||
			c >= 207 && c <= 211 ||
			c >= 307 && c <= 311 ||
			c >= 407 && c <= 411 ||
			c >= 507 && c <= 511 ||
			c >= 607 && c <= 611 ||
			c >= 701 && c <= 707 ||
			c >= 801 && c <= 806 ||
			c >= 901 && c <= 907 ||
			c >= 1001 && c <= 1006 ||
			c >= 1101 && c <= 1107 ||
			c >= 711 && c <= 717 ||
			c >= 812 && c <= 817 ||
			c >= 911 && c <= 917 ||
			c >= 1012 && c <= 1017 ||
			c >= 1111 && c <= 1117 ||
			c >= 1207 && c <= 1211 ||
			c >= 1307 && c <= 1311 ||
			c >= 1407 && c <= 1411 ||
			c >= 1507 && c <= 1511 ||
			c >= 1607 && c <= 1611 ||
			c >= 1707 && c <= 1711 ||
			c == 709 || c == 909 ||
			c == 1109;
	}
	/**
	 * the coordinate is on road
	 */
	public boolean onRoad(){
		return y==1 || y==17 || x==1 ||x==17
			|| coordinateValue == 309 || coordinateValue==408 || coordinateValue==410 || coordinateValue == 509
			|| coordinateValue == 804 || coordinateValue == 903 || coordinateValue == 905 || coordinateValue ==1004
			|| coordinateValue == 814 || coordinateValue == 913 || coordinateValue == 915 || coordinateValue ==1014
			|| coordinateValue == 1309 || coordinateValue==1408 || coordinateValue==1410 || coordinateValue == 1509
			|| inCamp();
	}
	/**
	 * the coordinate is on railway
	 */
	public boolean onRailWay(){
		return ! onRoad(); 
	}
	/**
	 * is in camp
	 */
	public boolean inCamp(){
		return 
			coordinateValue==308||
			coordinateValue==310||
			coordinateValue==409||
			coordinateValue==508||
			coordinateValue==510||
			coordinateValue==803||
			coordinateValue==805||
			coordinateValue==904||
			coordinateValue==1003||
			coordinateValue==1005||
			coordinateValue==813||
			coordinateValue==815||
			coordinateValue==914||
			coordinateValue==1013||
			coordinateValue==1015||
			coordinateValue==1308||
			coordinateValue==1310||
			coordinateValue==1409||
			coordinateValue==1508||
			coordinateValue==1510 ;
	}
	public boolean inHeaderquaracter(){
		return coordinateValue==108||coordinateValue== 110||coordinateValue== 801||coordinateValue== 1001
			||coordinateValue== 817||coordinateValue== 1017||coordinateValue== 1708||coordinateValue== 1710;
	}
	/**
	 * The coordinate is straight adjacent for current OR not
	 * @param c
	 * @return
	 */
	public boolean isStraightAdjacent(Coordinate c){
		return x == c.getX() && Math.abs(y - c.getY()) == 1 
			|| y == c.getY() && Math.abs(x - c.getX()) == 1;
	}
	
	public boolean isSlantAdjacent(Coordinate c){
		return Math.abs(x - c.getX()) ==1 && Math.abs(y - c.getY()) == 1;
	}
	/**
	 * Judge on same line or not 
	 * @param c
	 * @return
	 */
	public boolean onSameLineHorizon(Coordinate c){
		return y == c.getY()
			&& (y ==2 || y==6 || y==7 && x!=1 && x!=17|| y==9 && x>=6 && x<=12
			|| y==11 && x!=1 && x!=17 || y ==12 || y == 16) 
			&& (c.getY() ==2 || c.getY()==6 || c.getY()==7 && c.getX()!=1 && c.getX()!=17|| c.getY()==9 && c.getX()>=6 && c.getX()<=12
			|| c.getY()==11 && c.getX()!=1 && c.getX()!=17 || c.getY() ==12 || c.getY() == 16) ;
	}
	public boolean onSameLineVertical(Coordinate c){
		 return x == c.getX() 
		 	&& (c.getX() ==2 || c.getX()==6 || c.getX()==7 && c.getY()!=1 && c.getY()!=17|| c.getX()==9 && c.getY()>=6 && c.getY()<=12
			|| c.getX()==11 && c.getY()!=1 && c.getY()!=17 || c.getX() ==12 || c.getX() == 16) 
			&& (x ==2 || x==6 || x==7 && y!=1 && y!=17|| x==9 && y>=6 && y<=12
			|| x==11 && y!=1 && y!=17 || x ==12 || x == 16) ;
	}
	/**
	 * On the same railway but need turn around 
	 * @param target
	 * @return
	 */
	public int onTurnAroundRailway(Coordinate target){
		int ret  = -1;
		if (x == 7 && y >= 2 && y<=6 && target.getY() ==7 && target.getX() >=2 && target.getX() <= 6){
			ret = 0;	//From A->B
		}else if(y == 7 && x >= 2 && x <=6 && target.getX() ==7 && target.getY() >=2 && target.getY() <= 6){
			ret = 1;	//From B->A
		}else if(y ==11 && x >=2 && x <= 6 && target.getX() == 7 && target.getY() >= 12 && target.getY()<=16 ){
			ret = 2;	//From C->D
		}else if(x == 7 && y >= 12 && y<=16 && target.getY() ==11 && target.getX() >=2 && target.getX() <= 6){
			ret = 3;	//From D->C
		}else if(x == 11 && y >= 12 && y<=16 && target.getY() ==11 && target.getX() >=12 && target.getX() <= 16){
			ret = 4;	//From E->F
		}else if (y ==11 && x >=12 && x <= 16 && target.getX() == 11 && target.getY() >= 12 && target.getY()<=16){
			ret = 5;	//From F->E
		}else if (y == 7 && x >=12 && x <= 16 && target.getX() == 11 && target.getY() >=  2 && target.getY()<= 6 ){
			ret = 6;	//From G->H
		}else if (x==11 && y>=2 && y<=6 && target.getY()==7 && target.getX()>=12 && target.getX()<=16){
			ret = 7;	//From H->G
		}
		return ret ;
	}
	/**
	 * Get the engineer's adjacent coordinate which on railway
	 * @return
	 */
	public ArrayList<Coordinate> engineerAdjacent(){
		ArrayList<Coordinate> returns = new ArrayList<Coordinate>();
		if (isValidCoordinate(100*y+(x-1))){
			Coordinate c = new Coordinate(x-1,y);
			if (c.onRailWay()) returns.add(c);
		}else if (isValidCoordinate(100*y+(x-2))){
			Coordinate c = new Coordinate(x-2,y);
			if (c.onRailWay()) returns.add(c);
		}
		
		if (isValidCoordinate(100*y+(x+1))){
			Coordinate c = new Coordinate(x+1,y);
			if (c.onRailWay()) returns.add(c);
		}else if (isValidCoordinate(100*y+(x+2))){
			Coordinate c = new Coordinate(x+2,y);
			if (c.onRailWay()) returns.add(c);
		}
		
		if (isValidCoordinate(100*(y-1)+x)){
			Coordinate c = new Coordinate(x ,y-1);
			if (c.onRailWay()) returns.add(c);
		}else if (isValidCoordinate(100*(y-2)+ x)){
			Coordinate c = new Coordinate(x,y-2);
			if (c.onRailWay()) returns.add(c);
		}
		
		if (isValidCoordinate(100*(y+1)+x)){
			Coordinate c = new Coordinate(x ,y+1);
			if (c.onRailWay()) returns.add(c);
		}else if (isValidCoordinate(100*(y+2)+ x)){
			Coordinate c = new Coordinate(x,y+2);
			if (c.onRailWay()) returns.add(c);
		}
		
		if (returns.size()==3){
			if (isValidCoordinate(100*(y+1)+(x+1))){
				Coordinate c = new Coordinate(x+1,y+1);
				if (c.onRailWay()) {
					returns.add(c);
					return returns;
				}
			}
			if (isValidCoordinate(100*(y-1)+(x+1))){
				Coordinate c = new Coordinate(x+1,y-1);
				if (c.onRailWay()) {
					returns.add(c);
					return returns;
				}
			}
			if (isValidCoordinate(100*(y-1)+(x-1))){
				Coordinate c = new Coordinate(x-1,y-1);
				if (c.onRailWay()){
					returns.add(c);
					return returns;
				}
			}
			if (isValidCoordinate(100*(y+1)+(x-1))){
				Coordinate c = new Coordinate(x-1,y+1);
				if (c.onRailWay()) {
					returns.add(c);
					return returns;
				}
			}
		}
		return returns;
	}
	/**
	 * Get common chessman's adjacent
	 * @return
	 */
	public ArrayList<Coordinate> commonAdjacent(Coordinate target){
		ArrayList<Coordinate> returns = new ArrayList<Coordinate>();
		if (this.onSameLineHorizon(target)){
			if (isValidCoordinate(100 * y + (x + 1))){
				Coordinate c = new Coordinate(x + 1,y);
				if (c.onRailWay()) returns.add(c);
			}else if (isValidCoordinate(100 * y+ (x + 2))){
				Coordinate c = new Coordinate(x + 2,y);
				if (c.onRailWay()) returns.add(c);
			}
			if (isValidCoordinate(100 * y+(x - 1))){
				Coordinate c = new Coordinate(x - 1,y);
				if (c.onRailWay()) returns.add(c);
			}else if (isValidCoordinate(100 * y +(x - 2))){
				Coordinate c = new Coordinate(x - 2,y);
				if (c.onRailWay()) returns.add(c);
			}
		}else if(this.onSameLineVertical(target)){
			if (isValidCoordinate(100 * (y + 1) + x)){
				Coordinate c = new Coordinate(x ,y + 1);
				if (c.onRailWay()) returns.add(c);
			}else if (isValidCoordinate(100 * (y + 2) + x)){
				Coordinate c = new Coordinate(x ,y + 2);
				if (c.onRailWay()) returns.add(c);
			}
			if (isValidCoordinate(100 * (y - 1) + x)){
				Coordinate c = new Coordinate(x ,y - 1);
				if (c.onRailWay()) returns.add(c);
			}else if (isValidCoordinate(100 * (y - 2) + x)){
				Coordinate c = new Coordinate(x ,y - 2);
				if (c.onRailWay()) returns.add(c);
			}
		}else if (onTurnAroundRailway(target) >-1){
			for (int t:turnOffRailway()){
				Coordinate c = new Coordinate(t);
				if (this.getCostG(c) <= 14){
					returns.add(c);
				}
			}
			
		}
		return returns;
	}
	/**
	 * The G function 
	 * @param adjacent
	 * @return
	 */
	public int getCostG(Coordinate adjacent){
		if (adjacent.getX() == x && Math.abs(adjacent.getY()-y) == 1|| adjacent.getY() ==y && Math.abs(adjacent.getX() -x) ==1){
			return 10;
		}else if (adjacent.getX() == x && Math.abs(adjacent.getY()-y) == 2|| adjacent.getY() ==y && Math.abs(adjacent.getX() -x) ==2){
			return 20;
		}else if (Math.abs(adjacent.getX () -x)==1 && Math.abs(adjacent.getY() - y )==1){
			return 14;
		}else {
			return Integer.MAX_VALUE;
		}
	}
	/**
	 * The G cost
	 * @return
	 */
	public int getCostG(){
		if (parent!=null){
			return getCostG(parent);
		}
		return 0;
	}
	/**
	 * The H function 
	 * @param target
	 * @return
	 */
	public int getDistanceH(Coordinate target){
		return (Math.abs(target.getX() - x) + Math.abs(target.getY() - y)) * 10;
	}
	/**
	 * The F function
	 * @param target
	 * @return
	 */
	public int getF(Coordinate target){
		return getCostG() + getDistanceH(target);
	}
	
	/**
	 * Get the absolute Point
	 * @return
	 */
	public Point getPoint(){
		return new Point ((getX() -1) * Const.ROAD_SIDE_WIDTH + Const.BORDER_X, 
				(getY()-1) * Const.ROAD_SIDE_WIDTH + Const.BORDER_Y);
	}
	
	public int getPointX(){
		return (getX() -1) * Const.ROAD_SIDE_WIDTH + Const.BORDER_X;
	}
	
	public int getPointY(){
		return (getY()-1) * Const.ROAD_SIDE_WIDTH + Const.BORDER_Y;
	}
	
	private int[] turnOffRailway(){
		return new int[] {207,307,407,507,607,
				702,703,704,705,706,
				1102,1103,1104,1105,1106,
				1207,1307,1407,1507,1607,
				1211,1311,1411,1511,1611,
				1112,1113,1114,1115,1116,
				712,713,714,715,716,
				211,311,411,511,611
		};
	}
	/**
	 * toString() 
	 */
	public String toString(){
		return "Coordinate:" + coordinateValue +", x:" + getX() + ",y:" + getY();
	}
	/**
	 * Get all coordinates 
	 */
	public static int[] getAllCoordinateInt(){
		return new int[]{
			107, 108, 109, 110, 111, 
			207, 208, 209, 210, 211, 
			307, 308, 309, 310, 311, 
			407, 408, 409, 410, 411, 
			507, 508, 509, 510, 511, 
			607, 608, 609, 610, 611, 
			701, 702, 703, 704, 705, 706, 707, 709, 711, 712, 713, 714, 715, 716, 717, 
			801, 802, 803, 804, 805, 806, 812, 813, 814, 815, 816, 817, 
			901, 902, 903, 904, 905, 906, 907, 909, 911, 912, 913, 914, 915, 916, 917, 
			1001, 1002, 1003, 1004, 1005, 1006, 1012, 1013, 1014, 1015, 1016, 1017, 
			1101, 1102, 1103, 1104, 1105, 1106, 1107, 1109, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 
			1207, 1208, 1209, 1210, 1211, 
			1307, 1308, 1309, 1310, 1311, 
			1407, 1408, 1409, 1410, 1411, 
			1507, 1508, 1509, 1510, 1511, 
			1607, 1608, 1609, 1610, 1611, 
			1707, 1708, 1709, 1710, 1711
		};
	}
	/**
	 * Get the headquarters coordinate integer
	 */
	public static int[] getAllHeadquarterInt(){
		return new int[] {
				108, 110, 801, 1001, 817, 1017, 1708, 1710
		};
	}
	/**
	 * Get all camps coordinate integer
	 */
	public static int[] getAllCampInt(){
		return new int[]{
			308,310,409,508,510,
			803,805,904,1003,1005,
			813,815,914,1013,1015,
			1308,1310,1409,1508,1510
		};
	}
	
	/**
	 * @param coordinate
	 * @return
	 */
	public static boolean isValidCoordinateNumber(int coordinate){
		return coordinate >= 107 && coordinate <= 111 ||
			coordinate >= 207 && coordinate <= 211 ||
			coordinate >= 307 && coordinate <= 311 ||
			coordinate >= 407 && coordinate <= 411 ||
			coordinate >= 507 && coordinate <= 511 ||
			coordinate >= 607 && coordinate <= 611 ||
			coordinate >= 701 && coordinate <= 707 ||
			coordinate >= 801 && coordinate <= 806 ||
			coordinate >= 901 && coordinate <= 907 ||
			coordinate >= 1001 && coordinate <= 1006 ||
			coordinate >= 1101 && coordinate <= 1107 ||
			coordinate >= 711 && coordinate <= 717 ||
			coordinate >= 812 && coordinate <= 817 ||
			coordinate >= 911 && coordinate <= 917 ||
			coordinate >= 1012 && coordinate <= 1017 ||
			coordinate >= 1111 && coordinate <= 1117 ||
			coordinate >= 1207 && coordinate <= 1211 ||
			coordinate >= 1307 && coordinate <= 1311 ||
			coordinate >= 1407 && coordinate <= 1411 ||
			coordinate >= 1507 && coordinate <= 1511 ||
			coordinate >= 1607 && coordinate <= 1611 ||
			coordinate >= 1707 && coordinate <= 1711 ||
			coordinate == 709 || coordinate == 909 ||
			coordinate == 1109;
	}
	public void setCoordinateValue(int coordinateValue) {
		this.coordinateValue = coordinateValue;
	}
	
	public static Coordinate symmetry(Coordinate c1){
		Coordinate center = new Coordinate(909);
		int disX = c1.getX() - center.getX();
		int disY = c1.getY() - center.getY();
		Coordinate c2 = new Coordinate(center.getX() - disX, center.getY() - disY);
		return c2;
	}
	
	
	public static Coordinate steer90(Coordinate c1){
		Coordinate center = new Coordinate(909);
		int disX = c1.getX() - center.getX();
		int disY = c1.getY() - center.getY();
		Coordinate c2 = new Coordinate(center.getX() + disY, center.getY() - disX);
		return c2;
	}
	
	public static Coordinate steerMinus90(Coordinate c1){
		Coordinate center = new Coordinate(909);
		int disX = c1.getX() - center.getX();
		int disY = c1.getY() - center.getY();
		Coordinate c2 = new Coordinate(center.getX() - disY, center.getY() + disX);
		return c2;
	}
	
	public static void main(String[] args){
//		Coordinate c = new Coordinate (907);
//		ArrayList<Coordinate> returns = c.engineerAdjacent();
//		for (Coordinate cc: returns){
//			System.out.println(cc);
//		}
		
//		System.out.println(Coordinate.symmetry(new Coordinate(611))); //1207
//		System.out.println(Coordinate.symmetry(new Coordinate(1207))); //611
		
//		System.out.println(Coordinate.steer90(new Coordinate(1004))); //1410
//		System.out.println(Coordinate.steer90(new Coordinate(507))); //1105
		
//		System.out.println(Coordinate.steerMinus90(new Coordinate(1410))); //1004
//		System.out.println(Coordinate.steerMinus90(new Coordinate(1105))); //507
	}
}
	
	
	

