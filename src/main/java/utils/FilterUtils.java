package utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loggerUtils.DbLog;
import loggerUtils.debugLog;
import net.sf.json.JSONObject;
import service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FilterUtils implements HandlerInterceptor{

	@Autowired
	private UserService userService;
	
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
		PrintWriter os = null;
//		debugLog.debug("postHandler", "uri="+uri);//  /offer
//		debugLog.debug("postHandler", "url="+url);//  /offer/login/login
//		res.sendRedirect(uri+"/index/index");
//		req.getRequestDispatcher("/index").forward(req, res);
		String js = "<script type=\"text/javascript\" src=\"../ui/static/js/jquery.min.js\"></script><script> $( function(){ 	window.opener.location.reload();window.close(); });</script>";
		String json = cookieUtils.getCookie(req, res, "user");
		GlobalUtil.setSession("username",
				JSONObject.fromObject(json).get("username"), req);
		try {
			os = res.getWriter();
			os.write(js);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			os.close();
		}
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
			username = URLDecoder.decode( (String)json.get("username"), "utf-8") ;
			password = (String) json.get("password");
		}
		
		boolean flag = userService.checkUser(username, password);
		
		if( flag ){
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
