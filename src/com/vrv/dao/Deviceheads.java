package com.vrv.dao;

/**
 * Deviceheads entity. @author MyEclipse Persistence Tools
 */

public class Deviceheads implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private boolean checked;
	private String enName;

	// Constructors

	/** default constructor */
	public Deviceheads() {
	}

	/** minimal constructor */
	public Deviceheads(String name, boolean checked) {
		this.name = name;
		this.checked = checked;
	}

	/** full constructor */
	public Deviceheads(String name, boolean checked, String enName) {
		this.name = name;
		this.checked = checked;
		this.enName = enName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

}