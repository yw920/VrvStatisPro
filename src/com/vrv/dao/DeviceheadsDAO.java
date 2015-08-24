package com.vrv.dao;

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
 * Deviceheads entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.vrv.dao.Deviceheads
 * @author MyEclipse Persistence Tools
 */
public class DeviceheadsDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DeviceheadsDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String CHECKED = "checked";
	public static final String EN_NAME = "enName";

	protected void initDao() {
		// do nothing
	}

	public void save(Deviceheads transientInstance) {
		log.debug("saving Deviceheads instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Deviceheads persistentInstance) {
		log.debug("deleting Deviceheads instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Deviceheads findById(java.lang.Integer id) {
		log.debug("getting Deviceheads instance with id: " + id);
		try {
			Deviceheads instance = (Deviceheads) getHibernateTemplate().get(
					"com.vrv.dao.Deviceheads", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Deviceheads instance) {
		log.debug("finding Deviceheads instance by example");
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
		log.debug("finding Deviceheads instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Deviceheads as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByChecked(Object checked) {
		return findByProperty(CHECKED, checked);
	}

	public List findByEnName(Object enName) {
		return findByProperty(EN_NAME, enName);
	}

	public List findAll() {
		log.debug("finding all Deviceheads instances");
		try {
			String queryString = "from Deviceheads";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Deviceheads> findSelected() {
		System.out.println("finding Selected Deviceheads instances");
		Session s = HibernateSessionFactory.getSession();
		try {
			String sql = "select new Deviceheads(u.name,u.checked,u.enName) from com.vrv.dao.Deviceheads as u where u.checked=true order by u.id";
			Query q = s.createQuery(sql);
			System.out.println("In getDeviceDomInfo:createQuery is crossed");
			List<Deviceheads> deviceheadsList= q.list();
			System.out.println("list.size:"+deviceheadsList.size());
			return deviceheadsList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			re.printStackTrace();
			return null;
		}catch (Exception re) {
			log.error("find all failed", re);
			re.printStackTrace();
			return null;
		}
	}

	public boolean changeChecked(String Condition) {
		System.out.println("changing Selected Deviceheads instances");
		Session s = HibernateSessionFactory.getSession();
		try {
			String sql = "update deviceheads set checked=false";
			Query q = s.createSQLQuery(sql);
			q.executeUpdate();
			System.out.println("In DeviceheadsDAO:changeSelected to false");
			
			sql = "update deviceheads set checked=true where id " + Condition;
			q = s.createSQLQuery(sql);
			q.executeUpdate();
			System.out.println("In DeviceheadsDAO:changeSelected to true");
			return true;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			re.printStackTrace();
			return true;
		}catch (Exception re) {
			log.error("find all failed", re);
			re.printStackTrace();
			return true;
		}
	}
	
	public Deviceheads merge(Deviceheads detachedInstance) {
		log.debug("merging Deviceheads instance");
		try {
			Deviceheads result = (Deviceheads) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Deviceheads instance) {
		log.debug("attaching dirty Deviceheads instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Deviceheads instance) {
		log.debug("attaching clean Deviceheads instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DeviceheadsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DeviceheadsDAO) ctx.getBean("DeviceheadsDAO");
	}
}