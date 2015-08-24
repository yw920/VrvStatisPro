package com.vrv.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Clientuser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.vrv.dao.Clientuser
 * @author MyEclipse Persistence Tools
 */
public class ClientuserDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ClientuserDAO.class);
	// property constants
	public static final String CUSER_PASS = "cuserPass";
	public static final String CUSER_TYPE = "cuserType";
	public static final String CURRENT_IP = "currentIp";
	public static final String CUSER_TEL = "cuserTel";
	public static final String UNIT_NAME = "unitName";
	public static final String DEPT_NAME = "deptName";
	public static final String OFFICE_NAME = "officeName";
	public static final String ADD_USER = "addUser";
	public static final String LAST_LOGIN_DEVICE = "lastLoginDevice";
	public static final String EVER_USE_DEVICE = "everUseDevice";
	public static final String DESCRIPTION = "description";
	public static final String RESERVE1 = "reserve1";
	public static final String RESERVE2 = "reserve2";
	public static final String EMAIL_ADDRESS = "emailAddress";
	public static final String GROUP_ID = "groupId";
	public static final String GROUP_NAME = "groupName";

	protected void initDao() {
		// do nothing
	}

	public void save(Clientuser transientInstance) {
		log.debug("saving Clientuser instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Clientuser persistentInstance) {
		log.debug("deleting Clientuser instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Clientuser findById(java.lang.String id) {
		log.debug("getting Clientuser instance with id: " + id);
		try {
			Clientuser instance = (Clientuser) getHibernateTemplate().get(
					"com.vrv.dao.Clientuser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Clientuser instance) {
		log.debug("finding Clientuser instance by example");
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
		log.debug("finding Clientuser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Clientuser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCuserPass(Object cuserPass) {
		return findByProperty(CUSER_PASS, cuserPass);
	}

	public List findByCuserType(Object cuserType) {
		return findByProperty(CUSER_TYPE, cuserType);
	}

	public List findByCurrentIp(Object currentIp) {
		return findByProperty(CURRENT_IP, currentIp);
	}

	public List findByCuserTel(Object cuserTel) {
		return findByProperty(CUSER_TEL, cuserTel);
	}

	public List findByUnitName(Object unitName) {
		return findByProperty(UNIT_NAME, unitName);
	}

	public List findByDeptName(Object deptName) {
		return findByProperty(DEPT_NAME, deptName);
	}

	public List findByOfficeName(Object officeName) {
		return findByProperty(OFFICE_NAME, officeName);
	}

	public List findByAddUser(Object addUser) {
		return findByProperty(ADD_USER, addUser);
	}

	public List findByLastLoginDevice(Object lastLoginDevice) {
		return findByProperty(LAST_LOGIN_DEVICE, lastLoginDevice);
	}

	public List findByEverUseDevice(Object everUseDevice) {
		return findByProperty(EVER_USE_DEVICE, everUseDevice);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByReserve1(Object reserve1) {
		return findByProperty(RESERVE1, reserve1);
	}

	public List findByReserve2(Object reserve2) {
		return findByProperty(RESERVE2, reserve2);
	}

	public List findByEmailAddress(Object emailAddress) {
		return findByProperty(EMAIL_ADDRESS, emailAddress);
	}

	public List findByGroupId(Object groupId) {
		return findByProperty(GROUP_ID, groupId);
	}

	public List findByGroupName(Object groupName) {
		return findByProperty(GROUP_NAME, groupName);
	}

	public List findAll() {
		log.debug("finding all Clientuser instances");
		try {
			String queryString = "from Clientuser";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Clientuser merge(Clientuser detachedInstance) {
		log.debug("merging Clientuser instance");
		try {
			Clientuser result = (Clientuser) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Clientuser instance) {
		log.debug("attaching dirty Clientuser instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Clientuser instance) {
		log.debug("attaching clean Clientuser instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ClientuserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ClientuserDAO) ctx.getBean("ClientuserDAO");
	}
}