package cn.pro.bxy.autorun;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class justForTest {
	
public	static void main(String[] args){
	
	
	
	
	
	
	Calendar calendar = Calendar.getInstance();
	calendar.set(Calendar.HOUR_OF_DAY, 23); //晚上11：30点执行
	calendar.set(Calendar.MINUTE, 30);
	calendar.set(Calendar.SECOND, 0);
	
	
	long planTime= calendar.getTimeInMillis();
	
	
	Date date1=calendar.getTime(); //第一次执行定时任务的时间
	
	calendar.set(Calendar.HOUR_OF_DAY, 11); //晚上11：30点执行
	calendar.set(Calendar.MINUTE, 26);
	calendar.set(Calendar.SECOND, 0); 
	long T2= calendar.getTimeInMillis();    
	
	
	 long now = Calendar.getInstance().getTimeInMillis();
	System.out.println(planTime-now);
	
	Date date2=calendar.getTime(); //第一次执行定时任务的时间
	//如果第一次执行定时任务的时间 小于当前的时间
	//此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。

	 
	
	
	
	
//	if (date.before(new Date())) {
//		date = this.addDay(date, 1);
//	}
//	Timer timer = new Timer(); 
//	
//	AutoRunLoadDataTask task = new AutoRunLoadDataTask(context);  
//	
//	//安排指定的任务在指定的时间开始进行重复的固定延迟执行。
//	timer.schedule(task,date,5000);//PERIOD_DAY);  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	String str2 =" ";
	System.out.println(str2.length());
	System.out.println(str2.trim().length());
	
	
	
	
	
	
	
	  String str = "1384171247000+0800";
      String time = str.substring(0,str.length()-5);
    System.out.println(time); 
    str="1381976230000"; 
    //final String timeZone = str.substring(str.length()-5, str.length());
    //System.out.println(timeZone);
    final Date date = new Date(Long.parseLong(time));
    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println(format.format(date)); 
}

}
