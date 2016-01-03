package loggerUtils;

import loggerBase.BaseLogger;

public class debugLog extends BaseLogger{

	
	public static void debug(String method,String operator){
		debug.debug("方法：["+method+"] "+"操作：["+operator+"]");
	}

}
