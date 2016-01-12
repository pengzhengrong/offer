package service;

import java.util.List;
import java.util.Map;

import entrty.AdminPo;
import entrty.CompanyPo;
import entrty.UserDetailPo;

public interface AdminService {
	
	public int checkUser( String userName , String pwd );
	
	public int saveOrUpdate(String sql);
	
	public int delete(int id);
	
	public List queryByPage(String sql, int nowPage, int pageSize,Class ObjClass);
	
	public int getMaxRows(String sql);
	
}
