package service.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import loggerUtils.DbLog;
import entrty.CompanyPo;
import entrty.JobPo;
import service.SearchService;
import utils.BaseDao;

public class SearchServiceImpl extends BaseDao implements SearchService{


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

}
