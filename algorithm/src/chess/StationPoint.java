/**
 * Author by metaphy
 * Date Dec 20, 2007
 * All Rights Reserved.
 */
package chess;

import java.io.Serializable;


public class StationPoint implements Serializable{
	public enum StationPointType {
		HEADQUARTERS, CAMP, STATION
	}
	private Coordinate coordinate ;
	private StationPointType type ;
	
	public StationPoint(){}
	public StationPoint(Coordinate c){
		this.coordinate = c;
	}
	public StationPoint(int c){
		this.coordinate = new Coordinate(c);
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	public StationPointType getType() {
		return type;
	}
	public void setType(StationPointType type) {
		this.type = type;
	}
	
}




