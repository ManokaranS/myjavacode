package Servlet;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbmanagment.Action;


public class Actionservlet extends HttpServlet {
	String abspath;
	Properties prop;
	
	public void init(ServletConfig config) throws ServletException {
		
		String path=config.getInitParameter("config");
		
		System.out.println(path);
		
		
		ServletContext ctx=config.getServletContext();

		abspath=ctx.getRealPath(path);
		
		System.out.println(abspath);
		
		prop=new Properties();
		
		try {
			prop.load(new FileInputStream(abspath));
			
		} catch (Exception e) {
			System.out.println("error");
		} 
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String config1=request.getParameter("id");
		String actionclass=prop.getProperty(config1);
		Action action = (Action)Class.forName(prop.getProperty(config1)).newInstance();
		String result=action.execute(request, response);
		String nextpage=prop.getProperty(result);

		RequestDispatcher rt=request.getRequestDispatcher(nextpage);
		rt.forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
			}

}
