package com.tristan.web.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionFactoryUtil {
	
	private static org.hibernate.SessionFactory sessionFactory;
	private SessionFactoryUtil() {
	}
	
	static {
		// 使用xml文件或者注解方式加载hibernate配置
		sessionFactory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
		// 只是用xml文件方式加载hibernate配置
		// sessionFactory = new	Configuration().configure().buildSessionFactory();
	}
	
	public static SessionFactory getInstance() {
		return sessionFactory;
	}
	
	/**
	 * 打开会话但不绑定到会话上下文中
	 * @return the session
	 */
	public Session openSession() {
		return sessionFactory.openSession();
	}
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 关闭会话工厂
	 */
	public static void close() {
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;
	}
}