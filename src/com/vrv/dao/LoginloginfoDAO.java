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
 * Loginloginfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.vrv.dao.Loginloginfo
 * @author MyEclipse Persistence Tools
 */
public class LoginloginfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(LoginloginfoDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String LOGIN_TIME = "loginTime";
	public static final String HOST_NAME = "hostName";
	public static final String HOST_MAC = "hostMac";
	public static final String HOST_IP = "hostIp";

	protected void initDao() {
		// do nothing
	}
	
	public void insertInstance(Loginloginfo ins){
		Session s = HibernateSessionFactory.getSession();	
		
		String sql = "insert into Loginloginfo values(null,'"+ins.getUsername()+"','"+ins.getLoginTime()+"','"+ins.getHostName()+"','"+
		ins.getHostMac()+"','"+ins.getHostIp()+"',null)";
		Query q = s.createSQLQuery(sql);
		q.executeUpdate();
	}

	
	
	public List<Object[]> findTopN(int num){
		
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		List<Object[]> bq = new ArrayList();	
		sql = "select id,username,loginTime,hostMac,hostIP from loginloginfo "

				+"where opdatetime > now() "
				+ "- interval 1 day order by 1 desc limit "+num;
		Query q = s.createSQLQuery(sql);		
		bq= (List<Object[]>)q.list();	
		return bq;
		
	}
	public void save(Loginloginfo transientInstance) {
		log.debug("saving Loginloginfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Loginloginfo persistentInstance) {
		log.debug("deleting Loginloginfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Loginloginfo findById(java.lang.Integer id) {
		log.debug("getting Loginloginfo instance with id: " + id);
		try {
			Loginloginfo instance = (Loginloginfo) getHibernateTemplate().get(
					"com.vrv.dao.Loginloginfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Loginloginfo instance) {
		log.debug("finding Loginloginfo instance by example");
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
		log.debug("finding Loginloginfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Loginloginfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByLoginTime(Object loginTime) {
		return findByProperty(LOGIN_TIME, loginTime);
	}

	public List findByHostName(Object hostName) {
		return findByProperty(HOST_NAME, hostName);
	}

	public List findByHostMac(Object hostMac) {
		return findByProperty(HOST_MAC, hostMac);
	}

	public List findByHostIp(Object hostIp) {
		return findByProperty(HOST_IP, hostIp);
	}

	public List findAll() {
		log.debug("finding all Loginloginfo instances");
		try {
			String queryString = "from Loginloginfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Loginloginfo merge(Loginloginfo detachedInstance) {
		log.debug("merging Loginloginfo instance");
		try {
			Loginloginfo result = (Loginloginfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Loginloginfo instance) {
		log.debug("attaching dirty Loginloginfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Loginloginfo instance) {
		log.debug("attaching clean Loginloginfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LoginloginfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LoginloginfoDAO) ctx.getBean("LoginloginfoDAO");
	}
}