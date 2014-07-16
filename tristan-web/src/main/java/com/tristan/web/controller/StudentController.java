package com.tristan.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tristan.web.po.User;
import com.tristan.web.service.StudentService;
import com.tristan.web.service.UserService;
import com.tristan.web.vo.StudentVO;

@Controller
@RequestMapping("/student")
public class StudentController {
 
	@Resource(name="studentService")
	private StudentService studentService;
	
	
	@RequestMapping("/listAll")
	public String find(HttpServletRequest request){
		
		List<StudentVO> studentVOList = studentService.listAll();
		request.setAttribute("studentVOList", studentVOList);
		StudentVO studentVO = new StudentVO();
		request.setAttribute("studentVO", studentVO);
		return "/student/listAllPage";
	}

	@RequestMapping("/search")
	public String search(HttpServletRequest request, @Valid StudentVO studentVO, BindingResult result){
	
		List<StudentVO> studentVOList = studentService.search(studentVO);
		request.setAttribute("studentVOList", studentVOList);
		request.setAttribute("studentVO", studentVO);
		return "/student/listAllPage";
		
	}
}
