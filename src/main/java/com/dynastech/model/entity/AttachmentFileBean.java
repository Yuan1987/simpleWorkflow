package com.dynastech.model.entity;

import java.util.List;

public class AttachmentFileBean extends AttachmentFile {
	
	private List<PersonAbility> paList;
	
	public List<PersonAbility> getPaList() {
		return paList;
	}
	
	public void setPaList(List<PersonAbility> paList) {
		this.paList = paList;
	}

}
