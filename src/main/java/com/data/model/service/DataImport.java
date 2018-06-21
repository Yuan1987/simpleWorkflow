package com.data.model.service;

import com.data.model.entity.VUser;

public interface DataImport {
	
	public void deptImport();
	
	public void serialImport();
	
	public void userImport(VUser vu);
	
	public void userPostInfo();

}
