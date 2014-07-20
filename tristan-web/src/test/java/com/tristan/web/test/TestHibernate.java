package com.tristan.web.test;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.tristan.web.po.hibernate.User;


public class TestHibernate {
	
	public static void main(String[] args) {
		Session session = new AnnotationConfiguration().configure("hibernateTest.cfg.xml").buildSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user = new User();//瞬态
		user.setUserName("tristan4");
		user.setAge(11);
		
		session.save(user);//持久态
		tx.commit();//提交事务，提交后会自动关闭session
		
//		session.close();
	}
}
