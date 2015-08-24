package com.vrv.dao;

import java.util.ArrayList;
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
 * Orglist entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.vrv.dao.Orglist
 * @author MyEclipse Persistence Tools
 */
public class OrglistDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(OrglistDAO.class);
	// property constants
	public static final String SUPID = "supid";
	public static final String NEXTID = "nextid";
	public static final String CHILDID = "childid";
	public static final String NAME = "name";

	protected void initDao() {
		// do nothing
	}

	public void save(Orglist transientInstance) {
		log.debug("saving Orglist instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Orglist persistentInstance) {
		log.debug("deleting Orglist instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Orglist findById(java.lang.Integer id) {
		log.debug("getting Orglist instance with id: " + id);
		try {
			Orglist instance = (Orglist) getHibernateTemplate().get(
					"com.vrv.dao.Orglist", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Orglist instance) {
		log.debug("finding Orglist instance by example");
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
		log.debug("finding Orglist instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Orglist as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Object[]> getOrglists(Integer supId) {
		//HibernateSessionFactory.getSession().getTransaction().begin();
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		List<Object[]> bq = new ArrayList();
		sql = "SELECT id, name FROM orglist AS u WHERE supid="+supId;
		//System.out.println(sql);
		Query q = s.createSQLQuery(sql);
		//System.out.println("createQuery is clossed");
			
		bq= (List<Object[]>)q.list();
		
		return bq;
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	}	
	
	public List findBySupid(Object supid) {
		return findByProperty(SUPID, supid);
	}

	public List findByNextid(Object nextid) {
		return findByProperty(NEXTID, nextid);
	}

	public List findByChildid(Object childid) {
		return findByProperty(CHILDID, childid);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Orglist instances");
		try {
			String queryString = "from Orglist";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Orglist merge(Orglist detachedInstance) {
		log.debug("merging Orglist instance");
		try {
			Orglist result = (Orglist) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Orglist instance) {
		log.debug("attaching dirty Orglist instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Orglist instance) {
		log.debug("attaching clean Orglist instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrglistDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OrglistDAO) ctx.getBean("OrglistDAO");
	}
	
	//wengjiang
	public List<Object[]> getJustOneOrglists(Integer supId) {
		//HibernateSessionFactory.getSession().getTransaction().begin();
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		List<Object[]> bq = new ArrayList();
		sql = "SELECT id, name FROM orglist AS u WHERE ID="+supId;
		//System.out.println(sql);
		Query q = s.createSQLQuery(sql);
		//System.out.println("createQuery is clossed");
			
		bq= (List<Object[]>)q.list();
		
		return bq;
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	}
	//wengjiang end
}