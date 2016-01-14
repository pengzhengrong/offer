package entrty;

public class PageInfo {

	private static int nowPage = 1;
	
	private static int pageSize = 5;
	
	private int totalRows;
	
	private int totalPage;
	
	private int offset;
	
	
	public PageInfo(int page,int pageSize){
		this.offset = (page-1)*pageSize ;
		this.pageSize = pageSize;
	}


	public static int getNowPage() {
		return nowPage;
	}



	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public static int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalRows() {
		return totalRows;
	}


	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getOffset() {
		return offset;
	}


	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
	

	
	
	
}
