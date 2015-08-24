package com.vrv.action;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.vrv.dao.TUserDAO;
import com.vrv.dao.TUser;


public class AddUserAction {

	
	

	private String oprName;
	private String oprGpId;
	private TUserDAO tUserDAO;
	
	public void execute() throws IOException, ParseException {
		
		try{
		System.out.println("In AddUserAction!");
		
		tUserDAO.save(new TUser(oprName,"000000",Integer.parseInt(oprGpId)));
	
			
		ServletActionContext.getResponse().getWriter().write("suscess");
		}catch(Exception e){
			ServletActionContext.getResponse().getWriter().write("error");
		}
			
	}

	public String getOprName() {
		return oprName;
	}

	public void setOprName(String oprName) {
		this.oprName = oprName;
	}

	public String getOprGpId() {
		return oprGpId;
	}

	public void setOprGpId(String oprGpId) {
		this.oprGpId = oprGpId;
	}

	public TUserDAO gettUserDAO() {
		return tUserDAO;
	}

	public void settUserDAO(TUserDAO tUserDAO) {
		this.tUserDAO = tUserDAO;
	}

	


}
