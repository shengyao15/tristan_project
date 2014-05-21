package chess;

import java.util.ArrayList;

import chess.Road.RoadType;



public class InitRoads {
private static ArrayList<Road> roadsList = new ArrayList<Road>();
	
	public static ArrayList<Road> init(){
		if (roadsList.size()==0){
			// --
			Road road = new Road(107,108,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(108,109,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(109,110,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(110,111,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(207,208,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(208,209,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(209,210,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(210,211,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			
			road = new Road(307,308,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(308,309,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(309,310,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(310,311,RoadType.ROAD);
			roadsList.add(road);

			road = new Road(407,408,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(408,409,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(409,410,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(410,411,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(507,508,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(508,509,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(509,510,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(510,511,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(607,608,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(608,609,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(609,610,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(610,611,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			
			road = new Road(701,702,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(702,703,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(703,704,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(704,705,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(705,706,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(706,707,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(707,709,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(709,711,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(711,712,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(712,713,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(713,714,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(714,715,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(715,716,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(716,717,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(801,802,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(802,803,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(803,804,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(804,805,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(805,806,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(812,813,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(813,814,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(814,815,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(815,816,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(816,817,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(901,902,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(902,903,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(903,904,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(904,905,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(905,906,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(906,907,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(907,909,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(909,911,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(911,912,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(912,913,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(913,914,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(914,915,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(915,916,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(916,917,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1001,1002,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1002,1003,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1003,1004,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1004,1005,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1005,1006,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1012,1013,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1013,1014,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1014,1015,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1015,1016,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1016,1017,RoadType.ROAD);
			roadsList.add(road);
			
			
			road = new Road(1101,1102,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1102,1103,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1103,1104,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1104,1105,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1105,1106,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1106,1107,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1107,1109,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1109,1111,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1111,1112,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1112,1113,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1113,1114,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1114,1115,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1115,1116,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1116,1117,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1207,1208,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1208,1209,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1209,1210,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1210,1211,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			
			road = new Road(1307,1308,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1308,1309,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1309,1310,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1310,1311,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1407,1408,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1408,1409,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1409,1410,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1410,1411,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1507,1508,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1508,1509,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1509,1510,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1510,1511,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1607,1608,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1608,1609,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1609,1610,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1610,1611,RoadType.HORIZONTAL_RAILWAY);
			roadsList.add(road);
			
			road = new Road(1707,1708,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1708,1709,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1709,1710,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1710,1711,RoadType.ROAD);
			roadsList.add(road);
			
			// |
			road = new Road(701,801,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(801,901,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(901,1001,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1001,1101,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(702,802,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(802,902,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(902,1002,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1002,1102,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			
			road = new Road(703,803,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(803,903,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(903,1003,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1003,1103,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(704,804,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(804,904,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(904,1004,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1004,1104,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(705,805,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(805,905,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(905,1005,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1005,1105,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(706,806,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(806,906,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(906,1006,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1006,1106,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			
			
			road = new Road(107,207,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(207,307,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(307,407,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(407,507,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(507,607,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(607,707,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(707,907,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(907,1107,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1107,1207,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1207,1307,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1307,1407,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1407,1507,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1507,1607,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1607,1707,RoadType.ROAD);
			roadsList.add(road);
			 
			road = new Road(108,208,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(208,308,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(308,408,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(408,508,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(508,608,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1208,1308,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1308,1408,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1408,1508,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1508,1608,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1608,1708,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(109,209,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(209,309,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(309,409,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(409,509,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(509,609,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(609,709,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(709,909,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(909,1109,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1109,1209,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			
			road = new Road(1209,1309,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1309,1409,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1409,1509,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1509,1609,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1609,1709,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(110,210,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(210,310,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(310,410,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(410,510,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(510,610,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1210,1310,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1310,1410,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1410,1510,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1510,1610,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1610,1710,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(111,211,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(211,311,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(311,411,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(411,511,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(511,611,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(611,711,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(711,911,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(911,1111,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1111,1211,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1211,1311,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1311,1411,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1411,1511,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1511,1611,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1611,1711,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(712 ,812 ,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(812 ,912 ,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(912 ,1012 ,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1012 ,1112 ,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);

			road = new Road(713 ,813 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(813 ,913 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(913 ,1013 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1013 ,1113 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(714 ,814 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(814 ,914 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(914 ,1014 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1014 ,1114 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(715 ,815 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(815 ,915 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(915 ,1015 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1015 ,1115 ,RoadType.ROAD);
			roadsList.add(road);

			road = new Road(716 ,816 ,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(816 ,916 ,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(916 ,1016 ,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			road = new Road(1016 ,1116 ,RoadType.VERTICAL_RAILWAY);
			roadsList.add(road);
			
			road = new Road(717 ,817 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(817 ,917 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(917 ,1017 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1017 ,1117 ,RoadType.ROAD);
			roadsList.add(road);
			
			// / && \
			road = new Road(407 ,308 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(308 ,209 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(607 ,508 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(508 ,409 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(409 ,310 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(310 ,211 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(609 ,510 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(510 ,411 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(407 ,508 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(508 ,609 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(207 ,308 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(308 ,409 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(409 ,510 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(510 ,611 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(209 ,310 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(310 ,411 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(704 ,805 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(805 ,906 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(803 ,704 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(902 ,803 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1104 ,1005 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1005 ,906 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1003 ,1104 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(902, 1003 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(702 ,803  ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(803 ,904  ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(904 ,1005 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1005,1106 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1102,1003 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1003,904 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(904,805 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(805,706 ,RoadType.ROAD);
			roadsList.add(road);
			

			road = new Road(714 ,815 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(815 ,916 ,RoadType.ROAD);
			roadsList.add(road);
			
			
			road = new Road(813 ,714 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(912 ,813 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1114 ,1015 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1015 ,916 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1013 ,1114 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(912 ,1013 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(712 ,813  ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(813 ,914  ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(914 ,1015  ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1015 ,1116 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1112 ,1013,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1013 ,914 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(914 ,815 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(815 ,716 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1407 ,1308 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1308 ,1209 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1607 ,1508 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1508 ,1409 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1409 ,1310 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1310 ,1211 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1609 ,1510 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1510 ,1411 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1407 ,1508 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1508 ,1609 ,RoadType.ROAD);
			roadsList.add(road);
			
			road = new Road(1207 ,1308 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1308 ,1409 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1409 ,1510 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1510  ,1611 ,RoadType.ROAD);
			roadsList.add(road);

			road = new Road(1209 ,1310 ,RoadType.ROAD);
			roadsList.add(road);
			road = new Road(1310 ,1411 ,RoadType.ROAD);
			roadsList.add(road);
	
			road = new Road(706 ,607 ,RoadType.CROOKED_RAILWAY);
			roadsList.add(road);
			road = new Road(1106 ,1207 ,RoadType.CROOKED_RAILWAY);
			roadsList.add(road);
			road = new Road(611 ,712 ,RoadType.CROOKED_RAILWAY);
			roadsList.add(road); 	
			road = new Road(1211 ,1112 ,RoadType.CROOKED_RAILWAY);
			roadsList.add(road);
		}
		return roadsList ;
	}
	
	
}
