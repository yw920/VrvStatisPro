package com.vrv.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserExitAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = ActionContext.getContext().getSession();
		map.clear();
		
		return SUCCESS;
	}
	

}
