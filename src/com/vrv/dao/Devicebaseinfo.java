package com.vrv.dao;

import java.util.Date;

/**
 * Devicebaseinfo entity. @author MyEclipse Persistence Tools
 */

public class Devicebaseinfo implements java.io.Serializable {

	// Fields

	private long deviceId;
	private String devOnlyId;
	private String userName;
	private String deviceName;
	private String numIpaddress;
	private String ipaddres;
	private String macAddress;
	private String deptName;
	private String officeName;
	private String deviceType;
	private short registered;
	private short runStatus;
	private Integer areaId;
	private String cpuType;
	private Integer diskSize;
	private String antivirusSoftware;
	private String notInstalledPatch;
	private String ostype;
	private Date unInstallTime;
	private String description;
	private Integer devTypeCode;
	private String communicatIp;
	private Integer exField6;
	private String assetId;
	private String servicePack;
	private Date setupTmos;
	private String clientVersion;
	private Integer lockStatu;
	private Integer protectStatu;
	private Integer blockOrNot;
	private Integer area2;
	private String subOffice;
	private String room;
	private String tel;
	private String email;
	private Date lastTime;
	private Integer memorySize;
	private Integer cpusize;
	private Integer lastOnlineTime;
	private Integer onlineMinutes;
	private Integer onlineFormedTime;
	private Integer subHealthStatu;
	private Integer noBootDays;
	private Integer onlineTimes;
	private Integer diskSerialNumber;
	private String domainName;
	private String logonUserName;
	private String clientGroupId;
	private String clientUserId;
	private String netType;

	// Constructors

	/** default constructor */
	public Devicebaseinfo() {
	}

	/** full constructor */
	public Devicebaseinfo(String devOnlyId, String userName, String deviceName,
			String numIpaddress, String ipaddres, String macAddress,
			String deptName, String officeName, String deviceType,
			short registered, short runStatus, Integer areaId, String cpuType,
			Integer diskSize, String antivirusSoftware,
			String notInstalledPatch, String ostype, Date unInstallTime,
			String description, Integer devTypeCode, String communicatIp,
			Integer exField6, String assetId, String servicePack,
			Date setupTmos, String clientVersion, Integer lockStatu,
			Integer protectStatu, Integer blockOrNot, Integer area2,
			String subOffice, String room, String tel, String email,
			Date lastTime, Integer memorySize, Integer cpusize,
			Integer lastOnlineTime, Integer onlineMinutes,
			Integer onlineFormedTime, Integer subHealthStatu,
			Integer noBootDays, Integer onlineTimes, Integer diskSerialNumber,
			String domainName, String logonUserName, String clientGroupId,
			String clientUserId, String netType) {
		this.devOnlyId = devOnlyId;
		this.userName = userName;
		this.deviceName = deviceName;
		this.numIpaddress = numIpaddress;
		this.ipaddres = ipaddres;
		this.macAddress = macAddress;
		this.deptName = deptName;
		this.officeName = officeName;
		this.deviceType = deviceType;
		this.registered = registered;
		this.runStatus = runStatus;
		this.areaId = areaId;
		this.cpuType = cpuType;
		this.diskSize = diskSize;
		this.antivirusSoftware = antivirusSoftware;
		this.notInstalledPatch = notInstalledPatch;
		this.ostype = ostype;
		this.unInstallTime = unInstallTime;
		this.description = description;
		this.devTypeCode = devTypeCode;
		this.communicatIp = communicatIp;
		this.exField6 = exField6;
		this.assetId = assetId;
		this.servicePack = servicePack;
		this.setupTmos = setupTmos;
		this.clientVersion = clientVersion;
		this.lockStatu = lockStatu;
		this.protectStatu = protectStatu;
		this.blockOrNot = blockOrNot;
		this.area2 = area2;
		this.subOffice = subOffice;
		this.room = room;
		this.tel = tel;
		this.email = email;
		this.lastTime = lastTime;
		this.memorySize = memorySize;
		this.cpusize = cpusize;
		this.lastOnlineTime = lastOnlineTime;
		this.onlineMinutes = onlineMinutes;
		this.onlineFormedTime = onlineFormedTime;
		this.subHealthStatu = subHealthStatu;
		this.noBootDays = noBootDays;
		this.onlineTimes = onlineTimes;
		this.diskSerialNumber = diskSerialNumber;
		this.domainName = domainName;
		this.logonUserName = logonUserName;
		this.clientGroupId = clientGroupId;
		this.clientUserId = clientUserId;
		this.netType = netType;
	}

	// Property accessors

	public long getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDevOnlyId() {
		return this.devOnlyId;
	}

	public void setDevOnlyId(String devOnlyId) {
		this.devOnlyId = devOnlyId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getNumIpaddress() {
		return this.numIpaddress;
	}

	public void setNumIpaddress(String numIpaddress) {
		this.numIpaddress = numIpaddress;
	}

	public String getIpaddres() {
		return this.ipaddres;
	}

	public void setIpaddres(String ipaddres) {
		this.ipaddres = ipaddres;
	}

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOfficeName() {
		return this.officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public short getRegistered() {
		return this.registered;
	}

	public void setRegistered(short registered) {
		this.registered = registered;
	}

	public short getRunStatus() {
		return this.runStatus;
	}

	public void setRunStatus(short runStatus) {
		this.runStatus = runStatus;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getCpuType() {
		return this.cpuType;
	}

	public void setCpuType(String cpuType) {
		this.cpuType = cpuType;
	}

	public Integer getDiskSize() {
		return this.diskSize;
	}

	public void setDiskSize(Integer diskSize) {
		this.diskSize = diskSize;
	}

	public String getAntivirusSoftware() {
		return this.antivirusSoftware;
	}

	public void setAntivirusSoftware(String antivirusSoftware) {
		this.antivirusSoftware = antivirusSoftware;
	}

	public String getNotInstalledPatch() {
		return this.notInstalledPatch;
	}

	public void setNotInstalledPatch(String notInstalledPatch) {
		this.notInstalledPatch = notInstalledPatch;
	}

	public String getOstype() {
		return this.ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public Date getUnInstallTime() {
		return this.unInstallTime;
	}

	public void setUnInstallTime(Date unInstallTime) {
		this.unInstallTime = unInstallTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDevTypeCode() {
		return this.devTypeCode;
	}

	public void setDevTypeCode(Integer devTypeCode) {
		this.devTypeCode = devTypeCode;
	}

	public String getCommunicatIp() {
		return this.communicatIp;
	}

	public void setCommunicatIp(String communicatIp) {
		this.communicatIp = communicatIp;
	}

	public Integer getExField6() {
		return this.exField6;
	}

	public void setExField6(Integer exField6) {
		this.exField6 = exField6;
	}

	public String getAssetId() {
		return this.assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getServicePack() {
		return this.servicePack;
	}

	public void setServicePack(String servicePack) {
		this.servicePack = servicePack;
	}

	public Date getSetupTmos() {
		return this.setupTmos;
	}

	public void setSetupTmos(Date setupTmos) {
		this.setupTmos = setupTmos;
	}

	public String getClientVersion() {
		return this.clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public Integer getLockStatu() {
		return this.lockStatu;
	}

	public void setLockStatu(Integer lockStatu) {
		this.lockStatu = lockStatu;
	}

	public Integer getProtectStatu() {
		return this.protectStatu;
	}

	public void setProtectStatu(Integer protectStatu) {
		this.protectStatu = protectStatu;
	}

	public Integer getBlockOrNot() {
		return this.blockOrNot;
	}

	public void setBlockOrNot(Integer blockOrNot) {
		this.blockOrNot = blockOrNot;
	}

	public Integer getArea2() {
		return this.area2;
	}

	public void setArea2(Integer area2) {
		this.area2 = area2;
	}

	public String getSubOffice() {
		return this.subOffice;
	}

	public void setSubOffice(String subOffice) {
		this.subOffice = subOffice;
	}

	public String getRoom() {
		return this.room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getMemorySize() {
		return this.memorySize;
	}

	public void setMemorySize(Integer memorySize) {
		this.memorySize = memorySize;
	}

	public Integer getCpusize() {
		return this.cpusize;
	}

	public void setCpusize(Integer cpusize) {
		this.cpusize = cpusize;
	}

	public Integer getLastOnlineTime() {
		return this.lastOnlineTime;
	}

	public void setLastOnlineTime(Integer lastOnlineTime) {
		this.lastOnlineTime = lastOnlineTime;
	}

	public Integer getOnlineMinutes() {
		return this.onlineMinutes;
	}

	public void setOnlineMinutes(Integer onlineMinutes) {
		this.onlineMinutes = onlineMinutes;
	}

	public Integer getOnlineFormedTime() {
		return this.onlineFormedTime;
	}

	public void setOnlineFormedTime(Integer onlineFormedTime) {
		this.onlineFormedTime = onlineFormedTime;
	}

	public Integer getSubHealthStatu() {
		return this.subHealthStatu;
	}

	public void setSubHealthStatu(Integer subHealthStatu) {
		this.subHealthStatu = subHealthStatu;
	}

	public Integer getNoBootDays() {
		return this.noBootDays;
	}

	public void setNoBootDays(Integer noBootDays) {
		this.noBootDays = noBootDays;
	}

	public Integer getOnlineTimes() {
		return this.onlineTimes;
	}

	public void setOnlineTimes(Integer onlineTimes) {
		this.onlineTimes = onlineTimes;
	}

	public Integer getDiskSerialNumber() {
		return this.diskSerialNumber;
	}

	public void setDiskSerialNumber(Integer diskSerialNumber) {
		this.diskSerialNumber = diskSerialNumber;
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getLogonUserName() {
		return this.logonUserName;
	}

	public void setLogonUserName(String logonUserName) {
		this.logonUserName = logonUserName;
	}

	public String getClientGroupId() {
		return this.clientGroupId;
	}

	public void setClientGroupId(String clientGroupId) {
		this.clientGroupId = clientGroupId;
	}

	public String getClientUserId() {
		return this.clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String getNetType() {
		return this.netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

}