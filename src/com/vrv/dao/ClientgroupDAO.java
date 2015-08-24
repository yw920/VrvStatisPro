package com.vrv.dao;

import java.util.ArrayList;
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
 * Clientgroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.vrv.dao.Clientgroup
 * @author MyEclipse Persistence Tools
 */
public class ClientgroupDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ClientgroupDAO.class);
	// property constants
	public static final String GROUP_NAME = "groupName";
	public static final String CREATE_USER = "createUser";
	public static final String UP_GROUP = "upGroup";
	public static final String GROUP_DISTR = "groupDistr";
	public static final String DESCRIPTION = "description";
	public static final String GROUP_TYPE = "groupType";

	protected void initDao() {
		// do nothing
	}

	public void save(Clientgroup transientInstance) {
		log.debug("saving Clientgroup instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Clientgroup persistentInstance) {
		log.debug("deleting Clientgroup instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Clientgroup findById(java.lang.Integer id) {
		log.debug("getting Clientgroup instance with id: " + id);
		try {
			Clientgroup instance = (Clientgroup) getHibernateTemplate().get(
					"com.vrv.dao.Clientgroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Clientgroup instance) {
		log.debug("finding Clientgroup instance by example");
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
		log.debug("finding Clientgroup instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Clientgroup as model where model."
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
		sql = "SELECT GroupId, GroupName FROM Clientgroup AS u WHERE UpGroup="+supId;
		//System.out.println(sql);
		Query q = s.createSQLQuery(sql);
		//System.out.println("createQuery is clossed");
			
		bq= (List<Object[]>)q.list();
		
		return bq;
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	}
	
	public List findByGroupName(Object groupName) {
		return findByProperty(GROUP_NAME, groupName);
	}

	public List findByCreateUser(Object createUser) {
		return findByProperty(CREATE_USER, createUser);
	}

	public List findByUpGroup(Object upGroup) {
		return findByProperty(UP_GROUP, upGroup);
	}

	public List findByGroupDistr(Object groupDistr) {
		return findByProperty(GROUP_DISTR, groupDistr);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByGroupType(Object groupType) {
		return findByProperty(GROUP_TYPE, groupType);
	}

	public List findAll() {
		log.debug("finding all Clientgroup instances");
		try {
			String queryString = "from Clientgroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Clientgroup merge(Clientgroup detachedInstance) {
		log.debug("merging Clientgroup instance");
		try {
			Clientgroup result = (Clientgroup) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Clientgroup instance) {
		log.debug("attaching dirty Clientgroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Clientgroup instance) {
		log.debug("attaching clean Clientgroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ClientgroupDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ClientgroupDAO) ctx.getBean("ClientgroupDAO");
	}

	@SuppressWarnings("unchecked")
	//返回所有子节点
	public String OrganizeSubOrgList(int iOrgId){
		//先查询这个节点，如果没有查到，返回空
		try{
			//System.out.println("GetOrgListService.iOrgId:"+iOrgId);
			Clientgroup orgnode = this.findById(iOrgId);
			if(orgnode == null){
				//System.out.println("orgnode is null!");
				return null;
			}
			//System.out.println("findById finish!");
			//System.out.println("orgnode:"+orgnode.getGroupId()+";"+orgnode.getGroupId());
			//循环查询这个节点的子节点
			List<Object[]> subOrglist = (List<Object[]>)this.getOrglists(iOrgId);
			//System.out.println("subOrglist num:"+subOrglist.size());
			if(subOrglist.size()==0){//没有子节点
				String result01 = "";
				result01 = "'"+orgnode.getGroupId()+"',";
				return result01;
			}
			Iterator<Object[]> iterator = subOrglist.iterator();
			String orgsubnodepaths = "";
			while(iterator.hasNext()){//查询到节点后递归调用该函数
				Object[] orgObject01 = iterator.next();
				int subId = (int)orgObject01[0];
				//System.out.println("orgObject01_ID:"+subId+";orgObject01_name:"+(String)orgObject01[1]);
				String orgsubnodepath01 = this.OrganizeSubOrgList(subId);
				if(orgsubnodepath01 != null)orgsubnodepaths += orgsubnodepath01;
				else continue;
			}
			String result = "";
			result = "'"+orgnode.getGroupId()+"',"+orgsubnodepaths;
			return result;
		} catch (Exception e) {
	        e.printStackTrace();
	        throw(e);
	    }
	}
	
	//wengjiang
	public List<Object[]> getJustOneOrglists(Integer supId) {
		//HibernateSessionFactory.getSession().getTransaction().begin();
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		List<Object[]> bq = new ArrayList();
		sql = "SELECT GroupId, GroupName FROM Clientgroup AS u WHERE GroupId="+supId;
		System.out.println(sql);
		Query q = s.createSQLQuery(sql);
		System.out.println("createQuery is clossed");
			
		bq= (List<Object[]>)q.list();
		
		return bq;
	    //HibernateSessionFactory.getSession().getTransaction().commit();
	}
	//wengjiang end
	
	
	//by yanwei
	public List<Object[]> getTopOrglists() {
	
		Session s = HibernateSessionFactory.getSession();
		String sql = "";
		List<Object[]> bq = new ArrayList();
		sql = "SELECT GroupId,GroupName FROM Clientgroup WHERE UpGroup=1";
	
		Query q = s.createSQLQuery(sql);
	
			
		bq= (List<Object[]>)q.list();
		
		return bq;
	  
	}

}