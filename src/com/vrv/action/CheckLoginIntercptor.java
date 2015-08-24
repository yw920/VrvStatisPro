package com.vrv.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;




public class CheckLoginIntercptor extends AbstractInterceptor  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		
	
		
		ActionContext ctx = arg0.getInvocationContext();
		
		Map session = ctx.getSession();
		String user = (String)session.get("username");
		System.out.println("________________session:"+user);
		if(user!=null){
		
			return arg0.invoke();
		}
		
	
//		HttpServletRequest request= ServletActionContext.getRequest();
//		
//		
////		HttpServletResponse response= ServletActionContext.getResponse();
////	
////		response.sendRedirect("/index.html");
		
		
		return Action.NONE;
		
	}


	
	
	
}
