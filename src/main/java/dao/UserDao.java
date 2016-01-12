package dao;

import java.util.Map;

import entrty.UserPo;

public interface UserDao {

	public boolean checkUser( String userName , String pwd );
	
	public boolean register( String userName , String pwd );
	
	public int checkName( String userName );
	
}
