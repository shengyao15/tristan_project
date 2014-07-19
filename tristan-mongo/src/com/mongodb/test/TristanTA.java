package com.mongodb.test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class TristanTA {
	public static void main(String[] args) throws Exception {
		
		Mongo mg = new Mongo("127.0.0.1:27017");
		DB db = mg.getDB("tristan");
		DBCollection students = db.getCollection("students");
		DBCollection aggregation = db.getCollection("aggregation");
		//list();
		//prepareData();
		//list2(students);
		
		//findByName(students);
		//findByScore(students);
	
		//aggregate1(aggregation);
	}

	
	
	private static void aggregate1(DBCollection aggregation) {
		// create our pipeline operations, first with the $match 
		DBObject match = new BasicDBObject("$match", new BasicDBObject("type", "airfare") ); 
		  // build the $projection operation 
		DBObject fields = new BasicDBObject("department", 1); fields.put("amount", 1); 
		fields.put("_id", 0); 
		DBObject project = new BasicDBObject("$project", fields ); 
		// Now the $group operation 
		DBObject groupFields = new BasicDBObject( "_id", "$department"); groupFields.put("average", new BasicDBObject( "$avg", "$amount")); DBObject group = new BasicDBObject("$group", groupFields); 
		// run aggregation 
		AggregationOutput output = aggregation.aggregate( match, project, group );
		
		System.out.println(output.getCommandResult());
		
		 for(DBObject obj:output.results())
         {
              BasicDBObject obj2 = (BasicDBObject)obj;
              System.out.println("average:  "+obj2.getString("average"));
         }
	}



	private static void findByScore(DBCollection students) {
		// 查询所有的数据
		DBCursor cur = students.find(new BasicDBObject("score.English", new BasicDBObject("$gte",80)));
		while (cur.hasNext()) {
			DBObject object = cur.next();
			System.out.println(object.get("name") +" | "+ object.get("age") +" | "+ object.get("score"));
		}
		

	}
	
	private static void findByName(DBCollection students) {
		// 查询所有的数据
		DBCursor cur = students.find(new BasicDBObject("name", "s22"));
		while (cur.hasNext()) {
			DBObject object = cur.next();
			System.out.println(object.get("name") +" | "+ object.get("age") +" | "+ object.get("score"));
		}
		
		
	}

	private static void prepareData() throws Exception {
		Mongo mg = new Mongo("127.0.0.1:27017");
		
		MongoDb mongoDb = new MongoDb("tristan");
		//mongoDb.createCollection("students");
		
		Random r = new Random();
		List<DBObject> dbObjects = new ArrayList<DBObject>();
		
		for (int i = 0; i < 50; i++) {
			DBObject student = new BasicDBObject();
			student.put("name", "s"+i);
			student.put("age", 10+r.nextInt(20));
			DBObject score = new BasicDBObject();
			score.put("English", 30+r.nextInt(70));
			score.put("Math", 30+r.nextInt(70));
			score.put("Chinese", 30+r.nextInt(70));
			student.put("score", score);
			dbObjects.add(student);
		}
		mongoDb.insertBatch(dbObjects, "students");
	}

	private static void list2(DBCollection students) throws UnknownHostException {
		
		// 查询所有的数据
		DBCursor cur = students.find();
		while (cur.hasNext()) {
			DBObject object = cur.next();
			System.out.println(object.get("name") +" | "+ object.get("age") +" | "+ object.get("score"));
		}
		
		students.remove(new BasicDBObject("age", new BasicDBObject("$lt", 24))).getN();
	}
	
	private static void delete() throws UnknownHostException {
		Mongo mg = new Mongo("127.0.0.1:27017");
		DB db = mg.getDB("tristan");
		DBCollection students = db.getCollection("students");
		
		students.remove(new BasicDBObject("age", new BasicDBObject("$lt", 100))).getN();
	}
	
	private static void list() throws UnknownHostException {
		Mongo mg = new Mongo("127.0.0.1:27017");
		DB db = mg.getDB("tristan");
		DBCollection users = db.getCollection("persons");
		// 查询所有的数据
		DBCursor cur = users.find();
		while (cur.hasNext()) {
			DBObject object = cur.next();
			System.out.println(object.get("name") +" | "+ object.get("country") +" | "+ object.get("c") +" | "+ object.get("m") +" | "+ object.get("e"));
		}
	}
}
