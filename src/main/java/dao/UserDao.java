package dao;

import entrty.UserPo;

public interface UserDao {

	public UserPo getUserInfo( );
	
	public boolean checkUser( String userName , String pwd );
	
	public boolean register( String userName , String pwd );
	
	public int checkName( String userName );
	
}
