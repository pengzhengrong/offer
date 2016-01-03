package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loggerUtils.debugLog;
import net.sf.json.JSONObject;



public class cookieUtils {

	
	public static void addCookie(HttpServletRequest request,HttpServletResponse response ,JSONObject json){
		
		Cookie cookie = new Cookie("user", json.toString());
		cookie.setMaxAge(30);
		response.addCookie(cookie);
	}
	
	public static String getCookie(HttpServletRequest request,HttpServletResponse response ,String param){
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(param)) {
					return cookie.getValue(); 
				}
			}
		}
		
		return null;
		
	}
	
	public static void clearCookie(String param){
		Cookie cookie = new Cookie("user", "");
		cookie.setMaxAge(0);
		debugLog.debug("clearCookie", "清除缓存");
	}
	
	
	
}
