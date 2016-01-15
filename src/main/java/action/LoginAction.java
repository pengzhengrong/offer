package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loggerUtils.debugLog;
import net.sf.json.JSONObject;
import service.UserDetailService;
import service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import entrty.UserDetailPo;
import utils.GlobalUtil;
import utils.cookieUtils;

@Controller
// @RequestMapping("login")
public class LoginAction {

	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private UserDetailService userDetailService;

	@RequestMapping("index")
	public String index() {
		debugLog.debug("login/index", "index/main");
		return "index/main";
	}

	@RequestMapping("login/login")
	public String loginLogin() {
		debugLog.debug("login/login", "login/login");
		return "login/login";
	}

	@RequestMapping("login/Login")
	public String Login() {
		debugLog.debug("login/Login", "login/Login");
		return "login/login";
	}

	@RequestMapping("login/dologin")
	public void loginHandler(String cookie ,String username, String password, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter os = null;
		boolean islogin = false;
		debugLog.debug("login", "dologin cookie:"+cookie);
		String js = "<script type=\"text/javascript\" src=\"../ui/static/js/jquery.min.js\"></script><script> $( function(){ 	window.opener.location.reload();window.close(); });</script>";

		// 如果用户登入成功，那么记录用户的session
		islogin = userServiceImpl.checkUser(username, password);
		if (islogin) {
			if( cookie.equals("true")){
			try{
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("username", URLEncoder.encode(username,"utf-8"));
					jsonObj.put("password", password);
					cookieUtils.addCookie(request, response, jsonObj);
				}catch( Exception e){
					e.printStackTrace();
				}
			}
			GlobalUtil.setSession("username", username, request);
		} else {
			js = "<script>alert(\"登入失败,请重新登入!\");window.location.href=\"/offer/login/login\";</script>";
		}

		if (islogin) {
			int userId = userServiceImpl.getId(username);
			UserDetailPo po = userDetailService.getUserDetail(userId);
			if(po != null ){
				GlobalUtil.setSession("userName", po.getName(), request);
				GlobalUtil.setSession("userId", userId, request);
			}
		}

		try {
			response.setContentType("text/html; charset=utf-8");
			os = response.getWriter();
			os.write(js);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			os.close();
		}

	}

	@RequestMapping("login/logout")
	public void doLogout(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] args = { "username", "userId" };
		GlobalUtil.removeAll(args, request);
		debugLog.debug("dologout", "登出 ");
		try {
			response.sendRedirect("/offer/index");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
