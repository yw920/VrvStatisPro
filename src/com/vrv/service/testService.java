package com.vrv.service;

import com.vrv.dao.*;

public class testService {
	private StdStateDAO stdStateDAO;

	public StdState getStdState(Integer Id) {
		StdState stdState = this.stdStateDAO.findById(Id);
		return stdState;
	}
	public StdState getStdStateInfo(Integer Id) {
		StdState stdState = this.stdStateDAO.getMySql(Id);
		return stdState;
	}
	public Integer getStdCount() {
		Integer ICount = this.stdStateDAO.getCount();
		return ICount;
	}
	public StdStateDAO getStdStateDAO() {
		return stdStateDAO;
	}

	public void setStdStateDAO(StdStateDAO stdStateDAO) {
		this.stdStateDAO = stdStateDAO;
	}
	
}
