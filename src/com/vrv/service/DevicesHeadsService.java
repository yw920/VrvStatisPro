package com.vrv.service;

import java.util.List;

import com.vrv.dao.Deviceheads;
import com.vrv.dao.DeviceheadsDAO;

public class DevicesHeadsService {
	private DeviceheadsDAO deviceHeadsDao;
	
	@SuppressWarnings("unchecked")
	public String getDeviceHeadInfo() {
		System.out.println("getDeviceHeadInfo is here!");
		//获取该机构以及该机构下的所有资机构
		List<Deviceheads> a = deviceHeadsDao.findAll();
		String returns = "<tr>";
		int j = 0;
		for(int i = 0; i < a.size();i++){
			String Sign01 = "";
			if((boolean)a.get(i).getChecked()){
				Sign01 = "checked=\"checked\"";
			}
			String returns01 = "<td><i class=\"fa\">"+a.get(i).getName()+"</i></td><td><input type=\"checkbox\" id=\"selected"+a.get(i).getId()+"\" "+Sign01+" /></td>";
			if(j < 4){
				returns += returns01;
				j ++;
			}
			else{
				returns += returns01 + "</tr>";
				j = 0;
			}
		}
		return returns;
	}
	public String setTabHeadInfo() {
		System.out.println("setTabHeadInfo is here!");
		//获取该机构以及该机构下的所有资机构
		List<Deviceheads> deviceHeadList = deviceHeadsDao.findSelected();
		String returns = "";
		for(int i = 0; i < deviceHeadList.size();i++){
			String returns01 = "<th>"+deviceHeadList.get(i).getName()+"</th>";
			returns += returns01;
		}
		return returns;
	}
	public String changeTabHeadInfo(String Condition) {
		System.out.println("changeTabHeadInfo is here!");
		//获取该机构以及该机构下的所有资机构
		deviceHeadsDao.changeChecked(Condition);
		return "修改成功！";
	}
	public DeviceheadsDAO getDeviceHeadsDao() {
		return deviceHeadsDao;
	}

	public void setDeviceHeadsDao(DeviceheadsDAO deviceHeadsDao) {
		this.deviceHeadsDao = deviceHeadsDao;
	}
	public List<Deviceheads> getDeviceHeadList() {
		System.out.println("in getDeviceHeadList!");
		return deviceHeadsDao.findSelected();
	}

}
