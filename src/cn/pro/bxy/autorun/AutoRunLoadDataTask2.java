package cn.pro.bxy.autorun;
 

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Properties;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import cn.pro.bxy.common.SystemUtils;

public class AutoRunLoadDataTask2 extends TimerTask {

	 private static boolean isRunning = false; 
	 private ServletContext context = null; 

	 public AutoRunLoadDataTask2 ( ServletContext context){ 
		 
		 this.context = context ;  
	 }
	@Override
	public void run() {

		if (! isRunning){
			System.out.println("开始运行数据自动获取") ;
				isRunning = true ;
				
				 
				//begain load device data 
				
				context.log("根据配置文件载入数据") ;
				System.out.println("根据配置文件载入数据") ;
			    try {
			    	Properties AllProperties =new SystemUtils().getProperties(); 
					int LimitNum= Integer.parseInt( AllProperties.get("LimitNum").toString().trim());
			    	for(int i = 1; i <= LimitNum; i++){
			    		String ThisProperty = AllProperties.get("List"+i).toString().trim();
			    		System.out.println("第"+i+"个任务，载入配置文件："+ThisProperty);
			    		LoadAndSive a = new LoadAndSive();
						System.out.println("start setProperFile");
				    	a.setProperFile(ThisProperty);
						System.out.println("start doLoadLastNewDateAndSive");
				    	a.doLoadLastNewDateAndSive();
						System.out.println("start doLoadAndSive2");
						a.doLoadAndSive2();
						System.out.println("start doLoadAndSive3");
						a.doLoadAndSive3();
						System.out.println("start doLoadAndSive4");
						a.doLoadAndSive4();
						System.out.println("start doLoadAndSive5");
				    	a.doLoadAndSive5();
						System.out.println("start doLoadAndSive6");
						a.doLoadAndSive6();
			    	}
			    	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					isRunning = false;
					e.printStackTrace();
				}
					  
				
				isRunning = false; 
				context.log("载入数据结束"); 
				System.out.println("载入数据结束") ;

		}else{
			context.log("程序已运行，请不要重复运行"); 
		}
	}
}
