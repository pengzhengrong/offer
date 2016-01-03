package loggerUtils;

import loggerApi.DbLogAPI;
import loggerBase.Operations;
import loggerBase.ResultCode;

public class DbLog {

	
	public static void updateSuccess(String sql){
		DbLogAPI.info(sql, ResultCode.SUCCESS.result, Operations.UPDATE.operat);
	}
	
	public static void updateFailed(String sql , String className){
		DbLogAPI.error(sql, ResultCode.FAILED.result, Operations.UPDATE.operat , className);
	}
	
	public static void querySuccess( String sql ){
		DbLogAPI.info(sql, ResultCode.SUCCESS.result, "select");
	}
	
}
