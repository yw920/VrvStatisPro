package com.vrv.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Devicebaseinfohistory entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.vrv.dao.Devicebaseinfohistory
 * @author MyEclipse Persistence Tools
 */
public class DevicebaseinfohistoryDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DevicebaseinfohistoryDAO.class);
	// property constants
	public static final String DEVICE_ID = "deviceId";
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

	public void save(Devicebaseinfohistory transientInstance) {
		log.debug("saving Devicebaseinfohistory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Devicebaseinfohistory persistentInstance) {
		log.debug("deleting Devicebaseinfohistory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Devicebaseinfohistory findById(java.lang.Integer id) {
		log.debug("getting Devicebaseinfohistory instance with id: " + id);
		try {
			Devicebaseinfohistory instance = (Devicebaseinfohistory) getHibernateTemplate()
					.get("com.vrv.dao.Devicebaseinfohistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Devicebaseinfohistory instance) {
		log.debug("finding Devicebaseinfohistory instance by example");
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

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Devicebaseinfohistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Devicebaseinfohistory as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public BigInteger getActiveDeviceNumForAll() {
		// HibernateSessionFactory.getSession().getTransaction().begin();
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		List<BigInteger> bq = new ArrayList();
		sql = "select count(*) from (SELECT u.deviceId AS n FROM devicebaseinfohistory AS u WHERE u.lasttime BETWEEN DATE_SUB(NOW(), INTERVAL 1 MONTH) AND NOW() GROUP BY 1) as tb";
		System.out.println(sql);
		Query q = s.createSQLQuery(sql);
		System.out.println("createQuery is crossed");

		bq = (List<BigInteger>) q.list();
		System.out.println("list.size:" + bq.size());
		return bq.get(0);
		// HibernateSessionFactory.getSession().getTransaction().commit();
	}

	public List<Object[]> getActiveDeviceBaseInfo(String strStartTime,
			String strEndTime, int OrgId, String Condition) {
		// HibernateSessionFactory.getSession().getTransaction().begin();
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		List<Object[]> bq = new ArrayList();
		if (strStartTime != null && strEndTime != null) {
			if(OrgId == 1)Condition = "";
			else Condition = " AND (clientgroupid " + Condition + " ) ";
			sql = "SELECT u.deviceId AS n, COUNT(DISTINCT cast(lasttime as date)) FROM devicebaseinfohistory AS u WHERE (u.lasttime BETWEEN '"
					+ strStartTime
					+ "' AND '"
					+ strEndTime
					+ "')"+Condition+" GROUP BY 1";
			System.out.println(sql);
			Query q = s.createSQLQuery(sql);
			System.out.println("createQuery is crossed");

			bq = (List<Object[]>) q.list();
		}
		System.out.println("getDeviceBaseInfo by time between:" + strStartTime
				+ " and " + strEndTime);

		return bq;
		// HibernateSessionFactory.getSession().getTransaction().commit();
	}

	public List<String> getTargetDeviceActiveStateByID(String strStartTime,
			String strEndTime, Integer iDevID) {

		System.out.println("getDeviceActiveStateInfo by LastTime between:"
				+ strStartTime + " and " + strEndTime + " with Id: " + iDevID);

		Session s = HibernateSessionFactory.getSession();
		String sql = "";

		if (strStartTime != null && strEndTime != null) {
			sql = "SELECT FROM_DAYS(TO_DAYS(u.lastTime)) FROM com.vrv.dao.Devicebaseinfohistory AS u WHERE (deviceId = "
					+ iDevID
					+ " ) AND (lastTime BETWEEN '"
					+ strStartTime
					+ "' AND '"
					+ strEndTime
					+ "') GROUP BY (FROM_DAYS(TO_DAYS(u.lastTime)))";
			System.out.println(sql);
			Query q = s.createQuery(sql);
			System.out.println("createQuery is crossed");

			List<Date> liDateCal = q.list(); // which we got after using sql
			List<String> liStrCal = new ArrayList<>(); // which will be returned

			Iterator<Date> iterator = liDateCal.iterator();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String strDt;
			while (iterator.hasNext()) {
				strDt = sdf.format(iterator.next());
				liStrCal.add(strDt);
			}

			System.out.println("list.size: " + liStrCal.size());
			return liStrCal;
		} else
			return null;
		// HibernateSessionFactory.getSession().getTransaction().commit();
	}

	/*
	 * author Kevin Chan this function is aimed at getting a list of Count And
	 * LastTime By OfficeName use strOfficeName and two calendar to get each day
	 * that at least one device has been active and return the count of the
	 * active device number the second step use officeName and a target day to
	 * get all the devices that are active that day
	 */
	public List<Object[]> getliCountAndLastTimeByOfficeId(String strStartTime,
			String strEndTime, Integer OrgId) {
		// HibernateSessionFactory.getSession().getTransaction().begin();
		System.out.println("getTotalActiveStatebyOfficeId by time between:"
				+ strStartTime + " and " + strEndTime + "in office: " + OrgId);
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		if (strStartTime != null && strEndTime != null) {
			sql = "SELECT COUNT(DISTINCT deviceID), FROM_DAYS(TO_DAYS(u.lastTime)) FROM com.vrv.dao.Devicebaseinfohistory AS u WHERE (ClientGroupId = '"
					+ OrgId
					+ "' ) AND (lasttime BETWEEN '"
					+ strStartTime
					+ "' AND '"
					+ strEndTime
					+ "') GROUP BY (FROM_DAYS(TO_DAYS(u.lastTime)))";
			System.out.println(sql);
			Query q = s.createQuery(sql);
			System.out.println("createQuery is crossed");

			List<Object[]> liCountAndLastTime = (List<Object[]>) q.list(); // <(Integer
																			// ,YYYY-MM-DD),
																			// (Integer
																			// ,YYYY-MM-DD),
																			// ...,
																			// (Integer
																			// ,YYYY-MM-DD)>
			System.out.println("liCountAndLastTime's size: "
					+ liCountAndLastTime.size());
			return liCountAndLastTime;
		} else
			return null;
		// HibernateSessionFactory.getSession().getTransaction().commit();
	}

	public List findByDeviceId(Object deviceId) {
		return findByProperty(DEVICE_ID, deviceId);
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
		log.debug("finding all Devicebaseinfohistory instances");
		try {
			String queryString = "from Devicebaseinfohistory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Devicebaseinfohistory merge(Devicebaseinfohistory detachedInstance) {
		log.debug("merging Devicebaseinfohistory instance");
		try {
			Devicebaseinfohistory result = (Devicebaseinfohistory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Devicebaseinfohistory instance) {
		log.debug("attaching dirty Devicebaseinfohistory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Devicebaseinfohistory instance) {
		log.debug("attaching clean Devicebaseinfohistory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DevicebaseinfohistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DevicebaseinfohistoryDAO) ctx
				.getBean("DevicebaseinfohistoryDAO");
	}

	// wengjiang

	/*
	 * author wengjiang
	 */
	public List<Object[]> getliCountAndLastTimeByOfficeId(String strStartTime,
			String strEndTime, int OrgId, String Condition) {
		// HibernateSessionFactory.getSession().getTransaction().begin();
		System.out.println("getliCountAndLastTimeByOfficeId by time between:"
				+ strStartTime + " and " + strEndTime + "in office: " + OrgId);
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		if (strStartTime != null && strEndTime != null) {
			if(OrgId == 1)Condition = "";
			else Condition = " clientgroupid " + Condition+" AND ";
			sql = "SELECT COUNT(DISTINCT deviceID), FROM_DAYS(TO_DAYS(u.lastTime)) ,officeName FROM com.vrv.dao.Devicebaseinfohistory AS u WHERE "
					+ Condition
					+ " (lasttime BETWEEN '"
					+ strStartTime
					+ "' AND '"
					+ strEndTime
					+ "') GROUP BY (FROM_DAYS(TO_DAYS(u.lastTime)))";

			System.out.println(sql);
			Transaction tx = s.beginTransaction();
			Query q = s.createQuery(sql);
			tx.commit();
			System.out.println("createQuery is crossed");

			List<Object[]> liCountAndLastTime = (List<Object[]>) q.list(); // <(Integer
																			// ,YYYY-MM-DD),
																			// (Integer
																			// ,YYYY-MM-DD),
																			// ...,
																			// (Integer
																			// ,YYYY-MM-DD)>
			System.out.println("liCountAndLastTime's size: "
					+ liCountAndLastTime.size());
			return liCountAndLastTime;

		} else
			return null;
		// HibernateSessionFactory.getSession().getTransaction().commit();
	}

	public List<Object[]> getliCountAndLastTimeByDeviceId(String strStartTime,
			String strEndTime, String deviceId) {

		// HibernateSessionFactory.getSession().getTransaction().begin();
		System.out.println("getliCountAndLastTimeByDeviceId by time between:"
				+ strStartTime + " and " + strEndTime + "in deviceId: "
				+ deviceId);
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		if (strStartTime != null && strEndTime != null) {
			sql = "SELECT COUNT(DISTINCT deviceID), FROM_DAYS(TO_DAYS(u.lastTime)) ,officeName FROM com.vrv.dao.Devicebaseinfohistory AS u WHERE (DevOnlyID = '"
					+ deviceId
					+ "' ) AND (lasttime BETWEEN '"
					+ strStartTime
					+ "' AND '"
					+ strEndTime
					+ "') GROUP BY (FROM_DAYS(TO_DAYS(u.lastTime)))";

			System.out.println(sql);
			Transaction tx = s.beginTransaction();
			Query q = s.createQuery(sql);
			tx.commit();
			System.out.println("createQuery is crossed");

			List<Object[]> liCountAndLastTime = (List<Object[]>) q.list(); // <(Integer
																			// ,YYYY-MM-DD),
																			// (Integer
																			// ,YYYY-MM-DD),
																			// ...,
																			// (Integer
																			// ,YYYY-MM-DD)>
			System.out.println("liCountAndLastTime's size: "
					+ liCountAndLastTime.size());
			return liCountAndLastTime;
		} else
			return null;
		// HibernateSessionFactory.getSession().getTransaction().commit();

	}

	public List<Object[]> getOrglists(Integer supId) {
		// HibernateSessionFactory.getSession().getTransaction().begin();
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		List<Object[]> bq = new ArrayList();
		sql = "SELECT groupid, groupname FROM clientgroup AS u WHERE UpGroup="
				+ supId;
		System.out.println(sql);
		Query q = s.createSQLQuery(sql);
		System.out.println("createQuery is clossed");

		bq = (List<Object[]>) q.list();

		return bq;
		// HibernateSessionFactory.getSession().getTransaction().commit();
	}

	public List<Object[]> getActiveDeviceBaseInfoByOnlyId(String strDeviceOnlyId) {
		// HibernateSessionFactory.getSession().getTransaction().begin();
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		List<Object[]> bq = new ArrayList();

		sql = "SELECT * FROM devicebaseinfohistory AS u WHERE  DevOnlyID = '"
				+ strDeviceOnlyId + "'  GROUP BY 1";
		System.out.println(sql);
		Query q = s.createSQLQuery(sql);
		System.out.println("createQuery is crossed");
		bq = (List<Object[]>) q.list();
		return bq;
		// HibernateSessionFactory.getSession().getTransaction().commit();
	}
	// wengjiang end
}