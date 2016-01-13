package service;

import java.util.List;

import entrty.JobPo;

public interface JobService {
	
	public JobPo getJob(int id);
	
	public int save( JobPo po , int companyId ,String companyName);
	
	public int update(JobPo po);
	
	public int delete( int id);
	
	public List<JobPo> getList(int companyId);
	
	public List<JobPo> queryByPage(int companyId , int nowPage , int pageSize);
	
	public List<JobPo> queryByPage( String sql , int nowPage , int pageSize);
	
	public int getMaxRows();
	
}
