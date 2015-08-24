package com.vrv.dao;

import java.util.Date;

/**
 * Loginloginfo entity. @author MyEclipse Persistence Tools
 */

public class Loginloginfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String loginTime;
	private String hostName;
	private String hostMac;
	private String hostIp;
	private Date opdatetime;

	// Constructors

	/** default constructor */
	public Loginloginfo() {
	}

	/** full constructor */
	public Loginloginfo(String username, String loginTime, String hostName,
			String hostMac, String hostIp, Date opdatetime) {
		this.username = username;
		this.loginTime = loginTime;
		this.hostName = hostName;
		this.hostMac = hostMac;
		this.hostIp = hostIp;
		this.opdatetime = opdatetime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostMac() {
		return this.hostMac;
	}

	public void setHostMac(String hostMac) {
		this.hostMac = hostMac;
	}

	public String getHostIp() {
		return this.hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public Date getOpdatetime() {
		return this.opdatetime;
	}

	public void setOpdatetime(Date opdatetime) {
		this.opdatetime = opdatetime;
	}

}