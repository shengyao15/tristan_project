package com.tristan.web.dao.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tristan.web.po.hive.Student;

public class HiveDAO {

	static Connection con;
	static Statement stmt;
	static {
		try {
			Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");

			con = DriverManager.getConnection(
					"jdbc:hive://hbaseserver:10000/default", "", "");
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		listAll();
		groupByCountry();
	}

	public static List<Student> groupByCountry() throws SQLException {
		String sql = "select country, avg(english) from st group by country";
		ResultSet rs2 = stmt.executeQuery(sql);
		List<Student> list = new ArrayList<Student>();
		while (rs2.next()) {
			System.out.println(rs2.getString(1) + "  " + rs2.getString(2));
			Student s = new Student();
			s.setCountry(rs2.getString(1));
			s.setEnglish(rs2.getString(2));
			list.add(s);
			
		}
		
		return list;
	}

	public static List<Student> listAll() throws SQLException {
		String sql = "select * from st";
		ResultSet rs = stmt.executeQuery(sql);
		List<Student> list = new ArrayList<Student>();
		while (rs.next()) {
			System.out.println(rs.getString(2) + "  " + rs.getString(3) + "   "
					+ rs.getString(4) + "   " + rs.getString(5));
			
			Student s = new Student();
			s.setName(rs.getString(2));
			s.setAge(rs.getString(3));
			s.setCountry(rs.getString(4));
			s.setEnglish(rs.getString(5));
			list.add(s);
		}
		
		return list;
	}
}
