package com.vrv.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletResponse;

import org.apache.struts2.ServletActionContext;

import com.vrv.service.LoginfoServer;

public class LoginfoAction {
	private LoginfoServer loginfoServer;
	
	private String queryType;  //查询类型 0历史日志 1 登陆日志 
	public void execute() throws IOException, ParseException{
		if(Integer.parseInt(queryType)==0){
		String Loginfo01 = loginfoServer.getLoginfos(10);
		ServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(Loginfo01);
		}
		if(Integer.parseInt(queryType)==1){
			String Loginfo01 = loginfoServer.FindTopN(10);
			ServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Loginfo01);
			}
		
		return;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public LoginfoServer getLoginfoServer() {
		return loginfoServer;
	}

	public void setLoginfoServer(LoginfoServer loginfoServer) {
		this.loginfoServer = loginfoServer;
	}
	
	
}
