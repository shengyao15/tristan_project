package com.tristan.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student {
	private String name;
	private int age;
	private Score score;
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
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	
	
}
