package com.tristan;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentResponse {
	String status;
	String score;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	
}
