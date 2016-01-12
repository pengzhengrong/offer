package dao.impl;

import loggerUtils.DbLog;
import utils.BaseDao;
import dao.UserDetailDao;
import entrty.UserDetailPo;

public class UserDetailDaoImpl extends BaseDao implements UserDetailDao {

	@Override
	public UserDetailPo getUserDetail(int userId) {
		// TODO Auto-generated method stub
		String sql = "select * from `user_detail` where user_id=" + userId;
		return this.queryForObject(sql, UserDetailPo.class);
	}

	@Override
	public int add(UserDetailPo po) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(UserDetailPo po) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int detailId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveOrUpdate(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		try {
			DbLog.querySuccess(sql);
			return this.update(sql, args, argTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getId(String sql) {
		// TODO Auto-generated method stub
		return this.queryForInt(sql);
	}

}
