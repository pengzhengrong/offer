package service.impl;

import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

import loggerUtils.DbLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GlobalDao;
import dao.UserDao;
import entrty.AdminPo;
import entrty.CompanyPo;
import entrty.JobPo;
import entrty.UserPo;
import service.AdminService;
import service.CompanyService;
import service.UserService;
import utils.BaseDao;

@Service
public class AdminServiceImpl extends BaseDao implements AdminService {

	
	@Autowired
	private GlobalDao globalDao;
	
	@Override
	public int checkUser(String userName, String pwd) {
		// TODO Auto-generated method stub
		return globalDao.checkLogin("admin", userName, pwd);
	}

	@Override
	public int saveOrUpdate(String sql) {
		// TODO Auto-generated method stub
		return this.update(sql);
	}


	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from `company` where `id`="+id;
		return this.update(sql);
	}


	@Override
	public List queryByPage(String sql, int nowPage, int pageSize,Class ObjClass) {
		// TODO Auto-generated method stub
		return globalDao.queryByPage(sql, nowPage, pageSize, ObjClass);
	}
	
	public int getMaxRows(String sql){
		return globalDao.getMaxRows(sql);
	}
	
	
	
	


}
