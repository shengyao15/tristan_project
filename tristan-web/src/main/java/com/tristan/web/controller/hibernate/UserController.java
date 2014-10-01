package com.tristan.web.controller.hibernate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tristan.web.po.hibernate.User;
import com.tristan.web.service.hibernate.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
 
	Logger logger = Logger.getLogger(UserController.class);
	
	@Resource(name="userService")
	//@Autowired 
	private UserService userService;
	
	@RequestMapping("/delete")
	public void delete(int id,HttpServletResponse response){
		String result = "{\"result\":\"error\"}";
		if(userService.delete(id)){
			result = "{\"result\":\"success\"}";
		}
		PrintWriter out = null;
		response.setContentType("application/json");
		
		try {
			out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request){
		String id = request.getParameter("id");
		
		if(StringUtils.isEmpty(id)){
			User u = new User(); 
			request.setAttribute("user", u);
			//return "/user/updatePage";
			return "/user/updateMVCPage";
		}
		
		User user = userService.find(Integer.valueOf(id));
		request.setAttribute("user", user);
		//return "/user/updatePage";
		return "/user/updateMVCPage";
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request, User user){
		
		if(userService.update(user)){
/*			user = userService.find(user.getId());
			request.setAttribute("user", user);*/
			return "redirect:/user/search";
		}else{
			return "/error";
		}
		
	}
	
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request, @Valid User user, BindingResult result){
	
	/*	if(result.hasErrors()){
			List<ObjectError>  list = result.getAllErrors();
			for(ObjectError error: list){
				System.out.println(error.getDefaultMessage());//验证信息
			}
		}*/
		
		logger.info(userService);
		
		
		List<User> userList = userService.search(user);
		request.setAttribute("user", user);
		request.setAttribute("userList", userList);
		
		return "/user/listAllPage";
		
	}
}
