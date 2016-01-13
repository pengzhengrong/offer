package service;

import java.util.List;

import entrty.UserJobPo;

public interface UserJobService {

	public int save(int userId,String userName,String companyName , String jobName);
	
	public List<UserJobPo> queryByPage(String sql,int nowPage,int pageSize);
	
	public int update(int id ,int statu);
	
	public int getMaxRows(String sql);
	
	public int delete(int id);
	
}
