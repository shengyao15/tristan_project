package z.bigdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingleUpate {
	
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE", "tristan", "654321");

		 PreparedStatement stmt = con.prepareStatement("update t_bigtable set status=? where id =?");  
	
	        
		PreparedStatement pStmt = con.prepareStatement("select * from t_bigtable order by id");
		ResultSet result = pStmt.executeQuery();
		while (result.next()) {
			int id = result.getInt(1);
			System.out.println(id);
			updateStatus(id, stmt, 2);
		}
		
		con.close();
	}

	private static void updateStatus(int id, PreparedStatement stmt, int status) throws SQLException, InterruptedException {

		Thread.sleep(1000);
		stmt.setInt(1, status);
	     stmt.setInt(2, id);
	     
		 stmt.executeUpdate();  
	}
}
