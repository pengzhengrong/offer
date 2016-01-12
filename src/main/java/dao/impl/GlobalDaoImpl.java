package dao.impl;

import java.util.List;
import java.util.Map;

import utils.BaseDao;
import loggerUtils.DbLog;
import dao.GlobalDao;

public class GlobalDaoImpl extends BaseDao implements GlobalDao{

	
	@Override
	public Object queryForObject(String tableName , String[] args, Map<String, Object> contions,
			Class objClass) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(200);
		sql.append("select ");
		for (String key : args) {
			sql.append(key+",");
		}
		sql.append("id from `"+tableName+"` where 1=1 ");
		
		for (Map.Entry<String, Object> c : contions.entrySet()) {
			if( c.getValue() instanceof Integer ){
				sql.append(" and "+c.getKey()+"="+c.getValue());
				continue;
			}
			sql.append(" and "+c.getKey()+"='"+c.getValue()+"'");
			
		}
		DbLog.sql(sql.toString(), "", "GlobalDaoImpl.queryForObject");
		Object obj = null;
		try{
			obj = this.queryForObject(sql.toString(), objClass);
		}catch( Exception e){
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int update(String tableName, Map<String, Object> keys, Map<String, Object> contions) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(200);
		
		sql.append(" update `"+tableName+"` set ");
		
		for (Map.Entry<String, Object> c : keys.entrySet()) {
			if( c.getValue() instanceof Integer ){
				sql.append(c.getKey()+"="+c.getValue()+",");
				continue;
			}
			sql.append(c.getKey()+"='"+c.getValue()+"',");
			
		}
		sql.append("id=id where 1=1 ");
		
		for (Map.Entry<String, Object> c : contions.entrySet()) {
			if( c.getValue() instanceof Integer ){
				sql.append(" and "+c.getKey()+"="+c.getValue());
				continue;
			}
			sql.append(" and "+c.getKey()+"='"+c.getValue()+"'");
			
		}
		DbLog.sql(sql.toString(), "", "GlobalDaoImpl.update");
		int obj = 0;
		try{
			obj = this.update(sql.toString());
		}catch( Exception e){
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int checkLogin(String tableName, String userName, String password) {
		// TODO Auto-generated method stub
		String sql = "select count(1) as c from `"+tableName+"` where username='"+userName+"' and password='"+password+"'";
		return this.queryForInt(sql);
	}

	@Override
	public int queryForInt(String tableName, String keys, String conditions) {
		// TODO Auto-generated method stub
		String sql = "select "+keys+" from `"+tableName+"` where 1=1 "+conditions ;
		DbLog.sql(sql, "", "globalDaoImpl.queryForInt");
		return this.queryForInt(sql);
	}

	@Override
	public List queryByPage(String sql, int nowPage, int pageSize,
			Class objClass) {
		// TODO Auto-generated method stub
		int offset = (nowPage-1)*pageSize;
		sql += " limit "+offset+","+pageSize;
		DbLog.updateSuccess(sql);
		return this.queryForList(sql, objClass);
	}
	
	@Override
	public String queryFoString(String tableName, String keys, String conditions) {
		// TODO Auto-generated method stub
		String sql = "select "+keys+" from `"+tableName+"` where 1=1 "+conditions ;
		DbLog.sql(sql, "", "globalDaoImpl.queryForString");
		return this.queryForObject(sql, String.class);
	}
	
	public int getMaxRows( String sql ){
		sql = "select count(1) from ("+sql+") Z" ;
		return this.queryForInt(sql);
	}

}
