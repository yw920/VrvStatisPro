package com.vrv.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.pro.bxy.autorun.AutoRunLoadDataTask2;


public class DataregetAction {
	private AutoRunLoadDataTask2 autoRunLoadDataTask2;
	public void execute() throws IOException, ParseException{
        System.out.println("In AutoRunLoadDataTask2");
        try{
			ServletRequest request = ServletActionContext.getRequest();
			ServletContext context = request.getServletContext();
			//autoRunLoadDataTask2 = new AutoRunLoadDataTask2(context);
			ServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("0");
        }
        catch(Exception e){
        	ServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(e.getMessage());
        	e.printStackTrace();
        }
		return;
	}
	public AutoRunLoadDataTask2 getAutoRunLoadDataTask2() {
		return autoRunLoadDataTask2;
	}
	public void setAutoRunLoadDataTask2(AutoRunLoadDataTask2 autoRunLoadDataTask2) {
		this.autoRunLoadDataTask2 = autoRunLoadDataTask2;
	}

	
	
}
