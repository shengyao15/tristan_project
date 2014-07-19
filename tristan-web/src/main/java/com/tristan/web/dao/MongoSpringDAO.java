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
import com.tristan.mongo.Score;
import com.tristan.mongo.Student;

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

	
	public List<Student> listAll() {
		List<Student> list = mongoTemplate.find(null, Student.class);
		
		for (Student student : list) {
			System.out.println(student.getName()+" " + student.getScore().getEnglish());
		}
		
		return list;
	}


	public List<Student> groupByCountry() {
	    
	    AggregationOperation group = Aggregation.group("country")
	     		.avg("score.english").as("english_avg")
	     		.avg("score.math").as("math_avg")
	     		.avg("score.chinese").as("chinese_avg");
	    Aggregation aggregation = Aggregation.newAggregation(group);
	    AggregationResults<DBObject> result = mongoTemplate.aggregate(aggregation, "student", DBObject.class);
		
	    List<DBObject> list = result.getMappedResults();
	    List<Student> stuList = new ArrayList<Student>();
	    
	    for (DBObject db : list) {
			System.out.println(db.get("_id") +"  :  "+db.get("english_avg")+"  :  "+db.get("math_avg"));
			Student student = new Student();
			student.setCountry((String)db.get("_id"));
			
			Score score = new Score();
			score.setEnglish(((Double)db.get("english_avg")).intValue());
			score.setMath(((Double)db.get("math_avg")).intValue());
			score.setChinese(((Double)db.get("chinese_avg")).intValue());
			student.setScore(score);
			
			stuList.add(student);
		}
	    
	    return stuList;
	    

	}


	public void drop() {
		mongoTemplate.dropCollection(Student.class);
	}



	public List<Student> findByScore(Student student) {
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
		
		List<Student> list = mongoTemplate.find(query, Student.class);
		
		for (Student s : list) {
			System.out.println(s.getName()+" " + s.getScore().getEnglish());
		}
		
		return list;
	}



	public void deleteByName(String string) {

		Query query = new Query();
		query.addCriteria(new Criteria("name").is("james"));
		mongoTemplate.remove(query, Student.class);
	}



	public void prepareData() {
		
		if (!mongoTemplate.collectionExists(Student.class)) {
			mongoTemplate.createCollection(Student.class);
		}
		
		Random r = new Random();
		String[] country = {"USA", "China", "Japan"};
		List<Student> list = new ArrayList<Student>();
		for (int i = 0; i < 50; i++) {
			Student student = new Student();
			student.setName("s"+i);
			student.setAge(r.nextInt(20));
			student.setCountry(country[r.nextInt(3)]);
			
			Score score = new Score();
			score.setEnglish(30+r.nextInt(70));
			score.setMath(30+r.nextInt(70));
			score.setChinese(30+r.nextInt(70));
			student.setScore(score);
			list.add(student);
		}
		
		mongoTemplate.insertAll(list);
	}


	public void demo() {
		Student student = new Student();
		student.setName("james");
		student.setAge(23);
		
		Score score = new Score();
		score.setChinese(34);
		score.setEnglish(93);
		score.setMath(66);
		student.setScore(score);
		
		mongoTemplate.insert(student);
		
		Query query = new Query();
		query.addCriteria(new Criteria("name").is("james"));
		Student s = mongoTemplate.findOne(query, Student.class);
		System.out.println("English is " + s.getScore().getEnglish());
	}
}

