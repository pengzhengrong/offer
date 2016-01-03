package dao;

import entrty.UserPo;

public interface UserDao {

	public UserPo getUserInfo( );
	
	public boolean checkUser( String userName , String pwd );
	
}
