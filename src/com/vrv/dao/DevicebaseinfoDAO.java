package com.vrv.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Devicebaseinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.vrv.dao.Devicebaseinfo
 * @author MyEclipse Persistence Tools
 */
public class DevicebaseinfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DevicebaseinfoDAO.class);
	// property constants
	public static final String DEV_ONLY_ID = "devOnlyId";
	public static final String USER_NAME = "userName";
	public static final String DEVICE_NAME = "deviceName";
	public static final String NUM_IPADDRESS = "numIpaddress";
	public static final String IPADDRES = "ipaddres";
	public static final String MAC_ADDRESS = "macAddress";
	public static final String DEPT_NAME = "deptName";
	public static final String OFFICE_NAME = "officeName";
	public static final String DEVICE_TYPE = "deviceType";
	public static final String REGISTERED = "registered";
	public static final String RUN_STATUS = "runStatus";
	public static final String AREA_ID = "areaId";
	public static final String CPU_TYPE = "cpuType";
	public static final String DISK_SIZE = "diskSize";
	public static final String ANTIVIRUS_SOFTWARE = "antivirusSoftware";
	public static final String NOT_INSTALLED_PATCH = "notInstalledPatch";
	public static final String OSTYPE = "ostype";
	public static final String DESCRIPTION = "description";
	public static final String DEV_TYPE_CODE = "devTypeCode";
	public static final String COMMUNICAT_IP = "communicatIp";
	public static final String EX_FIELD6 = "exField6";
	public static final String ASSET_ID = "assetId";
	public static final String SERVICE_PACK = "servicePack";
	public static final String CLIENT_VERSION = "clientVersion";
	public static final String LOCK_STATU = "lockStatu";
	public static final String PROTECT_STATU = "protectStatu";
	public static final String BLOCK_OR_NOT = "blockOrNot";
	public static final String AREA2 = "area2";
	public static final String SUB_OFFICE = "subOffice";
	public static final String ROOM = "room";
	public static final String TEL = "tel";
	public static final String EMAIL = "email";
	public static final String MEMORY_SIZE = "memorySize";
	public static final String CPUSIZE = "cpusize";
	public static final String LAST_ONLINE_TIME = "lastOnlineTime";
	public static final String ONLINE_MINUTES = "onlineMinutes";
	public static final String ONLINE_FORMED_TIME = "onlineFormedTime";
	public static final String SUB_HEALTH_STATU = "subHealthStatu";
	public static final String NO_BOOT_DAYS = "noBootDays";
	public static final String ONLINE_TIMES = "onlineTimes";
	public static final String DISK_SERIAL_NUMBER = "diskSerialNumber";
	public static final String DOMAIN_NAME = "domainName";
	public static final String LOGON_USER_NAME = "logonUserName";
	public static final String CLIENT_GROUP_ID = "clientGroupId";
	public static final String CLIENT_USER_ID = "clientUserId";
	public static final String NET_TYPE = "netType";

	protected void initDao() {
		// do nothing
	}

	public void save(Devicebaseinfo transientInstance) {
		log.debug("saving Devicebaseinfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Devicebaseinfo persistentInstance) {
		log.debug("deleting Devicebaseinfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Devicebaseinfo findById(Long id) {
		log.debug("getting Devicebaseinfo instance with id: " + id);
		try {
			Devicebaseinfo instance = (Devicebaseinfo) getHibernateTemplate()
					.get("com.vrv.dao.Devicebaseinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Devicebaseinfo instance) {
		log.debug("finding Devicebaseinfo instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Devicebaseinfo> getDeviceDomInfo(Integer isDom, Integer OrgId, String Condition){
		//HibernateSessionFactory.getSession().getTransaction().begin();
		System.out.println("getDeviceDomInfo is here:"+isDom);
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		if(isDom == 0){
			if(OrgId == 1)Condition = "";
			else Condition = "where u.clientGroupId "+Condition;
			sql = "SELECT new Devicebaseinfo(u.devOnlyId, u.userName, u.deviceName, "+
				"u.numIpaddress, u.ipaddres, u.macAddress, "+
				"u.deptName, u.officeName, u.deviceType, "+
				"u.registered, u.runStatus, u.areaId, u.cpuType, "+
				"u.diskSize, u.antivirusSoftware, "+
				"u.notInstalledPatch, u.ostype, u.unInstallTime, "+
				"u.description, u.devTypeCode, u.communicatIp, "+
				"u.exField6, u.assetId, u.servicePack, "+
				"u.setupTmos, u.clientVersion, u.lockStatu, "+
				"u.protectStatu, u.blockOrNot, u.area2, "+
				"u.subOffice, u.room, u.tel, u.email, "+
				"u.lastTime, u.memorySize, u.cpusize, "+
				"u.lastOnlineTime, u.onlineMinutes, "+
				"u.onlineFormedTime, u.subHealthStatu, "+
				"u.noBootDays, u.onlineTimes, u.diskSerialNumber, "+
				"u.domainName, u.logonUserName, u.clientGroupId, "+
				"u.clientUserId, u.netType) from com.vrv.dao.Devicebaseinfo as u " +Condition;
		}
		else if(isDom == 1){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT new Devicebaseinfo(u.devOnlyId, u.userName, u.deviceName, "+
					"u.numIpaddress, u.ipaddres, u.macAddress, "+
					"u.deptName, u.officeName, u.deviceType, "+
					"u.registered, u.runStatus, u.areaId, u.cpuType, "+
					"u.diskSize, u.antivirusSoftware, "+
					"u.notInstalledPatch, u.ostype, u.unInstallTime, "+
					"u.description, u.devTypeCode, u.communicatIp, "+
					"u.exField6, u.assetId, u.servicePack, "+
					"u.setupTmos, u.clientVersion, u.lockStatu, "+
					"u.protectStatu, u.blockOrNot, u.area2, "+
					"u.subOffice, u.room, u.tel, u.email, "+
					"u.lastTime, u.memorySize, u.cpusize, "+
					"u.lastOnlineTime, u.onlineMinutes, "+
					"u.onlineFormedTime, u.subHealthStatu, "+
					"u.noBootDays, u.onlineTimes, u.diskSerialNumber, "+
					"u.domainName, u.logonUserName, u.clientGroupId, "+
					"u.clientUserId, u.netType) from com.vrv.dao.Devicebaseinfo as u where (u.domainName!='' and u.domainName is not null and u.domainName!='--' and u.domainName!='NULL') "+Condition;
		}
		else if(isDom == 2){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT new Devicebaseinfo(u.devOnlyId, u.userName, u.deviceName, "+
					"u.numIpaddress, u.ipaddres, u.macAddress, "+
					"u.deptName, u.officeName, u.deviceType, "+
					"u.registered, u.runStatus, u.areaId, u.cpuType, "+
					"u.diskSize, u.antivirusSoftware, "+
					"u.notInstalledPatch, u.ostype, u.unInstallTime, "+
					"u.description, u.devTypeCode, u.communicatIp, "+
					"u.exField6, u.assetId, u.servicePack, "+
					"u.setupTmos, u.clientVersion, u.lockStatu, "+
					"u.protectStatu, u.blockOrNot, u.area2, "+
					"u.subOffice, u.room, u.tel, u.email, "+
					"u.lastTime, u.memorySize, u.cpusize, "+
					"u.lastOnlineTime, u.onlineMinutes, "+
					"u.onlineFormedTime, u.subHealthStatu, "+
					"u.noBootDays, u.onlineTimes, u.diskSerialNumber, "+
					"u.domainName, u.logonUserName, u.clientGroupId, "+
					"u.clientUserId, u.netType) from com.vrv.dao.Devicebaseinfo as u where (u.domainName = '' or u.domainName is null or u.domainName = '--' or u.domainName = 'NULL') "+Condition;
		}
		else if(isDom == 3){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT new Devicebaseinfo(u.devOnlyId, u.userName, u.deviceName, "+
					"u.numIpaddress, u.ipaddres, u.macAddress, "+
					"u.deptName, u.officeName, u.deviceType, "+
					"u.registered, u.runStatus, u.areaId, u.cpuType, "+
					"u.diskSize, u.antivirusSoftware, "+
					"u.notInstalledPatch, u.ostype, u.unInstallTime, "+
					"u.description, u.devTypeCode, u.communicatIp, "+
					"u.exField6, u.assetId, u.servicePack, "+
					"u.setupTmos, u.clientVersion, u.lockStatu, "+
					"u.protectStatu, u.blockOrNot, u.area2, "+
					"u.subOffice, u.room, u.tel, u.email, "+
					"u.lastTime, u.memorySize, u.cpusize, "+
					"u.lastOnlineTime, u.onlineMinutes, "+
					"u.onlineFormedTime, u.subHealthStatu, "+
					"u.noBootDays, u.onlineTimes, u.diskSerialNumber, "+
					"u.domainName, u.logonUserName, u.clientGroupId, "+
					"u.clientUserId, u.netType) from com.vrv.dao.Devicebaseinfo as u where u.registered !=0 "+Condition;
		}
		else if(isDom == 4){
			if(OrgId == 1)Condition = "";
			else Condition = " and "+Condition;
			sql = "SELECT new Devicebaseinfo(u.devOnlyId, u.userName, u.deviceName, "+
					"u.numIpaddress, u.ipaddres, u.macAddress, "+
					"u.deptName, u.officeName, u.deviceType, "+
					"u.registered, u.runStatus, u.areaId, u.cpuType, "+
					"u.diskSize, u.antivirusSoftware, "+
					"u.notInstalledPatch, u.ostype, u.unInstallTime, "+
					"u.description, u.devTypeCode, u.communicatIp, "+
					"u.exField6, u.assetId, u.servicePack, "+
					"u.setupTmos, u.clientVersion, u.lockStatu, "+
					"u.protectStatu, u.blockOrNot, u.area2, "+
					"u.subOffice, u.room, u.tel, u.email, "+
					"u.lastTime, u.memorySize, u.cpusize, "+
					"u.lastOnlineTime, u.onlineMinutes, "+
					"u.onlineFormedTime, u.subHealthStatu, "+
					"u.noBootDays, u.onlineTimes, u.diskSerialNumber, "+
					"u.domainName, u.logonUserName, u.clientGroupId, "+
					"u.clientUserId, u.netType) from com.vrv.dao.Devicebaseinfo as u where u.registered = 0 "+Condition;
		}
		else return null;
		System.out.println(sql);
		try{
			Query q = s.createQuery(sql);
			System.out.println("In getDeviceDomInfo:createQuery is crossed");
			List<Devicebaseinfo> DevicebaseinfoList= q.list();
			System.out.println("list.size:"+DevicebaseinfoList.size());
			return DevicebaseinfoList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	}

	public List<Object[]> getDevicesInfo(Integer isDom, Integer OrgId, String Columns, String Condition){
		//HibernateSessionFactory.getSession().getTransaction().begin();
		System.out.println("getDeviceDomInfo is here:"+isDom);
		if(Columns == null || Columns.equals("")) Columns = "*";
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		if(isDom == 0){
			if(OrgId == 1)Condition = "";
			else Condition = "where u.clientGroupId "+Condition;
			sql = "SELECT "+Columns+" from Devicebaseinfo as u " +Condition;
		}
		else if(isDom == 1){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where (u.domainName!='' and u.domainName is not null and u.domainName!='--' and u.domainName!='NULL') "+Condition;
		}
		else if(isDom == 2){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where (u.domainName = '' or u.domainName is null or u.domainName = '--' or u.domainName = 'NULL') "+Condition;
		}
		else if(isDom == 3){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where u.registered !=0 "+Condition;
		}
		else if(isDom == 4){
			if(OrgId == 1)Condition = "";
			else Condition = " and "+Condition;
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where u.registered = 0 "+Condition;
		}
		else if(isDom == 5){
			if(OrgId == 1)Condition = " where NetType = '内网'";
			else Condition = "where u.clientGroupId "+Condition + " and NetType = '内网'";
			sql = "SELECT "+Columns+" from Devicebaseinfo as u " +Condition;
		}
		else if(isDom == 6){
			if(OrgId == 1)Condition = " and NetType = '内网'";
			else Condition = " and u.clientGroupId "+Condition + " and NetType = '内网'";
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where (u.domainName!='' and u.domainName is not null and u.domainName!='--' and u.domainName!='NULL') "+Condition;
		}
		else if(isDom == 7){
			if(OrgId == 1)Condition = " and NetType = '内网'";
			else Condition = " and u.clientGroupId "+Condition + " and NetType = '内网'";
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where (u.domainName = '' or u.domainName is null or u.domainName = '--' or u.domainName = 'NULL') "+Condition;
		}
		else if(isDom == 8){
			if(OrgId == 1)Condition = " and NetType = '内网'";
			else Condition = " and u.clientGroupId "+Condition + " and NetType = '内网'";
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where u.registered !=0 "+Condition;
		}
		else if(isDom == 9){
			if(OrgId == 1)Condition = " and NetType = '内网'";
			else Condition = " and "+Condition + " and NetType = '内网'";
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where u.registered = 0 "+Condition;
		}
		else if(isDom == 10){
			if(OrgId == 1)Condition = " where NetType = '外网'";
			else Condition = "where u.clientGroupId "+Condition + " and NetType = '外网'";
			sql = "SELECT "+Columns+" from Devicebaseinfo as u " +Condition;
		}
		else if(isDom == 11){
			if(OrgId == 1)Condition = " and NetType = '外网'";
			else Condition = " and u.clientGroupId "+Condition + " and NetType = '外网'";
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where (u.domainName!='' and u.domainName is not null and u.domainName!='--' and u.domainName!='NULL') "+Condition;
		}
		else if(isDom == 12){
			if(OrgId == 1)Condition = " and NetType = '外网'";
			else Condition = " and u.clientGroupId "+Condition + " and NetType = '外网'";
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where (u.domainName = '' or u.domainName is null or u.domainName = '--' or u.domainName = 'NULL') "+Condition;
		}
		else if(isDom == 13){
			if(OrgId == 1)Condition = " and NetType = '外网'";
			else Condition = " and u.clientGroupId "+Condition + " and NetType = '外网'";
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where u.registered !=0 "+Condition;
		}
		else if(isDom == 14){
			if(OrgId == 1)Condition = " and NetType = '外网'";
			else Condition = " and "+Condition + " and NetType = '外网'";
			sql = "SELECT "+Columns+" from Devicebaseinfo as u where u.registered = 0 "+Condition;
		}
		else return null;
		System.out.println(sql);
		try{
			Query q = s.createSQLQuery(sql);
			System.out.println("In getDeviceDomInfo:createQuery is crossed");
			List<Object[]> DevicebaseinfoList= q.list();
			System.out.println("list.size:"+DevicebaseinfoList.size());
			return DevicebaseinfoList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	}
	public List<Object[]> getDevicesInfoByCond(Integer isDom, Integer OrgId, String Columns, String Condition, String Condition2){
		//HibernateSessionFactory.getSession().getTransaction().begin();
		System.out.println("getDeviceDomInfo is here:"+isDom);
		if(Columns == null || Columns.equals("")) Columns = "*";
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		if(isDom == 15){
			Condition2 = Condition2.trim();
			if(OrgId == 1 && Condition2 != "")Condition = " where "+Condition2;
			else if(OrgId == 1)Condition = "";
			else if(Condition2 != "") Condition = " where u.clientGroupId "+Condition+" and "+Condition2;
			else Condition = " where u.clientGroupId "+Condition+" and "+Condition2;
			sql = "SELECT "+Columns+" from Devicebaseinfo as u " +Condition;
		}
		else return null;
		System.out.println(sql);
		try{
			Query q = s.createSQLQuery(sql);
			System.out.println("In getDeviceDomInfo:createQuery is crossed");
			List<Object[]> DevicebaseinfoList= q.list();
			System.out.println("list.size:"+DevicebaseinfoList.size());
			return DevicebaseinfoList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	}
	public Long getDeviceNumByOrgId(Integer isDom, Integer OrgId, String Condition){//获取含机构查询结果
		//HibernateSessionFactory.getSession().getTransaction().begin();
		System.out.println("getDeviceDomInfo is here:"+isDom);
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		if(isDom == 0){
			if(OrgId == 1)Condition = "";
			else Condition = "where u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u "+Condition;
		}
		else if(isDom == 1){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where (u.domainName!='' and u.domainName is not null and u.domainName!='--' and u.domainName!='NULL') "+Condition;
		}
		else if(isDom == 2){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where (u.domainName='' or u.domainName is null or u.domainName='--' or u.domainName = 'NULL') "+Condition;
		}
		else if(isDom == 3){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where registered !=0 "+Condition;
		}
		else if(isDom == 4){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where registered = 0 "+Condition;
		}
		else if(isDom == 5){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where registered != 0 "+Condition;
		}
		else if(isDom == 6){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where registered != 0 and (u.domainName!='' and u.domainName is not null and u.domainName!='--' and u.domainName!='NULL') "+Condition;
		}
		else if(isDom == 7){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where registered != 0 "+Condition+" and netType = '内网'";
		}
		else if(isDom == 8){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where registered != 0 and (u.domainName!='' and u.domainName is not null and u.domainName!='--' and u.domainName!='NULL') "+Condition+" and netType = '内网'";
		}
		else if(isDom == 9){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where registered != 0 "+Condition+" and netType = '外网'";
		}
		else if(isDom == 10){
			if(OrgId == 1)Condition = "";
			else Condition = " and u.clientGroupId "+Condition;
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where registered != 0 and (u.domainName!='' and u.domainName is not null and u.domainName!='--' and u.domainName!='NULL') "+Condition+" and netType = '外网'";
		}
		else return null;
		System.out.println(sql);
		Query q = s.createQuery(sql);
		System.out.println("createQuery is crossed");
		List<Long> DeviceNumList= q.list();
		System.out.println("list.size:"+DeviceNumList.size());
	    return DeviceNumList.get(0);
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	public Long getDeviceNumForAll(Integer isDom){//
		//HibernateSessionFactory.getSession().getTransaction().begin();
		System.out.println("getDeviceDomInfo is here:"+isDom);
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		if(isDom == 0){
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u ";
		}
		else if(isDom == 1){
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where u.domainName!='' and u.domainName is not null and u.domainName!='--' and u.domainName!='NULL' ";
		}
		else if(isDom == 2){
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where u.domainName='' or u.domainName is null or u.domainName='--' or u.domainName = 'NULL'";
		}
		else if(isDom == 3){
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where registered !=0 ";
		}
		else if(isDom == 4){
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where registered = 0 ";
		}
		else if(isDom == 5){
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where netType = '内网' ";
		}
		else if(isDom == 6){
			sql = "SELECT count(*) from com.vrv.dao.Devicebaseinfo as u where netType = '外网' ";
		}
		else return null;
		System.out.println(sql);
		Query q = s.createQuery(sql);
		System.out.println("createQuery is crossed");
		List<Long> DeviceNumList= q.list();
		System.out.println("list.size:"+DeviceNumList.size());
	    return DeviceNumList.get(0);
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	}
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Devicebaseinfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Devicebaseinfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDevOnlyId(Object devOnlyId) {
		return findByProperty(DEV_ONLY_ID, devOnlyId);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByDeviceName(Object deviceName) {
		return findByProperty(DEVICE_NAME, deviceName);
	}

	public List findByNumIpaddress(Object numIpaddress) {
		return findByProperty(NUM_IPADDRESS, numIpaddress);
	}

	public List findByIpaddres(Object ipaddres) {
		return findByProperty(IPADDRES, ipaddres);
	}

	public List findByMacAddress(Object macAddress) {
		return findByProperty(MAC_ADDRESS, macAddress);
	}

	public List findByDeptName(Object deptName) {
		return findByProperty(DEPT_NAME, deptName);
	}

	public List findByOfficeName(Object officeName) {
		return findByProperty(OFFICE_NAME, officeName);
	}

	public List findByDeviceType(Object deviceType) {
		return findByProperty(DEVICE_TYPE, deviceType);
	}

	public List findByRegistered(Object registered) {
		return findByProperty(REGISTERED, registered);
	}

	public List findByRunStatus(Object runStatus) {
		return findByProperty(RUN_STATUS, runStatus);
	}

	public List findByAreaId(Object areaId) {
		return findByProperty(AREA_ID, areaId);
	}

	public List findByCpuType(Object cpuType) {
		return findByProperty(CPU_TYPE, cpuType);
	}

	public List findByDiskSize(Object diskSize) {
		return findByProperty(DISK_SIZE, diskSize);
	}

	public List findByAntivirusSoftware(Object antivirusSoftware) {
		return findByProperty(ANTIVIRUS_SOFTWARE, antivirusSoftware);
	}

	public List findByNotInstalledPatch(Object notInstalledPatch) {
		return findByProperty(NOT_INSTALLED_PATCH, notInstalledPatch);
	}

	public List findByOstype(Object ostype) {
		return findByProperty(OSTYPE, ostype);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByDevTypeCode(Object devTypeCode) {
		return findByProperty(DEV_TYPE_CODE, devTypeCode);
	}

	public List findByCommunicatIp(Object communicatIp) {
		return findByProperty(COMMUNICAT_IP, communicatIp);
	}

	public List findByExField6(Object exField6) {
		return findByProperty(EX_FIELD6, exField6);
	}

	public List findByAssetId(Object assetId) {
		return findByProperty(ASSET_ID, assetId);
	}

	public List findByServicePack(Object servicePack) {
		return findByProperty(SERVICE_PACK, servicePack);
	}

	public List findByClientVersion(Object clientVersion) {
		return findByProperty(CLIENT_VERSION, clientVersion);
	}

	public List findByLockStatu(Object lockStatu) {
		return findByProperty(LOCK_STATU, lockStatu);
	}

	public List findByProtectStatu(Object protectStatu) {
		return findByProperty(PROTECT_STATU, protectStatu);
	}

	public List findByBlockOrNot(Object blockOrNot) {
		return findByProperty(BLOCK_OR_NOT, blockOrNot);
	}

	public List findByArea2(Object area2) {
		return findByProperty(AREA2, area2);
	}

	public List findBySubOffice(Object subOffice) {
		return findByProperty(SUB_OFFICE, subOffice);
	}

	public List findByRoom(Object room) {
		return findByProperty(ROOM, room);
	}

	public List findByTel(Object tel) {
		return findByProperty(TEL, tel);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByMemorySize(Object memorySize) {
		return findByProperty(MEMORY_SIZE, memorySize);
	}

	public List findByCpusize(Object cpusize) {
		return findByProperty(CPUSIZE, cpusize);
	}

	public List findByLastOnlineTime(Object lastOnlineTime) {
		return findByProperty(LAST_ONLINE_TIME, lastOnlineTime);
	}

	public List findByOnlineMinutes(Object onlineMinutes) {
		return findByProperty(ONLINE_MINUTES, onlineMinutes);
	}

	public List findByOnlineFormedTime(Object onlineFormedTime) {
		return findByProperty(ONLINE_FORMED_TIME, onlineFormedTime);
	}

	public List findBySubHealthStatu(Object subHealthStatu) {
		return findByProperty(SUB_HEALTH_STATU, subHealthStatu);
	}

	public List findByNoBootDays(Object noBootDays) {
		return findByProperty(NO_BOOT_DAYS, noBootDays);
	}

	public List findByOnlineTimes(Object onlineTimes) {
		return findByProperty(ONLINE_TIMES, onlineTimes);
	}

	public List findByDiskSerialNumber(Object diskSerialNumber) {
		return findByProperty(DISK_SERIAL_NUMBER, diskSerialNumber);
	}

	public List findByDomainName(Object domainName) {
		return findByProperty(DOMAIN_NAME, domainName);
	}

	public List findByLogonUserName(Object logonUserName) {
		return findByProperty(LOGON_USER_NAME, logonUserName);
	}

	public List findByClientGroupId(Object clientGroupId) {
		return findByProperty(CLIENT_GROUP_ID, clientGroupId);
	}

	public List findByClientUserId(Object clientUserId) {
		return findByProperty(CLIENT_USER_ID, clientUserId);
	}

	public List findByNetType(Object netType) {
		return findByProperty(NET_TYPE, netType);
	}

	public List findAll() {
		log.debug("finding all Devicebaseinfo instances");
		try {
			String queryString = "from Devicebaseinfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Devicebaseinfo merge(Devicebaseinfo detachedInstance) {
		log.debug("merging Devicebaseinfo instance");
		try {
			Devicebaseinfo result = (Devicebaseinfo) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Devicebaseinfo instance) {
		log.debug("attaching dirty Devicebaseinfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Devicebaseinfo instance) {
		log.debug("attaching clean Devicebaseinfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DevicebaseinfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DevicebaseinfoDAO) ctx.getBean("DevicebaseinfoDAO");
	}
}