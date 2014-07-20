package com.tristan.web.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tristan.web.po.hibernate.User;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean delete(int id) {
		String hql = "delete User u where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (query.executeUpdate() > 0);
	}
	
	public User find(int id) {
		return (User)sessionFactory.getCurrentSession().get(User.class, id);
	}
	
	public boolean update(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return true;
	}

	public List<User> search(User user) {
		StringBuilder sb = new StringBuilder();
		sb.append("from User u where 1=1 ");
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		if (!StringUtils.isEmpty(user.getUserName())) {
			sb.append(" and u.userName = :userName");
			paramsMap.put("userName", user.getUserName());
		}

		if (user.getAge() != 0) {
			sb.append(" and u.age = :age");
			paramsMap.put("age", String.valueOf(user.getAge()));
		}

		if (!StringUtils.isEmpty(user.getGender())) {
			sb.append(" and u.gender = :gender");
			paramsMap.put("gender", user.getGender());
		}
		
		if (!StringUtils.isEmpty(user.getSpeciality())) {
			sb.append(" and u.speciality = :speciality");
			paramsMap.put("speciality", user.getSpeciality());
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		for(String key :paramsMap.keySet()){
			query.setString(key, (String) paramsMap.get(key));
		}
		
		return query.list();
	}
	
	

}
