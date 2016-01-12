package dao;

import java.util.List;
import java.util.Map;

public interface GlobalDao {

	public Object queryForObject(String tableName , String[] args,Map<String, Object> contions , Class objClass);
	
	public int update(String tableName , Map<String, Object> keys ,Map<String, Object> contions);
	
	public int checkLogin(String tableName , String userName , String password);
	
	public int queryForInt(String tableName , String keys , String conditions );
	
	public List queryByPage( String sql , int nowPage , int pageSize ,Class objClass);
	
	public int getMaxRows( String sql );
	
	public String queryFoString(String tableName, String keys, String conditions);
	
}
