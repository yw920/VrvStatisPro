package com.vrv.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vrv.dao.ClientgroupDAO;
import com.vrv.dao.Devicebaseinfo;
import com.vrv.dao.Devicebaseinfohistory;
import com.vrv.dao.DevicebaseinfohistoryDAO;
import com.vrv.dao.DevicebaseinfoDAO;;

public class ActiveStateService {
	private DevicebaseinfoDAO devicebaseinfoDAO;
	private DevicebaseinfohistoryDAO devicebaseinfohistoryDao;
	private ClientgroupDAO clientGroupDAO;

	public Devicebaseinfohistory getDeviceBaseInfohistory(Integer Id) {
		Devicebaseinfohistory d = this.devicebaseinfohistoryDao.findById(Id);
		return d;
	}

	public List<Object[]> getDeviceActiveStateHistory(String strStartTime, String strEndTime, int OrgId) {
		System.out.println("getDeviceActiveStateHistory is here!");
		String strOrganizeSubOrgList = this.clientGroupDAO.OrganizeSubOrgList(OrgId);
		String Condition = "in ("+strOrganizeSubOrgList.substring(0,strOrganizeSubOrgList.length()-1)+")";
		//System.out.println("Condition:"+Condition);
		List<Object[]> bq = this.devicebaseinfohistoryDao.getActiveDeviceBaseInfo(strStartTime, strEndTime, OrgId, Condition);
		
		List<Object[]> listDeviceCountInfo = new ArrayList<>();
		int iCountSize = bq.size();
		System.out.println(iCountSize);
		for(int i = 0; i< iCountSize; i++){
			Object[] deviceCountInfo = bq.get(i);
			Integer iDevId = (Integer)deviceCountInfo[0];
			System.out.println("iDevId: " + iDevId);
			BigInteger iActiveCount = (BigInteger)deviceCountInfo[1];
			System.out.println("iDevIdtoLang: " + Long.parseLong(iDevId.toString()));
			Devicebaseinfo Device = this.devicebaseinfoDAO.findById(Long.parseLong(iDevId.toString()));
			Object[] deviceCountInfoObj = new Object[]{iDevId, iActiveCount, Device};
			listDeviceCountInfo.add(deviceCountInfoObj);
		}
		return listDeviceCountInfo;
	}
	

	public Devicebaseinfohistory getdevicebaseinfohistory(Calendar calStartTime, Calendar calEndTime, int iDevID) {
		Devicebaseinfohistory devh = new DevicebaseinfohistoryDAO().findById(iDevID);
		return devh;
	}	

	public List<String> getTatgetDeviceActiveState(String strStartTime, String strEndTime, Integer iDevID) {
		System.out.println("getDeviceActiveState is here!");
		List<String> liActSta = this.devicebaseinfohistoryDao.getTargetDeviceActiveStateByID(strStartTime, strEndTime, iDevID);

	return liActSta;
	}

	public List<Object[]> getliCountAndLastTimeByOfficeId(String strStartTime, String strEndTime, Integer strOfficeId) {
		System.out.println("getDeviceActiveState is here!");
		List<Object[]> liCountAndLastTime = this.devicebaseinfohistoryDao.getliCountAndLastTimeByOfficeId(strStartTime, strEndTime, strOfficeId);
		return liCountAndLastTime;
	}

	public DevicebaseinfoDAO getDevicebaseinfoDAO() {
		return devicebaseinfoDAO;
	}

	public void setDevicebaseinfoDAO(DevicebaseinfoDAO devicebaseinfoDAO) {
		this.devicebaseinfoDAO = devicebaseinfoDAO;
	}

	public DevicebaseinfohistoryDAO getDevicebaseinfohistoryDao() {
		return devicebaseinfohistoryDao;
	}

	public void setDevicebaseinfohistoryDao(
			DevicebaseinfohistoryDAO devicebaseinfohistoryDao) {
		this.devicebaseinfohistoryDao = devicebaseinfohistoryDao;
	}	
	
	public ClientgroupDAO getClientGroupDAO() {
		return clientGroupDAO;
	}

	public void setClientGroupDAO(ClientgroupDAO clientGroupDAO) {
		this.clientGroupDAO = clientGroupDAO;
	}

	//wengjiang 
	public List<Object[]> getliCountAndLastTimeByDeviceId(String strStartTime, String strEndTime, String DevID) {
		System.out.println("getliCountAndLastTimeByDeviceId  is here!");
		List<Object[]> liCountAndLastTime = this.devicebaseinfohistoryDao.getliCountAndLastTimeByDeviceId(strStartTime, strEndTime,DevID);
		return liCountAndLastTime;
	}
	

		public List<Object[]> getDeviceActiveStateHistoryOfOneOrgAndItsSubOrg(String strStartTime, String strEndTime, String strOfficeName,int officeID) {
			System.out.println("getDeviceActiveState is here!");
			
			List<Object[]> subOrgList = this.devicebaseinfohistoryDao.getOrglists(officeID);
			  
			
			
			
			
			Iterator<Object[]> iterator = subOrgList.iterator();// 
	 
			
			List<Object[]> listDeviceCountInfo = new ArrayList<>(); 
			while (iterator.hasNext()) {// 
				Object[] obj = iterator.next();  
				System.out.println("  "+obj[1]+"  getDeviceActiveStateHistoryOfOneOrgAndItsSubOrg");
				String strOrganizeSubOrgList = this.clientGroupDAO.OrganizeSubOrgList((int)obj[0]);
				String Condition = "in ("+strOrganizeSubOrgList.substring(0,strOrganizeSubOrgList.length()-1)+")";
				//System.out.println("Condition:"+Condition);
				List<Object[]> bq = this.devicebaseinfohistoryDao.getActiveDeviceBaseInfo(strStartTime, strEndTime, (int)obj[0], Condition); 
				int iCountSize = bq.size();
				System.out.println(iCountSize);
				for(int i = 0; i< iCountSize; i++){
					Object[] deviceCountInfo = bq.get(i);
					Integer iDevId = (Integer)deviceCountInfo[0];
					System.out.println("iDevId: " + iDevId);
					BigInteger iActiveCount = (BigInteger)deviceCountInfo[1];
					System.out.println("iDevIdtoLang: " + Long.parseLong(iDevId.toString()));
					Devicebaseinfo Device = this.devicebaseinfoDAO.findById(Long.parseLong(iDevId.toString()));
					Object[] deviceCountInfoObj = new Object[]{iDevId, iActiveCount, Device};
					listDeviceCountInfo.add(deviceCountInfoObj);
				}  
			} 
			return listDeviceCountInfo;
		}

		
		//D返回 一个设备的所有信息  
		public List<Object[]> getActiveDeviceBaseInfoByOnlyId(String deviceOnlyId){
			return this.devicebaseinfohistoryDao.getActiveDeviceBaseInfoByOnlyId(deviceOnlyId);
		}
		
		public List<Object[]> getDeviceInfoById( String strDeviceId) {
			System.out.println("getDeviceInfoById is here!");
			//List<Object[]> bq = this.devicebaseinfohistoryDao.getActiveDeviceBaseInfo(strStartTime, strEndTime, strOfficeName);
			
			List<Object[]> listDeviceCountInfo = new ArrayList<>();
			int iCountSize =1; //bq.size();
			//System.out.println(iCountSize);
			for(int i = 0; i< iCountSize; i++){
				//Object[] deviceCountInfo = bq.get(i);
				Integer iDevId =(int) Long.parseLong(strDeviceId);
				System.out.println("iDevId: " + iDevId);
				int iActiveCount = 1;//(BigInteger)deviceCountInfo[1];
				
				System.out.println("iDevIdtoLang: " + Long.parseLong(iDevId.toString()));
				Devicebaseinfo Device = this.devicebaseinfoDAO.findById(Long.parseLong(strDeviceId));//iDevId.toString()));
				Object[] deviceCountInfoObj = new Object[]{iDevId, iActiveCount, Device};
				listDeviceCountInfo.add(deviceCountInfoObj);
			}
			return listDeviceCountInfo;
		}
	//wengjiang end 
	
}
