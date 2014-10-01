package com.tristan.encode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		BaseAesSecurity b = new BaseAesSecurity();
		String x = b.encode("3637340020", "a2Fi%k6=");
		System.out.println(x);
		//resp.setContentType("multipart/form-data");
		resp.setContentType("application/x-www-form-urlencoded");
		
		resp.sendRedirect("/hello.jsp?x="+x+"&taobao_level=V2");
	}

	
}
