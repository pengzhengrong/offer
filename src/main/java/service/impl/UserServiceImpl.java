package service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GlobalDao;
import dao.UserDao;
import entrty.UserPo;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GlobalDao globalDao;
	
	@Override
	public boolean checkUser(String userName, String pwd) {
		// TODO Auto-generated method stub
		return userDao.checkUser(userName, pwd);
	}

	@Override
	public boolean register(String userName, String pwd) {
		// TODO Auto-generated method stub
		return userDao.register(userName, pwd);
	}

	@Override
	public int checkName(String userName) {
		// TODO Auto-generated method stub
		return userDao.checkName(userName);
	}


	@Override
	public int update(String tableName, Map<String, Object> keys,
			Map<String, Object> contions) {
		// TODO Auto-generated method stub
		return globalDao.update(tableName, keys, contions);
	}
	
	@Override
	public int getId(String userName) {
		// TODO Auto-generated method stub
		return globalDao.queryForInt("user", "id", " and username='"+userName+"'");
	}

	@Override
	public String getUserName(int id) {
		// TODO Auto-generated method stub
		return globalDao.queryFoString("user", "`username`", " and `id`="+id);
	}

}
