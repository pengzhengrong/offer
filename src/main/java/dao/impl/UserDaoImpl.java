package dao.impl;

import java.util.Date;
import java.util.Map;

import dao.UserDao;
import entrty.UserPo;
import loggerApi.DbLogAPI;
import loggerUtils.DbLog;
import utils.BaseDao;

public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public boolean checkUser(String userName, String pwd) {
		// TODO Auto-generated method stub
		String sql = "select count(1) as c from `user` where `username`='"+userName+"' and `password`='"+pwd+"'";
		int ishas = this.queryForInt(sql);
		DbLog.sql(sql, ishas, "select");
		return ishas>0;
	}

	@Override
	public boolean register(String userName, String pwd) {
		// TODO Auto-generated method stub
		int now = (int)new Date().getTime();
		String sql = "insert into `user`(`username`,`password`,`create_time`,`update_time`) values('"+userName+"','"+pwd+"',"+now+","+now+") ";
		int res = this.update(sql);
		DbLog.sql(sql, "RES:"+res, "insert");
		return res>0;
	}

	@Override
	public int checkName(String userName) {
		// TODO Auto-generated method stub
		String sql = "select count(1) from `user` where `username`='"+userName+"'";
		return this.queryForInt(sql);
	}



}
