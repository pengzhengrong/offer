package service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dao.GlobalDao;
import dao.UserDetailDao;
import entrty.UserDetailPo;
import service.UserDetailService;

public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserDetailDao userDetail;
	
	@Autowired
	private GlobalDao globalDao;
	
	@Override
	public UserDetailPo getUserDetail(int userId) {
		// TODO Auto-generated method stub
		return userDetail.getUserDetail(userId);
	}

	@Override
	public int saveOrUpdate(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		return userDetail.saveOrUpdate(sql, args, argTypes);
	}

	@Override
	public int getId(String userName) {
		// TODO Auto-generated method stub
		return globalDao.queryForInt("user_detail", "id", " and username='"+userName+"'");
	}



	
	

}
