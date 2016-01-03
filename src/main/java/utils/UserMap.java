package utils;

import java.util.HashMap;
import java.util.Map;

public class UserMap {

	private static Map<String, String> map = new HashMap<String, String>();
	
	public static String getUser(){
		return map.get("user");
	}
	
	public static void setUser(String user){
		map.put("user", user);
	}
}
