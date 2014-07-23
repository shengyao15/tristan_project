package com.tristan.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tristan.web.dao.hbase.HBaseDAO;
import com.tristan.web.po.hbase.Student;

public class HBaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HBaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("method");
		if("listAll".equals(m)){
			List<Student> list = HBaseDAO.listAll();
			output(response, list);
		}else if("searchCountry".equals(m)){
			String country = request.getParameter("country");
			List<Student> list = HBaseDAO.searchByCountry(country);
			output(response, list);
		}else if("searchName".equals(m)){
			String name = request.getParameter("name");
			Student student = HBaseDAO.searchByName(name);
			List<Student> list = new ArrayList<Student>();
			list.add(student);
			
			output(response, list);
		}
		
	}
	private void output(HttpServletResponse response, List<Student> list)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<th width=\"50\" align=\"left\"></th>");
		sb.append("<th width=\"200\" align=\"left\">学生姓名</th>");
		sb.append("<th width=\"200\" align=\"left\">年龄</th>");
		sb.append("<th width=\"200\" align=\"left\">国家</th>");
		sb.append("<th width=\"200\" align=\"left\">英文</th>");
		sb.append("</tr>");
		
		sb.append("<tr><th colspan=\"5\"><hr></th></tr>");
		for (Student s : list) {
			sb.append("<tr>");
			sb.append("<td></td>");
			sb.append("<td>"+s.getName()+"</td>");
			sb.append("<td>"+s.getAge()+"</td>");
			sb.append("<td>"+s.getCountry()+"</td>");
			sb.append("<td>"+s.getEnglish()+"</td>");
			
			sb.append("</tr>");
		}
		sb.append("</table>");
		
		response.setCharacterEncoding("GBK");
		response.getWriter().print(sb.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
