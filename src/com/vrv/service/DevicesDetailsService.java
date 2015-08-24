package com.vrv.service;

import java.util.List;

import com.vrv.dao.ClientgroupDAO;
import com.vrv.dao.Devicebaseinfo;
import com.vrv.dao.DevicebaseinfoDAO;

public class DevicesDetailsService {
	private DevicebaseinfoDAO devicebaseinfoDao;
	private ClientgroupDAO clientGroupDAO;

	public Devicebaseinfo getDeviceBaseInfo(Long Id) {
		Devicebaseinfo d = this.devicebaseinfoDao.findById(Id);
		return d;
	}

	public List<Object[]> getDeviceInfo(int iTem, int OrgId, String columns) {
		System.out.println("getDeviceInfo is here!");
		//获取该机构以及该机构下的所有资机构
		String strOrganizeSubOrgList = clientGroupDAO.OrganizeSubOrgList(OrgId);
		String Condition = "";
		if(strOrganizeSubOrgList.isEmpty() || strOrganizeSubOrgList == null){
			return null;
		}else{
			Condition = "in ("+strOrganizeSubOrgList.substring(0,strOrganizeSubOrgList.length()-1)+")";
		}
		//System.out.println("Condition:"+Condition);
		List<Object[]> p = this.devicebaseinfoDao.getDevicesInfo(iTem, OrgId, columns, Condition);
		return p;
	}

	public List<Object[]> getDeviceInfoByCond(int iTem, int OrgId, String columns, String Condition2) {
		System.out.println("getDeviceInfoByCond is here!");
		//获取该机构以及该机构下的所有资机构
		String strOrganizeSubOrgList = clientGroupDAO.OrganizeSubOrgList(OrgId);
		String Condition = "";
		if(strOrganizeSubOrgList.isEmpty() || strOrganizeSubOrgList == null){
			return null;
		}else{
			Condition = "in ("+strOrganizeSubOrgList.substring(0,strOrganizeSubOrgList.length()-1)+")";
		}
		System.out.println("Condition:"+Condition2);
		List<Object[]> p = this.devicebaseinfoDao.getDevicesInfoByCond(iTem, OrgId, columns, Condition, Condition2);
		return p;
	}

	public DevicebaseinfoDAO getDevicebaseinfoDao() {
		return devicebaseinfoDao;
	}

	public void setDevicebaseinfoDao(DevicebaseinfoDAO devicebaseinfoDao) {
		this.devicebaseinfoDao = devicebaseinfoDao;
	}

	public ClientgroupDAO getClientGroupDAO() {
		return clientGroupDAO;
	}

	public void setClientGroupDAO(ClientgroupDAO clientGroupDAO) {
		this.clientGroupDAO = clientGroupDAO;
	}

}
