package com.tristan.web.controller.redis;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tristan.web.dao.redis.JedisDAO;
import com.tristan.web.po.redis.StudentJedis;


@Controller
@RequestMapping("/jedis")
public class JedisController {
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		try {
			request.setAttribute("list", JedisDAO.listAll());
			StudentJedis student = new StudentJedis();
			request.setAttribute("student", student);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/jedis/list";
	}
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request, @Valid StudentJedis student, BindingResult result){
		try {
			request.setAttribute("list", JedisDAO.searchByCountry(student.getCountry()));
			request.setAttribute("student", student);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/jedis/list";
	}
	
	
	@RequestMapping("/avg")
	public String avg(HttpServletRequest request ){
		try {
			request.setAttribute("list", JedisDAO.avgByCountry());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/jedis/avg";
	}
}
