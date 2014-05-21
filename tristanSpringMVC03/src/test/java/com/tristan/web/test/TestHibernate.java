package com.tristan.web.test;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tristan.web.po.User;


public class TestHibernate {
	
	public static void main(String[] args) {
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user = new User();//瞬态
		user.setUserName("tristan");
		user.setAge(11);
		
		session.save(user);//持久态
		tx.commit();//提交事务，提交后会自动关闭session
		
//		session.close();
	}
}
