package service;

import java.util.Map;

import entrty.UserDetailPo;

public interface UserDetailService {

	public UserDetailPo getUserDetail( int userId);
	
	public int saveOrUpdate( String sql , Object[] args , int[] argTypes );
	
	public int getId(String userName);
}
