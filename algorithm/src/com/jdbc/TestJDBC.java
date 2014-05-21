package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 由于shared pool存放了执行计划(全表扫描)所以导致以后prepareStatement用不到索引了
 * oracle重启后清空shared pool 就很快了
 */
public class TestJDBC {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.81.128:1521:ORCL", "oracle",
				"oracle");

		long begin = System.currentTimeMillis(); // 测试起始时间

		test1(con);

		long end = System.currentTimeMillis(); // 测试结束时间
		System.out.println("操作所需时间：" + (end - begin) + " 毫秒"); // 打印使用时间

	}

	//18027 ms
	private static void test1(Connection con) throws Exception {
		PreparedStatement pStmt = con
				.prepareStatement("select * from Testtab3 where id = ?");
		pStmt.setInt(1, 32);
		ResultSet result = pStmt.executeQuery();

	}
	
	//57 ms
	private static void test2(Connection con) throws Exception {
		Statement st = con.createStatement();
		ResultSet result = st.executeQuery("select * from Testtab3 where id = 32");

	}
}
