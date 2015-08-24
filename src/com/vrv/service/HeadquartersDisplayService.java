package com.vrv.service;

import java.math.BigInteger;
import java.util.List;

import com.vrv.dao.Devicebaseinfo;
import com.vrv.dao.DevicebaseinfoDAO;
import com.vrv.dao.DevicebaseinfohistoryDAO;

public class HeadquartersDisplayService {
   private DevicebaseinfoDAO devicebaseinfoDao;
   private DevicebaseinfohistoryDAO devicebaseinfohistoryDao;
   
   public DevicebaseinfoDAO getDevicebaseinfoDao() {
	return devicebaseinfoDao;
}
 
public void setDevicebaseinfoDao(DevicebaseinfoDAO devicebaseinfoDao) {
	this.devicebaseinfoDao = devicebaseinfoDao;
}

public Devicebaseinfo getDeviceBaseInfo(Long Id) {
	Devicebaseinfo d = this.devicebaseinfoDao.findById(Id);
	return d;
}

public DevicebaseinfohistoryDAO getDevicebaseinfohistoryDao() {
	return devicebaseinfohistoryDao;
}

public void setDevicebaseinfohistoryDao(
		DevicebaseinfohistoryDAO devicebaseinfohistoryDao) {
	this.devicebaseinfohistoryDao = devicebaseinfohistoryDao;
}

public Long getSumOfDevice() {
	System.out.println("getSumOfDevice is here!");
	return this.devicebaseinfoDao.getDeviceNumForAll(0);
}

public Long getUnregisteredNumOfDevice() { 
	System.out.println("getUnregisteredNumOfDevice is here!");
	return this.devicebaseinfoDao.getDeviceNumForAll(4);
}

public BigInteger getActiveNumOfDevice() { 
	System.out.println("getInactiveNumOfDevice is here!");
	return this.devicebaseinfohistoryDao.getActiveDeviceNumForAll();
}
public Long getOutdomainNumOfDevice() { 
	System.out.println("getOutdomainNumOfDevice is here!");
	return this.devicebaseinfoDao.getDeviceNumForAll(2);
}
public Long getIntranetNumOfDevice() { 
	System.out.println("getIntranetNumOfDevice is here!");
	return this.devicebaseinfoDao.getDeviceNumForAll(5);
}
public Long getInternetNumOfDevice() { 
	System.out.println("getInternetNumOfDevice is here!");
	return this.devicebaseinfoDao.getDeviceNumForAll(6);
}
}
