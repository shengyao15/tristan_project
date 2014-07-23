package com.tristan.web.dao.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import com.tristan.web.po.hbase.Student;

public class HBaseDAO {
	public static final byte[] TABLE_NAME = Bytes.toBytes("students");

	static HBaseAdmin admin;
	static Configuration conf;
	static HTableInterface students;
	static HTablePool pool;
	static {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "hbaseserver");
		try {
			HBaseAdmin admin = new HBaseAdmin(conf);
			checkTableExist(admin);

			pool = new HTablePool(conf, 10);
			students = pool.getTable("students");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		// dropTable(admin);
/*		checkTableExist(admin);

		HTablePool pool = new HTablePool(conf, 10);
		HTableInterface students = pool.getTable("students");*/

		// prepareData(students,"hbase");
		// delete(students);

		listAll();
		searchByName("hbase19");
		searchByCountry("USA");
	}

	public static List<Student> searchByCountry(String country)
			throws IOException {
		Scan scan = new Scan();
		Filter filter = new SingleColumnValueFilter(Bytes.toBytes("info"),
				Bytes.toBytes("country"), CompareOp.EQUAL,
				Bytes.toBytes(country));
		scan.setFilter(filter);
		scan.addFamily(Bytes.toBytes("info"));
		ResultScanner results = students.getScanner(scan);
		int i = 0;
		List<Student> studentList = new ArrayList<Student>();
		for (Result r : results) {
			Student student = new Student();
			student.setName(Bytes.toString((r.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")))));
			student.setAge(Integer.valueOf(Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("age")))));
			student.setCountry(Bytes.toString((r.getValue(Bytes.toBytes("info"), Bytes.toBytes("country")))));
			student.setEnglish(Integer.valueOf(Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("english")))));
			studentList.add(student);
		}
		return studentList;
	}

	public static Student searchByName(String name)
			throws IOException {
		Get get = new Get(Bytes.toBytes(name));
		Scan s = new Scan(get);
		s.addFamily(Bytes.toBytes("info"));
		ResultScanner results = students.getScanner(s);
		int i = 0;
		Result r = results.next();
		Student student = new Student();
		student.setName(Bytes.toString((r.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")))));
		student.setAge(Integer.valueOf(Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("age")))));
		student.setCountry(Bytes.toString((r.getValue(Bytes.toBytes("info"), Bytes.toBytes("country")))));
		student.setEnglish(Integer.valueOf(Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("english")))));
		return student;

	}

	public static List<Student> listAll() throws IOException {
		Scan s = new Scan();
		s.addFamily(Bytes.toBytes("info"));
		ResultScanner results = students.getScanner(s);
		int i = 0;
		List<Student> studentList = new ArrayList<Student>();
		for (Result r : results) {
			Student student = new Student();
			student.setName(Bytes.toString((r.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")))));
			student.setAge(Integer.valueOf(Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("age")))));
			student.setCountry(Bytes.toString((r.getValue(Bytes.toBytes("info"), Bytes.toBytes("country")))));
			student.setEnglish(Integer.valueOf(Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("english")))));
			studentList.add(student);
		}

		return studentList;
	}

	public static void checkTableExist(HBaseAdmin admin) throws IOException {
		if (!admin.tableExists(Bytes.toBytes("students"))) {
			HTableDescriptor desc = new HTableDescriptor(
					Bytes.toBytes("students"));
			HColumnDescriptor c = new HColumnDescriptor(Bytes.toBytes("info"));
			desc.addFamily(c);
			admin.createTable(desc);
		}
	}

	public static void dropTable(HBaseAdmin admin) throws IOException {
		admin.disableTable("students");
		admin.deleteTable("students");

	}

	public static void delete(HTableInterface students) throws IOException {
		Delete d = new Delete(Bytes.toBytes("tristan"));
		students.delete(d);
	}

	public static void prepareData(HTableInterface students, String name)
			throws IOException {

		Random r = new Random();
		String[] countrys = { "USA", "China", "Japan" };
		for (int i = 0; i < 20; i++) {
			Map<String, String> map = new HashMap<String, String>();

			String country = countrys[r.nextInt(3)];
			String english = String.valueOf(30 + r.nextInt(70));
			String username = name + i;

			Put p = new Put(Bytes.toBytes(username));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("name"),
					Bytes.toBytes(username));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("age"),
					Bytes.toBytes(String.valueOf(20 + r.nextInt(10))));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("country"),
					Bytes.toBytes(country));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("english"),
					Bytes.toBytes(english));
			students.put(p);

		}

	}

	public static void p(byte[] v) {
		System.out.println(Bytes.toString(v) + "   ");
	}

}