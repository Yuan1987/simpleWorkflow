package com.dynastech.base.webResponse;

import java.util.List;

public class RestPage<T> {

	private int page;
	
	private boolean hasNext;
	
	private boolean hasPrevous;
	
	private List<T> data;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrevous() {
		return hasPrevous;
	}

	public void setHasPrevous(boolean hasPrevous) {
		this.hasPrevous = hasPrevous;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	/*public static <T> RestPage<T> create(PageList<T> pageList){
		 RestPage<T> restPage=null;
			if(pageList!=null){
				Paginator paginator =pageList.getPaginator();
				 restPage = new  RestPage<T>();
				 restPage.setData(new ArrayList<T>(pageList));
				 if(null!=paginator){
					 restPage.setHasNext(!paginator.isLastPage());
					 restPage.setHasPrevous(!paginator.isFirstPage());
					 restPage.setPage(paginator.getPage());
				 }else{
					 restPage.setHasNext(true);
					 restPage.setHasPrevous(true);
					 restPage.setPage(0);
				 }
			}
			
			 return restPage;
	}*/
	
}
