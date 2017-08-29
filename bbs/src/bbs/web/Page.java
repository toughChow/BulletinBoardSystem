package bbs.web;

import java.util.List;

public class Page<T> {

	private int pageNo = 3;
	
	private List<T> list;
	
	private int pageSize = 4;
	
	private long totalPostNumber;
	
	
	public Page() {
		super();
	}
	public Page(int pageNo){
		super();
		this.pageNo = pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageNo(){
		if(pageNo < 0){
			pageNo = 1;
		}
		if(pageNo > getTotalPageNumber()){
			pageNo = getTotalPageNumber();
		}
		return pageNo;
	}

	public int getPageSize(){
		return pageSize;
	}
	
	public void setList(List<T> list){
		this.list = list;
	}
	
	public List<T> getList(){
		return list;
	}
	
	public int getTotalPageNumber() {
		int totalPageNumber = (int) (totalPostNumber/pageSize);
		if(totalPostNumber % pageSize != 0){
			totalPageNumber = totalPageNumber + 1;
		}
		return totalPageNumber;
	}
	
	public void setTotalPostNumber(long totalPostNumber){
		this.totalPostNumber = totalPostNumber;
	}

	public boolean isHasNext(){
		if(getPageNo() < getTotalPageNumber()){
			return true;
		}
		
		return false;
	}
	
	public boolean isHasPrev(){
		if(getPageNo() > 1){
			return true;
		}
		
		return false;
	}
	
	public int getPrevPage(){
		if(isHasPrev()){
			return getPageNo() - 1;
		}
		
		return getPageNo();
	}
	
	public int getNextPage(){
		if(isHasNext()){
			return getPageNo() + 1;
		}
		
		return getPageNo();
	}
}
