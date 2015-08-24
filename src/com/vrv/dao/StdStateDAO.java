package com.vrv.dao;

import java.util.Date;
import java.util.Iterator;
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
 * StdState entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.vrv.dao.StdState
 * @author MyEclipse Persistence Tools
 */
public class StdStateDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(StdStateDAO.class);
	// property constants
	public static final String STD_NO = "stdNo";
	public static final String STD_NAME = "stdName";
	public static final String TECH_DOMAIN_ID = "techDomainId";
	public static final String STD_COMMENT = "stdComment";

	protected void initDao() {
		// do nothing
	}

	public void save(StdState transientInstance) {
		log.debug("saving StdState instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StdState persistentInstance) {
		log.debug("deleting StdState instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StdState findById(java.lang.Integer id) {
		log.debug("getting StdState instance with id: " + id);
		try {
			StdState instance = (StdState) getHibernateTemplate().get(
					"com.vrv.dao.StdState", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(StdState instance) {
		log.debug("finding StdState instance by example");
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
		log.debug("finding StdState instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from StdState as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public StdState getMySql(Integer Id){
		String result = "";
		
		//HibernateSessionFactory.getSession().getTransaction().begin();  
		Session s = HibernateSessionFactory.getSession();

		String sql = "SELECT new StdState(u.stdNo, u.stdName,u.techDomainId,u.stdComment) from StdState as u where id = " + Id;
		Query q = s.createQuery(sql);
	    List<StdState> StdStateValList= q.list();
	    Iterator<StdState> iterator = StdStateValList.iterator();
    	while(iterator.hasNext()){
    		System.out.println("开始取值！");
    		StdState stdState = iterator.next();
    		result = stdState.getStdName();
    		System.out.println(result);
    		return stdState;
        }
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	    return null;
	}

	public Integer getCount(){
		Integer result = 0;
		
		//HibernateSessionFactory.getSession().getTransaction().begin();  
		Session s = HibernateSessionFactory.getSession();
		String sql = "SELECT count(u.stdNo) from StdState as u ";
		Query q = s.createQuery(sql);
	    List<Integer> StdCount= q.list();
    	System.out.println(StdCount.get(0));
    	result = Integer.parseInt(String.valueOf(StdCount.get(0)));
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	    return result;
	}
	public List findByStdNo(Object stdNo) {
		return findByProperty(STD_NO, stdNo);
	}

	public List findByStdName(Object stdName) {
		return findByProperty(STD_NAME, stdName);
	}

	public List findByTechDomainId(Object techDomainId) {
		return findByProperty(TECH_DOMAIN_ID, techDomainId);
	}

	public List findAll() {
		log.debug("finding all StdState instances");
		try {
			String queryString = "from StdState";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StdState merge(StdState detachedInstance) {
		log.debug("merging StdState instance");
		try {
			StdState result = (StdState) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StdState instance) {
		log.debug("attaching dirty StdState instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StdState instance) {
		log.debug("attaching clean StdState instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StdStateDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StdStateDAO) ctx.getBean("StdStateDAO");
	}
}