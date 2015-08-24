package cn.pro.bxy.wsclient2;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;

import org.apache.axis2.AxisFault;

import cn.pro.bxy.common.JsonUtils;
import cn.pro.bxy.common.SystemUtils;

public class GetDateByFromWS2 {
	private VrvWebServerStub vss;
	private SystemUtils vsSysUtils;
	
	public GetDateByFromWS2(String wsdlAddress){
		try {
			this.vss= new VrvWebServerStub(wsdlAddress);
			this.vsSysUtils = new SystemUtils();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String login(String name,String password) throws IOException{
		String token ="{}"; 
		try { 
			this.vsSysUtils.putKeyStoreToEnvironment();
			VrvWebServerStub.Login lg= new VrvWebServerStub.Login();
			lg.setUserName(name);
			lg.setPassword(password);

			token= this.vss.Login(lg).getLoginResult(); 
			System.out.println("login取回的数据："+token); 
			return token; 
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return token;
	}
	public String getRegisteredDeviceBaseInfo(String token , int pageIndex,int pageNumber )throws IOException{
		 
		try {   
			vsSysUtils.putKeyStoreToEnvironment(); 
			VrvWebServerStub.GetBasicReaderRegistered grr = new VrvWebServerStub.GetBasicReaderRegistered();
			
			grr.setUserGuid(token);
			grr.setPageIndex(pageIndex);
			grr.setPageSize(pageNumber);
			
			
			String result=vss.GetBasicReaderRegistered(grr).getGetBasicReaderRegisteredResult();//   .GetBasicReaderRegistered(grr);
			System.out.println("GetBasicReaderRegistered取回的数据："+result);
			
			return(result);
			   
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return token;
	}

	public String getBasicReaderLasttime(String token , int pageIndex,int pageNumber )throws IOException{
		 
		try {   
			vsSysUtils.putKeyStoreToEnvironment(); 
			VrvWebServerStub.GetBasicReaderLasttime grr = new VrvWebServerStub.GetBasicReaderLasttime();
			
			grr.setUserGuid(token);
			grr.setPageIndex(pageIndex);
			grr.setPageSize(pageNumber);
			
			
			String result=vss.GetBasicReaderLasttime(grr).getGetBasicReaderLasttimeResult();//   .GetBasicReaderRegistered(grr);
			System.out.println("GetBasicReaderRegistered取回的数据："+result);
			
			return(result);
			   
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return token;
	}
	public String getClientUserInfo(String token , int pageIndex,int pageNumber )throws IOException{
		 
		try {   
			vsSysUtils.putKeyStoreToEnvironment();
			VrvWebServerStub.GetTerminalUser grr = new VrvWebServerStub.GetTerminalUser();
			
			grr.setUserGuid(token);
			grr.setPageIndex(pageIndex);
			grr.setPageSize(pageNumber);
			
			
			String result=vss.GetTerminalUser(grr).getGetTerminalUserResult();//   .GetBasicReaderRegistered(grr);
			System.out.println("GetTerminalUser取回的数据："+result);
			
			return(result);
			   
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return token;
	}
	public String getClientGroupInfo(String token , int pageIndex,int pageNumber )throws IOException{
		 
		try {   
			vsSysUtils.putKeyStoreToEnvironment();
			VrvWebServerStub.GetTerminalGroup grr = new VrvWebServerStub.GetTerminalGroup();
			
			grr.setUserGuid(token);
			grr.setPageIndex(pageIndex);
			grr.setPageSize(pageNumber);
			
			
			String result=vss.GetTerminalGroup(grr).getGetTerminalGroupResult();//   .GetBasicReaderRegistered(grr);
			System.out.println("GetTerminalGroup取回的数据："+result);
			
			return(result);
			   
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return token;
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException{
		
		try { 
			new SystemUtils().putKeyStoreToEnvironment(); 
			VrvWebServerStub vss= new VrvWebServerStub();
			//VrvWebServerStub.Login lg;//=new VrvWebServerStub.ge
			VrvWebServerStub.Login lg= new VrvWebServerStub.Login();
			lg.setUserName("wdb");
			lg.setPassword("123456");
			String str= vss.Login(lg).getLoginResult();
			System.out.println(str); 
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} 
}
