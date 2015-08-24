package com.vrv.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.CallSite;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.ServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.vrv.dao.Devicebaseinfo;
import com.vrv.dao.Devicebaseinfohistory;
import com.vrv.dao.StdState;
import com.vrv.service.DomLoginTerminalService;
import com.vrv.service.GetOrgListService;
import com.vrv.service.testService;
import com.vrv.service.ActiveStateService;

public class ActiveStateAction {
	private ActiveStateService activeStateService;

	private Integer ActiveState;
	private Integer DeviceId;
	private String DevOnlyId;
	private String UserName;
	private String DeviceName;
	private String NumIpaddress;
	private String Ipaddres;
	private String MacAddress;
	private String DeptName;
	private String OfficeName;
	private String DeviceType;
	private Integer Registered;
	private Integer RunStatus;
	private Integer AreaId;
	private String CpuType;
	private Integer DiskSize;
	private String AntivirusSoftware;
	private String NotInstalledPatch;
	private String Ostype;
	private String Description;
	private Integer DevTypeCode;
	private String CommunicatIp;
	private Integer ExField6;
	private Integer AssetId;
	private String ServicePack;
	private String ClientVersion;
	private Integer LockStatu;
	private Integer ProtectStatu;
	private Integer BlockOrNot;
	private Integer Area2;
	private String SubOffice;
	private String Room;
	private String Tel;
	private String Email;
	private Integer MemorySize;
	private Integer Cpusize;
	private Integer LastOnlineTime;
	private Integer OnlineMinutes;
	private Integer OnlineFormedTime;
	private Integer SubHealthStatu;
	private Integer NoBootDays;
	private Integer OnlineTimes;
	private Integer DiskSerialNumber;
	private String strStartTime;
	private String strEndTime;
	private Integer iFunc;
	private String strOfficeName;

	private int officeID;

	private GetOrgListService getOrgListService;

	public void execute() throws IOException, ParseException {
		iFunc = (Integer) getIFunc();
		System.out.println(iFunc);
		if (iFunc == 1) {//  this  function  just get one org's device  
			System.out.println("Hi, I am here!");
			JSONObject contents = new JSONObject();
			Map<String, Object> contentsMap = new TreeMap<String, Object>();
			System.out.println("Yeah, nice!");

			strStartTime = (String) getStrStartTime();
			strStartTime = URLDecoder.decode(strStartTime, "utf-8");
			strEndTime = (String) getStrEndTime();
			strEndTime = URLDecoder.decode(strEndTime, "utf-8");
			strOfficeName = (String) getStrOfficeName();
			strOfficeName = URLDecoder.decode(strOfficeName, "utf-8");
			officeID = (int) getOfficeID();
			System.out.println("strStartTime: " + strStartTime);
			System.out.println("strEndTime: " + strEndTime);
			System.out.println("strOfficeName: " + strOfficeName);

			ActiveState = this.getActiveState();
			System.out.println("ActiveState: " + ActiveState);

			// object here has three elements: iDevId, iActiveCount, Device
			List<Object[]> DeviceInfoList = this.activeStateService
					.getDeviceActiveStateHistory(strStartTime, strEndTime,
							officeID);
			Iterator<Object[]> iterator = DeviceInfoList.iterator();
			System.out.println("DeviceInfoList size" + DeviceInfoList.size());

			Devicebaseinfo deviceBaseInfo = new Devicebaseinfo();
			long lValue = 0;
			String sValue = "";
			Integer iValue = 0;
			short siValue = 0;
			Date dValue;
			int i = 0;
			Integer Id = 0;
			BigInteger DeviceCount;
			JSONArray Content = new JSONArray();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				Id = (Integer) obj[0];
				DeviceCount = (BigInteger) obj[1];
				deviceBaseInfo = (Devicebaseinfo) obj[2];

				JSONObject Column = new JSONObject();
				lValue = deviceBaseInfo.getDeviceId();
				Column.put("f1", lValue);
				sValue = deviceBaseInfo.getDevOnlyId();
				Column.put("f2", sValue);
				sValue = deviceBaseInfo.getUserName();
				Column.put("f3", sValue);
				sValue = deviceBaseInfo.getDeviceName();
				Column.put("f4", sValue);
				sValue = deviceBaseInfo.getNumIpaddress();
				Column.put("f5", sValue);
				sValue = deviceBaseInfo.getIpaddres();
				Column.put("f6", sValue);
				sValue = deviceBaseInfo.getMacAddress();
				Column.put("f7", sValue);
				sValue = deviceBaseInfo.getDeptName();
				Column.put("f8", sValue);
				sValue = deviceBaseInfo.getOfficeName();
				Column.put("f9", sValue);
				sValue = deviceBaseInfo.getDeviceType();
				Column.put("f10", sValue);
				siValue = deviceBaseInfo.getRegistered();
				Column.put("f11", sValue);
				siValue = deviceBaseInfo.getRunStatus();
				Column.put("f12", sValue);
				iValue = deviceBaseInfo.getAreaId();
				Column.put("f13", iValue);
				sValue = deviceBaseInfo.getCpuType();
				Column.put("f14", sValue);
				iValue = deviceBaseInfo.getDiskSize();
				Column.put("f15", iValue);
				sValue = deviceBaseInfo.getAntivirusSoftware();
				Column.put("f16", sValue);
				sValue = deviceBaseInfo.getNotInstalledPatch();
				Column.put("f17", sValue);
				sValue = deviceBaseInfo.getOstype();
				Column.put("f18", sValue);
				dValue = deviceBaseInfo.getUnInstallTime();
				Column.put("f19", dValue);
				sValue = deviceBaseInfo.getDescription();
				Column.put("f20", sValue);
				iValue = deviceBaseInfo.getDevTypeCode();
				Column.put("f21", iValue);
				sValue = deviceBaseInfo.getCommunicatIp();
				Column.put("f22", sValue);
				iValue = deviceBaseInfo.getExField6();
				Column.put("f23", iValue);
				sValue = deviceBaseInfo.getAssetId();
				Column.put("f24", sValue);
				sValue = deviceBaseInfo.getServicePack();
				Column.put("f25", sValue);
				dValue = deviceBaseInfo.getSetupTmos();
				Column.put("f26", dValue);
				sValue = deviceBaseInfo.getClientVersion();
				Column.put("f27", sValue);
				iValue = deviceBaseInfo.getLockStatu();
				Column.put("f28", iValue);
				iValue = deviceBaseInfo.getProtectStatu();
				Column.put("f29", iValue);
				iValue = deviceBaseInfo.getBlockOrNot();
				Column.put("f30", iValue);
				iValue = deviceBaseInfo.getArea2();
				Column.put("f31", iValue);
				sValue = deviceBaseInfo.getSubOffice();
				Column.put("f32", sValue);
				sValue = deviceBaseInfo.getRoom();
				Column.put("f33", sValue);
				sValue = deviceBaseInfo.getTel();
				Column.put("f34", sValue);
				sValue = deviceBaseInfo.getEmail();
				Column.put("f35", sValue);
				dValue = deviceBaseInfo.getLastTime();
				Column.put("f36", dValue.toLocaleString());
				iValue = deviceBaseInfo.getMemorySize();
				Column.put("f37", iValue);
				iValue = deviceBaseInfo.getCpusize();
				Column.put("f38", sValue);
				iValue = deviceBaseInfo.getLastOnlineTime();
				Column.put("f39", iValue);
				iValue = deviceBaseInfo.getOnlineMinutes();
				Column.put("f40", iValue);
				iValue = deviceBaseInfo.getOnlineFormedTime();
				Column.put("f41", iValue);
				iValue = deviceBaseInfo.getSubHealthStatu();
				Column.put("f42", iValue);
				iValue = deviceBaseInfo.getNoBootDays();
				Column.put("f43", iValue);
				iValue = deviceBaseInfo.getOnlineTimes();
				Column.put("f44", iValue);
				iValue = deviceBaseInfo.getDiskSerialNumber();
				Column.put("f45", iValue);
				sValue = deviceBaseInfo.getDomainName();
				Column.put("f46", sValue);
				sValue = deviceBaseInfo.getLogonUserName();
				Column.put("f47", sValue);
				Column.put("DevID", Id);
				Column.put("ActDays", DeviceCount);
				Content.add(i, Column);
				i++;
			}
			contentsMap.put("code", 0);
			contentsMap.put("IsLastPage", 1);
			contentsMap.put("return", Content);
			contents = JSONObject.fromObject(contentsMap);
			System.out.println(contents.toString());
			ActionContext.getContext().getSession()
					.put("back", contents.toString());
			ServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(contents.toString());
			return;
		} else if (iFunc == 2) {
			System.out.println("Hi, I am here!");
			JSONObject contents = new JSONObject(); // the whole Json
			System.out.println("Yeah, nice!");

			strStartTime = getStrStartTime();
			strEndTime = getStrEndTime();

			DeviceId = (Integer) getDeviceId();
			ActiveState = this.getActiveState();

			System.out.println("ActiveState: " + ActiveState);

			List<String> liStrCal = this.activeStateService
					.getTatgetDeviceActiveState(strStartTime, strEndTime,
							DeviceId);

			Iterator<String> iterator = liStrCal.iterator();

			String sValue = "";
			JSONArray Content = new JSONArray(); // the Json to contain the List
													// of Calendar as String

			while (iterator.hasNext()) {
				sValue = iterator.next();

				Content.add(sValue);
			}
			contents.put("code", 0);
			contents.put("IsLastPage", 1);
			contents.put("CalendarStrings", Content);

			System.out.println(contents.toString());
			ActionContext.getContext().getSession()
					.put("back", contents.toString());
			ServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(contents.toString());
			return;

		} else if (iFunc == 3) {
			/*
			 * by wengjiang 思路： 1、 先去查出它的子部门 循环查出所有子部门 2、 再循环调用 陈毅的接口
			 * 
			 * 以前是以部门名字来查 但部门名字会有重复 所以现在以id来查
			 */

			System.out.println("Hi, I am here!");
			System.out.println("ActiveState: " + ActiveState);
			System.out.println("Yeah, nice! 3");

			JSONObject contents = new JSONObject(); // the whole Json

			strStartTime = getStrStartTime();
			strEndTime = getStrEndTime();
			// DeviceId = (Integer)getDeviceId();
			strOfficeName = getStrOfficeName();
			officeID = getOfficeID();
			// System.out.println("officeID: "+officeID);
		 

//			List<Object[]> liCountAndLastTime = this.activeStateService
//					.getliCountAndLastTimeByOfficeName(strStartTime,
//							strEndTime, strOfficeName);

			Vector vDep = new Vector();// 用于存放返回的数据的日期数目
			vDep = getOrgListService.getORGDeviceActiveInfo(strStartTime,
					strEndTime, officeID);

			// 是一个vector 数组
			/*
			 * 数组每个点内容是一个list 一个list 对应一个子部门
			 * 
			 * 每个list内容是一个 object[]数组
			 * 
			 * object【】数组的内容是 一个部门在历史天数中每天的活动统计 内容为 活动次数、日期、部门名字
			 */

			JSONArray jsonArry = new JSONArray();
			ArrayList<JSONObject> temp = new ArrayList<JSONObject>();
			for (int j = 0; j < vDep.size(); j++) {
				List<Object[]> list = (List<Object[]>) vDep.get(j);

				Object[] obj;
				JSONObject Content = new JSONObject();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// write by wengjiang
				// {"IsLastPage":1,"CalendarStrings":{"2015-03-09":2,"2015-03-07":1,"2015-03-08":1,"2015-03-05":1,"2015-03-06":1,"2015-03-20":1},"code":0}

				/*
				 * 思路： 从第一天开始 去查询数据库返回的结果看是否有 数据 如果有数据 在Content中添加键值对 如果没有
				 * 设置content中对应的值为零
				 */

				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				ParsePosition pos = new ParsePosition(0);

				System.out.println(strStartTime);
				System.out.println(strEndTime);

				Date dateStart1 = formatter.parse(strStartTime);
				Date dateEnd1 = formatter.parse(strEndTime);
				Date dateStart = formatter.parse(strStartTime, pos);

				int days = daysBetween(dateEnd1, dateStart1);
				Vector v = new Vector();// 用于存放返回的数据的日期数目
				System.out.println(days);
				for (int i = 0; i < days; i++) {
					Iterator<Object[]> iterator = list.iterator();// 一个部门的统计情况

					Date dateTmp = addDay(dateStart, i);
					String dateString = sdf.format(dateTmp);
					v.add(dateString);

					int tag = 0;
					while (iterator.hasNext()) {// 开始对比这一天是否有数据
						obj = iterator.next();
						Long iActDay = (Long) obj[0];//
						Date date = (Date) obj[1];

						String strDay = sdf.format(date);
						// System.out.println(strDay+"  "+dateString);
						if (dateString.equals(strDay)) {// 如果有数据 则按照天 --值 的方式保存到
														// jsonobject中
							System.out.println(strDay + "  " + dateString);
							Content.put(dateString, iActDay);
							// Content.put("depName", DepName);
							// v.add(dateString);
							tag = 1;
							break;
						}
					}
					if (tag == 0) {
						Content.put(dateString.toString(), 0);
					}
				}

				// 经过上个流程得到了一个部门的 一天为单位的统计数据 Content 这个时候
				// end by wengjiang

				JSONObject subContentTmp = new JSONObject(); // the whole Json

				subContentTmp.put("depName", list.get(0)[2].toString());// list是一个
																		// object的数组
																		// 第三个数组项就是部门名字

				subContentTmp.put("CalendarStrings", Content);

				subContentTmp.put("dateDays", v);

				jsonArry.add(subContentTmp);

			}

			System.out.println("开始取值！");
			contents.put("code", 0);
			contents.put("IsLastPage", 1);
			// 把含有各个部门的信息的json数组加入 要返回的的jsonobject中
			contents.put("departments", jsonArry);


			System.out.println("返回前的数据为 ：7：" + contents.toString());
			ActionContext.getContext().getSession()
					.put("back", contents.toString());
			ServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(contents.toString());
			return;
		}

		// 查出一个设备的历史记录信息
		else if (iFunc == 5) {
			System.out.println("Hi, I am here!");
			JSONObject contents = new JSONObject(); // the whole Json
			System.out.println("Yeah, nice! 5");

			strStartTime = getStrStartTime();
			strEndTime = getStrEndTime();

			// DeviceId = (Integer)getDeviceId();
			strOfficeName = getStrOfficeName();
			ActiveState = this.getActiveState();

			System.out.println("ActiveState: " + ActiveState);

			List<Object[]> liCountAndLastTime = this.activeStateService.getliCountAndLastTimeByDeviceId(strStartTime, strEndTime, "1");

			System.out.println("开始取值！");

			Object[] obj;
			JSONObject Content = new JSONObject();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// write by wengjiang
			// {"IsLastPage":1,"CalendarStrings":{"2015-03-09":2,"2015-03-07":1,"2015-03-08":1,"2015-03-05":1,"2015-03-06":1,"2015-03-20":1},"code":0}
			/*
			 * 思路： 从第一天开始 去查询数据库返回的结果看是否有 数据 如果有数据 在Content中添加键值对 如果没有
			 * 设置content中对应的值为零
			 */

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);

			System.out.println(strStartTime);
			System.out.println(strEndTime);

			Date dateStart1 = formatter.parse(strStartTime);
			Date dateEnd1 = formatter.parse(strEndTime);
			Date dateStart = formatter.parse(strStartTime, pos);

			int days = daysBetween(dateEnd1, dateStart1);
			Vector v = new Vector();// 用于存放返回的数据的日期数目
			System.out.println(days);
			for (int i = 0; i < days; i++) {
				Iterator<Object[]> iterator = liCountAndLastTime.iterator();
				Date dateTmp = addDay(dateStart, i);
				String dateString = sdf.format(dateTmp);
				v.add(dateString);

				int tag = 0;
				while (iterator.hasNext()) {
					obj = iterator.next();
					Long iActDay = (Long) obj[0];//
					Date date = (Date) obj[1];
					String strDay = sdf.format(date);
					// System.out.println(strDay+"  "+dateString);
					if (dateString.equals(strDay)) {
						System.out.println(strDay + "  " + dateString);
						Content.put(dateString, iActDay);
					//	v.add(dateString);
						tag = 1;
						break;
					}
				}
				if (tag == 0) {
					 Content.put(dateString.toString(), 0);
				}
			}
			// end by wengjiang

			// while (iterator.hasNext()) {
			// obj = iterator.next();
			// Long iActDay = (Long) obj[0];
			// Date date = (Date) obj[1];
			// String strDay = sdf.format(date);
			// Content.put(strDay, iActDay);
			// }

			contents.put("code", 0);
			contents.put("IsLastPage", 1);
			contents.put("CalendarStrings", Content);
			contents.put("dateDays", v);
			System.out.println(contents.toString());
			ActionContext.getContext().getSession()
					.put("back", contents.toString());
			ServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(contents.toString());
			return;
		}
		else if (iFunc==6){
			
			/*
			 * 这个函数只是为了实现 查询设备的历史信息 的时候所展示出来的曲线图 
			 * 实现返回一个设别的历史信息
			 * by wengjiang 思路： 直接调用 陈毅的接口  以设备id去查
			 *   
			 */

			System.out.println("Hi, I am here!");
			System.out.println("ActiveState: " + ActiveState);
			System.out.println("Yeah, nice! 6");

			JSONObject contents = new JSONObject(); // the whole Json

			strStartTime = getStrStartTime();
			strEndTime = getStrEndTime();
			// DeviceId = (Integer)getDeviceId();
			strOfficeName = getStrOfficeName();
			officeID = getOfficeID();   //这里虽然是officeId 实际上是指deviceid
			// System.out.println("officeID: "+officeID);
			ActiveState = this.getActiveState();
 
			List<Object[]> liCountAndLastTime = this.activeStateService.getliCountAndLastTimeByDeviceId(strStartTime, strEndTime, officeID+"");
 
			// 是一个vector 数组
			/*
			 * 数组每个点内容是一个list 一个list 对应一个子部门
			 * 
			 * 每个list内容是一个 object[]数组
			 * 
			 * object【】数组的内容是 一个部门在历史天数中每天的活动统计 内容为 活动次数、日期、部门名字
			 * 
			 */

			JSONArray jsonArry = new JSONArray();
			ArrayList<JSONObject> temp = new ArrayList<JSONObject>();
			for (int j = 0; j < 1; j++) { // vDep.size(); j++) { //因为这类改造成为一个设备的信息 所以这里只要是1就行了
				List<Object[]>  list=liCountAndLastTime;   //这样直接  让
				Object[] obj;
				JSONObject Content = new JSONObject();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// write by wengjiang
				// {"IsLastPage":1,"CalendarStrings":{"2015-03-09":2,"2015-03-07":1,"2015-03-08":1,"2015-03-05":1,"2015-03-06":1,"2015-03-20":1},"code":0}

				/*
				 * 思路： 从第一天开始 去查询数据库返回的结果看是否有 数据 如果有数据 在Content中添加键值对 如果没有
				 * 设置content中对应的值为零
				 * 
				 */

				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				ParsePosition pos = new ParsePosition(0);

				System.out.println(strStartTime);
				System.out.println(strEndTime);

				Date dateStart1 = formatter.parse(strStartTime);
				Date dateEnd1 = formatter.parse(strEndTime);
				Date dateStart = formatter.parse(strStartTime, pos);

				int days = daysBetween(dateEnd1, dateStart1);
				Vector v = new Vector();// 用于存放返回的数据的日期数目
				System.out.println(days);
				for (int i = 0; i < days; i++) {
					Iterator<Object[]> iterator = list.iterator();// 一个部门的统计情况

					Date dateTmp = addDay(dateStart, i);
					String dateString = sdf.format(dateTmp);
					v.add(dateString);

					int tag = 0;
					while (iterator.hasNext()) {// 开始对比这一天是否有数据
						obj = iterator.next();
						Long iActDay = (Long) obj[0];//
						Date date = (Date) obj[1];

						String strDay = sdf.format(date);
						// System.out.println(strDay+"  "+dateString);
						if (dateString.equals(strDay)) {// 如果有数据 则按照天 --值 的方式保存到
														// jsonobject中
							System.out.println(strDay + "  " + dateString);
							Content.put(dateString, iActDay);
							// Content.put("depName", DepName);
							// v.add(dateString);
							tag = 1;
							break;
						}
					}
					if (tag == 0) {
						Content.put(dateString.toString(), 0);
					}
				}

				// 经过上个流程得到了一个部门的 一天为单位的统计数据 Content 这个时候
				// end by wengjiang

				JSONObject subContentTmp = new JSONObject(); // the whole Json

				//subContentTmp.put("depName","设备编码为  "+officeID+ "  的设备<br>在 "+strStartTime +" 到  "+strEndTime+" 时间段内的 使用记录 ");//这里直接修修改为 设置为 设备的编号      //list.get(0)[2].toString());// list是一个
				subContentTmp.put("depName","设备编码为  "+officeID);// "  的设备<br>在 "+strStartTime +" 到  "+strEndTime+" 时间段内的 使用记录 ");//这里直接修修改为 设置为 设备的编号      //list.get(0)[2].toString());// list是一个
																	// object的数组
																		// 第三个数组项就是部门名字

				subContentTmp.put("CalendarStrings", Content);

				subContentTmp.put("dateDays", v);

				jsonArry.add(subContentTmp);

			}

			System.out.println("开始取值！");
			contents.put("code", 0);
			contents.put("IsLastPage", 1);
			// 把含有各个部门的信息的json数组加入 要返回的的jsonobject中   
			contents.put("departments", jsonArry);
			
			  
			System.out.println("返回前的数据为 ：6：" + contents.toString());
			ActionContext.getContext().getSession()
					.put("back", contents.toString());
			ServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(contents.toString());
			return;
			
		} 
		else if (iFunc == 7) {//  this  function  just get one org's And It's  subORG's device  
			System.out.println("Hi, I am here!");
			JSONObject contents = new JSONObject();
			Map<String, Object> contentsMap = new TreeMap<String, Object>();
			System.out.println("Yeah, nice!");

			strStartTime = (String) getStrStartTime();
			strStartTime = URLDecoder.decode(strStartTime, "utf-8");
			strEndTime = (String) getStrEndTime();
			strEndTime = URLDecoder.decode(strEndTime, "utf-8");
			strOfficeName = (String) getStrOfficeName();
			strOfficeName = URLDecoder.decode(strOfficeName, "utf-8");
			officeID = getOfficeID();
			System.out.println("strStartTime: " + strStartTime);
			System.out.println("strEndTime: " + strEndTime);
			System.out.println("strOfficeName: " + strOfficeName);

			ActiveState = this.getActiveState();
			System.out.println("ActiveState: " + ActiveState);

			// object here has three elements: iDevId, iActiveCount, Device
			List<Object[]> DeviceInfoList = this.activeStateService
					.getDeviceActiveStateHistoryOfOneOrgAndItsSubOrg(strStartTime, strEndTime,
							strOfficeName,officeID);
			Iterator<Object[]> iterator = DeviceInfoList.iterator();
			System.out.println("DeviceInfoList size" + DeviceInfoList.size());

			Devicebaseinfo deviceBaseInfo = new Devicebaseinfo();
			long lValue = 0;
			String sValue = "";
			Integer iValue = 0;
			short siValue = 0;
			Date dValue;
			int i = 0;
			Integer Id = 0;
			BigInteger DeviceCount;
			JSONArray Content = new JSONArray();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				Id = (Integer) obj[0];
				DeviceCount = (BigInteger) obj[1];
				deviceBaseInfo = (Devicebaseinfo) obj[2];

				JSONObject Column = new JSONObject();
				lValue = deviceBaseInfo.getDeviceId();
				Column.put("f1", lValue);
				sValue = deviceBaseInfo.getDevOnlyId();
				Column.put("f2", sValue);
				sValue = deviceBaseInfo.getUserName();
				Column.put("f3", sValue);
				sValue = deviceBaseInfo.getDeviceName();
				Column.put("f4", sValue);
				sValue = deviceBaseInfo.getNumIpaddress();
				Column.put("f5", sValue);
				sValue = deviceBaseInfo.getIpaddres();
				Column.put("f6", sValue);
				sValue = deviceBaseInfo.getMacAddress();
				Column.put("f7", sValue);
				sValue = deviceBaseInfo.getDeptName();
				Column.put("f8", sValue);
				sValue = deviceBaseInfo.getOfficeName();
				Column.put("f9", sValue);
				sValue = deviceBaseInfo.getDeviceType();
				Column.put("f10", sValue);
				siValue = deviceBaseInfo.getRegistered();
				Column.put("f11", sValue);
				siValue = deviceBaseInfo.getRunStatus();
				Column.put("f12", sValue);
				iValue = deviceBaseInfo.getAreaId();
				Column.put("f13", sValue);
				sValue = deviceBaseInfo.getCpuType();
				Column.put("f14", sValue);
				iValue = deviceBaseInfo.getDiskSize();
				Column.put("f15", sValue);
				sValue = deviceBaseInfo.getAntivirusSoftware();
				Column.put("f16", sValue);
				sValue = deviceBaseInfo.getNotInstalledPatch();
				Column.put("f17", sValue);
				sValue = deviceBaseInfo.getOstype();
				Column.put("f18", sValue);
				dValue = deviceBaseInfo.getUnInstallTime();
				Column.put("f19", sValue);
				sValue = deviceBaseInfo.getDescription();
				Column.put("f20", sValue);
				iValue = deviceBaseInfo.getDevTypeCode();
				Column.put("f21", sValue);
				sValue = deviceBaseInfo.getCommunicatIp();
				Column.put("f22", sValue);
				iValue = deviceBaseInfo.getExField6();
				Column.put("f23", sValue);
				sValue = deviceBaseInfo.getAssetId();
				Column.put("f24", sValue);
				sValue = deviceBaseInfo.getServicePack();
				Column.put("f25", sValue);
				dValue = deviceBaseInfo.getSetupTmos();
				Column.put("f26", sValue);
				sValue = deviceBaseInfo.getClientVersion();
				Column.put("f27", sValue);
				iValue = deviceBaseInfo.getLockStatu();
				Column.put("f28", sValue);
				iValue = deviceBaseInfo.getProtectStatu();
				Column.put("f29", sValue);
				iValue = deviceBaseInfo.getBlockOrNot();
				Column.put("f30", sValue);
				iValue = deviceBaseInfo.getArea2();
				Column.put("f31", sValue);
				sValue = deviceBaseInfo.getSubOffice();
				Column.put("f32", sValue);
				sValue = deviceBaseInfo.getRoom();
				Column.put("f33", sValue);
				sValue = deviceBaseInfo.getTel();
				Column.put("f34", sValue);
				sValue = deviceBaseInfo.getEmail();
				Column.put("f35", sValue);
				dValue = deviceBaseInfo.getLastTime();
				Column.put("f36", dValue.toLocaleString());
				iValue = deviceBaseInfo.getMemorySize();
				Column.put("f37", sValue);
				iValue = deviceBaseInfo.getCpusize();
				Column.put("f38", sValue);
				iValue = deviceBaseInfo.getLastOnlineTime();
				Column.put("f39", sValue);
				iValue = deviceBaseInfo.getOnlineMinutes();
				Column.put("f40", sValue);
				iValue = deviceBaseInfo.getOnlineFormedTime();
				Column.put("f41", sValue);
				iValue = deviceBaseInfo.getSubHealthStatu();
				Column.put("f42", sValue);
				iValue = deviceBaseInfo.getNoBootDays();
				Column.put("f43", sValue);
				iValue = deviceBaseInfo.getOnlineTimes();
				Column.put("f44", sValue);
				iValue = deviceBaseInfo.getDiskSerialNumber();
				Column.put("f45", sValue);
				sValue = deviceBaseInfo.getDomainName();
				Column.put("f46", sValue);
				sValue = deviceBaseInfo.getLogonUserName();
				Column.put("f47", sValue);
				Column.put("DevID", Id);
				Column.put("ActDays", DeviceCount);
				Content.add(i, Column);
				i++;
			}
			contentsMap.put("code", 0);
			contentsMap.put("IsLastPage", 1);
			contentsMap.put("return", Content);
			contents = JSONObject.fromObject(contentsMap);
			System.out.println(contents.toString());
			ActionContext.getContext().getSession()
					.put("back", contents.toString());
			ServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(contents.toString());
			return;
		}
		else if (iFunc == 8) {
			/*
			 * by wengjiang 思路： 1、 先去查出它的子部门 循环查出所有子部门 2、 再循环调用 陈毅的接口
			 * 
			 * 以前是以部门名字来查 但部门名字会有重复 所以现在以id来查
			 */

			System.out.println("Hi, I am here!");
			System.out.println("ActiveState: " + ActiveState);
			System.out.println("Yeah, nice! 8");

			JSONObject contents = new JSONObject(); // the whole Json

			strStartTime = getStrStartTime();
			strEndTime = getStrEndTime();
			// DeviceId = (Integer)getDeviceId();
			strOfficeName = getStrOfficeName();
			officeID = getOfficeID();
			// System.out.println("officeID: "+officeID);
		 

//			List<Object[]> liCountAndLastTime = this.activeStateService
//					.getliCountAndLastTimeByOfficeName(strStartTime,
//							strEndTime, strOfficeName);

			Vector vDep = new Vector();// 用于存放返回的数据的日期数目
			vDep = getOrgListService.getJustOneORGDeviceActiveInfo(strStartTime,
					strEndTime, officeID);

			// 是一个vector 数组
			/*
			 * 数组每个点内容是一个list 一个list 对应一个子部门
			 * 
			 * 每个list内容是一个 object[]数组
			 * 
			 * object【】数组的内容是 一个部门在历史天数中每天的活动统计 内容为 活动次数、日期、部门名字
			 */

			JSONArray jsonArry = new JSONArray();
			ArrayList<JSONObject> temp = new ArrayList<JSONObject>();
			for (int j = 0; j < vDep.size(); j++) {
				List<Object[]> list = (List<Object[]>) vDep.get(j);

				Object[] obj;
				JSONObject Content = new JSONObject();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// write by wengjiang
				// {"IsLastPage":1,"CalendarStrings":{"2015-03-09":2,"2015-03-07":1,"2015-03-08":1,"2015-03-05":1,"2015-03-06":1,"2015-03-20":1},"code":0}

				/*
				 * 思路： 从第一天开始 去查询数据库返回的结果看是否有 数据 如果有数据 在Content中添加键值对 如果没有
				 * 设置content中对应的值为零
				 */

				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				ParsePosition pos = new ParsePosition(0);

				System.out.println(strStartTime);
				System.out.println(strEndTime);

				Date dateStart1 = formatter.parse(strStartTime);
				Date dateEnd1 = formatter.parse(strEndTime);
				Date dateStart = formatter.parse(strStartTime, pos);

				int days = daysBetween(dateEnd1, dateStart1);
				Vector v = new Vector();// 用于存放返回的数据的日期数目
				System.out.println(days);
				for (int i = 0; i < days; i++) {
					Iterator<Object[]> iterator = list.iterator();// 一个部门的统计情况

					Date dateTmp = addDay(dateStart, i);
					String dateString = sdf.format(dateTmp);
					//System.out.println("dateString:"+dateString);
					v.add(dateString);

					int tag = 0;
					while (iterator.hasNext()) {// 开始对比这一天是否有数据
						obj = iterator.next();
						Long iActDay = (Long) obj[0];//
						Date date = (Date) obj[1];

						String strDay = sdf.format(date);
						// System.out.println(strDay+"  "+dateString);
						if (dateString.equals(strDay)) {// 如果有数据 则按照天 --值 的方式保存到
														// jsonobject中
							System.out.println(strDay + "  " + dateString);
							Content.put(dateString, iActDay);
							// Content.put("depName", DepName);
							// v.add(dateString);
							tag = 1;
							break;
						}
					}
					if (tag == 0) {
						Content.put(dateString.toString(), 0);
					}
				}

				// 经过上个流程得到了一个部门的 一天为单位的统计数据 Content 这个时候
				// end by wengjiang

				JSONObject subContentTmp = new JSONObject(); // the whole Json
				System.out.println("list 8:"+list.get(0)[0].toString() + " vs "+list.get(0)[1].toString() + " vs "+list.get(0)[2].toString());
				
				//subContentTmp.put("depName", list.get(0)[2].toString());// list是一个
				subContentTmp.put("depName",strOfficeName );// object的数组
																		// 第三个数组项就是部门名字

				subContentTmp.put("CalendarStrings", Content);

				subContentTmp.put("dateDays", v);

				jsonArry.add(subContentTmp);

			}

			System.out.println("开始取值！");
			contents.put("code", 0);
			contents.put("IsLastPage", 1);
			// 把含有各个部门的信息的json数组加入 要返回的的jsonobject中
			contents.put("departments", jsonArry);
 

			System.out.println("返回前的数据为 ：8：" + contents.toString());
			ActionContext.getContext().getSession()
					.put("back", contents.toString());
			ServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(contents.toString());
			return;
		}

		
	}
	
	
	

	private int daysBetween(Date now, Date returnDate) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(now);
		cReturnDate.setTime(returnDate);
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs)+1;
	}

	private int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / (1000 * 86400));
	}

	private void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}

	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	public ActiveStateService getActiveStateService() {
		return activeStateService;
	}

	public void setActiveStateService(ActiveStateService activeStateService) {
		this.activeStateService = activeStateService;
	}

	public Integer getActiveState() {
		return ActiveState;
	}

	public void setActiveState(Integer activeState) {
		ActiveState = activeState;
	}

	public Integer getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(Integer deviceId) {
		DeviceId = deviceId;
	}

	public String getDevOnlyId() {
		return DevOnlyId;
	}

	public void setDevOnlyId(String devOnlyId) {
		DevOnlyId = devOnlyId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getDeviceName() {
		return DeviceName;
	}

	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}

	public String getNumIpaddress() {
		return NumIpaddress;
	}

	public void setNumIpaddress(String numIpaddress) {
		NumIpaddress = numIpaddress;
	}

	public String getIpaddres() {
		return Ipaddres;
	}

	public void setIpaddres(String ipaddres) {
		Ipaddres = ipaddres;
	}

	public String getMacAddress() {
		return MacAddress;
	}

	public void setMacAddress(String macAddress) {
		MacAddress = macAddress;
	}

	public String getDeptName() {
		return DeptName;
	}

	public void setDeptName(String deptName) {
		DeptName = deptName;
	}

	public String getOfficeName() {
		return OfficeName;
	}

	public void setOfficeName(String officeName) {
		OfficeName = officeName;
	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	public Integer getRegistered() {
		return Registered;
	}

	public void setRegistered(Integer registered) {
		Registered = registered;
	}

	public Integer getRunStatus() {
		return RunStatus;
	}

	public void setRunStatus(Integer runStatus) {
		RunStatus = runStatus;
	}

	public Integer getAreaId() {
		return AreaId;
	}

	public void setAreaId(Integer areaId) {
		AreaId = areaId;
	}

	public String getCpuType() {
		return CpuType;
	}

	public void setCpuType(String cpuType) {
		CpuType = cpuType;
	}

	public Integer getDiskSize() {
		return DiskSize;
	}

	public void setDiskSize(Integer diskSize) {
		DiskSize = diskSize;
	}

	public String getAntivirusSoftware() {
		return AntivirusSoftware;
	}

	public void setAntivirusSoftware(String antivirusSoftware) {
		AntivirusSoftware = antivirusSoftware;
	}

	public String getNotInstalledPatch() {
		return NotInstalledPatch;
	}

	public void setNotInstalledPatch(String notInstalledPatch) {
		NotInstalledPatch = notInstalledPatch;
	}

	public String getOstype() {
		return Ostype;
	}

	public void setOstype(String ostype) {
		Ostype = ostype;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Integer getDevTypeCode() {
		return DevTypeCode;
	}

	public void setDevTypeCode(Integer devTypeCode) {
		DevTypeCode = devTypeCode;
	}

	public String getCommunicatIp() {
		return CommunicatIp;
	}

	public void setCommunicatIp(String communicatIp) {
		CommunicatIp = communicatIp;
	}

	public Integer getExField6() {
		return ExField6;
	}

	public void setExField6(Integer exField6) {
		ExField6 = exField6;
	}

	public Integer getAssetId() {
		return AssetId;
	}

	public void setAssetId(Integer assetId) {
		AssetId = assetId;
	}

	public String getServicePack() {
		return ServicePack;
	}

	public void setServicePack(String servicePack) {
		ServicePack = servicePack;
	}

	public String getClientVersion() {
		return ClientVersion;
	}

	public void setClientVersion(String clientVersion) {
		ClientVersion = clientVersion;
	}

	public Integer getLockStatu() {
		return LockStatu;
	}

	public void setLockStatu(Integer lockStatu) {
		LockStatu = lockStatu;
	}

	public Integer getProtectStatu() {
		return ProtectStatu;
	}

	public void setProtectStatu(Integer protectStatu) {
		ProtectStatu = protectStatu;
	}

	public Integer getBlockOrNot() {
		return BlockOrNot;
	}

	public void setBlockOrNot(Integer blockOrNot) {
		BlockOrNot = blockOrNot;
	}

	public Integer getArea2() {
		return Area2;
	}

	public void setArea2(Integer area2) {
		Area2 = area2;
	}

	public String getSubOffice() {
		return SubOffice;
	}

	public void setSubOffice(String subOffice) {
		SubOffice = subOffice;
	}

	public String getRoom() {
		return Room;
	}

	public void setRoom(String room) {
		Room = room;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Integer getMemorySize() {
		return MemorySize;
	}

	public void setMemorySize(Integer memorySize) {
		MemorySize = memorySize;
	}

	public Integer getCpusize() {
		return Cpusize;
	}

	public void setCpusize(Integer cpusize) {
		Cpusize = cpusize;
	}

	public Integer getLastOnlineTime() {
		return LastOnlineTime;
	}

	public void setLastOnlineTime(Integer lastOnlineTime) {
		LastOnlineTime = lastOnlineTime;
	}

	public Integer getOnlineMinutes() {
		return OnlineMinutes;
	}

	public void setOnlineMinutes(Integer onlineMinutes) {
		OnlineMinutes = onlineMinutes;
	}

	public Integer getOnlineFormedTime() {
		return OnlineFormedTime;
	}

	public void setOnlineFormedTime(Integer onlineFormedTime) {
		OnlineFormedTime = onlineFormedTime;
	}

	public Integer getSubHealthStatu() {
		return SubHealthStatu;
	}

	public void setSubHealthStatu(Integer subHealthStatu) {
		SubHealthStatu = subHealthStatu;
	}

	public Integer getNoBootDays() {
		return NoBootDays;
	}

	public void setNoBootDays(Integer noBootDays) {
		NoBootDays = noBootDays;
	}

	public Integer getOnlineTimes() {
		return OnlineTimes;
	}

	public void setOnlineTimes(Integer onlineTimes) {
		OnlineTimes = onlineTimes;
	}

	public Integer getDiskSerialNumber() {
		return DiskSerialNumber;
	}

	public void setDiskSerialNumber(Integer diskSerialNumber) {
		DiskSerialNumber = diskSerialNumber;
	}

	public String getStrStartTime() {
		return strStartTime;
	}

	public void setStrStartTime(String strStartTime) {
		this.strStartTime = strStartTime;
	}

	public String getStrEndTime() {
		return strEndTime;
	}

	public void setStrEndTime(String strEndTime) {
		this.strEndTime = strEndTime;
	}

	public Integer getIFunc() {
		return iFunc;
	}

	public void setIFunc(Integer iFunc) {
		this.iFunc = iFunc;
	}

	public String getStrOfficeName() {
		return strOfficeName;
	}

	public void setStrOfficeName(String strOfficeName) {
		this.strOfficeName = strOfficeName;
	}

	public GetOrgListService getGetOrgListService() {
		return getOrgListService;
	}

	public void setGetOrgListService(GetOrgListService getOrgListService) {
		this.getOrgListService = getOrgListService;
	}

	public Integer getiFunc() {
		return iFunc;
	}

	public void setiFunc(Integer iFunc) {
		this.iFunc = iFunc;
	}

	public int getOfficeID() {
		return officeID;
	}

	public void setOfficeID(int officeID) {
		this.officeID = officeID;
	}

}
