package com.vrv.dao;

/**
 * StdState entity. @author MyEclipse Persistence Tools
 */

public class StdState implements java.io.Serializable {

	// Fields

	private Integer id;
	private String stdNo;
	private String stdName;
	private Integer techDomainId;
	private String stdComment;

	// Constructors

	/** default constructor */
	public StdState() {
	}

	/** minimal constructor */
	public StdState(String stdNo, Integer techDomainId) {
		this.stdNo = stdNo;
		this.techDomainId = techDomainId;
	}

	/** full constructor */
	public StdState(String stdNo, String stdName, Integer techDomainId,
			String stdComment) {
		this.stdNo = stdNo;
		this.stdName = stdName;
		this.techDomainId = techDomainId;
		this.stdComment = stdComment;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStdNo() {
		return this.stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public String getStdName() {
		return this.stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public Integer getTechDomainId() {
		return this.techDomainId;
	}

	public void setTechDomainId(Integer techDomainId) {
		this.techDomainId = techDomainId;
	}

	public String getStdComment() {
		return this.stdComment;
	}

	public void setStdComment(String stdComment) {
		this.stdComment = stdComment;
	}

}