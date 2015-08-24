package com.vrv.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;



import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.opensymphony.xwork2.ActionContext;
import com.vrv.service.DevicesStatisService;
public class DevicesStatisAction {
	private DevicesStatisService devicesStatisService;
	private Integer NetType;
	private Integer strOfficeName;
	private String officeID;
	
	public void execute() throws IOException{
		System.out.println("I am coming");
		JSONObject contents= new JSONObject();
		Map<String, Object> contentsMap = new TreeMap<String,Object>();
		System.out.println("I am coming2");
		NetType = this.getNetType();
		strOfficeName = this.getStrOfficeName();
		officeID = this.getOfficeID();
		System.out.println("NetType:"+NetType+", strOfficeName:" + strOfficeName+", officeID:"+officeID);
		List<Object[]> a = devicesStatisService.getSubOrgDeviceNumByCondition(NetType, Integer.parseInt(officeID));
		
		int SumNum = a.size();
		JSONArray Content = new JSONArray();
		JSONObject Column = new JSONObject();
		for(int i=0; i<SumNum; i++){
			Column.put("name", a.get(i)[0]);
			Column.put("num1", a.get(i)[1]);
			Column.put("num2", a.get(i)[2]);
			Content.add(i,Column);
		}
    	System.out.println("Content:"+Content.toString());
		//ActionContext.getContext().getSession().put("back", Content.toString());
    	ServletResponse response =  ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		out.println(Content.toString());
		
		return;
	}

	public Integer getNetType() {
		return NetType;
	}

	public void setNetType(Integer netType) {
		NetType = netType;
	}

	public Integer getStrOfficeName() {
		return strOfficeName;
	}

	public void setStrOfficeName(Integer strOfficeName) {
		this.strOfficeName = strOfficeName;
	}

	public String getOfficeID() {
		return officeID;
	}

	public void setOfficeID(String officeID) {
		this.officeID = officeID;
	}

	public DevicesStatisService getDevicesStatisService() {
		return devicesStatisService;
	}

	public void setDevicesStatisService(DevicesStatisService devicesStatisService) {
		this.devicesStatisService = devicesStatisService;
	}

}
