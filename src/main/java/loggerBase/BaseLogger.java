package loggerBase;

import org.apache.log4j.Logger;


public class BaseLogger {

	
	protected static Logger manage = Logger.getLogger(LoggerType.MANAGE.name);
	protected static Logger login = Logger.getLogger(LoggerType.LOG_IN.name);
	
	protected static Logger db = Logger.getLogger(LoggerType.DB.name);
	protected static Logger cache = Logger.getLogger(LoggerType.CACHE.name);
	protected static Logger api = Logger.getLogger(LoggerType.API.name);
	protected static Logger error = Logger.getLogger(LoggerType.ERROR.name);
	protected static Logger system = Logger.getLogger(LoggerType.SYSTEM.name);
	protected static Logger debug = Logger.getLogger(LoggerType.DEBUG.name);
	
	
	public void logTime(String message, long startTime) {
		if (system.isDebugEnabled()) {
			long timeCost = System.currentTimeMillis() - startTime;
			system.debug("用时" + message + (timeCost) + "ms");
		}
	}
}
