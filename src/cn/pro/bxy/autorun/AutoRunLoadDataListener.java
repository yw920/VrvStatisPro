package cn.pro.bxy.autorun;
 
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AutoRunLoadDataListener implements ServletContextListener {

	private java.util.Timer timer = null ;
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent event) { 
		 
		event.getServletContext().log("定时器已启动。") ;
		 
		//timer.schedule(new AutoRunLoadDataTask(event.getServletContext()), 0, 5000) ;
		try {
			new TimerManager(event.getServletContext());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		event.getServletContext().log("已经添加任务调度表。" ) ; 
	}

}
