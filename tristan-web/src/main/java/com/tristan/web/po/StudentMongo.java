package com.tristan.web.po;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "student")
public class StudentMongo {
	private String name;
	private int age;
	private ScoreMongo score;
	private String country;
	
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public ScoreMongo getScore() {
		return score;
	}
	public void setScore(ScoreMongo score) {
		this.score = score;
	}
	
	
}
