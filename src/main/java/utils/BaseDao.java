package utils;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import entrty.PageInfo;

public class BaseDao extends JdbcDaoSupport implements IBaseDao{
		
	//getJdbcTemplate().update(sql, args, argTypes);
		//getJdbcTemplate().queryForObject(sql, args, argTypes, rowMapper)
		//getJdbcTemplate().queryForObject(sql, args, argTypes, requiredType)
		
//		getJdbcTemplate().queryForInt(sql);
//		getJdbcTemplate().queryForInt(sql, args)
//		getJdbcTemplate().queryForInt(sql, args, argTypes)
//		getJdbcTemplate().queryForList(sql);
//		getJdbcTemplate().queryForList(sql, args)
//		getJdbcTemplate().queryForList(sql)
//		getJdbcTemplate().queryForList(sql, elementType)
//		getJdbcTemplate().queryForList(sql, elementType, args)
//		getJdbcTemplate().queryForList(sql, args, argTypes, elementType)
//		getJdbcTemplate().queryForList(sql, args, argTypes)
//		getJdbcTemplate().queryForList(sql, args, elementType)
		
//		getJdbcTemplate().queryForLong(sql)
//		getJdbcTemplate().queryForLong(sql, args, argTypes)
		
//		getJdbcTemplate().update(sql)
//		getJdbcTemplate().update(sql, args, argTypes)
		
//		getJdbcTemplate().queryForMap(sql)
//		getJdbcTemplate().queryForMap(sql, args, argTypes)
//		getJdbcTemplate().getMaxRows();
		
		/*
		 * pageSize 页大小
		 * page 页码
		 * totalCount 总的行数
		 * start = pageSize*(page-1)
		 * end   = pageSize*page
		 * totalPage = totalCount/pageSize
		 */

	@Override
	public <T> T queryForObject(String sql, Class<T> objClass) {
		// TODO Auto-generated method stub
		T r = null;
		try{
			r = getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<T>(objClass));
		}catch(DataAccessException e){
			e.getMessage();
		}
		return r;
	}

	@Override
	public <T> T queryForObject(String sql, Object[] args, int[] argTypes,
			Class<T> objClass) {
		T r = null;
		try {
			r = getJdbcTemplate().queryForObject(sql, args, argTypes, new BeanPropertyRowMapper<T>(objClass));
		} catch (Exception e) {
			e.getMessage();
		}
		return r;
	}

	@Override
	public int queryForInt(String sql) {
		try {
			int i = ((IBaseDao) getJdbcTemplate()).queryForInt(sql);
			return i;
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	@Override
	public int queryForInt(String sql, Object... args) {
		try {
			int i = getJdbcTemplate().queryForInt(sql,args);
			return i;
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	@Override
	public int queryForInt(String sql, Object[] args, int[] argTypes) {
		try {
			int i = getJdbcTemplate().queryForInt(sql,args,argTypes);
			return i;
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	@Override
	public int update(String sql) {
		try {
			int i = getJdbcTemplate().update(sql);
			return i;
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	@Override
	public int update(String sql, Object[] args, int[] argTypes) {
		try {
			int i = getJdbcTemplate().update(sql, args, argTypes);
			return i;
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	@Override
	public <T> List<T> queryByPage(String sql, PageInfo page,Class<T> objClass) {
		int totalCount = getMaxRows(sql);
		page.setTotalCount(totalCount);
		sql = sql+" limit "+page.getStart()+","+page.getPageSize();
		List<T> list = queryForList(sql, objClass);
		return list;
	}

	@Override
	public int getMaxRows(String sql) {
		sql = "select count(*) from ("+sql+") Z";
		int rows = queryForInt(sql);
		return rows;
	}

	@Override
	public <T> List<T> queryForList(String sql, Class<T> objClass) {
		try {
			List<T> list= getJdbcTemplate().query(sql,new BeanPropertyRowMapper<T>(objClass) );
			return list;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public <T> List<T> queryForList(String sql, Object[] args, int[] argTypes,
			Class<T> objClass) {
		
		try {
			List<T> list= getJdbcTemplate().query(sql,args,argTypes,new BeanPropertyRowMapper<T>(objClass) );
			return list;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
	
	
	
	
	
	
}
