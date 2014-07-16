package z.bigdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleUpdate {

	private static int maxStoreSize = 1000;
	public static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(
			maxStoreSize);

	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE", "tristan", "654321");

		PreparedStatement stmt = con
				.prepareStatement("update t_bigtable set status=? where id =?");

		PreparedStatement pStmt = con
				.prepareStatement("select * from t_bigtable order by id");
		ResultSet result = pStmt.executeQuery();
		while (result.next()) {
			int id = result.getInt(1);
			System.out.println(id);

			queue.put(id);
		}

		ExecutorService es = Executors.newCachedThreadPool();
		Worker w = new Worker(stmt,3);
		
		es.submit(w);
		es.submit(w);
		es.submit(w);
		es.submit(w);
		es.submit(w);
		es.submit(w);
		es.submit(w);
		es.submit(w);
		es.submit(w);
		es.submit(w);
		
		Thread.sleep(Long.MAX_VALUE);
		// con.close();
	}

}

class Worker implements Runnable {
	PreparedStatement stmt;
	int status;
	
	public Worker(PreparedStatement stmt, int status) {
		this.stmt = stmt;
		this.status = status;
	}

	@Override
	public void run() {
		while(true){
			try {
				int id = (Integer)MultipleUpdate.queue.take();
				System.out.println(Thread.currentThread().getName() + " : "+id);
				
				Thread.sleep(1000);
				stmt.setInt(1, status);
				stmt.setInt(2, id);
				stmt.executeUpdate();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}

