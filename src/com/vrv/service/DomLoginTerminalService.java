package com.vrv.service;

import java.util.ArrayList;
import java.util.List;




import com.vrv.dao.ClientgroupDAO;
import com.vrv.dao.Devicebaseinfo;
import com.vrv.dao.DevicebaseinfoDAO;

public class DomLoginTerminalService {
	private DevicebaseinfoDAO devicebaseinfoDao;
	private ClientgroupDAO clientGroupDAO;

	public Devicebaseinfo getDeviceBaseInfo(Long Id) {
		Devicebaseinfo d = this.devicebaseinfoDao.findById(Id);
		return d;
	}

	public List<Devicebaseinfo> getDeviceDomInfo(int isDom, int OrgId) {
		System.out.println("getDeviceDomInfo is here!");
		String strOrganizeSubOrgList = clientGroupDAO.OrganizeSubOrgList(OrgId);
		String Condition = "in ("+strOrganizeSubOrgList.substring(0,strOrganizeSubOrgList.length()-1)+")";
		//System.out.println("Condition:"+Condition);
		List<Devicebaseinfo> p = this.devicebaseinfoDao.getDeviceDomInfo(isDom, OrgId, Condition);
		return p;
	}

	public List<Object[]> getSubOrgDeviceNum(Integer OrgId) {
		System.out.println("getSubOrgDeviceNum is here!");
		List<Object[]> SubOrgDeviceNum = new ArrayList();
		List<Object[]> OrgInfoList = this.clientGroupDAO.getOrglists(OrgId);
		System.out.println("OrgInfoList.size:"+OrgInfoList.size());
		for(int i=0;i<OrgInfoList.size();i++){
			String strOrganizeSubOrgList = this.clientGroupDAO.OrganizeSubOrgList((Integer)(OrgInfoList.get(i)[0]));
			String Condition = "in ("+strOrganizeSubOrgList.substring(0,strOrganizeSubOrgList.length()-1)+")";
			//System.out.println("Condition:"+Condition);
			Long DeviceDomNum = this.devicebaseinfoDao.getDeviceNumByOrgId(2, (Integer)(OrgInfoList.get(i)[0]), Condition);
			Object[] SubOrgInfo = {(String)(OrgInfoList.get(i)[1]), DeviceDomNum};
			System.out.println((String)SubOrgInfo[0]+(Long)SubOrgInfo[1]);
			SubOrgDeviceNum.add(SubOrgInfo);
			System.out.println("next:"+i);
		}
		
		return SubOrgDeviceNum;
	}
	
	public Long getDeviceDomNum(int isDom, Integer OrgId) {
		System.out.println("getDeviceDomNum is here!");
		String strOrganizeSubOrgList = this.clientGroupDAO.OrganizeSubOrgList(OrgId);
		String Condition = "in ("+strOrganizeSubOrgList.substring(0,strOrganizeSubOrgList.length()-1)+")";
		//System.out.println("Condition:"+Condition);
		Long p = this.devicebaseinfoDao.getDeviceNumByOrgId(isDom, OrgId, Condition);
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
