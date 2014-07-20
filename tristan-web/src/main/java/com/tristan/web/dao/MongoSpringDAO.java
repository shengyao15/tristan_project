package com.tristan.web.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.tristan.web.po.ScoreMongo;
import com.tristan.web.po.StudentMongo;

@Repository
public class MongoSpringDAO {
	
	
	@Autowired
	public MongoTemplate mongoTemplate;
	
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = null;
		context = new ClassPathXmlApplicationContext("config/spring-mongo.xml");

		MongoSpringDAO mongoDAO = context.getBean(MongoSpringDAO.class);
		//mongoDAO.demo();
		
//		mongoDAO.drop();
//		
//		mongoDAO.prepareData();
		
		//mongoDAO.deleteByName("james");
		
		//mongoDAO.findByScore();
		
		mongoDAO.listAll();
	}

	
	public List<StudentMongo> listAll() {
		List<StudentMongo> list = mongoTemplate.find(null, StudentMongo.class);
		
		for (StudentMongo student : list) {
			System.out.println(student.getName()+" " + student.getScore().getEnglish());
		}
		
		return list;
	}


	public List<StudentMongo> groupByCountry() {
	    
	    AggregationOperation group = Aggregation.group("country")
	     		.avg("score.english").as("english_avg")
	     		.avg("score.math").as("math_avg")
	     		.avg("score.chinese").as("chinese_avg");
	    Aggregation aggregation = Aggregation.newAggregation(group);
	    AggregationResults<DBObject> result = mongoTemplate.aggregate(aggregation, "student", DBObject.class);
		
	    List<DBObject> list = result.getMappedResults();
	    List<StudentMongo> stuList = new ArrayList<StudentMongo>();
	    
	    for (DBObject db : list) {
			System.out.println(db.get("_id") +"  :  "+db.get("english_avg")+"  :  "+db.get("math_avg"));
			StudentMongo student = new StudentMongo();
			student.setCountry((String)db.get("_id"));
			
			ScoreMongo score = new ScoreMongo();
			score.setEnglish(((Double)db.get("english_avg")).intValue());
			score.setMath(((Double)db.get("math_avg")).intValue());
			score.setChinese(((Double)db.get("chinese_avg")).intValue());
			student.setScore(score);
			
			stuList.add(student);
		}
	    
	    return stuList;
	    

	}


	public void drop() {
		mongoTemplate.dropCollection(StudentMongo.class);
	}



	public List<StudentMongo> findByScore(StudentMongo student) {
		Query query = new Query();
		
		int english = student.getScore().getEnglish();
		int math = student.getScore().getMath();
		int chinese = student.getScore().getChinese();
		if(english!=0){
			query.addCriteria(new Criteria("score.english").gt(english));
		}
		if(math!=0){
			query.addCriteria(new Criteria("score.math").gt(math));
		}
		if(chinese!=0){
			query.addCriteria(new Criteria("score.chinese").gt(chinese));
		}
		
		List<StudentMongo> list = mongoTemplate.find(query, StudentMongo.class);
		
		for (StudentMongo s : list) {
			System.out.println(s.getName()+" " + s.getScore().getEnglish());
		}
		
		return list;
	}



	public void deleteByName(String string) {

		Query query = new Query();
		query.addCriteria(new Criteria("name").is("james"));
		mongoTemplate.remove(query, StudentMongo.class);
	}



	public void prepareData() {
		
		if (!mongoTemplate.collectionExists(StudentMongo.class)) {
			mongoTemplate.createCollection(StudentMongo.class);
		}
		
		Random r = new Random();
		String[] country = {"USA", "China", "Japan"};
		List<StudentMongo> list = new ArrayList<StudentMongo>();
		for (int i = 0; i < 50; i++) {
			StudentMongo student = new StudentMongo();
			student.setName("s"+i);
			student.setAge(r.nextInt(20));
			student.setCountry(country[r.nextInt(3)]);
			
			ScoreMongo score = new ScoreMongo();
			score.setEnglish(30+r.nextInt(70));
			score.setMath(30+r.nextInt(70));
			score.setChinese(30+r.nextInt(70));
			student.setScore(score);
			list.add(student);
		}
		
		mongoTemplate.insertAll(list);
	}


	public void demo() {
		StudentMongo student = new StudentMongo();
		student.setName("james");
		student.setAge(23);
		
		ScoreMongo score = new ScoreMongo();
		score.setChinese(34);
		score.setEnglish(93);
		score.setMath(66);
		student.setScore(score);
		
		mongoTemplate.insert(student);
		
		Query query = new Query();
		query.addCriteria(new Criteria("name").is("james"));
		StudentMongo s = mongoTemplate.findOne(query, StudentMongo.class);
		System.out.println("English is " + s.getScore().getEnglish());
	}
}

