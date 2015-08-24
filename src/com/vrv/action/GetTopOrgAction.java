package com.vrv.action;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

import org.apache.struts2.ServletActionContext;

import com.vrv.service.*;

public class GetTopOrgAction {
	
	private GetOrgListService getOrgListService;
	
	public void execute(){
		
		String result = getOrgListService.topOrganizeOrg();
		ServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return;
		}
	
		return;
		
		
		
	}

	public GetOrgListService getGetOrgListService() {
		return getOrgListService;
	}

	public void setGetOrgListService(GetOrgListService getOrgListService) {
		this.getOrgListService = getOrgListService;
	}

}
