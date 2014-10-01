package com.tristan;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student{
	private String country;
	private int age;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return country+"  " +age;
	}
	
	
	
}