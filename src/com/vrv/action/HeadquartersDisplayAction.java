package com.vrv.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.vrv.service.HeadquartersDisplayService;

public class HeadquartersDisplayAction {
	private HeadquartersDisplayService headquartersDisplayService;
	private Integer totalNumber;
	private Integer unregisteredNumber;
	private Integer inactiveNumber;
	private Integer outdomainNumber;
	private Integer intranetNumber;
	private Integer internetNumber;
	

	public HeadquartersDisplayService getHeadquartersDisplayService() {
		return headquartersDisplayService;
	}


	public void setHeadquartersDisplayService(
			HeadquartersDisplayService headquartersDisplayService) {
		this.headquartersDisplayService = headquartersDisplayService;
	}


	public Integer getTotalNumber() {
		return totalNumber;
	}


	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}


	public Integer getUnregisteredNumber() {
		return unregisteredNumber;
	}


	public void setUnregisteredNumber(Integer unregisteredNumber) {
		this.unregisteredNumber = unregisteredNumber;
	}


	public Integer getInactiveNumber() {
		return inactiveNumber;
	}


	public void setInactiveNumber(Integer inactiveNumber) {
		this.inactiveNumber = inactiveNumber;
	}


	public Integer getOutdomainNumber() {
		return outdomainNumber;
	}


	public void setOutdomainNumber(Integer outdomainNumber) {
		this.outdomainNumber = outdomainNumber;
	}


	public Integer getIntranetNumber() {
		return intranetNumber;
	}


	public void setIntranetNumber(Integer intranetNumber) {
		this.intranetNumber = intranetNumber;
	}


	public Integer getInternetNumber() {
		return internetNumber;
	}


	public void setInternetNumber(Integer internetNumber) {
		this.internetNumber = internetNumber;
	}


	public void execute() throws IOException{
		JSONObject contents= new JSONObject();
		String sValue = "";
		Long AllSum = headquartersDisplayService.getSumOfDevice();
		sValue = "" + headquartersDisplayService.getSumOfDevice();
		contents.put("totalNumber", sValue);
		sValue = "" + headquartersDisplayService.getUnregisteredNumOfDevice();
		contents.put("unregisteredNumber", sValue);
		sValue = "" + (AllSum - headquartersDisplayService.getActiveNumOfDevice().longValue());
		contents.put("inactiveNumber", sValue);
		sValue = "" + headquartersDisplayService.getOutdomainNumOfDevice();
		contents.put("outdomainNumber", sValue);
		sValue = "" + headquartersDisplayService.getIntranetNumOfDevice();
		contents.put("intranetNumber", sValue);
		sValue = "" + headquartersDisplayService.getInternetNumOfDevice();
		contents.put("internetNumber", sValue);
		
		ServletResponse response =  ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		out.println(contents.toString());
	}
}
 