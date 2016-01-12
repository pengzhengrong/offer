package service;

import java.util.Map;

import entrty.UserPo;

public interface UserService {
	
	public boolean checkUser( String userName , String pwd );
	
	public boolean register( String userName , String pwd );
	
	public int checkName( String userName );
	
	public int update(String tableName , Map<String, Object> keys ,Map<String, Object> contions);
	
	public int getId(String userName);
	
	public String getUserName(int id);
	
	
}
