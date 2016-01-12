package service;

import java.util.Map;

import entrty.CompanyPo;
import entrty.JobPo;

public interface SearchService {
	
	public Object searchFor(Map<String, String> map ,String value );
	
	public Object searchByPage(Map<String, String> map ,String value ,int nowPage ,int pageSize ,Class objClass);
	
	public int getMaxRows();
	
}
