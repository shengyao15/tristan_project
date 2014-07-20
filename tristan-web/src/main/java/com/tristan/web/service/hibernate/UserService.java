package com.tristan.web.service.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tristan.web.dao.hibernate.UserDAO;
import com.tristan.web.po.hibernate.User;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDao;
	private List<User> userList = new ArrayList<User>();
	
	public boolean delete(int id) {
		return userDao.delete(id);
	}

	public User find(int id) {
		return userDao.find(id);
	}

	public boolean update(User user) {
		boolean flag = userDao.update(user);
		//if(true) throw new NullPointerException();
		return flag;
	}

	public List<User> search(User user) {
		
/*		测试内存溢出
 * 		User u = new User();
		userList.add(u);*/
		
		return userDao.search(user);
	}

}
