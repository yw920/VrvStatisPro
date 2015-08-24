package com.vrv.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;



import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.vrv.dao.Deviceheads;
import com.vrv.service.DevicesDetailsService;
import com.vrv.service.DevicesHeadsService;

public class DevicesDetailsAction {
	private DevicesDetailsService devicesDetails;
	private DevicesHeadsService devicesHeadsService;
	private Integer Item;
	private String OfficeName;
	private Integer OrgId;
	private String Condition2;
	
	@SuppressWarnings("deprecation")
	public void execute() throws IOException{
		System.out.println("In DevicesDetailsAction!");
		JSONObject contents= new JSONObject();
		System.out.println("I am coming2");
		Item = this.getItem();
		OfficeName = this.getOfficeName();
		OfficeName = URLDecoder.decode(OfficeName,"utf-8");
		OrgId = this.getOrgId();
		if(Item == 15)Condition2 = this.getCondition2();
		
		System.out.println("Item:"+Item+", OfficeName:" + OfficeName+", OrgId:"+OrgId);
		List<Deviceheads> deviceHeadList = devicesHeadsService.getDeviceHeadList();
		String Columns = "";
		System.out.println("HeadListNum:"+deviceHeadList.size());
		int HeadListNum = deviceHeadList.size();
		String Column = "";
		for(int i=0;i<HeadListNum;i++){
			Column = (String)deviceHeadList.get(i).getEnName();
			if(i < HeadListNum-1)Columns += Column + ",";
			else Columns += Column;
		}

		List<Object[]> DeviceInfoList;
		if(Item == 15){
			System.out.println("Condition2:"+Condition2);
			Condition2 = URLDecoder.decode(Condition2,"utf-8");
			DeviceInfoList = this.devicesDetails.getDeviceInfoByCond(Item,OrgId,Columns,Condition2);
		}
		else DeviceInfoList = this.devicesDetails.getDeviceInfo(Item,OrgId,Columns);
		int SumNum = DeviceInfoList.size();
		JSONArray Content = new JSONArray();
		System.out.println("In DevicesDetailsAction: start");
		
    	for(int i=0; i<SumNum; i++){
    		JSONArray ColumnArr = new JSONArray();
    		for(int j=0; j<HeadListNum; j++){
        		//if(DeviceInfoList.get(i)[j] != null)System.out.println("Column:"+DeviceInfoList.get(i)[j].getClass());
    			if(DeviceInfoList.get(i)[j] instanceof java.sql.Timestamp)ColumnArr.add(((Date)DeviceInfoList.get(i)[j]).toLocaleString());
    			else ColumnArr.add(DeviceInfoList.get(i)[j]);
    		}
    		//System.out.println("ColumnArr:"+ColumnArr);
    		Content.add(i,ColumnArr);
        }
    	System.out.println("In DevicesDetailsAction: finish");
    	contents.put("code",0);
    	contents.put("IsLastPage",1);
    	contents.put("return",Content);
    	System.out.println(contents);
		ActionContext.getContext().getSession().put("back", contents.toString());
    	ServletResponse response =  ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		out.println(contents.toString());
		
		return;
	}
	
	public Integer getItem() {
		return Item;
	}

	public void setItem(Integer item) {
		Item = item;
	}

	public String getOfficeName() {
		return OfficeName;
	}

	public void setOfficeName(String officeName) {
		OfficeName = officeName;
	}

	public Integer getOrgId() {
		return OrgId;
	}

	public void setOrgId(Integer orgId) {
		OrgId = orgId;
	}

	public DevicesDetailsService getDevicesDetails() {
		return devicesDetails;
	}

	public void setDevicesDetails(DevicesDetailsService devicesDetails) {
		this.devicesDetails = devicesDetails;
	}

	public DevicesHeadsService getDevicesHeadsService() {
		return devicesHeadsService;
	}

	public void setDevicesHeadsService(DevicesHeadsService devicesHeadsService) {
		this.devicesHeadsService = devicesHeadsService;
	}

	public String getCondition2() {
		return Condition2;
	}

	public void setCondition2(String condition2) {
		Condition2 = condition2;
	}
	
	
}
