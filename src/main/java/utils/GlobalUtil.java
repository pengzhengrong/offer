package utils;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;






import entrty.PageInfo;
import service.UserService;

public class GlobalUtil {

	private static HttpSession session = null;
	
	
	
	public static String getUserName(HttpServletRequest request){
		session = request.getSession();
		return (String) session.getAttribute("username");
	}
	
	public static int getUserDetailId(HttpServletRequest request){
		session = request.getSession();
		return  (int) session.getAttribute("detailId");
	}
	
	public static void setSession(String key , Object value , HttpServletRequest request){
		session = request.getSession();
		session.setAttribute(key, value);
	}
	
	public static Object getSession(String key , HttpServletRequest request){
		session = request.getSession();
		if (session.getAttribute(key) == null ){
			return -1;
		}
		return session.getAttribute(key);
	}
	
	public static void removeAll(String[] args , HttpServletRequest request){
		session = request.getSession();
		for (String key : args) {
			session.removeAttribute(key);
		}
	}
	
	public static PageInfo initPage(){
		PageInfo page = new PageInfo(1, 10);
		return page;
	}
	
	public static boolean isNumber(String str){
		String regex = "[0-9]+";
		return str.matches(regex);
	}
	
	public static Map<String,String> getKey(String key){
		Map<String, String> map = new HashMap<String, String>();
		if( key.trim().equals("地址")){
			map.put("company", "address");
		}
		if( key.trim().equals("公司名称")){
			map.put("company", "company_name");
		}
		if( key.trim().equals("职位")){
			map.put("jobs", "name");
		}
		if( key.trim().equals("薪资")){
			map.put("jobs", "salay");
		}
		return map;
	}
	
	
}
