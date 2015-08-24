package com.vrv.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;



import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.opensymphony.xwork2.ActionContext;
import com.vrv.dao.Devicebaseinfo;
import com.vrv.service.DomLoginTerminalService;

public class DomLoginTerminalAction {
	private DomLoginTerminalService DLTService;
	private Integer IsDom;
	private Integer OrgId;
	private String DeviceId;
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
	
	public void execute() throws IOException{
		System.out.println("I am coming");
		JSONObject contents= new JSONObject();
		Map<String, Object> contentsMap = new TreeMap<String,Object>();
		System.out.println("I am coming2");
		/*DLTSId = this.getDLTSId();
		System.out.println("DLTSId:"+DLTSId);
		Devicebaseinfo devicebaseInfo = new Devicebaseinfo();
		devicebaseInfo = this.DLTService.getDeviceBaseInfo(DLTSId);
		System.out.println("devicebaseInfo.deviceName:"+devicebaseInfo.getDeviceName());
		if(devicebaseInfo != null){
			ActionContext.getContext().getSession().put("current", devicebaseInfo);
			return "success";
		}*/
		IsDom = this.getIsDom();
		OfficeName = this.getOfficeName();
		OfficeName = URLDecoder.decode(OfficeName,"utf-8");
		OrgId = this.getOrgId();
		System.out.println("IsDom:"+IsDom+", OfficeName:" + OfficeName+", OrgId:" + OrgId);
		List<Devicebaseinfo> DeviceInfoList = this.DLTService.getDeviceDomInfo(IsDom,OrgId);
		int SumNum = DeviceInfoList.size();
		Iterator<Devicebaseinfo> iterator = DeviceInfoList.iterator();
		Devicebaseinfo deviceBaseInfo = new Devicebaseinfo();
		long lValue = 0;
		String sValue = "";
		Integer iValue = 0;
		short siValue = 0;
		Date dValue;
		int i = 0;
		JSONArray Content = new JSONArray();
    	while(iterator.hasNext()){
    		deviceBaseInfo = iterator.next();
    		JSONObject Column = new JSONObject();
    		sValue= deviceBaseInfo.getDevOnlyId();
    		Column.put("f2", sValue);
    		sValue= deviceBaseInfo.getUserName();
    		Column.put("f3", sValue);
    		sValue= deviceBaseInfo.getMacAddress();
    		Column.put("f7", sValue);
    		sValue= deviceBaseInfo.getDeptName();
    		Column.put("f8", sValue);
    		sValue= deviceBaseInfo.getOfficeName();
    		Column.put("f9", sValue);
    		dValue= deviceBaseInfo.getLastTime();
    		Column.put("f36", dValue.toString());
    		iValue= deviceBaseInfo.getLastOnlineTime();
    		Column.put("f39", iValue);
    		Content.add(i,Column);
    		i++;
      }
    	contentsMap.put("code",0);
    	contentsMap.put("IsLastPage",1);
    	contentsMap.put("return",Content);
    	Long IsLogin = this.DLTService.getDeviceDomNum(1, OrgId);
    	Long NoLogin = this.DLTService.getDeviceDomNum(2, OrgId);
    	contentsMap.put("IsLogin", IsLogin);
    	contentsMap.put("NoLogin", NoLogin);
    	contentsMap.put("All", this.DLTService.getDeviceDomNum(0, OrgId));
    	System.out.println("OrgId:"+OrgId);
    	List<Object[]> mdd = this.DLTService.getSubOrgDeviceNum(OrgId);
    	JSONArray OrgInfos = new JSONArray();
    	
    	System.out.println(mdd.size());
    	for(int k=0;k<mdd.size();k++){
    		JSONArray OrgInfo = new JSONArray();
    		OrgInfo.add(0, (Long)(mdd.get(k)[1]));
    		OrgInfo.add(1, (String)(mdd.get(k)[0]));
    		OrgInfos.add(k,OrgInfo);
    	}
    	if(mdd.size() == 0){//直接传输本部分的信息
    		JSONArray OrgInfo = new JSONArray();
    		OrgInfo.add(0, NoLogin);
    		OrgInfo.add(1, OfficeName);
    		OrgInfos.add(0,OrgInfo);
    	}
    	System.out.println(OrgInfos.toString());
    	contentsMap.put("orginfos", OrgInfos);
    	contents = JSONObject.fromObject(contentsMap);
    	System.out.println(contents.toString());
    	ServletResponse response =  ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		out.println(contents.toString());
		
		return;
	}
	
	public DomLoginTerminalService getDLTService() {
		return DLTService;
	}
	public void setDLTService(DomLoginTerminalService dLTService) {
		DLTService = dLTService;
	}
	
	public Integer getIsDom() {
		return IsDom;
	}
	public void setIsDom(Integer isDom) {
		IsDom = isDom;
	}

	public Integer getOrgId() {
		return OrgId;
	}

	public void setOrgId(Integer orgId) {
		OrgId = orgId;
	}

	public String getDeviceId() {
		return DeviceId;
	}
	public void setDeviceId(String deviceId) {
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
	
	
}
