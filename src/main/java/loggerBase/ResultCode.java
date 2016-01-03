package loggerBase;

import org.apache.commons.lang.StringUtils;

public enum ResultCode {

	SUCCESS("1","成功"),
	FAILED("-1","失败"),
	OTHER("0","其他");
	
	public String value;
	public String result;
	
	private ResultCode(String value, String result) {
		this.value = value;
		this.result = result;
	}
	
	public static ResultCode getEnum(String value){
		if(value != null){
			for (ResultCode e : values()) {
				if(StringUtils.equalsIgnoreCase(value, e.value)){
					return e;
				}
			}
			
		}
		return OTHER;
	}
	
}
