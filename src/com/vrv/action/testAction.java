package com.vrv.action;

import com.opensymphony.xwork2.ActionContext;
import com.vrv.dao.*;
import com.vrv.service.*;

public class testAction {
	private testService testServ;
	private Integer stdId;
	private String stdName;
	private String stdComment;
	
	public String execute(){
		String result = "error";
		System.out.println("I am coming!");
		stdId = this.getStdId();
		System.out.println("stdId:"+stdId);
		StdState stdState = new StdState();
		stdState = this.testServ.getStdState(stdId);
		System.out.println("stdState.Name:"+stdState.getStdName());
		if(stdState != null){
			ActionContext.getContext().getSession().put("current", stdState);
		}
		stdState = this.testServ.getStdStateInfo(stdId);
		System.out.println("getStdStateInfo:"+stdState.getStdName());
		if(stdState != null){
			ActionContext.getContext().getSession().put("current", stdState);
		}
		System.out.println("Count:"+this.testServ.getStdCount());
		return result;
	}
	public testService getTestServ() {
		return testServ;
	}
	public void setTestServ(testService testServ) {
		this.testServ = testServ;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public String getStdComment() {
		return stdComment;
	}
	public void setStdComment(String stdComment) {
		this.stdComment = stdComment;
	}
	public Integer getStdId() {
		return stdId;
	}
	public void setStdId(Integer stdId) {
		this.stdId = stdId;
	}
	
}
