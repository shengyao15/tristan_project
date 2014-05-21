/**
 * Author by metaphy
 * Date Dec 24, 2007
 * All Rights Reserved.
 */
package chess;

import java.util.ArrayList;

import chess.StationPoint.StationPointType;



public class InitStationPoints {
	public static ArrayList<StationPoint> init() {
		ArrayList<StationPoint> list = new ArrayList<StationPoint>();
		int[] all = Coordinate.getAllCoordinateInt();
		for (int coor : all) {
			StationPoint sp = new StationPoint(coor);
//			sp.setCoordinate(new Coordinate(coor));
			switch (coor) {
			case 108:
			case 110:
			case 801:
			case 1001:
			case 817:
			case 1017:
			case 1708:
			case 1710:
				sp.setType(StationPointType.HEADQUARTERS);
				break;
			case 308:
			case 310:
			case 409:
			case 508:
			case 510:
			case 803:
			case 805:
			case 904:
			case 1003:
			case 1005:
			case 813:
			case 815:
			case 914:
			case 1013:
			case 1015:
			case 1308:
			case 1310:
			case 1409:
			case 1508:
			case 1510:
				sp.setType(StationPointType.CAMP);
				break;
			default:
				sp.setType(StationPointType.STATION);
			}
			list.add(sp);
		}
		return list;
	}
}
