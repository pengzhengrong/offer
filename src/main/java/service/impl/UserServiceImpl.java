package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean checkUser(String userName, String pwd) {
		// TODO Auto-generated method stub
		return userDao.checkUser(userName, pwd);
	}

	@Override
	public boolean register(String userName, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int checkName(String userName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
