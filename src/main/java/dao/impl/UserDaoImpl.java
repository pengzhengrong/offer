package dao.impl;

import dao.UserDao;
import entrty.UserPo;
import loggerApi.DbLogAPI;
import loggerUtils.DbLog;
import utils.BaseDao;

public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public UserPo getUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkUser(String userName, String pwd) {
		// TODO Auto-generated method stub
		String sql = "select count(1) as c from `user` where `username`='"+userName+"' and `password`='"+pwd+"'";
		int ishas = this.queryForInt(sql);
		return ishas>0?true:false;
	}

}
