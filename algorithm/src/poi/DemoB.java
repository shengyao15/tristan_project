package poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFTextbox;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DemoB {
	
	public static void main(String[] args) throws Exception {
		FileInputStream in = null;  
		Workbook wb = null;  
		  
		    in = new FileInputStream("test.xls");  
		    wb = WorkbookFactory.create(in);  
		    
//		//创建一个EXCEL
//		Workbook wb = new HSSFWorkbook();
		
		HSSFPatriarch patriarch = ((HSSFSheet)wb.getSheet("Sheet1")).createDrawingPatriarch();

		//直线
		HSSFClientAnchor clientAnchor1 = new HSSFClientAnchor(0, 0, 0, 0,
				(short) 4, 2, (short) 6, 5);
		HSSFSimpleShape shape1 = patriarch.createSimpleShape(clientAnchor1);
		shape1.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);

		//圆圈（椭圆）
		HSSFClientAnchor clientAnchor2 = new HSSFClientAnchor(0, 0, 0, 0,
				(short) 8, 4, (short) 6, 5);
		HSSFSimpleShape shape2 = patriarch.createSimpleShape(clientAnchor2);
		shape2.setShapeType(HSSFSimpleShape.OBJECT_TYPE_OVAL);

		//正方形（长方形）
		HSSFClientAnchor clientAnchor3 = new HSSFClientAnchor(0, 0, 0, 0,
				(short) 12, 6, (short) 6, 5);
		HSSFSimpleShape shape3 = patriarch.createSimpleShape(clientAnchor3);
		shape3.setShapeType(HSSFSimpleShape.OBJECT_TYPE_RECTANGLE);

		//Textbox
		HSSFClientAnchor clientAnchor4 = new HSSFClientAnchor(0, 0, 0, 0,
				(short) 14, 8, (short) 6, 5);
		HSSFTextbox textbox = patriarch.createTextbox(clientAnchor4);
		textbox.setString(new HSSFRichTextString("This is a test"));

		FileOutputStream fileOut = new FileOutputStream("test2.xls");
	    wb.write(fileOut);
	    fileOut.close();
	}
	

}
