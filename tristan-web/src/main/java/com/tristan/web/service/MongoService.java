package com.tristan.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tristan.web.dao.MongoSpringDAO;
import com.tristan.web.dao.StudentDAO;
import com.tristan.web.dao.UserDAO;
import com.tristan.web.po.StudentMongo;
import com.tristan.web.po.User;
import com.tristan.web.vo.StudentVO;

@Service
public class MongoService {
	
	@Autowired
	private MongoSpringDAO mongoDAO;
	
	public List<StudentMongo> listAll(){
		return mongoDAO.listAll();
		
	}
	
	public List<StudentMongo> search(StudentMongo s){
		return mongoDAO.findByScore(s);
		
	}
	
	public List<StudentMongo> groupByCountry(){
		return mongoDAO.groupByCountry();
		
	}
	
}
