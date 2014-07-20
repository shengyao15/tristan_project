package com.tristan.web.controller.hibernate;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tristan.web.service.hibernate.StudentService;
import com.tristan.web.vo.StudentVO;

@Controller
@RequestMapping("/student")
public class StudentController {
 
	@Resource(name="studentService")
	private StudentService studentService;
	
	@RequestMapping("/listEachCourseGrade")
	public String listEachCourseGrade(HttpServletRequest request){
		request.setAttribute("list", studentService.listEachCourseGrade());
		return "/student/listEachCourseGrade";
	}
	
	
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
