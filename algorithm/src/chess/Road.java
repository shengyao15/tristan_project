package chess;

/**
 * Author by metaphy
 * Date Dec 21, 2007
 * All Rights Reserved.
 */


public class Road {
	public enum RoadType {
		ROAD, 
		HORIZONTAL_RAILWAY,
		VERTICAL_RAILWAY,
		CROOKED_RAILWAY
	}
	private Coordinate start;
	private Coordinate end;
	private RoadType type;
	
	public Road(){}
	public Road(Coordinate start, Coordinate end, RoadType type) {
		this.start = start;
		this.end = end;
		this.type = type;
	}
	
	public Road(int startInt, int endInt, RoadType type) {
		this.start = new Coordinate(startInt);
		this.end = new Coordinate (endInt);
		this.type = type;
	}

	public Coordinate getStart() {
		return start;
	}

	public void setStart(Coordinate start) {
		this.start = start;
	}

	public Coordinate getEnd() {
		return end;
	}

	public void setEnd(Coordinate end) {
		this.end = end;
	}

	public RoadType getType() {
		return type;
	}

	public void setType(RoadType type) {
		this.type = type;
	}
	
	public String toString(){
		return "Start:"+ start.toString() +"; End:"+ end.toString() +";Type:"+ type;
	}
}

