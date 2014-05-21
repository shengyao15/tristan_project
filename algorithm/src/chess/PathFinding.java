/**
 * Author by metaphy
 * Date 2008-1-28
 * All Rights Reserved.
 */
package chess;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import chess.Chessman.Soldier;



public class PathFinding {

	private ArrayList<Coordinate> openList = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> closeList = new ArrayList<Coordinate>();
	private Coordinate start,end;
	
	
	
	/**
	 * Try to find the path 
	 * @return
	 */
	public ArrayList<Integer> tryToFind(Coordinate _start ,Coordinate _end,Chessman _from, Chessman _target){
		ArrayList<Integer> path = new ArrayList<Integer>();
		start = _start;
		end = _end;
		if (start.onRoad()||end.onRoad()){		//Start or end on the road,just 1 step
			if (_target == null || (_target!=null && !end.inCamp())){
				if (start.isStraightAdjacent(end) && !start.inHeaderquaracter()){
					path.add(start.getInteger());
					path.add(end.getInteger());
				}else if (start.isSlantAdjacent(end)){
					if (start.inCamp() || end.inCamp()){
						path.add(start.getInteger());
						path.add(end.getInteger());
					}
				}
			}
		}else if (start.onRailWay() && end.onRailWay()){	//Both on railway
			if (start.onSameLineHorizon(end) ||
					start.onSameLineVertical(end) ||
					start.onTurnAroundRailway(end)>=0 ||	
					_from.getSoldier()==Soldier.GONGBING){	// engineer-soldier
				Coordinate current = null;
				ArrayList<Coordinate> adjacentList ;
				openList.add(start);
				do{
					current = lookForMinF();
					openList.remove(current);
					closeList.add(current);
					if (_from.getSoldier()==Soldier.GONGBING){
						adjacentList = current.engineerAdjacent();
					} else {
						adjacentList = current.commonAdjacent(end);
					}
					for (Coordinate adjacent:adjacentList){
						if (!onCloseList(adjacent) && (!isTakenup(adjacent) || adjacent.getCoordinateValue() == end.getCoordinateValue())){
							if (!onOpenList(adjacent)){
								adjacent.setParent(current);
								openList.add(adjacent);
							} else {
								int g0 = current.getParent().getCostG(adjacent);
								int g1 = current.getCostG() + current.getCostG(adjacent);
								if (g1 < g0){
									adjacent.setParent(current);
								}
							}
						}
					}
				} while(!findTarget() && openList.size()>0);
				end.setParent(current);
				if (findTarget()){
//					System.out.println("Got it!A*");
					Stack<Integer> stack = new Stack<Integer>();
					Coordinate t = end;
					while (!t.equals(start)){
						stack.push(t.getInteger());
						t = t.getParent();
					}
					path.add(start.getInteger());
					while (!stack.empty()){
						path.add(stack.pop());
					}
				}
			}
		}
		if (path.size()<1){
			//System.out.println("Can't find the path");
		}
			
		return path;
	}
	/**
	 * Look for the coordinate that has the min F value from open list 
	 * @return
	 */
	private Coordinate lookForMinF(){
		Coordinate c = openList.get(0);
		for (Coordinate tmp :openList){
			if (tmp.getF(end) < c.getF(end)){
				c = tmp ;
			}
		}
		return c;
	}
	/**
	 * Find the target or not
	 * @return
	 */
	private boolean findTarget(){
		for (Coordinate open: openList){
			if (open.equals(end))
				return true;
		}
		return false;
	}
	private boolean onCloseList(Coordinate c){
		for (Coordinate close: closeList){
			if (close.equals(c))
				return true;
		}
		return false;
	}
	private boolean onOpenList (Coordinate c){
		for (Coordinate open: openList){
			if (open.equals(c))
				return true;
		}
		return false;
	}
	/**
	 * The coordinte is taken up by others or not
	 * @param c
	 * @return
	 */
	private boolean isTakenup(Coordinate c){
		Map<String, ChessmanFigure> map = MainPanel.figureFinalMap;
		for(String key : map.keySet()){
				ChessmanFigure f = map.get(key);
				Chessman chessman = f.getChessman();
				if (chessman.getCoordinate().equals(c) ){
					//System.out.println("冲突     "+ key + " ： " +chessman.getCaption() + "  " + chessman.getCoordinate());
					return true;
				}
			
		}
		return false;
	}
	
}

