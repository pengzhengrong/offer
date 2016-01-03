package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loggerUtils.DbLog;
import loggerUtils.debugLog;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FilterUtils implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2, ModelAndView model) throws Exception {
		// TODO Auto-generated method stub
		String uri = req.getContextPath();
		String url = req.getRequestURI();
		
		debugLog.debug("postHandler", "uri="+uri);
		debugLog.debug("postHandler", "url="+url);
//		res.sendRedirect(uri+"/index/index");
		req.getRequestDispatcher("/index/main").forward(req, res);
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {
		// TODO Auto-generated method stub
		String user = cookieUtils.getCookie(req, res, "user");
		String username = "";
		String password = "";
		debugLog.debug("prehandler", "用户："+user);
		if(user != null){
			JSONObject json = JSONObject.fromObject(user);
			
			username = (String) json.get("username");
			password = (String) json.get("password");
		}
		
		if(username.equals("pzr") && password.equals("pzr")){
			debugLog.debug("preHandler", "登入成功!");
			return true;
		}else{
			debugLog.debug("preHandler", "登入失败!");
			//在此进入了死循环，过滤拦截/login/login 请求又是这个。
			res.sendRedirect(req.getContextPath()+"/login/Login");
			return false;
		}
	}

}
