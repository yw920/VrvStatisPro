package com.vrv.service;

import java.util.Iterator;
import java.util.List;

import com.vrv.dao.*;
public class LoginfoServer {
	private LoginfoDAO loginfoDAO;
	private LoginloginfoDAO loginloginfoDAO;

	
	public String getLoginfos(int needNum){
		List<Object[]> a = loginfoDAO.getLoginfos(needNum);
		int loginfoSize = a.size();
		String tbTableBody = "";
		for(int i = 0; i<loginfoSize; i++ ){
			Object[] b = a.get(i);
			String log1 = "<tr><td>"+b[0]+"</td><td>"+b[1]+"</td><td>"+b[2]+"</td><td>"+b[3]+"</td></tr>";
			tbTableBody = tbTableBody + log1;
		}
		return tbTableBody;
	}
	
	
	public String FindTopN(int num){
		int count=0;
		String resultHtml="";
		try{
		List<Object[]> list = loginloginfoDAO.findTopN(num);
		Iterator<Object[]> it = list.iterator();
		
	
		while(it.hasNext()){
			Object[] ob = it.next();
			if(ob==null)return resultHtml;
			resultHtml+="<tr>";
			for(Object a:ob){
				if(count==0){
					resultHtml+="<td>"+(int)a+"</td>";
					count++;				
				}
				else{
				resultHtml+="<td>"+(String)a+"</td>";
				count++;}
			}
			
			resultHtml+="</tr>";		
		}}catch(Exception e){
			System.out.println("count:"+count);
		}
		
		return resultHtml;	
	}
	public LoginloginfoDAO getLoginloginfoDAO() {
		return loginloginfoDAO;
	}
	public void setLoginloginfoDAO(LoginloginfoDAO loginloginfoDAO) {
		this.loginloginfoDAO = loginloginfoDAO;
	}
	public LoginfoDAO getLoginfoDAO() {
		return loginfoDAO;
	}
	public void setLoginfoDAO(LoginfoDAO loginfoDAO) {
		this.loginfoDAO = loginfoDAO;
	}
	
}
