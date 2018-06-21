package com.dynastech.flow.entity;

import java.util.List;

public class QueryTaskResult {
	
	private long count;
	
	private List<Task> tList;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<Task> gettList() {
		return tList;
	}

	public void settList(List<Task> tList) {
		this.tList = tList;
	}

}
