package cn.pro.bxy.common;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MysqTools {
	public static Connection getConn() throws IOException {
		Properties prop = new SystemUtils().getProperties();
		
	    String driver =prop.getProperty("mysqlDriver");// "com.mysql.jdbc.Driver";
	    String url = prop.getProperty("mysqlUrl");//"jdbc:mysql://localhost:3306/vrvaudit";
	    String username = prop.getProperty("mysqlUserName");//"root";
	    String password = prop.getProperty("mysqlPassword");//"";
	    Connection conn = null;
	    
	    
	    
//	    String driver = "com.mysql.jdbc.Driver";
//	    String url = "jdbc:mysql://localhost:3306/vrvaudit";
//	    String username = "root";
//	    String password = "";
//	    Connection conn = null;
	     
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	/*
	 *  功能 ：把lasttime活动的设备信息存到数据库里面去，
	 * 	过程：先取出设备id值    检查该id设备信息在  deviceBaseInfo 表中 并且也在history 中保存该条信息
	 * 
	 * */
	public static int siveActiveDeviceInfo2History( JSONArray jarray,String netType ) throws ParseException, IOException {
		 
	    Connection conn = getConn();

	    int i = 0;
	    String sql = "replace into devicebaseinfohistory (" 
	    		+ "   DeviceID     ,  DevOnlyID    ,UserName  ,DeviceName     , NumIPAddress ,IPAddres   , MacAddress   , DeptName    ,OfficeName   ,DeviceType        , Registered"
	    		+", RunStatus     ,AreaID   , CpuType   , DiskSize      , AntivirusSoftware	,NotInstalledPatch, OSType,UnInstallTime, Description  ,DevTypeCode 	, CommunicatIP ,ExField6"
	    		+",AssetID  , ServicePack , SetupTmos   ,ClientVersion  ,LockStatu	,ProtectStatu,BlockOrNot , Area2 ,SubOffice ,Room	, Tel , Email   , LastTime  ,MemorySize" 
	    		+", CPUSize ,LastOnlineTime ,OnlineMinutes,OnlineFormedTime	,SubHealthStatu,NoBootDays , OnlineTimes ,DiskSerialNumber,DomainName,LogonUserName,ClientUserId,NetType" 
	    		+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        
	        
	        //2015-1-14 22:59:06
	        SimpleDateFormat   f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  
            java.util.Date date ;   
	        for(int j=0;j<jarray.size();j++){ 
	    			JSONObject jobjTmp =(JSONObject) jarray.get(j);
	    			System.out.println(" 开始向数据库插入数据  ");  
	    			System.out.println(jobjTmp.toString()); 
	    			
	    			//if(!new MysqTools().checkIfExsistThisDevice(jobjTmp)){
	    				//如果没有存在  
	    			//	new MysqTools().siveDeviceBaseInfo(jarray);
	    			//} 
	    			
	    			//date = f.parse(jobjTmp.get("f19").toString());
	    			
	    			if( (jobjTmp.get("f19")!=null)&&(!jobjTmp.get("f19").toString().equals("null")) &&(jobjTmp.get("f19").toString()).trim().length()>0  ){ 
	    				date = f.parse(jobjTmp.get("f19").toString());  
	    					 pstmt.setTimestamp(19,new Timestamp(date.getTime()));  
	    			}else
	    				pstmt.setNull(19, Types.INTEGER);
	    			

	    			if( (jobjTmp.get("f26")!=null)&&(!jobjTmp.get("f26").toString().equals("null")) &&(jobjTmp.get("f26").toString()).trim().length()>0  ){ 
	    			  
	    				date =new Date(Long.parseLong(jobjTmp.get("f26").toString().substring(6,jobjTmp.get("f26").toString().length()-2)));
	    				  
	    				String DateStr= f.format(date);
	    				date=f.parse(DateStr); 
	    				pstmt.setTimestamp(26,new Timestamp(date.getTime()));  
	    			}else
	    				pstmt.setNull(26, Types.INTEGER);
	    			
	    			if( (jobjTmp.get("f36")!=null)&&(!jobjTmp.get("f36").toString().equals("null")) &&(jobjTmp.get("f36").toString()).trim().length()>0  ){ 
	    				date =new Date(Long.parseLong(jobjTmp.get("f36").toString().substring(6,jobjTmp.get("f36").toString().length()-2)));
	    				 
	    				String DateStr= f.format(date);
	    				date=f.parse(DateStr); 
	    				pstmt.setTimestamp(36,new Timestamp(date.getTime()));  
	    			}else
	    				pstmt.setNull(36, Types.INTEGER);
 			
//	    			 pstmt.setString(26, (jobjTmp.get("f26")!=null)&&(jobjTmp.get("f26").toString().length()>0)&&(!jobjTmp.get("f26").toString().equals("null")) &&(jobjTmp.get("f26").toString()).trim().length()>0  ? (jobjTmp.get("f26").toString().substring(6,jobjTmp.get("f26").toString().length()-2)) : ("null")   );    // setupdate  /Date(1381976230000)/
//	    			 pstmt.setString(36, (jobjTmp.get("f36")!=null)&&(jobjTmp.get("f36").toString().length()>0)&&(!jobjTmp.get("f36").toString().equals("null")) &&(jobjTmp.get("f36").toString()).trim().length()>0  ? (jobjTmp.get("f36").toString().substring(6,jobjTmp.get("f36").toString().length()-2)) : ("null")   ); //lasttime  /Date(1425498558000)/ 
     	           
	    	        if( (jobjTmp.get("f1")!=null)&&(!jobjTmp.get("f1").toString().equals("null")) &&(jobjTmp.get("f1").toString()).trim().length()>0  ){ pstmt.setInt(1,Integer.parseInt(jobjTmp.get("f1").toString())); } else {pstmt.setNull(1, Types.INTEGER);}//int
	    	        //if( (jobjTmp.get("f2")!=null)&&(!jobjTmp.get("f2").toString().equals("null")) &&(jobjTmp.get("f").toString()).trim().length()>0  ){ pstmt.setInt(2,Integer.parseInt(jobjTmp.get("f2").toString().trim())); } else {pstmt.setNull(2, Types.INTEGER);}//int
	    	        pstmt.setString(2, (jobjTmp.get("f2")!=null)?(jobjTmp.get("f2").toString()):(""));//varchar
		    	     
	    	        pstmt.setString(3, (jobjTmp.get("f3")!=null)?(jobjTmp.get("f3").toString()):(""));//varchar
	    	        pstmt.setString(4, (jobjTmp.get("f4")!=null)?(jobjTmp.get("f4").toString()):(""));//varchar
	    	        //if( (jobjTmp.get("f5")!=null)&&(!jobjTmp.get("f5").toString().equals("null")) &&(jobjTmp.get("f").toString()).trim().length()>0  ){ pstmt.setInt(5,Integer.parseInt(jobjTmp.get("f5").toString())); } else {pstmt.setNull(3, Types.INTEGER);}//int
	    	        pstmt.setString(5, (jobjTmp.get("f5")!=null)?(jobjTmp.get("f5").toString()):(""));//varchar
		    	       
	    	        pstmt.setString(6, (jobjTmp.get("f6")!=null)?(jobjTmp.get("f6").toString()):(""));//varchar
	    	        pstmt.setString(7, (jobjTmp.get("f7")!=null)?(jobjTmp.get("f7").toString()):(""));//varchar
	    	        pstmt.setString(8, (jobjTmp.get("f8")!=null)?(jobjTmp.get("f8").toString()):(""));//varchar
	    	        pstmt.setString(9, (jobjTmp.get("f9")!=null)?(jobjTmp.get("f9").toString()):(""));//varchar
	    	        pstmt.setString(10, (jobjTmp.get("f10")!=null)?(jobjTmp.get("f10").toString()):("")); //varchar
	    	        if( (jobjTmp.get("f11")!=null)&&(!jobjTmp.get("f11").toString().equals("null")) &&(jobjTmp.get("f11").toString()).trim().length()>0  ){ pstmt.setInt(11,Integer.parseInt(jobjTmp.get("f11").toString())); } else {pstmt.setNull(11, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f12")!=null)&&(!jobjTmp.get("f12").toString().equals("null")) &&(jobjTmp.get("f12").toString()).trim().length()>0  ){ pstmt.setInt(12,Integer.parseInt(jobjTmp.get("f12").toString())); } else {pstmt.setNull(12, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f13")!=null)&&(!jobjTmp.get("f13").toString().equals("null")) &&(jobjTmp.get("f13").toString()).trim().length()>0  ){ pstmt.setInt(13,Integer.parseInt(jobjTmp.get("f13").toString())); } else {pstmt.setNull(13, Types.INTEGER);} //int
	    	        pstmt.setString(14, (jobjTmp.get("f14")!=null)?(jobjTmp.get("f14").toString()):("")); //varchar
	    	        if( (jobjTmp.get("f15")!=null)&&(!jobjTmp.get("f15").toString().equals("null")) &&(jobjTmp.get("f15").toString()).trim().length()>0  ){ pstmt.setInt(15,Integer.parseInt(jobjTmp.get("f15").toString())); } else {pstmt.setNull(15, Types.INTEGER);} //int
	    	        pstmt.setString(16, (jobjTmp.get("f16")!=null)?(jobjTmp.get("f16").toString()):("")); //varchar
	    	        pstmt.setString(17, (jobjTmp.get("f17")!=null)?(jobjTmp.get("f17").toString()):("")); //varchar
	    	        pstmt.setString(18, (jobjTmp.get("f18")!=null)?(jobjTmp.get("f18").toString()):("")); //varchar
 
	    	        
	    	        pstmt.setString(20, (jobjTmp.get("f20")!=null)?(jobjTmp.get("f20").toString()):("")); //varchar
	    	        if( (jobjTmp.get("f21")!=null)&&(!jobjTmp.get("f21").toString().equals("null")) &&(jobjTmp.get("f21").toString()).trim().length()>0  ){ pstmt.setInt(21,Integer.parseInt(jobjTmp.get("f21").toString())); } else {pstmt.setNull(21, Types.INTEGER);}//int
	                pstmt.setString(22, (jobjTmp.get("f22")!=null)?(jobjTmp.get("f22").toString()):("")); //varchar
	    	        if( (jobjTmp.get("f23")!=null)&&(!jobjTmp.get("f23").toString().equals("null")) &&(jobjTmp.get("f23").toString()).trim().length()>0  ){ pstmt.setInt(23,Integer.parseInt(jobjTmp.get("f23").toString())); } else {pstmt.setNull(23, Types.INTEGER);} //int
	    	        pstmt.setString(24, (jobjTmp.get("f24")!=null)?(jobjTmp.get("f24").toString()):("")); //varchar
	    	        pstmt.setString(25, (jobjTmp.get("f25")!=null)?(jobjTmp.get("f25").toString()):("")); //varchar
	    	        pstmt.setString(27, (jobjTmp.get("f27")!=null)?(jobjTmp.get("f27").toString()):("")); //varchar
	    	        if( (jobjTmp.get("f28")!=null)&&(!jobjTmp.get("f28").toString().equals("null")) &&(jobjTmp.get("f28").toString()).trim().length()>0  ){ pstmt.setInt(28,Integer.parseInt(jobjTmp.get("f28").toString())); } else {pstmt.setNull(28, Types.INTEGER);}  //int
	    	        if( (jobjTmp.get("f29")!=null)&&(!jobjTmp.get("f29").toString().equals("null")) &&(jobjTmp.get("f29").toString()).trim().length()>0  ){ pstmt.setInt(29,Integer.parseInt(jobjTmp.get("f29").toString())); } else {pstmt.setNull(29, Types.INTEGER);}//int
	    	        if( (jobjTmp.get("f30")!=null)&&(!jobjTmp.get("f30").toString().equals("null")) &&(jobjTmp.get("f30").toString()).trim().length()>0  ){ pstmt.setInt(30,Integer.parseInt(jobjTmp.get("f30").toString())); } else {pstmt.setNull(30, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f31")!=null)&&(!jobjTmp.get("f31").toString().equals("null")) &&(jobjTmp.get("f31").toString()).trim().length()>0  ){ pstmt.setInt(31,Integer.parseInt(jobjTmp.get("f31").toString())); } else {pstmt.setNull(31, Types.INTEGER);} //int
	    	        pstmt.setString(32, (jobjTmp.get("f32")!=null)?(jobjTmp.get("f32").toString()):("")); //varchar
	    	        pstmt.setString(33, (jobjTmp.get("f33")!=null)?(jobjTmp.get("f33").toString()):("")); //varchar
	    	        // if( (jobjTmp.get("f33")!=null)&&(!jobjTmp.get("f33").toString().equals("null")) &&(jobjTmp.get("f33").toString()).trim().length()>0  ){ pstmt.setInt(33,Integer.parseInt(jobjTmp.get("f33").toString())); } else {pstmt.setNull(33, Types.INTEGER);} //int
	    	        pstmt.setString(34, (jobjTmp.get("f34")!=null)?(jobjTmp.get("f34").toString()):("")); //varchar
	    	        pstmt.setString(35, (jobjTmp.get("f35")!=null)?(jobjTmp.get("f35").toString()):("")); //varchar
	    	        
	    	        
	    	        if( (jobjTmp.get("f37")!=null)&&(!jobjTmp.get("f37").toString().equals("null")) &&(jobjTmp.get("f37").toString()).trim().length()>0  ){ pstmt.setInt(37,Integer.parseInt(jobjTmp.get("f37").toString())); } else {pstmt.setNull(37, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f38")!=null)&&(!jobjTmp.get("f38").toString().equals("null")) &&(jobjTmp.get("f38").toString()).trim().length()>0  ){ pstmt.setInt(38,Integer.parseInt(jobjTmp.get("f38").toString())); } else {pstmt.setNull(38, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f39")!=null)&&(!jobjTmp.get("f39").toString().equals("null")) &&(jobjTmp.get("f39").toString()).trim().length()>0  ){ pstmt.setInt(39,Integer.parseInt(jobjTmp.get("f39").toString())); } else {pstmt.setNull(39, Types.INTEGER);}
	    	         
	    	        if( (jobjTmp.get("f40")!=null)&&(!jobjTmp.get("f40").toString().equals("null")) &&(jobjTmp.get("f40").toString()).trim().length()>0  ){ pstmt.setInt(40,Integer.parseInt(jobjTmp.get("f40").toString())); } else {pstmt.setNull(40, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f41")!=null)&&(!jobjTmp.get("f41").toString().equals("null")) &&(jobjTmp.get("f41").toString()).trim().length()>0  ){ pstmt.setInt(41,Integer.parseInt(jobjTmp.get("f41").toString())); } else {pstmt.setNull(41, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f42")!=null)&&(!jobjTmp.get("f42").toString().equals("null")) &&(jobjTmp.get("f42").toString()).trim().length()>0  ){ pstmt.setInt(42,Integer.parseInt(jobjTmp.get("f42").toString())); } else {pstmt.setNull(42, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f43")!=null)&&(!jobjTmp.get("f43").toString().equals("null")) &&(jobjTmp.get("f43").toString()).trim().length()>0  ){ pstmt.setInt(43,Integer.parseInt(jobjTmp.get("f43").toString())); } else {pstmt.setNull(43, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f44")!=null)&&(!jobjTmp.get("f44").toString().equals("null")) &&(jobjTmp.get("f44").toString()).trim().length()>0  ){ pstmt.setInt(44,Integer.parseInt(jobjTmp.get("f44").toString())); } else {pstmt.setNull(44, Types.INTEGER);} //int  
	    	        if( (jobjTmp.get("f45")!=null)&&(!jobjTmp.get("f45").toString().equals("null")) &&(jobjTmp.get("f45").toString()).trim().length()>0  ){ pstmt.setInt(45,Integer.parseInt(jobjTmp.get("f45").toString())); } else {pstmt.setNull(45, Types.INTEGER);} //int 
	    	        pstmt.setString(46, (jobjTmp.get("f46")!=null)?(jobjTmp.get("f46").toString()):("")); //varchar
	    	        pstmt.setString(47, (jobjTmp.get("f47")!=null)?(jobjTmp.get("f47").toString()):("")); //varchar
	    	        pstmt.setString(48, (jobjTmp.get("f48")!=null)?(jobjTmp.get("f48").toString()):("")); //varchar
	    	        pstmt.setString(49, (netType!=null&&netType.trim().length()>0)?(netType):("")); //varchar
	    	         
	    	        i = pstmt.executeUpdate();
	    	        
	        } 
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	

	public static int siveDeviceBaseInfo( JSONArray jarray,String netType ) throws ParseException, IOException {
		
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "replace into devicebaseinfo (" 
	    		+ "   DeviceID     ,  DevOnlyID    ,UserName  ,DeviceName     , NumIPAddress ,IPAddres   , MacAddress   , DeptName    ,OfficeName   ,DeviceType        , Registered"
	    		+", RunStatus     ,AreaID   , CpuType   , DiskSize      , AntivirusSoftware	,NotInstalledPatch, OSType,UnInstallTime, Description  ,DevTypeCode 	, CommunicatIP ,ExField6"
	    		+",AssetID  , ServicePack , SetupTmos   ,ClientVersion  ,LockStatu	,ProtectStatu,BlockOrNot , Area2 ,SubOffice ,Room	, Tel , Email   , LastTime  ,MemorySize" 
	    		+", CPUSize ,LastOnlineTime ,OnlineMinutes,OnlineFormedTime	,SubHealthStatu,NoBootDays , OnlineTimes ,DiskSerialNumber,DomainName,LogonUserName,ClientUserId,NetType" 
	    		+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        
	        
	        //2015-1-14 22:59:06
	        SimpleDateFormat   f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  
             java.util.Date date ;   
	        for(int j=0;j<jarray.size();j++){ 
	    			JSONObject jobjTmp =(JSONObject) jarray.get(j);
	    			System.out.println(" 开始向数据库插入数据  ");  
	    			System.out.println(jobjTmp.toString()); 
	    			//date = f.parse(jobjTmp.get("f19").toString());
	    			
	    			if( (jobjTmp.get("f19")!=null)&&(!jobjTmp.get("f19").toString().equals("null")) &&(jobjTmp.get("f19").toString()).trim().length()>0  ){ 
	    				date = f.parse(jobjTmp.get("f19").toString());  
	    					 pstmt.setTimestamp(19,new Timestamp(date.getTime()));  
	    			}else
	    				pstmt.setNull(19, Types.INTEGER);
	    			

	    			if( (jobjTmp.get("f26")!=null)&&(!jobjTmp.get("f26").toString().equals("null")) &&(jobjTmp.get("f26").toString()).trim().length()>0  ){ 
	    			  
	    				date =new Date(Long.parseLong(jobjTmp.get("f26").toString().substring(6,jobjTmp.get("f26").toString().length()-2)));
	    				  
	    				String DateStr= f.format(date);
	    				date=f.parse(DateStr); 
	    				pstmt.setTimestamp(26,new Timestamp(date.getTime()));  
	    			}else
	    				pstmt.setNull(26, Types.INTEGER);
	    			
	    			if( (jobjTmp.get("f36")!=null)&&(!jobjTmp.get("f36").toString().equals("null")) &&(jobjTmp.get("f36").toString()).trim().length()>0  ){ 
	    				date =new Date(Long.parseLong(jobjTmp.get("f36").toString().substring(6,jobjTmp.get("f36").toString().length()-2)));
	    				 
	    				String DateStr= f.format(date);
	    				date=f.parse(DateStr); 
	    				pstmt.setTimestamp(36,new Timestamp(date.getTime()));  
	    			}else
	    				pstmt.setNull(36, Types.INTEGER);
 			
//	    			 pstmt.setString(26, (jobjTmp.get("f26")!=null)&&(jobjTmp.get("f26").toString().length()>0)&&(!jobjTmp.get("f26").toString().equals("null")) &&(jobjTmp.get("f26").toString()).trim().length()>0  ? (jobjTmp.get("f26").toString().substring(6,jobjTmp.get("f26").toString().length()-2)) : ("null")   );    // setupdate  /Date(1381976230000)/
//	    			 pstmt.setString(36, (jobjTmp.get("f36")!=null)&&(jobjTmp.get("f36").toString().length()>0)&&(!jobjTmp.get("f36").toString().equals("null")) &&(jobjTmp.get("f36").toString()).trim().length()>0  ? (jobjTmp.get("f36").toString().substring(6,jobjTmp.get("f36").toString().length()-2)) : ("null")   ); //lasttime  /Date(1425498558000)/ 
     	           
	    	        if( (jobjTmp.get("f1")!=null)&&(!jobjTmp.get("f1").toString().equals("null")) &&(jobjTmp.get("f1").toString()).trim().length()>0  ){ pstmt.setInt(1,Integer.parseInt(jobjTmp.get("f1").toString())); } else {pstmt.setNull(1, Types.INTEGER);}//int
	    	        //if( (jobjTmp.get("f2")!=null)&&(!jobjTmp.get("f2").toString().equals("null")) &&(jobjTmp.get("f").toString()).trim().length()>0  ){ pstmt.setInt(2,Integer.parseInt(jobjTmp.get("f2").toString().trim())); } else {pstmt.setNull(2, Types.INTEGER);}//int
	    	        pstmt.setString(2, (jobjTmp.get("f2")!=null)?(jobjTmp.get("f2").toString()):(""));//varchar
		    	     
	    	        pstmt.setString(3, (jobjTmp.get("f3")!=null)?(jobjTmp.get("f3").toString()):(""));//varchar
	    	        pstmt.setString(4, (jobjTmp.get("f4")!=null)?(jobjTmp.get("f4").toString()):(""));//varchar
	    	        //if( (jobjTmp.get("f5")!=null)&&(!jobjTmp.get("f5").toString().equals("null")) &&(jobjTmp.get("f").toString()).trim().length()>0  ){ pstmt.setInt(5,Integer.parseInt(jobjTmp.get("f5").toString())); } else {pstmt.setNull(3, Types.INTEGER);}//int
	    	        pstmt.setString(5, (jobjTmp.get("f5")!=null)?(jobjTmp.get("f5").toString()):(""));//varchar
		    	       
	    	        pstmt.setString(6, (jobjTmp.get("f6")!=null)?(jobjTmp.get("f6").toString()):(""));//varchar
	    	        pstmt.setString(7, (jobjTmp.get("f7")!=null)?(jobjTmp.get("f7").toString()):(""));//varchar
	    	        pstmt.setString(8, (jobjTmp.get("f8")!=null)?(jobjTmp.get("f8").toString()):(""));//varchar
	    	        pstmt.setString(9, (jobjTmp.get("f9")!=null)?(jobjTmp.get("f9").toString()):(""));//varchar
	    	        pstmt.setString(10, (jobjTmp.get("f10")!=null)?(jobjTmp.get("f10").toString()):("")); //varchar
	    	        if( (jobjTmp.get("f11")!=null)&&(!jobjTmp.get("f11").toString().equals("null")) &&(jobjTmp.get("f11").toString()).trim().length()>0  ){ pstmt.setInt(11,Integer.parseInt(jobjTmp.get("f11").toString())); } else {pstmt.setNull(11, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f12")!=null)&&(!jobjTmp.get("f12").toString().equals("null")) &&(jobjTmp.get("f12").toString()).trim().length()>0  ){ pstmt.setInt(12,Integer.parseInt(jobjTmp.get("f12").toString())); } else {pstmt.setNull(12, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f13")!=null)&&(!jobjTmp.get("f13").toString().equals("null")) &&(jobjTmp.get("f13").toString()).trim().length()>0  ){ pstmt.setInt(13,Integer.parseInt(jobjTmp.get("f13").toString())); } else {pstmt.setNull(13, Types.INTEGER);} //int
	    	        pstmt.setString(14, (jobjTmp.get("f14")!=null)?(jobjTmp.get("f14").toString()):("")); //varchar
	    	        if( (jobjTmp.get("f15")!=null)&&(!jobjTmp.get("f15").toString().equals("null")) &&(jobjTmp.get("f15").toString()).trim().length()>0  ){ pstmt.setInt(15,Integer.parseInt(jobjTmp.get("f15").toString())); } else {pstmt.setNull(15, Types.INTEGER);} //int
	    	        pstmt.setString(16, (jobjTmp.get("f16")!=null)?(jobjTmp.get("f16").toString()):("")); //varchar
	    	        pstmt.setString(17, (jobjTmp.get("f17")!=null)?(jobjTmp.get("f17").toString()):("")); //varchar
	    	        pstmt.setString(18, (jobjTmp.get("f18")!=null)?(jobjTmp.get("f18").toString()):("")); //varchar
 
	    	        
	    	        pstmt.setString(20, (jobjTmp.get("f20")!=null)?(jobjTmp.get("f20").toString()):("")); //varchar
	    	        if( (jobjTmp.get("f21")!=null)&&(!jobjTmp.get("f21").toString().equals("null")) &&(jobjTmp.get("f21").toString()).trim().length()>0  ){ pstmt.setInt(21,Integer.parseInt(jobjTmp.get("f21").toString())); } else {pstmt.setNull(21, Types.INTEGER);}//int
	                pstmt.setString(22, (jobjTmp.get("f22")!=null)?(jobjTmp.get("f22").toString()):("")); //varchar
	    	        if( (jobjTmp.get("f23")!=null)&&(!jobjTmp.get("f23").toString().equals("null")) &&(jobjTmp.get("f23").toString()).trim().length()>0  ){ pstmt.setInt(23,Integer.parseInt(jobjTmp.get("f23").toString())); } else {pstmt.setNull(23, Types.INTEGER);} //int
	    	        pstmt.setString(24, (jobjTmp.get("f24")!=null)?(jobjTmp.get("f24").toString()):("")); //varchar
	    	        pstmt.setString(25, (jobjTmp.get("f25")!=null)?(jobjTmp.get("f25").toString()):("")); //varchar
	    	        pstmt.setString(27, (jobjTmp.get("f27")!=null)?(jobjTmp.get("f27").toString()):("")); //varchar
	    	        if( (jobjTmp.get("f28")!=null)&&(!jobjTmp.get("f28").toString().equals("null")) &&(jobjTmp.get("f28").toString()).trim().length()>0  ){ pstmt.setInt(28,Integer.parseInt(jobjTmp.get("f28").toString())); } else {pstmt.setNull(28, Types.INTEGER);}  //int
	    	        if( (jobjTmp.get("f29")!=null)&&(!jobjTmp.get("f29").toString().equals("null")) &&(jobjTmp.get("f29").toString()).trim().length()>0  ){ pstmt.setInt(29,Integer.parseInt(jobjTmp.get("f29").toString())); } else {pstmt.setNull(29, Types.INTEGER);}//int
	    	        if( (jobjTmp.get("f30")!=null)&&(!jobjTmp.get("f30").toString().equals("null")) &&(jobjTmp.get("f30").toString()).trim().length()>0  ){ pstmt.setInt(30,Integer.parseInt(jobjTmp.get("f30").toString())); } else {pstmt.setNull(30, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f31")!=null)&&(!jobjTmp.get("f31").toString().equals("null")) &&(jobjTmp.get("f31").toString()).trim().length()>0  ){ pstmt.setInt(31,Integer.parseInt(jobjTmp.get("f31").toString())); } else {pstmt.setNull(31, Types.INTEGER);} //int
	    	        pstmt.setString(32, (jobjTmp.get("f32")!=null)?(jobjTmp.get("f32").toString()):("")); //varchar
	    	        pstmt.setString(33, (jobjTmp.get("f33")!=null)?(jobjTmp.get("f33").toString()):("")); //varchar
	    	       // if( (jobjTmp.get("f33")!=null)&&(!jobjTmp.get("f33").toString().equals("null")) &&(jobjTmp.get("f33").toString()).trim().length()>0  ){ pstmt.setInt(33,Integer.parseInt(jobjTmp.get("f33").toString())); } else {pstmt.setNull(33, Types.INTEGER);} //int
	    	        pstmt.setString(34, (jobjTmp.get("f34")!=null)?(jobjTmp.get("f34").toString()):("")); //varchar
	    	        pstmt.setString(35, (jobjTmp.get("f35")!=null)?(jobjTmp.get("f35").toString()):("")); //varchar
	    	        
	    	        
	    	        if( (jobjTmp.get("f37")!=null)&&(!jobjTmp.get("f37").toString().equals("null")) &&(jobjTmp.get("f37").toString()).trim().length()>0  ){ pstmt.setInt(37,Integer.parseInt(jobjTmp.get("f37").toString())); } else {pstmt.setNull(37, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f38")!=null)&&(!jobjTmp.get("f38").toString().equals("null")) &&(jobjTmp.get("f38").toString()).trim().length()>0  ){ pstmt.setInt(38,Integer.parseInt(jobjTmp.get("f38").toString())); } else {pstmt.setNull(38, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f39")!=null)&&(!jobjTmp.get("f39").toString().equals("null")) &&(jobjTmp.get("f39").toString()).trim().length()>0  ){ pstmt.setInt(39,Integer.parseInt(jobjTmp.get("f39").toString())); } else {pstmt.setNull(39, Types.INTEGER);}
	    	         
	    	        if( (jobjTmp.get("f40")!=null)&&(!jobjTmp.get("f40").toString().equals("null")) &&(jobjTmp.get("f40").toString()).trim().length()>0  ){ pstmt.setInt(40,Integer.parseInt(jobjTmp.get("f40").toString())); } else {pstmt.setNull(40, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f41")!=null)&&(!jobjTmp.get("f41").toString().equals("null")) &&(jobjTmp.get("f41").toString()).trim().length()>0  ){ pstmt.setInt(41,Integer.parseInt(jobjTmp.get("f41").toString())); } else {pstmt.setNull(41, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f42")!=null)&&(!jobjTmp.get("f42").toString().equals("null")) &&(jobjTmp.get("f42").toString()).trim().length()>0  ){ pstmt.setInt(42,Integer.parseInt(jobjTmp.get("f42").toString())); } else {pstmt.setNull(42, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f43")!=null)&&(!jobjTmp.get("f43").toString().equals("null")) &&(jobjTmp.get("f43").toString()).trim().length()>0  ){ pstmt.setInt(43,Integer.parseInt(jobjTmp.get("f43").toString())); } else {pstmt.setNull(43, Types.INTEGER);} //int
	    	        if( (jobjTmp.get("f44")!=null)&&(!jobjTmp.get("f44").toString().equals("null")) &&(jobjTmp.get("f44").toString()).trim().length()>0  ){ pstmt.setInt(44,Integer.parseInt(jobjTmp.get("f44").toString())); } else {pstmt.setNull(44, Types.INTEGER);} //int  
	    	        if( (jobjTmp.get("f45")!=null)&&(!jobjTmp.get("f45").toString().equals("null")) &&(jobjTmp.get("f45").toString()).trim().length()>0  ){ pstmt.setInt(45,Integer.parseInt(jobjTmp.get("f45").toString())); } else {pstmt.setNull(45, Types.INTEGER);} //int 
	    	        pstmt.setString(46, (jobjTmp.get("f46")!=null)?(jobjTmp.get("f46").toString()):("")); //varchar
	    	        pstmt.setString(47, (jobjTmp.get("f47")!=null)?(jobjTmp.get("f47").toString()):("")); //varchar
	    	        pstmt.setString(48, (jobjTmp.get("f48")!=null)?(jobjTmp.get("f48").toString()):("")); //varchar
	    	        pstmt.setString(49, (netType!=null&&netType.trim().length()>0)?(netType):("")); //varchar
	    	         
	    	        i = pstmt.executeUpdate();
	    	        
	        } 
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	

	public static int siveClientUser( JSONArray jarray ) throws ParseException, IOException {
		 
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "replace into ClientUser (" 
	    		+ "CuserName,CuserPass,CuserType,CurrentIP ,CuserTel, UnitName,DeptName,OfficeName, AddUser"
	    		+", LastLoginTime ,LastLoginDevice, EverUseDevice, Description, Reserve1,Reserve2, EmailAddress, GroupID, GroupName" 
	    		+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        //2015-1-14 22:59:06
	        SimpleDateFormat   f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date;   
	        for(int j=0;j<jarray.size();j++){ 
	    			JSONObject jobjTmp =(JSONObject) jarray.get(j);
	    			System.out.println(" 开始向数据库插入数据  ");  
	    			System.out.println(jobjTmp.toString()); 
	    			//date = f.parse(jobjTmp.get("f19").toString());
	    			
	    			
	    	        pstmt.setString(1, (jobjTmp.get("CuserName")!=null)?(jobjTmp.get("CuserName").toString()):(""));//varchar
		    	    pstmt.setString(2, (jobjTmp.get("CuserPass")!=null)?(jobjTmp.get("CuserPass").toString()):(""));//varchar
	    	        pstmt.setString(3, (jobjTmp.get("CuserType")!=null)?(jobjTmp.get("CuserType").toString()):(""));//varchar
	    	        pstmt.setString(4, (jobjTmp.get("CurrentIP")!=null)?(jobjTmp.get("CurrentIP").toString()):(""));//varchar
	    	        pstmt.setString(5, (jobjTmp.get("CuserTel")!=null)?(jobjTmp.get("CuserTel").toString()):(""));//varchar
	    	        pstmt.setString(6, (jobjTmp.get("UnitName")!=null)?(jobjTmp.get("UnitName").toString()):(""));//varchar
	    	        pstmt.setString(7, (jobjTmp.get("DeptName")!=null)?(jobjTmp.get("DeptName").toString()):(""));//varchar
	    	        pstmt.setString(8, (jobjTmp.get("OfficeName")!=null)?(jobjTmp.get("OfficeName").toString()):("")); //varchar
	    	        pstmt.setString(9, (jobjTmp.get("AddUser")!=null)?(jobjTmp.get("AddUser").toString()):("")); //varchar
	    	        
	    	        if( (jobjTmp.get("LastLoginTime")!=null)&&(!jobjTmp.get("LastLoginTime").toString().equals("null")) &&(jobjTmp.get("LastLoginTime").toString()).trim().length()>0  ){ 
		    			  
	    				date =new Date(Long.parseLong(jobjTmp.get("LastLoginTime").toString().substring(6,jobjTmp.get("LastLoginTime").toString().length()-2)));
	    				  
	    				String DateStr= f.format(date);
	    				date=f.parse(DateStr); 
	    				pstmt.setTimestamp(10,new Timestamp(date.getTime()));  
	    			}else
	    				pstmt.setNull(10, Types.INTEGER);
	    			
	    	        
	    	        pstmt.setString(11, (jobjTmp.get("LastLoginDevice")!=null)?(jobjTmp.get("LastLoginDevice").toString()):("")); //varchar
	    	        pstmt.setString(12, (jobjTmp.get("EverUseDevice")!=null)?(jobjTmp.get("EverUseDevice").toString()):("")); //varchar
	    	        pstmt.setString(13, (jobjTmp.get("Description")!=null)?(jobjTmp.get("Description").toString()):("")); //varchar
	    	        pstmt.setString(14, (jobjTmp.get("Reserve1")!=null)?(jobjTmp.get("Reserve1").toString()):("")); //varchar
	    	        pstmt.setString(15, (jobjTmp.get("Reserve2")!=null)?(jobjTmp.get("Reserve2").toString()):("")); //varchar
	    	        pstmt.setString(16, (jobjTmp.get("EmailAddress")!=null)?(jobjTmp.get("EmailAddress").toString()):("")); //varchar
	    	        pstmt.setString(17, (jobjTmp.get("GroupID")!=null)?(jobjTmp.get("GroupID").toString()):("")); //varchar
	    	        pstmt.setString(18, (jobjTmp.get("GroupName")!=null)?(jobjTmp.get("GroupName").toString()):("")); //varchar

	    	        i = pstmt.executeUpdate();
	        } 
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}

	public static int siveClientGroup( JSONArray jarray ) throws ParseException, IOException {
		 
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "replace into ClientGroup (" 
	    		+ "GroupID,GroupName,CreateUser,CreateTime ,RepTime, UpGroup,GroupDistr,Description, GroupType"
	    		+ ") values(?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        //2015-1-14 22:59:06
	        SimpleDateFormat   f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date;   
	        for(int j=0;j<jarray.size();j++){ 
	    			JSONObject jobjTmp =(JSONObject) jarray.get(j);
	    			System.out.println(" 开始向数据库插入数据  ");  
	    			System.out.println(jobjTmp.toString()); 
	    			//date = f.parse(jobjTmp.get("f19").toString());
	    			
	    			if( (jobjTmp.get("GroupID")!=null)&&(!jobjTmp.get("GroupID").toString().equals("null")) &&(jobjTmp.get("GroupID").toString()).trim().length()>0  ){ pstmt.setInt(1,Integer.parseInt(jobjTmp.get("GroupID").toString())); } else {pstmt.setNull(1, Types.INTEGER);} //int
	    	        pstmt.setString(2, (jobjTmp.get("GroupName")!=null)?(jobjTmp.get("GroupName").toString()):(""));//varchar
	    	        pstmt.setString(3, (jobjTmp.get("CreateUser")!=null)?(jobjTmp.get("CreateUser").toString()):(""));//varchar
	    	        pstmt.setString(4, (jobjTmp.get("CreateTime")!=null)&&(!jobjTmp.get("CreateTime").equals(""))?(jobjTmp.get("CreateTime").toString()):("0000-00-00 00:00:00"));//varchar
	    	        pstmt.setString(5, (jobjTmp.get("RepTime")!=null)&&(!jobjTmp.get("RepTime").equals(""))?(jobjTmp.get("RepTime").toString()):("0000-00-00 00:00:00"));//varchar
	    	        /*if( (jobjTmp.get("CreateTime")!=null)&&(!jobjTmp.get("CreateTime").toString().equals("null")) &&(jobjTmp.get("CreateTime").toString()).trim().length()>0  ){ 
		    			  
	    				date =new Date(Long.parseLong(jobjTmp.get("CreateTime").toString().substring(6,jobjTmp.get("CreateTime").toString().length()-2)));
	    				  
	    				String DateStr= f.format(date);
	    				date=f.parse(DateStr); 
	    				pstmt.setTimestamp(4,new Timestamp(date.getTime()));  
	    			}else
	    				pstmt.setNull(4, Types.INTEGER);*/
	    	        /*if( (jobjTmp.get("RepTime")!=null)&&(!jobjTmp.get("RepTime").toString().equals("null")) &&(jobjTmp.get("RepTime").toString()).trim().length()>0  ){ 
		    			  
	    				date =new Date(Long.parseLong(jobjTmp.get("RepTime").toString().substring(6,jobjTmp.get("RepTime").toString().length()-2)));
	    				  
	    				String DateStr= f.format(date);
	    				date=f.parse(DateStr); 
	    				pstmt.setTimestamp(5,new Timestamp(date.getTime()));  
	    			}else
	    				pstmt.setNull(5, Types.INTEGER);*/
	    			
	    	        if( (jobjTmp.get("UpGroup")!=null)&&(!jobjTmp.get("UpGroup").toString().equals("null")) &&(jobjTmp.get("UpGroup").toString()).trim().length()>0  ){ pstmt.setInt(6,Integer.parseInt(jobjTmp.get("UpGroup").toString())); } else {pstmt.setNull(6, Types.INTEGER);} //int
	    	        pstmt.setString(7, (jobjTmp.get("GroupDistr")!=null)?(jobjTmp.get("GroupDistr").toString()):(""));//varchar
	    	        pstmt.setString(8, (jobjTmp.get("Description")!=null)?(jobjTmp.get("Description").toString()):("")); //varchar
	    	        pstmt.setString(9, (jobjTmp.get("GroupType")!=null)?(jobjTmp.get("GroupType").toString()):("")); //varchar
	    	        
	    	        i = pstmt.executeUpdate();
	        } 
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	public static int SetUserGroupId( ) throws ParseException, IOException {
		 
	    Connection conn = getConn();
	    int i = 0;
	    
	    String sql = "update clientuser inner join ClientGroup "+
	                 "set clientuser.GroupId = ClientGroup.GroupID "+
	    		     "where (clientuser.GroupId is null or clientuser.GroupId=0) and ClientGroup.groupdistr = substring(clientuser.description,13)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	public static int SetDeviceGroup( ) throws ParseException, IOException {
		 
	    Connection conn = getConn();
	    int i = 0;
	    
	    String sql = "update devicebaseinfo inner join clientuser "+
	                 "set devicebaseinfo.ClientGroupId = clientuser.GroupID "+
	    		     "where (devicebaseinfo.ClientGroupId is null or devicebaseinfo.ClientGroupId=0) and devicebaseinfo.ClientUserId = clientuser.CuserName";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}

	public static int SetDevicehistoryGroup( ) throws ParseException, IOException {
		 
	    Connection conn = getConn();
	    int i = 0;
	    
	    String sql = "update devicebaseinfohistory inner join clientuser "+
	                 "set devicebaseinfohistory.ClientGroupId = clientuser.GroupID "+
	    		     "where (devicebaseinfohistory.ClientGroupId is null or devicebaseinfohistory.ClientGroupId=0) and devicebaseinfohistory.ClientUserId = clientuser.CuserName";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	public static int SetLoginfo( String Optype ) throws ParseException, IOException {
		 
	    Connection conn = getConn();
	    int i = 0;
	    
	    String sql = "insert into loginfo(optype)  "+
	                 "values ('"+Optype+"') ";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	//只是为了测试这个类而设置的  没有用 
	public static void main(String[]args) throws ParseException, IOException{
		
		 Connection conn = getConn();
		    
		    
		    String queryStr="select * from devicebaseinfo where DeviceID=171 ";
		    
		    try {
				
		    	 ResultSet rs=conn.createStatement().executeQuery(queryStr);
		    	 while(rs.next()){
		    		 System.out.println(rs.getInt("DeviceID"));
			        }
		    	  
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		    
		    
		
		
		
		//Integer.valueOf("3111111111");
		//Integer.parseInt("3231226333");
		siveDeviceBaseInfo(  constructJArrayForTest(), "内网" );
	}
	 
	public static JSONArray  constructJArrayForTest(){
		JSONArray jarray;
		String str="[{'f1':'16',";
		for (int i=2;i<45;i++){
			if(i==19) 
				str=str+"'f"+i+"':'2015-1-14 22:59:06',";
			else 
				str=str+"'f"+i+"':'2015-1-14 22:59:06',";
		}
		str=str+"'f45':'中文'}]"; 
		
		String strw="[{\"f1\":171,\"f2\":9377479,\"f3\":\"测试\",\"f4\":\"NVTC8PMREMWCSPA\",\"f5\":236333,\"f6\":\"192.168.3.45\",\"f7\":\"94DE80D69B70\",\"f8\":\"11\",\"f9\":\"test180\",\"f10\":null,\"f11\":1,\"f12\":1,\"f13\":1,\"f14\":\"Intel(R) Core(TM) i5-3470 CPU @ 3.20GHz\",\"f15\":953865,\"f16\":\"\",\"f17\":null,\"f18\":\"Windows XP Professional (2600.3.0)\",\"f19\":\"2015-1-14 22:59:06\",\"f20\":null,\"f21\":1,\"f22\":\"192.168.3.45\",\"f23\":0,\"f24\":\"11\",\"f25\":\"SP3\",\"f26\":\"\\/Date(1381976230000)\\/\",\"f27\":\"6.6.1410.01\",\"f28\":0,\"f29\":0,\"f30\":0,\"f31\":1,\"f32\":\"\",\"f33\":\"11\",\"f34\":\"11\",\"f35\":\"11\",\"f36\":\"\\/Date(1425498558000)\\/\",\"f37\":3520,\"f38\":3200,\"f39\":null,\"f40\":30773,\"f41\":\"810\",\"f42\":0,\"f43\":18,\"f44\":810,\"f46\":\"--\",\"f47\":\"Administrator\"}]";
		
		System.out.println("数据构造完成 ： "+str);
		jarray=JSONArray.fromObject(strw);
		return jarray; 
	}
	
	public Boolean checkIfExsistThisDevice(JSONObject jobj ) throws IOException{

		
		int deviceId= Integer.parseInt(jobj.get("f1").toString().trim());
		
		 
	    String queryStr="select * from devicebaseinfo where DeviceID="+deviceId;
	    Connection conn = getConn();
		 
	    try { 
	    	 ResultSet rs=conn.createStatement().executeQuery(queryStr); 
	    	 while(rs.next()){ 
	    		if( rs.getInt("DeviceID")== deviceId) 
	    			System.out.println("该设备信息已经存在  将把该数据作为历史数据存储  "); 
	    			return true; 
		        } 
		     return false;  
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return false; 
	}
	
	
	
	
}
