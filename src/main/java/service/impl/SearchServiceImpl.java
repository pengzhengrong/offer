package service.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import dao.GlobalDao;
import loggerUtils.DbLog;
import entrty.CompanyPo;
import entrty.JobPo;
import service.SearchService;
import utils.BaseDao;

public class SearchServiceImpl extends BaseDao implements SearchService{


	@Autowired
	private GlobalDao globalDao;
	
	private int totalRows;
	
	@Override
	public Object searchFor(Map<String, String> map ,String value) {
		// TODO Auto-generated method stub
		String tableName = "";
		String column = "";
		if( map.get("jobs") != null ){
			tableName = "jobs";
			column = map.get("jobs");
			String sql = "select * from `"+tableName+"`";
			if( column != null){
			 sql += " where `statu`=1 and "+column+" like '%"+value+"%'" ;
			}
			DbLog.querySuccess(sql);
			return this.queryForList(sql, JobPo.class);
		}else{
			tableName = "company";
			column = map.get("company");
			String sql = "select * from `"+tableName+"`";
			if( column != null){
			 sql += " where `statu`=1 and "+column+" like '%"+value+"%'" ;
			}
			DbLog.querySuccess(sql);
			return this.queryForList(sql, CompanyPo.class);
		}
	}

	@Override
	public Object searchByPage(Map<String, String> map ,String value, int nowPage, int pageSize,
			Class objClass) {
		// TODO Auto-generated method stub
		String tableName = "";
		String column = "";
		StringBuffer sql = new StringBuffer();
		if( map.get("jobs") != null ){
			tableName = "jobs";
			column = map.get("jobs");
			sql.append("select * from `"+tableName+"`"); 
			if( column != null){
			 sql.append( " where `statu`=1 and "+column+" like '%"+value+"%'") ;
			}
		}else{
			tableName = "company";
			column = map.get("company");
			sql.append( "select * from `"+tableName+"`" );
			if( column != null){
			 sql.append(" where `statu`=1 and "+column+" like '%"+value+"%'" );
			}
		}
		totalRows = this.getMaxRows(sql.toString());
		DbLog.querySuccess(sql.toString()+" totalRows="+totalRows);
		return globalDao.queryByPage(sql.toString(), nowPage, pageSize, objClass);
	}
	
	public int getMaxRows(){
		return totalRows;
	}

}
