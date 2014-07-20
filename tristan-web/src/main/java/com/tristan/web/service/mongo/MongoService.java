package com.tristan.web.service.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tristan.web.dao.hibernate.StudentDAO;
import com.tristan.web.dao.hibernate.UserDAO;
import com.tristan.web.dao.mongo.MongoSpringDAO;
import com.tristan.web.po.hibernate.User;
import com.tristan.web.po.mongo.StudentMongo;
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
