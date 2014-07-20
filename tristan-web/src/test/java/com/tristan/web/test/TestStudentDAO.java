package com.tristan.web.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tristan.web.po.hibernate.Student;
import com.tristan.web.po.hibernate.User;

@Repository
public class TestStudentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public Student find(int id) {
		return (Student)sessionFactory.getCurrentSession().get(Student.class, id);
	}
	
	public static void main(String[] args) {
		Session session = new AnnotationConfiguration().configure("hibernateTest.cfg.xml").buildSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Student stu = new Student();
		stu.setSno("999");
		stu.setSname("X man");
		stu.setSage(30);
		stu.setSdept("HP");
		stu.setSsex("M");
		
		session.save(stu);//持久态
		tx.commit();//提交事务，提交后会自动关闭session
		
//		session.close();
	}	
	

}
