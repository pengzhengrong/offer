package service.impl;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.GlobalDao;
import entrty.UserJobPo;
import loggerUtils.DbLog;
import service.UserJobService;
import utils.BaseDao;

public class UserJobServiceImpl extends BaseDao implements UserJobService{

	@Autowired
	private GlobalDao globalDao;
	
	@Override
	public int save(int userId,String userName, String companyName, String jobName) {
		// TODO Auto-generated method stub
		int now = (int) new Date().getTime();
		String sql = "insert into `user_job`(`user_id`,`user_name`,`company_name`,`job_name`,`create_time`,`update_time`) values(?,?,?,?,?,?)";
		DbLog.querySuccess(sql);
		int[] argTypes = {Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		Object[] args = {userId,userName,companyName,jobName,now,now};
		return this.update(sql, args, argTypes);
	}

	@Override
	public List<UserJobPo> queryByPage(String sql, int nowPage, int pageSize) {
		// TODO Auto-generated method stub
		return globalDao.queryByPage(sql, nowPage, pageSize, UserJobPo.class);
	}

	@Override
	public int update(int id,int statu) {
		// TODO Auto-generated method stub
		String sql = "update `user_job` set `statu`="+statu+" where `id`="+id;
		DbLog.querySuccess(sql);
		return this.update(sql);
	}
	
	public int getMaxRows(String sql){
		return globalDao.getMaxRows(sql);
	}
	
	
	
	

}
