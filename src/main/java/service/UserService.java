package service;

public interface UserService {
	
	public boolean checkUser( String userName , String pwd );
	
	public boolean register( String userName , String pwd );
	
	public int checkName( String userName );
	
}
