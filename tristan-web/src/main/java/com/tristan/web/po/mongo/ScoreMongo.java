package com.tristan.web.po.mongo;

import com.mongodb.BasicDBObject;

public class ScoreMongo{
	
	private int english;
	private int math;
	private int chinese;
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getChinese() {
		return chinese;
	}
	public void setChinese(int chinese) {
		this.chinese = chinese;
	}
	
}
