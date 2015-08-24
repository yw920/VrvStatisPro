package com.vrv.action;

import java.io.IOException;
import java.io.PrintWriter;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;


import java.net.URLDecoder;

import javax.servlet.ServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.vrv.service.DevicesHeadsService;

public class DevicesHeadsAction {
	private DevicesHeadsService devicesHeadsService;
	private Integer action;
	private String selecteds;
	
	public void execute() throws IOException{
		System.out.println("In DevicesHeadsAction!");
		action = this.getAction();
		System.out.println("action:"+action);
		String returns = "";
		if(action == 0){
			returns = devicesHeadsService.getDeviceHeadInfo();
		}
		else if(action == 1){
			returns = devicesHeadsService.setTabHeadInfo();
		}
		else if(action == 2){
			selecteds = this.getSelecteds();
			selecteds = URLDecoder.decode(selecteds, "utf-8");
			System.out.println("selecteds:"+selecteds);
			String Condition = "in (" +selecteds+ ")";
			returns = devicesHeadsService.changeTabHeadInfo(Condition);
		}
		else returns = null;
		ActionContext.getContext().getSession().put("back", returns);
    	ServletResponse response =  ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		out.println(returns);
		
		return;
	}

	public DevicesHeadsService getDevicesHeadsService() {
		return devicesHeadsService;
	}

	public void setDevicesHeadsService(DevicesHeadsService devicesHeadsService) {
		this.devicesHeadsService = devicesHeadsService;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getSelecteds() {
		return selecteds;
	}

	public void setSelecteds(String selecteds) {
		this.selecteds = selecteds;
	}

}
