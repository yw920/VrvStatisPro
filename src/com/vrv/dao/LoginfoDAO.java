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
 	* A data access object (DAO) providing persistence and search support for Loginfo entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.vrv.dao.Loginfo
  * @author MyEclipse Persistence Tools 
 */
public class LoginfoDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(LoginfoDAO.class);
		//property constants
	public static final String OPTYPE = "optype";
	public static final String START_SYS_TIME = "startSysTime";
	public static final String END_SYS_TIME = "endSysTime";
	
	public List<Object[]> getLoginfos(int needNum) {
		//HibernateSessionFactory.getSession().getTransaction().begin();
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		List<Object[]> bq = new ArrayList();
		//sql = "SELECT opdatetime, optype FROM loginfo AS u where opdatetime > now() - 

//interval 1 day order by 1 desc limit 0, "+needNum;
		
		
		sql = "select a.groupname,b.optype,b.startsystime,b.endsystime from clientgroup "

				+"a,loginfo b where a.groupid=b.id and opdatetime > now() "
				+ "- interval 1 day order by 1 desc limit 0,"+needNum;
		//System.out.println(sql);
		
		Query q = s.createSQLQuery(sql);
		//System.out.println("createQuery is clossed");
			
		bq= (List<Object[]>)q.list();
		
		return bq;
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	}




	protected void initDao() {
		//do nothing
	}
    
    public void save(Loginfo transientInstance) {
        log.debug("saving Loginfo instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Loginfo persistentInstance) {
        log.debug("deleting Loginfo instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Loginfo findById( java.lang.Integer id) {
        log.debug("getting Loginfo instance with id: " + id);
        try {
            Loginfo instance = (Loginfo) getHibernateTemplate()
                    .get("com.vrv.dao.Loginfo", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Loginfo instance) {
        log.debug("finding Loginfo instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Loginfo instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Loginfo as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByOptype(Object optype
	) {
		return findByProperty(OPTYPE, optype
		);
	}
	
	public List findByStartSysTime(Object startSysTime
	) {
		return findByProperty(START_SYS_TIME, startSysTime
		);
	}
	
	public List findByEndSysTime(Object endSysTime
	) {
		return findByProperty(END_SYS_TIME, endSysTime
		);
	}
	

	public List findAll() {
		log.debug("finding all Loginfo instances");
		try {
			String queryString = "from Loginfo";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Loginfo merge(Loginfo detachedInstance) {
        log.debug("merging Loginfo instance");
        try {
            Loginfo result = (Loginfo) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Loginfo instance) {
        log.debug("attaching dirty Loginfo instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Loginfo instance) {
        log.debug("attaching clean Loginfo instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static LoginfoDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (LoginfoDAO) ctx.getBean("LoginfoDAO");
	}
}