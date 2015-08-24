package com.vrv.service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.vrv.dao.Clientgroup;
import com.vrv.dao.ClientgroupDAO;
import com.vrv.dao.DevicebaseinfoDAO;
import com.vrv.dao.DevicebaseinfohistoryDAO;

public class GetOrgListService {
	private ClientgroupDAO clientGroupDao;

	public String topOrganizeOrg(){
		try{
			List<Object[]> list = clientGroupDao.getTopOrglists();
			Iterator<Object[]> it = list.iterator();
			String result="";
			while(it.hasNext()){
			
				Object[] ob= it.next();
				if(ob==null)continue;
				int groupId = (int)ob[0];
				String name = (String)ob[1];
				result += "<li><a "+"id=\""+groupId+"\" onclick='myf("+groupId+")'>"+name+"</a></li>";
			}
			
			return result;
		}catch(Exception e){
			return null;
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject OrganizeOrgInfo(int iOrgId, int OrgAction, int Level){
		//先查询这个节点，如果没有查到，返回空
		try{
			//System.out.println("GetOrgListService.iOrgId:"+iOrgId);
			Clientgroup orgnode = clientGroupDao.findById(iOrgId);
			if(orgnode == null){
				//System.out.println("orgnode is null!");
				return null;
			}
			//System.out.println("findById finish!");
			//System.out.println("orgnode:"+orgnode.getGroupId()+";"+orgnode.getGroupName());
			//循环查询这个节点的子节点
			List<Object[]> subOrglist = (List<Object[]>)clientGroupDao.getOrglists(iOrgId);
			//System.out.println("subOrglist num:"+subOrglist.size());
			if(subOrglist.size()==0){//没有子节点
				JSONObject result01 = new JSONObject();
				result01.put("id", iOrgId);
				result01.put("text", orgnode.getGroupName());
				return result01;
			}
			else if(Level >= 3){
				JSONObject result01 = new JSONObject();
				result01.put("id", iOrgId);
				result01.put("text", orgnode.getGroupName());
				result01.put("state", "closed");
				return result01;
			}
			Iterator<Object[]> iterator = subOrglist.iterator();
			JSONArray orgsubnodepaths = new JSONArray();
			while(iterator.hasNext()){//查询到节点后递归调用该函数
				Object[] orgObject01 = iterator.next();
				int subId = (int)orgObject01[0];
				JSONObject orgsubnodepath01 = this.OrganizeOrgInfo(subId,OrgAction,Level + 1);
				if(orgsubnodepath01 != null){
					orgsubnodepaths.add(orgsubnodepath01);
				}
				else continue;
			}
			JSONObject result = new JSONObject();
			result.put("id", iOrgId);
			result.put("text", orgnode.getGroupName());
			result.put("state", "closed");
			result.put("children", orgsubnodepaths);
			return result;
		} catch (Exception e) {
	        e.printStackTrace();
	        throw(e);
	    }
	}
	@SuppressWarnings("unchecked")
	public JSONArray SubOrganizeOrgInfo(int iOrgId, int OrgAction, int Level){
		//先查询这个节点，如果没有查到，返回空
		try{
			//循环查询这个节点的子节点
			List<Object[]> subOrglist = (List<Object[]>)clientGroupDao.getOrglists(iOrgId);
			if(subOrglist.size()==0){//没有子节点
				return null;
			}
			Iterator<Object[]> iterator = subOrglist.iterator();
			JSONArray orgsubnodepaths = new JSONArray();
			while(iterator.hasNext()){//查询到节点后递归调用该函数
				Object[] orgObject01 = iterator.next();
				int subId = (int)orgObject01[0];
				JSONObject orgsubnodepath01 = this.OrganizeOrgInfo(subId,OrgAction,Level + 1);
				if(orgsubnodepath01 != null){
					orgsubnodepaths.add(orgsubnodepath01);
				}
				else continue;
			}
			return orgsubnodepaths;
		} catch (Exception e) {
	        e.printStackTrace();
	        throw(e);
	    }
	}
	//wengjiang
	
	private DevicebaseinfoDAO devicebaseinfoDAO; 
	private DevicebaseinfohistoryDAO devicebaseinfohistoryDao;
	
	/* 
	 *  wengjiangg
	 * 	先查出所有该节点的子部门
	 * 	遍历子部门
	 * 	返回结果
	 *    暂时不实现递归查看子部门的子部门情况
	 * 
	 *  结果字符串：[ {depName:'name',state:'str' },{ } ...]
	 * 
	 * */
	public Vector getORGDeviceActiveInfo(String strStartTime, String strEndTime,  int officeID ){ 
		 
		 
		List<Object[]> subOrglist = (List<Object[]>)clientGroupDao.getOrglists(officeID); //"SELECT id, name FROM orglist AS u WHERE supid="+supId
		//System.out.println(subOrglist.size());
		
		//开始算出所有子部门的活跃状态 
		Vector v=new  Vector();//用于存放返回的数据的日期数目   
		Iterator<Object[]> iterator = subOrglist.iterator(); 
		while(iterator.hasNext()){ 
			Object[] orgObject01 = iterator.next(); 
			List<Object[]> liCountAndLastTime=	devicebaseinfohistoryDao.getliCountAndLastTimeByOfficeId(strStartTime, strEndTime, (Integer)orgObject01[0]);
			Object[]  o=new Object[3];
			long temp=0;
			o[0]=temp;//设备使用次数 
			java.util.Date d=new java.util.Date(0000-00-00);
			o[1]=d;//设备使用时间
			o[2]=(String)orgObject01[1];//部门名字 
			liCountAndLastTime.add(o);  //把部门信息加到最后   这里加的原因是因为  有时候getliCountAndLastTimeByOfficeName里面查不到信息 部门信息就没有了 这样导致空指针错误  
			v.add(liCountAndLastTime); 
		} 
		return v;
	} 
	/* 
	 *  wengjiang
	 * 	先查出所有该节点的子部门   //这里为了简便只实际上只返回一个部门的信息  是就是要查的部门  用id去查到 名字   因为目前 2015-5-10  还没有部门id信息    下一步再去实现  用id去查
	 * 	遍历子部门
	 * 	返回结果
	 *    暂时不实现递归查看子部门的子部门情况
	 * 
	 *  结果字符串：[ {depName:'name',state:'str' },{ } ...]
	 * 
	 * */
	public Vector getJustOneORGDeviceActiveInfo(String strStartTime, String strEndTime,  int officeID ){ 
		System.out.println("getJustOneORGDeviceActiveInfo officeID:"+officeID);
		String strOrganizeSubOrgList = this.clientGroupDao.OrganizeSubOrgList(officeID);
		String Condition = "in ("+strOrganizeSubOrgList.substring(0,strOrganizeSubOrgList.length()-1)+")";
		//System.out.println("Condition:"+Condition);
		//开始算出所有子部门的活跃状态 
		Vector v=new  Vector();//用于存放返回的数据的日期数目   
		List<Object[]> liCountAndLastTime=	devicebaseinfohistoryDao.getliCountAndLastTimeByOfficeId(strStartTime, strEndTime, officeID,Condition);
		Object[]  o=new Object[3];
		long temp=0;
		o[0]=temp;//设备使用次数 
		java.util.Date d=new java.util.Date(0000-00-00);
		o[1]=d;//设备使用时间
		o[2]="";//部门名字 
		liCountAndLastTime.add(o);
		v.add(liCountAndLastTime); 
		return v;
	} 
	
	
	//wengjiang end
	public DevicebaseinfoDAO getDevicebaseinfoDAO() {
		return devicebaseinfoDAO;
	}


	public void setDevicebaseinfoDAO(DevicebaseinfoDAO devicebaseinfoDAO) {
		this.devicebaseinfoDAO = devicebaseinfoDAO;
	}





	public DevicebaseinfohistoryDAO getDevicebaseinfohistoryDao() {
		return devicebaseinfohistoryDao;
	}





	public void setDevicebaseinfohistoryDao(
			DevicebaseinfohistoryDAO devicebaseinfohistoryDao) {
		this.devicebaseinfohistoryDao = devicebaseinfohistoryDao;
	}


	public ClientgroupDAO getClientGroupDao() {
		return clientGroupDao;
	}


	public void setClientGroupDao(ClientgroupDAO clientGroupDao) {
		this.clientGroupDao = clientGroupDao;
	}



}
