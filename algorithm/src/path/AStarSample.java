package path;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class AStarSample {
	public static  ArrayList<Coordinate> openList = new ArrayList<Coordinate>();
	public static  ArrayList<Coordinate> closeList = new ArrayList<Coordinate>();
	
	public static  List<Coordinate> path = new ArrayList<Coordinate>();
	public static  Set<Coordinate> openSet = new HashSet<Coordinate>();
	
	public static String fileName = "gen/path.xls";
	
	public static int MAP_ROW = 17;
	public static int MAP_COLUMN = 19;
	
	public static  Coordinate start = new Coordinate(403);
	public static  Coordinate end = new Coordinate(415);
	
	public static List<Coordinate> water = new ArrayList<Coordinate>();
	
	static{
		//--------1--------
		water.add(new Coordinate(305));
		water.add(new Coordinate(306));
		water.add(new Coordinate(405));
		water.add(new Coordinate(504));
		water.add(new Coordinate(505));
		
		//-------2---------
		water.add(new Coordinate(205));
		water.add(new Coordinate(605));
		water.add(new Coordinate(204));
		water.add(new Coordinate(203));
		
		water.add(new Coordinate(705));
		water.add(new Coordinate(805));
		water.add(new Coordinate(905));
		water.add(new Coordinate(1005));
		
		
		//----block--------
//		water.add(new Coordinate(314));
//		water.add(new Coordinate(315));
//		water.add(new Coordinate(316));
//		water.add(new Coordinate(414));
//		water.add(new Coordinate(416));
//		water.add(new Coordinate(514));
//		water.add(new Coordinate(515));
//		water.add(new Coordinate(516));
	}
	
	/**
	 * Try to find the path
	 * @return
	 */
	public static void pathFinding(){
		
		Coordinate current = null;
		openList.add(start);
		openSet.add(start);
		
		do{
			current = lookForMinF();
			
			openList.remove(current);
			closeList.add(current);
			ArrayList<Coordinate> list = current.getValidAdjacent();
			for (Coordinate adjacent:list){
				if (!inClosedList(adjacent)){
					if (!inOpenedList(adjacent)){
						adjacent.setParent(current);
						openList.add(adjacent);
						openSet.add(adjacent);
					}else{
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
			Coordinate tmp = new Coordinate();
			tmp = end;
			while (tmp.getValue() != start.getValue()){
				//System.out.println(tmp.getValue());
				
				if(end.getValue() != tmp.getValue()){
					path.add(tmp);
				}
				
				tmp = tmp.getParent();
			}
			//System.out.println(start.getValue());
			
		}else{
			System.out.println("Can't find the path.");
		}
	}
	/**
	 * Look for the min F value coordinate from open list 
	 * @return
	 */
	public static  Coordinate lookForMinF(){
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
	public static  boolean findTarget(){
		for (Coordinate open: openList){
			if (open.getValue() == end.getValue())
				return true;
		}
		return false;
	}
	public static  boolean inClosedList(Coordinate c){
		for (Coordinate close: closeList){
			if (close.getValue() == c.getValue())
				return true;
		}
		return false;
	}
	public static  boolean inOpenedList (Coordinate c){
		for (Coordinate open: openList){
			if (open.getValue() == c.getValue())
				return true;
		}
		return false;
	}
	
	public static void drawMap() throws Exception{


		Workbook wb = new HSSFWorkbook();
	    Sheet s = wb.createSheet("map");
	    s.setDefaultColumnWidth(2);

	    for(int i=1; i<=MAP_ROW; i++){
	    	Row r = s.createRow(i);
	    	for(int j=1; j<=MAP_COLUMN; j++){
	    		Cell c = r.createCell(j);
	 	 	    CellStyle style = wb.createCellStyle();
	 	 	    style.setBorderTop(CellStyle.BORDER_THIN);  
	 	 	    style.setBorderBottom(CellStyle.BORDER_THIN);  
	 	 	    style.setBorderLeft(CellStyle.BORDER_THIN);  
	 	 	    style.setBorderRight(CellStyle.BORDER_THIN);
	 	 	    c.setCellValue("");
		 	    c.setCellStyle(style);
	    	}
	    }
	    
	    
	    for(int i=1; i<=MAP_ROW; i++){
	    	Row r = s.getRow(i);
	    	for(int j=1; j<=MAP_COLUMN; j++){
	    		Cell c = r.createCell(0);
	    		c.setCellValue(i);
	    	}	
	    }
	    
	    Row row0 = s.createRow(0);
    	for(int j=1; j<=MAP_COLUMN; j++){
    		Cell c = row0.createCell(j);
    		c.setCellValue(j);
    	}	
	    
	    Row row1 = s.getRow(1);
	    Row row2 = s.getRow(2);
	    Row row3 = s.getRow(3);
	    Row row4 = s.getRow(4);
	    Row row5 = s.getRow(5);
	    Row row6 = s.getRow(6);
	    Row row7 = s.getRow(7);
	    
	    CellStyle yellow = wb.createCellStyle();
	    yellow.setFillForegroundColor(HSSFColor.YELLOW.index);
	    yellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    yellow.setBorderTop(CellStyle.BORDER_THIN);  
	    yellow.setBorderBottom(CellStyle.BORDER_THIN);  
	    yellow.setBorderLeft(CellStyle.BORDER_THIN);  
	    yellow.setBorderRight(CellStyle.BORDER_THIN);
	    
	    CellStyle blue = wb.createCellStyle();
	    blue.setFillForegroundColor(HSSFColor.BLUE.index);
	    blue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    blue.setBorderTop(CellStyle.BORDER_THIN);  
	    blue.setBorderBottom(CellStyle.BORDER_THIN);  
	    blue.setBorderLeft(CellStyle.BORDER_THIN);  
	    blue.setBorderRight(CellStyle.BORDER_THIN);
	    
	    CellStyle green = wb.createCellStyle();
	    green.setFillForegroundColor(HSSFColor.GREEN.index);
	    green.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    green.setBorderTop(CellStyle.BORDER_THIN);  
	    green.setBorderBottom(CellStyle.BORDER_THIN);  
	    green.setBorderLeft(CellStyle.BORDER_THIN);  
	    green.setBorderRight(CellStyle.BORDER_THIN);
	    
	    CellStyle orange = wb.createCellStyle();
	    orange.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
	    orange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    orange.setBorderTop(CellStyle.BORDER_THIN);  
	    orange.setBorderBottom(CellStyle.BORDER_THIN);  
	    orange.setBorderLeft(CellStyle.BORDER_THIN);  
	    orange.setBorderRight(CellStyle.BORDER_THIN);
	    
	    
	    //Water
	    for(Coordinate c : water){
	    	Row r = s.getRow(c.getY());
	    	Cell cell = r.getCell(c.getX());
	    	cell.setCellStyle(blue);
	    	
	    }
	    
	  //openSet
	    for(Coordinate c : openSet){
	    	int x = c.getX();
	 	    int y = c.getY();
	 	    
	 	    Row rowP = s.getRow(y);
	 	    Cell cellP = rowP.getCell(x);
	 	    cellP.setCellStyle(orange);
	    }
	    
	    //Path  
	    System.out.println("--------Path-------");
	    for(Coordinate c : path){
	    	int x = c.getX();
	 	    int y = c.getY();
	 	    
	 	    System.out.println(c.getValue());
	 	    Row rowP = s.getRow(y);
	 	    Cell cellP = rowP.getCell(x);
	 	    cellP.setCellStyle(green);
	    }
	   
	    //start
	    Row startRow = s.getRow(start.getY());
	    Cell startCell = startRow.getCell(start.getX());
	    startCell.setCellStyle(yellow);
	    startCell.setCellValue("S");
	    
	    //end
	    Row endRow = s.getRow(end.getY());
	    Cell endCell = startRow.getCell(end.getX());
	    endCell.setCellStyle(yellow);
	    endCell.setCellValue("E");
	    
	    
	    FileOutputStream fileOut = new FileOutputStream(fileName);
	    wb.write(fileOut);
	    fileOut.close();

		
	} 
	
	public static void main(String[] args) throws Exception {
		pathFinding();
	    drawMap();
		
		
	}
}
