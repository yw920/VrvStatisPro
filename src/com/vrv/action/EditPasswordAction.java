package com.vrv.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.vrv.dao.TUserDAO;
import com.vrv.dao.TUser;


public class EditPasswordAction {

	private String oldPassword;
	private String newPassword;
	private TUserDAO tUserDAO;
	
	public TUserDAO gettUserDAO() {
		return tUserDAO;
	}

	public void settUserDAO(TUserDAO tUserDAO) {
		this.tUserDAO = tUserDAO;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void execute() throws IOException, ParseException{
		Map<String, Object> map = ActionContext.getContext().getSession();
		System.out.println("In EditPasswordAction!");
		String un = (String) map.get("username");
		
		try {
			
			List list = tUserDAO.myFindByUserName(un);
			
			String pswd = (String) list.get(0);
			
			if(list.size()>1){
				ServletActionContext.getResponse().getWriter().write("error");
				return;
			}
			//System.out.println("pswd:"+pswd+" vs oldPassword:"+oldPassword+" vs newPassword:"+newPassword);
			if(!pswd.equals(oldPassword)){
				ServletActionContext.getResponse().getWriter().write("error");
				return;
			}
			
			tUserDAO.updatePassword(un, newPassword);
			ServletActionContext.getResponse().getWriter().write("success");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ServletActionContext.getResponse().getWriter().write("error");
		}

	}
	
}
