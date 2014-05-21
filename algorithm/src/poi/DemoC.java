package poi;


import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 * 如果 是用的POI2.x的jar包
 * Workbook wb = new HSSFWorkbook(); 会编译报错
 * POI 2.x 和 POI3.6有冲突
 * @author shengra
 *
 */
public class DemoC {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Workbook wb = new HSSFWorkbook();
	    Sheet s = wb.createSheet("map");
	    s.setDefaultColumnWidth(2);

	    for(int i=1; i<=7; i++){
	    	Row r = s.createRow(i);
	    	for(int j=1; j<=9; j++){
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
	    
	    CellStyle blue = wb.createCellStyle();
	    blue.setFillForegroundColor(HSSFColor.BLUE.index);
	    blue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    
	    //start
	    Cell cell4_3 = row4.getCell(3);
	    cell4_3.setCellStyle(yellow);
	    
	    //end
	    Cell cell4_7 = row4.getCell(7);
	    cell4_7.setCellStyle(yellow);
	    
	    //Water
	    Cell cell3_5 = row3.getCell(5);
	    cell3_5.setCellStyle(blue);
	    Cell cell3_6 = row3.getCell(6);
	    cell3_6.setCellStyle(blue);
	    Cell cell4_5 = row4.getCell(5);
	    cell4_5.setCellStyle(blue);
	    Cell cell5_4 = row5.getCell(4);
	    cell5_4.setCellStyle(blue);
	    Cell cell5_5 = row5.getCell(5);
	    cell5_5.setCellStyle(blue);
	    
	    //Path  
//	    307
//	    206
//	    205
//	    304
	    
	    
	    
	    
	    
	    FileOutputStream fileOut = new FileOutputStream("testC.xls");
	    wb.write(fileOut);
	    fileOut.close();


	}

}
