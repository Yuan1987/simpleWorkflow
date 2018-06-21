package com.dynastech.flow.entity;

public class QueryTask {
	
	private String key;
	
	private Boolean status;
	
	private String node;
	
	private int page;
	
	private int size;
	
	public QueryTask(String key,int page,int size){
		this.key=key;
		this.page=page;
		this.size=size;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
