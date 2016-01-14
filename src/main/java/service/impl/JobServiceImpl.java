package service.impl;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.GlobalDao;
import loggerUtils.DbLog;
import entrty.JobPo;
import service.JobService;
import utils.BaseDao;

public class JobServiceImpl extends BaseDao implements JobService {

	private int totalRows;
	
	@Autowired
	private GlobalDao globalDao;
	
	@Override
	public JobPo getJob(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from `jobs` where `id`="+id;
		return this.queryForObject(sql, JobPo.class);
	}

	@Override
	public int save(JobPo po , int companyId, String companyName) {
		// TODO Auto-generated method stub
		int now = (int) new Date().getTime();
		String sql = "insert into `jobs`(`company_id`,`company_name`,`name`,`desc`,`address`,`salay`,`create_time`,`update_time`) values(?,?,?,?,?,?,?,?)";
		int[] argTypes = {Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.LONGVARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		Object[] args = {companyId ,companyName, po.getName(),po.getDesc(),po.getAddress(),po.getSalay(),now ,now };
		return this.update(sql, args, argTypes);
	}

	@Override
	public int update(JobPo po) {
		// TODO Auto-generated method stub
		int now = (int) new Date().getTime();
		String sql = "update `jobs` set `name`=?,`desc`=?,`address`=?,`salay`=?,`update_time`=? where `id`=?";
		int[] argTypes = {Types.VARCHAR,Types.LONGVARCHAR,Types.VARCHAR,Types.VARCHAR ,Types.INTEGER,Types.INTEGER};
		Object[] args = {po.getName(),po.getDesc(),po.getAddress(),po.getSalay(),now ,po.getId() };
		return this.update(sql, args, argTypes);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		String sql ="delete from `jobs` where `id`="+id;
		return this.update(sql);
	}

	@Override
	public List<JobPo> getList(int companyId) {
		// TODO Auto-generated method stub
		String sql = "select * from `jobs` where `company_id`="+companyId;
		return this.queryForList(sql, JobPo.class);
	}

	@Override
	public List<JobPo> queryByPage(int companyId, int nowPage , int pageSize) {
		// TODO Auto-generated method stub
		int offset = (nowPage-1)*pageSize;
		String sql = "select * from `jobs` where `company_id`="+companyId +" limit "+offset+","+pageSize;
		return this.queryForList(sql, JobPo.class);
	}
	
	@Override
	public List<JobPo> queryByPage(String sql, int nowPage, int pageSize) {
		// TODO Auto-generated method stub
		totalRows = globalDao.getMaxRows(sql);
		return globalDao.queryByPage(sql, nowPage, pageSize, JobPo.class);
	}
	
	public int getMaxRows(){
		return totalRows;
	}

}
