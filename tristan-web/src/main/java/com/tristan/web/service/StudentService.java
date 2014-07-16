package com.tristan.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tristan.web.dao.StudentDAO;
import com.tristan.web.dao.UserDAO;
import com.tristan.web.po.User;
import com.tristan.web.vo.StudentVO;

@Service
public class StudentService {
	
	@Autowired
	private StudentDAO studentDao;
	
	public List listEachCourseGrade(){
		return studentDao.listEachCourseGrade();
		
	}
	
	public List<StudentVO> listAll(){
		List list = studentDao.listAll();
		List<StudentVO> studentList = new ArrayList<StudentVO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[])list.get(i); 
			StudentVO vo = new StudentVO();
			vo.setSno((String)o[0]);
			vo.setSname((String)o[1]);
			vo.setCno((String)o[2]);
			vo.setCname((String)o[3]);
			vo.setGrade((Integer)o[4]);
			studentList.add(vo);
		}
		
		return studentList;
	}
	
	
	public List<StudentVO> search(StudentVO studentVO){
		List list = studentDao.search(studentVO);
		List<StudentVO> studentList = new ArrayList<StudentVO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[])list.get(i); 
			StudentVO vo = new StudentVO();
			vo.setSno((String)o[0]);
			vo.setSname((String)o[1]);
			vo.setCno((String)o[2]);
			vo.setCname((String)o[3]);
			vo.setGrade((Integer)o[4]);
			studentList.add(vo);
		}
		
		return studentList;
		
	}
}
