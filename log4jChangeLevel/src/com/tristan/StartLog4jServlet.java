package com.tristan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StartLog4jServlet extends HttpServlet{
	
	
	@Override
	public void init() throws ServletException {

		System.out.println("-----StartLog4jServlet init-----");
		Thread t1 = new Thread(new Thread1());
		t1.start();
		super.init();
	}


}
