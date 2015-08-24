package cn.pro.bxy.wsclient;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;

import org.apache.axis2.AxisFault;

import cn.pro.bxy.common.JsonUtils;
import cn.pro.bxy.common.SystemUtils;

public class GetDateByFromWS { 
	public String login(String name,String password) throws IOException{
		String token ="{}"; 
		try {
			

//			  
//			 Properties prop = new Properties();//属性集合对象   
//			 InputStream path =Thread.currentThread().getContextClassLoader().getResourceAsStream("SystemConfig.properties");//获取路径并转换成流
//			 prop.load(path); 
//			 String mykeystoreURl=prop.getProperty("mykeystoreURl"); 
//			 path.close(); 
//	  
//			 System.setProperty("javax.net.ssl.trustStore", mykeystoreURl);        
//		     System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol"); 
//		     
//				
			new SystemUtils().putKeyStoreToEnvironment();
		     
		      
			
		 
			VrvWebServerStub vss= new VrvWebServerStub();
			//VrvWebServerStub.Login lg;//=new VrvWebServerStub.ge
			VrvWebServerStub.Login lg= new VrvWebServerStub.Login();
			lg.setUserName(name);//"wdb");
			lg.setPassword(password);
			token= vss.Login(lg).getLoginResult();
			
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
			
			
			
//			 Properties prop = new Properties();//属性集合对象   
//			 InputStream path =Thread.currentThread().getContextClassLoader().getResourceAsStream("SystemConfig.properties");//获取路径并转换成流
//			 prop.load(path); 
//			 String mykeystoreURl=prop.getProperty("mykeystoreURl"); 
//			 path.close(); 
//	  
//			 System.setProperty("javax.net.ssl.trustStore", mykeystoreURl);        
//		     System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol"); 
//		     
//		     
			new SystemUtils().putKeyStoreToEnvironment();
		     
		     
		     
		     
		     
		 
			VrvWebServerStub vss= new VrvWebServerStub();
			//VrvWebServerStub.Login lg;//=new VrvWebServerStub.ge
			
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
