package com.vrv.dao;

/**
 * Orglist entity. @author MyEclipse Persistence Tools
 */

public class Orglist implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer supid;
	private Integer nextid;
	private Integer childid;
	private String name;

	// Constructors

	/** default constructor */
	public Orglist() {
	}

	/** full constructor */
	public Orglist(Integer supid, Integer nextid, Integer childid, String name) {
		this.supid = supid;
		this.nextid = nextid;
		this.childid = childid;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSupid() {
		return this.supid;
	}

	public void setSupid(Integer supid) {
		this.supid = supid;
	}

	public Integer getNextid() {
		return this.nextid;
	}

	public void setNextid(Integer nextid) {
		this.nextid = nextid;
	}

	public Integer getChildid() {
		return this.childid;
	}

	public void setChildid(Integer childid) {
		this.childid = childid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}