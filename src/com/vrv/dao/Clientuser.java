package com.vrv.dao;

import java.util.Date;

/**
 * Clientuser entity. @author MyEclipse Persistence Tools
 */

public class Clientuser implements java.io.Serializable {

	// Fields

	private String cuserName;
	private String cuserPass;
	private String cuserType;
	private String currentIp;
	private String cuserTel;
	private String unitName;
	private String deptName;
	private String officeName;
	private String addUser;
	private Date lastLoginTime;
	private String lastLoginDevice;
	private String everUseDevice;
	private String description;
	private Integer reserve1;
	private String reserve2;
	private String emailAddress;
	private Integer groupId;
	private String groupName;

	// Constructors

	/** default constructor */
	public Clientuser() {
	}

	/** full constructor */
	public Clientuser(String cuserPass, String cuserType, String currentIp,
			String cuserTel, String unitName, String deptName,
			String officeName, String addUser, Date lastLoginTime,
			String lastLoginDevice, String everUseDevice, String description,
			Integer reserve1, String reserve2, String emailAddress,
			Integer groupId, String groupName) {
		this.cuserPass = cuserPass;
		this.cuserType = cuserType;
		this.currentIp = currentIp;
		this.cuserTel = cuserTel;
		this.unitName = unitName;
		this.deptName = deptName;
		this.officeName = officeName;
		this.addUser = addUser;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginDevice = lastLoginDevice;
		this.everUseDevice = everUseDevice;
		this.description = description;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
		this.emailAddress = emailAddress;
		this.groupId = groupId;
		this.groupName = groupName;
	}

	// Property accessors

	public String getCuserName() {
		return this.cuserName;
	}

	public void setCuserName(String cuserName) {
		this.cuserName = cuserName;
	}

	public String getCuserPass() {
		return this.cuserPass;
	}

	public void setCuserPass(String cuserPass) {
		this.cuserPass = cuserPass;
	}

	public String getCuserType() {
		return this.cuserType;
	}

	public void setCuserType(String cuserType) {
		this.cuserType = cuserType;
	}

	public String getCurrentIp() {
		return this.currentIp;
	}

	public void setCurrentIp(String currentIp) {
		this.currentIp = currentIp;
	}

	public String getCuserTel() {
		return this.cuserTel;
	}

	public void setCuserTel(String cuserTel) {
		this.cuserTel = cuserTel;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginDevice() {
		return this.lastLoginDevice;
	}

	public void setLastLoginDevice(String lastLoginDevice) {
		this.lastLoginDevice = lastLoginDevice;
	}

	public String getEverUseDevice() {
		return this.everUseDevice;
	}

	public void setEverUseDevice(String everUseDevice) {
		this.everUseDevice = everUseDevice;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReserve1() {
		return this.reserve1;
	}

	public void setReserve1(Integer reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return this.reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}