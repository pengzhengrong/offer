package loggerBase;


import org.apache.commons.lang.StringUtils;


/**
 * @author duzl
 * 
 */
public enum LoggerType {

	DB("1003","accountBook.logger.db","记账系统：db","db.log"),
	
	SYSTEM("9999","accountBook.logger.system","记账系统：system","system.log"),
	
	LOG_IN("1001", "accountBook.logger.login", "记账系统：login", "login.log"),
	
	MANAGE("1002", "accountBook.logger.manage", "记账系统：manage", "manage.log"),
	
	CACHE("0004", "accountBook.logger.cache", "记账系统：cache", "cache.log"),
	
	API("0003", "accountBook.logger.api", "记账系统：api", "api.log"),
	
	DEBUG("0000","accountBook.logger,debug","debug","sdtout"),
	
	ERROR("0002", "accountBook.logger.error", "记账系统：error", "error.log");
	
	
	
	public String value;
	public String name;
	public String desc;
	public String file;
	
	
	
	private LoggerType(String value, String name, String desc, String file) {
		this.value = value;
		this.name = name;
		this.desc = desc;
		this.file = file;
	}
	
	public LoggerType getEnum(String value){
		if(value != null){
			for(LoggerType e:values()){
				if(StringUtils.equalsIgnoreCase(e.value, value)){
					return e;
				}
			}
		}
		return SYSTEM;
	}
	
	
	public static LoggerType getEnumByFileName(String fileName){
		if (fileName!=null)
		for(LoggerType e:values()){
			if(e.file.startsWith(fileName)){
				return e;
			}
		}
		return SYSTEM;
	}
	
}
