package dao;

import entrty.UserDetailPo;

public interface UserDetailDao {

	public UserDetailPo getUserDetail( int detailId);
	
	public int add( UserDetailPo po );
	
	public int update(UserDetailPo po);
	
	
	public int delete(int detailId);
	
	public int saveOrUpdate( String sql , Object[] args , int[] argTypes );
	
	public int getId( String sql );
	
}
