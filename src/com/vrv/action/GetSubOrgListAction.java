package com.vrv.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.vrv.service.GetOrgListService;

public class GetSubOrgListAction {
	GetOrgListService getorglistService;
	Integer OrgAction;
	int SubOrgId;
	
	public void execute() throws IOException, ParseException{
		OrgAction = this.getOrgAction();
		SubOrgId = this.getSubOrgId();
		//System.out.println(contents);
		JSONArray OrgInfo01 = getorglistService.SubOrganizeOrgInfo(1,OrgAction,1);
		//System.out.println(OrgInfo01);
		ServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(OrgInfo01.toString());
		return;
	}

	public GetOrgListService getGetorglistService() {
		return getorglistService;
	}

	public void setGetorglistService(GetOrgListService getorglistService) {
		this.getorglistService = getorglistService;
	}

	public Integer getOrgAction() {
		return OrgAction;
	}

	public void setOrgAction(Integer orgAction) {
		OrgAction = orgAction;
	}

	public int getSubOrgId() {
		return SubOrgId;
	}

	public void setSubOrgId(int subOrgId) {
		SubOrgId = subOrgId;
	}

}
