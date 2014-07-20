package com.tristan.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tristan.web.po.ScoreMongo;
import com.tristan.web.po.StudentMongo;
import com.tristan.web.service.MongoService;


@Controller
@RequestMapping("/mongo")
public class MongoController {
	
	@Resource(name="mongoService")
	private MongoService mongoService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		try {
			request.setAttribute("list", mongoService.listAll());
			
			StudentMongo student = new StudentMongo();
			student.setScore(new ScoreMongo());
			request.setAttribute("student", student);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/mongo/listAllPage";
	}
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request, @Valid StudentMongo student, BindingResult result){
		try {
			request.setAttribute("list", mongoService.search(student));
			request.setAttribute("student", student);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/mongo/listAllPage";
	}
	
	@RequestMapping("/groupByCountry")
	public String groupByCountry(HttpServletRequest request){
		try {
			request.setAttribute("list", mongoService.groupByCountry());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/mongo/groupByCountry";
	}
}
