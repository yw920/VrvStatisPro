package com.vrv.service;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import com.vrv.dao.TUserDAO;
import com.vrv.dao.TUser;


public class UserLoginService {
	
	private TUserDAO tUserDAO;
	private TUser tUser;
	
	public boolean queryIfExist(String userName,String passWord){
	
		
		try{
		//System.out.println("Service:"+userName+""+passWord);
		 List list = tUserDAO.myFindByUserName(userName);
		 
		     if(list.get(0).equals(passWord)){
		    	return true;
		     }
		

	
		 
		}catch(Exception e){
			return false;
		}
		return false;
				
	
		
	}

	public TUserDAO gettUserDAO() {
		return tUserDAO;
	}

	public void settUserDAO(TUserDAO tUserDAO) {
		this.tUserDAO = tUserDAO;
	}

	public TUser gettUser() {
		return tUser;
	}

	public void settUser(TUser tUser) {
		this.tUser = tUser;
	}
	

}
