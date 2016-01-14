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
import entrty.CompanyPo;
import entrty.JobPo;
import entrty.UserPo;
import service.CompanyService;
import service.UserService;
import utils.BaseDao;

@Service
public class CompanyServiceImpl extends BaseDao implements CompanyService {

	private int totalRows;
	
	@Autowired
	private GlobalDao globalDao;
	
	@Override
	public int checkUser(String userName, String pwd) {
		// TODO Auto-generated method stub
		return globalDao.checkLogin("company", userName, pwd);
	}

	@Override
	public int publishJob(JobPo po ,int companyId) {
		// TODO Auto-generated method stub
		int now = (int) new Date().getTime();
		String sql = "insert into `jobs`(`company_id`,`name`,`desc`,`address`,`salay`,`create_time`,`update_time`) values(?,?,?,?,?,?,?)";
		Object[] args = {po.getCompanyId(),po.getName(),po.getDesc(),po.getAddress(),po.getSalay(),now,now};
		int varchar = Types.VARCHAR;
		int text = Types.LONGVARCHAR;
		int _int = Types.INTEGER;
		int[] argTypes = {_int , varchar , text , varchar , varchar , _int , _int};
		return this.update(sql, args, argTypes);
	}

	@Override
	public int update(CompanyPo po) {
		// TODO Auto-generated method stub
		int now = (int) new Date().getTime();
		String sql = "update `company` set `company_name`=?,`desc`=?,`thumb`=?,`address`=?,`tel`=?,`update_time`=?,`statu`=? where `id`=?";
		Object[] args = {
				po.getCompanyName(),po.getDesc(),po.getThumb(),
				po.getAddress(),po.getTel(),now,po.getStatu(),po.getId()
			};
		int varchar = Types.VARCHAR;
		int text = Types.LONGVARCHAR;
		int _int = Types.INTEGER;
		int tinyint = Types.TINYINT;
		int[] argTypes = {varchar, text , varchar , varchar , varchar,  _int , tinyint , _int};
		DbLog.sql(sql, "", "company/update");
		return this.update(sql, args, argTypes);
	}

	@Override
	public int changePwd(String newPwd ,String oldPwd,  int companyId) {
		// TODO Auto-generated method stub
		String sql = "update `company` set `password`='"+newPwd+"' where `password`='"+oldPwd+"' and `id`="+companyId ;
		DbLog.querySuccess(sql);
		return this.update(sql);
	}

	@Override
	public CompanyPo getCompany(String userName) {
		// TODO Auto-generated method stub
		String sql = "select * from `company` where `username`='"+userName+"'";
		return this.queryForObject(sql, CompanyPo.class);
	}

	@Override
	public int getId(String userName) {
		// TODO Auto-generated method stub
		String sql = " select id from `company` where `username`='"+userName+"'";
		return this.queryForInt(sql);
	}

	
	


}
