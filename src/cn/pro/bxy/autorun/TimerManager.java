package cn.pro.bxy.autorun;
 
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;

import javax.servlet.ServletContext;

import cn.pro.bxy.common.SystemUtils;

/**
 * 任务管理
 * @author wengjiang
 *  晚上11：30点执行 取数据操作
 */
public class TimerManager {
	 
	private ServletContext context = null;  
	//时间间隔(一天)
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	public TimerManager(  ServletContext context) throws IOException {
		 this.context = context ;  
		
		Calendar calendar = Calendar.getInstance();
		long nowTimeMilliseconds =calendar.getTimeInMillis();
		 
		Properties properties =new SystemUtils().getProperties(); 
		int hour= Integer.parseInt( properties.get("autoLoadDataFromWSHour").toString().trim());
		int minute= Integer.parseInt( properties.get("autoLoadDataFromWSMinute").toString().trim());
		int second= Integer.parseInt(properties.get("autoLoadDataFromWSSecond").toString().trim());
		
		calendar.set(Calendar.HOUR_OF_DAY, hour); 
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		 
		long planTimeMilliseconds =calendar.getTimeInMillis(); 
		Date date=calendar.getTime(); //第一次执行定时任务的时间 
		//如果第一次执行定时任务的时间 小于当前的时间
		//此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		AutoRunLoadDataTask2 task2 = new AutoRunLoadDataTask2(context);  
		//Timer timer = new Timer();
		Timer timer2 = new Timer();  
		if ((new Date().before(date))) {
			long delayTimeMilliseconds =planTimeMilliseconds-nowTimeMilliseconds;
			//timer.schedule(task,delayTimeMilliseconds,PERIOD_DAY);//PERIOD_DAY);  
			timer2.schedule(task2,delayTimeMilliseconds+1*6*1000,PERIOD_DAY);//PERIOD_DAY); 
		}else{
			//date = this.addDay(date, 1);
			//安排指定的任务在指定的时间开始进行重复的固定延迟执行。
			long delayTimeMilliseconds= PERIOD_DAY - nowTimeMilliseconds + planTimeMilliseconds; 
			//timer.schedule(task,delayTimeMilliseconds,PERIOD_DAY);//PERIOD_DAY);  
			timer2.schedule(task2,delayTimeMilliseconds+1*6*1000,PERIOD_DAY);//PERIOD_DAY);  
		} 
	}
	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	} 
}
