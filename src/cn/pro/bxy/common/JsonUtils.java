package cn.pro.bxy.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//
//import org.apache.cxf.endpoint.Client;
//import org.apache.cxf.frontend.ClientProxy;
//import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
 
import cn.pro.bxy.wsclient.GetDateByFromWS;
import cn.pro.bxy.wsclient2.GetDateByFromWS2;

public class JsonUtils {
	
	private GetDateByFromWS2 GBF;
	private String properFile;
	private String wsdlAddress;
 public JsonUtils(String wsdlAddress) throws IOException{
	 
//	  
//			Properties prop = new Properties();//鐏炵偞锟介梿鍡楁値鐎电钖�  
//			InputStream path =Thread.currentThread().getContextClassLoader().getResourceAsStream("SystemConfig.properties");//閼惧嘲褰囩捄顖氱窞楠炴儼娴嗛幑銏″灇濞达拷
//			prop.load(path); 
//			String mykeystoreURl=prop.getProperty("mykeystoreURl"); 
//			path.close(); 
//	 
//	  
//		 System.setProperty("javax.net.ssl.trustStore", mykeystoreURl);        
//	     System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol"); 
	 new SystemUtils().putKeyStoreToEnvironment();
	 this.GBF = new GetDateByFromWS2(wsdlAddress);
 }
	
 public String getProperFile() {
	return properFile;
}

public void setProperFile(String properFile) {
	this.properFile = properFile;
}

public String login(String userName,String password) throws IOException{ 
		String result ="";
     

		Properties prop = new Properties();//鐏炵偞锟介梿鍡楁値鐎电钖�  
		InputStream path =Thread.currentThread().getContextClassLoader().getResourceAsStream(this.properFile);//閼惧嘲褰囩捄顖氱窞楠炴儼娴嗛幑銏″灇濞达拷
		prop.load(path);//鐏忓棗鐫橀幀褎鏋冩禒鑸电ウ鐟佸懓娴囬崚鐧檙operties鐎电钖勬稉锟�
		
		//System.out.println("Properties娑擃叀骞忓妤冩畱閸氬秴鐡ч敍锟�prop.get("userName"));
		
	    try { 
	    	result=  this.GBF.login(userName, password);
		 
			result= getTokenFromStr(result);
			  
			 return  result;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	   System.out.println(" login1    "+  result);
	    return  "";
		
		
	}
 
 public String login2(String userName,String password) throws IOException{ 
		String result ="";
     

		Properties prop = new Properties();
		InputStream path =Thread.currentThread().getContextClassLoader().getResourceAsStream(this.properFile);//閼惧嘲褰囩捄顖氱窞楠炴儼娴嗛幑銏″灇濞达拷
		prop.load(path);
		
	    try { 
	    	System.out.println(this.wsdlAddress);
			result=  this.GBF.login(userName, password);
			 
			result= getTokenFromStr(result);
			 
			 return  result;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	   System.out.println(" webservice    "+  result);
	    return  "";
		
		
	}

//	IsLastPage
	public String  loadJson(String token , int pageIndex,int pageNumber ) throws IOException{
		
		    String result;
		    result=  this.GBF.getRegisteredDeviceBaseInfo(token, pageIndex, pageNumber); 
			return  result; 
	}
//	IsLastPage
	public String  loadJson2(String token , int pageIndex,int pageNumber ) throws IOException{
		
		    String result;
		    result=  this.GBF.getBasicReaderLasttime(token, pageIndex, pageNumber); 
			return  result; 
	}
	
	public String  loadJson3(String token , int pageIndex,int pageNumber ) throws IOException{
		
		    String result;
		    result=  this.GBF.getClientUserInfo(token, pageIndex, pageNumber); 
			return  result; 
	}

	public String  loadJson4(String token , int pageIndex,int pageNumber ) throws IOException{
		
		    String result;
		    result=  this.GBF.getClientGroupInfo(token, pageIndex, pageNumber); 
			return  result; 
	}
	
public 	static void main(String[] args) throws IOException{
	
	 
	    JsonUtils ju=	new JsonUtils("https://192.168.22.133/tds/VrvWebServer.asmx");
		
		String token= ju.login("wdb", "123456");
	         int	pageNumber=1;
	         int     pageIndex=1;
	         
	         int  IsLastPage=1;//0 means  its the end of  page
	         
	         
	         
		String deviceInfo=ju.loadJson(token,pageIndex,pageNumber);
		
		JSONObject jobj=	ju.str2json(deviceInfo);
		System.out.println(jobj.toString());
		
		
		
		System.out.println(jobj.get("result").toString());
		String result=jobj.get("result").toString();
		
		result=  result.substring(1,result.length()-1);
		JSONArray jArry=	ju.str2JsonArry(result);
		
		for(int i=0;i<jArry.size();i++){
			JSONObject jobjTmp =(JSONObject) jArry.get(i);
			System.out.println(jobjTmp.toString()); 
			System.out.println(jobjTmp.get("f3")); 
		}
		
		
		
//		result=result.replace("null", "\"\"");
//		result=result.replace("\\/", "");
//		result=result.replace("Intel(R) Core(TM) i5-3470 CPU @ 3.20GHz", "");
//		
//		
//		System.out.println(result);
//		  
		//		   byte[] bs = result.getBytes();
//		   try {
//			result=   new String(bs, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		   ju.str2json(result.substring(1,result.length()-1));
			System.out.println("ok");
		//JSONObject resultJobj= ju.str2json("{"+result+"}");
	
		 
	//	System.out.println(resultJobj.toString());
		//JSONArray ja= (JSONArray);
		//System.out.println(((JSONObject)ja.get(0)).get("f1"));
		
	}
	


public JSONArray str2JsonArry(String jsonStr){
	return  JSONArray.fromObject(jsonStr); 
}


public String gbk2utf8(String str){
//閻€劑绮拋銈呯摟缁楋妇绱惍浣叫掗惍浣哥摟缁楋缚瑕嗛妴锟�
//閻€劍鏌婇惃鍕摟缁楋妇绱惍浣烘晸閹存劕鐡х粭锔胯
try {
	byte[] bs = str.getBytes("GBK");
	str=   new String(bs, "UTF-8");
} catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return str;
}


	


public JSONObject str2json(String jsonStr){
	 
	 // String json = "{x:'1',y:'2',userId:'112',element:[{id:'123',name:'haha'},{id:'456',name:'hehe'}]}"; 
		if(jsonStr!=null&&jsonStr.trim().length()>0){
			  JSONObject obj = JSONObject.fromObject(jsonStr);
			   return obj;
		}else{
			  JSONObject obj = JSONObject.fromObject("{}");
			   return obj; 
		}
	 
	   
//	   String x = obj.getString("x");
//	   String userid = obj.getString("userId");
//	   System.out.println("x is:" + x);
//	   System.out.println("userId is:" + userid);
//	  
//	  //  閺佹壆绮峚rray缂佹挻鐏夐敍姝攞"id":"123","name":"haha"},{"id":"456","name":"hehe"}]
//	   JSONArray jsonArray = obj.getJSONArray("element");
//	   for (int i = 0; i < jsonArray.size(); i++) {
//	    System.out.println("element " + i + " :" + jsonArray.get(i));
//	   } 
  
}
 

public String getTokenFromStr(String jsonStr){
	   
	   JSONObject obj = JSONObject.fromObject(jsonStr);
	   return obj.get("result").toString(); 
} 


public JSONArray getJSONObjectArrysReusltFromStr(String jsonStr){
	  
	   JSONObject obj = JSONObject.fromObject(jsonStr);
	   return (JSONArray)obj.get("result"); 
} 
}
