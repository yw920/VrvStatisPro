package com.vrv.action;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.vrv.service.UserLoginService;
import com.vrv.dao.*;

public class UserLoginAction extends ActionSupport {

	private UserLoginService userLoginService;
	private LoginloginfoDAO loginloginfoDAO;

	private String userName;

	public LoginloginfoDAO getLoginloginfoDAO() {
		return loginloginfoDAO;
	}

	public void setLoginloginfoDAO(LoginloginfoDAO loginloginfoDAO) {
		this.loginloginfoDAO = loginloginfoDAO;
	}

	private String passWord;

	private String hostName;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String execute() throws IOException, ParseException{


		boolean result = userLoginService.queryIfExist(userName,passWord);

		String ip = getRomoteIP(ServletActionContext.getRequest());
		String mac = getMACAddress(ip);

		if(result){

			Map<String, Object> map = ActionContext.getContext().getSession();
			map.put("username", userName);
			@SuppressWarnings("deprecation")
			String time = new Date().toLocaleString();
			Loginloginfo loginLoginfo = new Loginloginfo(userName,time,hostName,mac,ip,new Date());
			//loginloginfoDAO.insertInstance(loginLoginfo);

			loginloginfoDAO.save(loginLoginfo);



			return SUCCESS;

		}

		Map<String, Object> map = ActionContext.getContext().getSession();
		map.clear();


			return LOGIN;





	}

	public UserLoginService getUserLoginService() {
		return userLoginService;
	}

	public void setUserLoginService(UserLoginService userLoginService) {
		this.userLoginService = userLoginService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	private String getRomoteIP(HttpServletRequest re){
		if(re.getHeader("x-forwarded-for")==null){
			return re.getRemoteAddr();
		}
		return re.getHeader("x-forwarded-for");
	}

	private String getMACAddress(String ip) {
		        String str = "";
		       String macAddress = "";
		       try {
		           Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
		           InputStreamReader ir = new InputStreamReader(p.getInputStream());
		           LineNumberReader input = new LineNumberReader(ir);
		           for (int i = 1; i < 100; i++) {
		               str = input.readLine();
		              if (str != null) {
		                   if (str.indexOf("MAC") > 1) {
		                       macAddress = str.substring(
		                                str.indexOf("MAC") + 9, str.length());
	                        break;
		                  }
		               }
		           }
	        } catch (IOException e) {
	            e.printStackTrace(System.out);
	        }
	        return macAddress;
		   }
    private void test1(){}



}
