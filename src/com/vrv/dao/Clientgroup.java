package com.vrv.dao;

import java.util.Date;

/**
 * Clientgroup entity. @author MyEclipse Persistence Tools
 */

public class Clientgroup implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private String groupName;
	private String createUser;
	private Date createTime;
	private Date repTime;
	private Integer upGroup;
	private String groupDistr;
	private String description;
	private String groupType;

	// Constructors

	/** default constructor */
	public Clientgroup() {
	}

	/** full constructor */
	public Clientgroup(String groupName, String createUser, Date createTime,
			Date repTime, Integer upGroup, String groupDistr,
			String description, String groupType) {
		this.groupName = groupName;
		this.createUser = createUser;
		this.createTime = createTime;
		this.repTime = repTime;
		this.upGroup = upGroup;
		this.groupDistr = groupDistr;
		this.description = description;
		this.groupType = groupType;
	}

	// Property accessors

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

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getRepTime() {
		return this.repTime;
	}

	public void setRepTime(Date repTime) {
		this.repTime = repTime;
	}

	public Integer getUpGroup() {
		return this.upGroup;
	}

	public void setUpGroup(Integer upGroup) {
		this.upGroup = upGroup;
	}

	public String getGroupDistr() {
		return this.groupDistr;
	}

	public void setGroupDistr(String groupDistr) {
		this.groupDistr = groupDistr;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGroupType() {
		return this.groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

}