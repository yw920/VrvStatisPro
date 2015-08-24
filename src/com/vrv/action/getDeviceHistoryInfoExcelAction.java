package com.vrv.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.CallSite;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.vrv.dao.Devicebaseinfo;
import com.vrv.dao.Devicebaseinfohistory;
import com.vrv.dao.StdState;
import com.vrv.service.DomLoginTerminalService;
import com.vrv.service.GetOrgListService;
import com.vrv.service.testService;
import com.vrv.service.ActiveStateService;

public class getDeviceHistoryInfoExcelAction {
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

	public String strOfficeName;
	public String oofficeName;
	

	public String getOofficeName() {
		return oofficeName;
	}


	public void setOofficeName(String oofficeName) {
		this.oofficeName = oofficeName;
	}


	private int officeID;
	private int deviceId;

	private GetOrgListService getOrgListService;

	public void execute() throws IOException, ParseException {
		
		HttpServletRequest request2 = ServletActionContext.getRequest();   
		HttpServletResponse response2 = ServletActionContext.getResponse();  
		
		//System.out.println(request2.getParameterValues("deviceId")[0].toString());
						   
		//System.out.println(this.getDeviceId());//   strEndTime));//deviceId"));
		
		//HttpSession session = request.getSession(); 
		try { 
			this.creatExcelAndPush2Client(request2, response2); 
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
		  
	
	//生成excel方法  
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	 
	public void creatExcelAndPush2Client(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String docsPath = request.getSession().getServletContext()
				.getRealPath("docs");
		String fileName = "export2007_" + System.currentTimeMillis() + ".xlsx";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		String[]  deviceIds=  request.getParameterValues("deviceId");

		Enumeration<String>  deviceId=  request.getParameterNames(); //  .getParameter("strEndTime");
		  
		try {
			// 输出流
			OutputStream os = new FileOutputStream(filePath);
			// 工作区
			XSSFWorkbook wb = new XSSFWorkbook(); 
			
			//开始写excel内容 
			creatExcelContent( wb ,deviceIds);
			//写excel内容结束
			
			// 写文件
			wb.write(os);
			// 关闭输出流
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		download(filePath, response);
	}

	
	private void creatExcelContent(XSSFWorkbook wb,String[]  deviceIds ) { 
		XSSFSheet sheet = wb.createSheet("test"); 
		
	//	HSSFSheet.setColumnWidth(i,value.toString().length() * 512);
		// 创建第一个sheet
		// 生成第一行
		for (int i = 0; i < 1; i++) {
			
			XSSFRow row = sheet.createRow(i);
			// 给这一行的第一列赋值
			row.createCell(0).setCellValue("MAC");
			// 给这一行的第一列赋值
			row.createCell(1).setCellValue("设备编码");
			row.createCell(2).setCellValue("使用人");
			row.createCell(3).setCellValue("部门信息");
			row.createCell(4).setCellValue("组织结构编码");
			row.createCell(5).setCellValue("上次登录时间");
			row.createCell(6).setCellValue("登录次数");
			
			System.out.println("第一行生成完毕 ");
		}
		 
		//继续生产剩下的内容
		try {
				
			createExcelContentRows(  sheet,deviceIds );
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		sheet.autoSizeColumn((short)0); //自动调整列宽
	    sheet.autoSizeColumn((short)1); //调整第一列宽度
		sheet.autoSizeColumn((short)2); 
		sheet.autoSizeColumn((short)3); 
		sheet.autoSizeColumn((short)4); 
		sheet.autoSizeColumn((short)5);  
	}
	
	//暂时先用数据库取回来的数据和deviceIds这个数组里面的数据进行比较  有相同的才生成excel 
	private void createExcelContentRows(XSSFSheet sheet ,String[]  deviceIds  ) throws UnsupportedEncodingException{
		JSONObject contents = new JSONObject();
		Map<String, Object> contentsMap = new TreeMap<String, Object>();
	 
		strStartTime = (String) getStrStartTime();
		//strStartTime = URLDecoder.decode(strStartTime, "utf-8");
		//strStartTime ="2013-03-03 17:23:07";
		strEndTime = (String) getStrEndTime();
		//strEndTime = URLDecoder.decode(strEndTime, "utf-8");
		//strEndTime="2016-03-20 11:34:30";
		strOfficeName = (String) getStrOfficeName();//(); 
		//strOfficeName = URLDecoder.decode(strOfficeName, "utf-8");
		//strOfficeName="吉林分公司";
		System.out.println("strStartTime: " + strStartTime);
		System.out.println("strEndTime: " + strEndTime);
		System.out.println("strOfficeName: " + strOfficeName);
		
		
		strStartTime = (String)getStrStartTime();
		strStartTime = URLDecoder.decode(strStartTime,"utf-8");
		strEndTime = (String)getStrEndTime();
		strEndTime = URLDecoder.decode(strEndTime,"utf-8");
		strOfficeName = (String)getStrOfficeName();
		strOfficeName = URLDecoder.decode(strOfficeName,"utf-8");
		officeID = (int) getOfficeID();
		// object here has three elements: iDevId, iActiveCount, Device
		List<Object[]> DeviceInfoList = this.activeStateService
				.getDeviceActiveStateHistory(strStartTime, strEndTime,
						officeID);
		Iterator<Object[]> iterator = DeviceInfoList.iterator();
		System.out.println("DeviceInfoList size" + DeviceInfoList.size());

		Devicebaseinfo deviceBaseInfo = new Devicebaseinfo();
		long lValue = 0;
		String sValue = "";
		Integer iValue= 0;
		short siValue = 0;
		Date dValue;
		int i = 0;
		Integer Id = 0;
		BigInteger DeviceCount;
		JSONArray Content = new JSONArray(); 
		int ii=1;
		
		while (iterator.hasNext()) {
			Object[] obj = iterator.next();
			deviceBaseInfo = (Devicebaseinfo) obj[2];
			DeviceCount = (BigInteger) obj[1];
  
			if(deviceIds!=null){
				for(String deviceId : deviceIds){
					if(deviceId.equals(deviceBaseInfo.getDevOnlyId().toString())){
						
						XSSFRow row = sheet.createRow(ii);
						// 给这一行的第一列赋值
						row.createCell(0).setCellValue( deviceBaseInfo.getMacAddress());//"MAC");
						// 给这一行的第一列赋值
						row.createCell(1).setCellValue(deviceBaseInfo.getDevOnlyId());//"设备编码");
						row.createCell(2).setCellValue(deviceBaseInfo.getUserName());//"使用人");
						row.createCell(3).setCellValue(deviceBaseInfo.getDeptName());//"部门信息");
						row.createCell(4).setCellValue( deviceBaseInfo.getOfficeName());//"组织结构编码");
						row.createCell(5).setCellValue(deviceBaseInfo.getLastTime());//"上次登录时间");
						row.createCell(6).setCellValue(DeviceCount.toString());//"登录次数"); 
						ii++;
						break;
					}  
				}
			}else{ 
				XSSFRow row = sheet.createRow(ii);
				// 给这一行的第一列赋值
				row.createCell(0).setCellValue("该部门没有设备 或者 是您没有在前台选择需要导入excel的设备" );
				break;
			} 
			
			 
		/*
			Id = (Integer) obj[0]; 
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
			iValue = deviceBaseInfo.getAssetId();
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
			Column.put("f36", sValue);
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
		*/
			i++;
		}   
	}
	
	
	private void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	//  excel方法结束


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


	public Integer getiFunc() {
		return iFunc;
	}


	public void setiFunc(Integer iFunc) {
		this.iFunc = iFunc;
	}


	public String getStrOfficeName() {
		return strOfficeName;
	}


	public void setStrOfficeName(String strOfficeName) {
		this.strOfficeName = strOfficeName;
	}


	public int getOfficeID() {
		return officeID;
	}


	public void setOfficeID(int officeID) {
		this.officeID = officeID;
	}


	public int getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}


	public GetOrgListService getGetOrgListService() {
		return getOrgListService;
	}


	public void setGetOrgListService(GetOrgListService getOrgListService) {
		this.getOrgListService = getOrgListService;
	}


	public static String getFileSeparator() {
		return FILE_SEPARATOR;
	}



}
