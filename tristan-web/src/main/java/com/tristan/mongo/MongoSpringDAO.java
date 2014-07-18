package com.tristan.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MongoSpringDAO {
	
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static void main(String[] args) throws Exception {
		springMongo();
	}

	
	
	private static void springMongo() {
		ConfigurableApplicationContext context = null;
		context = new ClassPathXmlApplicationContext("config/mongo.xml");

		MongoSpringDAO mongoDAO = context.getBean(MongoSpringDAO.class);

		Student student = new Student();
		student.setName("james");
		student.setAge(23);
		
		Score score = new Score();
		score.setChinese(34);
		score.setEnglish(93);
		score.setMath(66);
		student.setScore(score);
		
		mongoDAO.mongoTemplate.insert(student);
		
		Query query = new Query();
		query.addCriteria(new Criteria("name").is("james"));
		Student s = mongoDAO.mongoTemplate.findOne(query, Student.class);
		System.out.println("English is " + s.getScore().getEnglish());
	}
}

