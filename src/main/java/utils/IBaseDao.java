package utils;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import entrty.PageInfo;

public interface IBaseDao {

	//query for object
	public <T> T queryForObject(String sql, Class<T> objClass);
	
	public <T> T queryForObject(String sql,Object[] args,int[] argTypes, Class<T> objClass);
	
	public int queryForInt(String sql);
	
	public int queryForInt(String sql,Object... args);
	
	public int queryForInt(String sql,Object[] args,int[] argTypes);
	
	public <T> List<T> queryForList(String sql, Class<T> objClass);
	
	public <T> List<T> queryForList(String sql,Object[] args,int[] argTypes,Class<T> objClass);
	
	public int update(String sql);
	
	public int update(String sql,Object[] args,int[] argTypes);
	
	public <T> List<T> queryByPage(String sql,PageInfo page,Class<T> objClass);
	
	public int getMaxRows(String sql);
	
	
	
//	public 
	
	//public Object queryForObject(String sql,Object[] args,RowMapper rowMapper);
	
	
	
}
