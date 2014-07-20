package com.tristan.web.test;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tristan.web.po.hibernate.User;
import com.tristan.web.service.hibernate.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/config/spring-core.xml"})
public class TestUserService {

	@Autowired
	private UserService userService; 
	
	@Test
	public void testInstance(){
		assertNotNull(userService);
	}
	
	@Test
	public void testSearch(){
		List<User> userList = userService.search(new User());
		assertTrue(userList.size()>0);
	}
	
	@Test
	public void testCRUD(){
		User u = new User();
		u.setUserName("tristan55");
		u.setAge(11);
		u.setGender("m");
		u.setHeight(111);
		u.setWeight(111);
		u.setCity("shanghai");
		u.setSpeciality("IT");
		u.setInterest("guitar");
		userService.update(u);
		
		List<User> userList = userService.search(u);
		assertTrue(userList.size()>0);
		
		/*userService.delete(userList.get(0).getId());
		
		User u2 = userService.find(userList.get(0).getId());
		assertNull(u2);*/
		
	}
	
	
}
