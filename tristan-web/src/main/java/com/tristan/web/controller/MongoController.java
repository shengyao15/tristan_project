package com.tristan.web.controller;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.tristan.mongo.MongoOriginalDAO;

@Controller
@RequestMapping("/mongo")
public class MongoController {
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		try {
			Mongo mg = new Mongo("127.0.0.1:27017");
			DB db = mg.getDB("tristan");
			DBCollection students = db.getCollection("students");
			
			MongoOriginalDAO dao = new MongoOriginalDAO();
			//request.setAttribute("list", dao.list2(students));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/student/listEachCourseGrade";
	}
}
