package com.vrv.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.vrv.service.GetOrgListService;

public class GetOrgListAction {
	GetOrgListService getorglistService;
	Integer OrgAction;
	Integer OrgId;
	
	public void execute() throws IOException, ParseException{
		OrgAction = this.getOrgAction();
		OrgId = this.getOrgId();
		JSONObject OrgInfo01 = null;
		JSONArray SubOrgInfo01 = null;
		if(OrgAction == 1)OrgInfo01 = getorglistService.OrganizeOrgInfo(OrgId,OrgAction,1);
		else if(OrgAction == 2)SubOrgInfo01 = getorglistService.SubOrganizeOrgInfo(OrgId,OrgAction,1);
		JSONArray OrgInfos = new JSONArray();
		
		/*String OrgInfo01 = "[{   "+
		"    \"id\": 1,     "+
		"    \"text\": \"Node 1\",     "+
		"    \"state\": \"closed\",     "+
		"    \"children\": [{     "+
		"        \"id\": 11,     "+
		"        \"text\": \"Node 11\"    "+
		"    },{     "+
		"        \"id\": 12,     "+
		"        \"text\": \"Node 12\"    "+
		"    }]     "+
		"},{     "+
		"    \"id\": 2,     "+
		"    \"text\": \"Node 2\",     "+
		"    \"state\": \"closed\"    "+
		"}]";*/
		
		ServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(OrgAction == 1){
			OrgInfos.add(OrgInfo01);
			System.out.println("OrganizeOrgInfo:"+OrgInfos.toString());
			out.println(OrgInfos.toString());
		}
		else if(OrgAction == 2){
			System.out.println("SubOrganizeOrgInfo:"+SubOrgInfo01.toString());
			out.println(SubOrgInfo01.toString());
		}
		
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

	public Integer getOrgId() {
		return OrgId;
	}

	public void setOrgId(Integer orgId) {
		OrgId = orgId;
	}

}
