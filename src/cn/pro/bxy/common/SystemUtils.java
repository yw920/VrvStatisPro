package cn.pro.bxy.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemUtils {

	/*
	 * 为了能访问 https的webservice  
	 * 把证书加到环境里面去   如果以后修改方法了后 
	 * */
	public  void putKeyStoreToEnvironment() throws IOException{
		 
		 Properties prop = new Properties();//属性集合对象   
		 InputStream path =Thread.currentThread().getContextClassLoader().getResourceAsStream("SystemConfig.properties");//获取路径并转换成流
		 prop.load(path); 
		 String mykeystoreURl=prop.getProperty("mykeystoreURl"); 
		 path.close();  
		 
		 System.setProperty("javax.net.ssl.trustStore", mykeystoreURl);        
	     System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");
	}

	/*
	 * 返回 Propertie 的实例对象
	 * */
	public  Properties getProperties() throws IOException{
		 
		 Properties prop = new Properties();//属性集合对象   
		 InputStream path =Thread.currentThread().getContextClassLoader().getResourceAsStream("SystemConfig.properties");//获取路径并转换成流
		 prop.load(path); 
		 path.close(); 
		 return prop;
	}

	/*
	 * 返回指定文件的 Propertie 的实例对象
	 * */
	public  Properties getProperties(String properFile) throws IOException{
		 
		 Properties prop = new Properties();//属性集合对象   
		 InputStream path =Thread.currentThread().getContextClassLoader().getResourceAsStream(properFile);//获取路径并转换成流
		 prop.load(path); 
		 path.close(); 
		 return prop;
	}
	 
}
