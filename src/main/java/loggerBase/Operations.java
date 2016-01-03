package loggerBase;

import org.apache.commons.lang.StringUtils;

public enum Operations {

	INSERT("1","插入"),
	UPDATE("2","更新"),
	DELETE("3","删除"),
	OTHER("4","其他");
	
	public String value;
	public String operat;
	
	
	private Operations(String value, String operat) {
		this.value = value;
		this.operat = operat;
	}
	
	public static Operations getEnum(String value){
		if(value != null){
			for (Operations e: values()) {
				if(StringUtils.equalsIgnoreCase(e.value, value)){
					return e;
				}
			}
		}
		return OTHER;
	}
	
	
}
