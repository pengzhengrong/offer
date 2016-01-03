package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loggerUtils.debugLog;
import net.sf.json.JSONObject;
import service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.cookieUtils;

@Controller
//@RequestMapping("login")
public class LoginAction {
	
	@Autowired
	private UserService userServiceImpl;
	
	
	
	public UserService getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@RequestMapping("index")
	public String index(){
		debugLog.debug("login/index", "index/main");
		return "index/main";
	}
	
	@RequestMapping("login/login")
	public String loginLogin(){
		debugLog.debug("login/login", "login/login");
		return "login/login";
	}
	
	@RequestMapping("login/Login")
	public String Login(){
		debugLog.debug("login/Login", "login/Login");
		return "login/login";
	}
	
	@RequestMapping("login/dologin")
	public void loginHandler(String username, String password,
			ModelMap model, HttpServletRequest request,HttpServletResponse response){
		debugLog.debug("login", "dologin");
		String js = "<script type=\"text/javascript\" src=\"../ui/index/js/jquery.min.js\"></script><script> $( function(){ 	window.opener.location.reload();window.close(); });</script>";
		String json = cookieUtils.getCookie(request, response, "user");
		if(json!=null){
			debugLog.debug("dologinSuccess", "cookie登入成功");
			HttpSession session = request.getSession();
			session.setAttribute("username", JSONObject.fromObject(json).get("username"));
			debugLog.debug("dologinSuccess", "username and password登入成功 session="+session.getAttribute("username"));
		}else {
			//如果用户登入成功，那么记录用户的session
//			boolean islogin = userServiceImpl.checkUser(username, password);
			boolean islogin = false;
			if( islogin ){
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				debugLog.debug("dologinSuccess", "username and password登入成功 session="+session.getAttribute("username"));
			}
		}
			 try {
					PrintWriter os = response.getWriter();
					os.write(js);
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		
	}
	
	@RequestMapping("login/register")
	public String register(){
		return "login/register";
	}
	
	@ResponseBody
	@RequestMapping("login/checkName")
	public int checkName(String username,
			ModelMap model, HttpServletRequest request,HttpServletResponse response){
		int ishas = userServiceImpl.checkName( username );
		return ishas;
	}
	
	@RequestMapping("login/doRegister")
	public void doregister(String username, String password,
			ModelMap model, HttpServletRequest request,HttpServletResponse response){
		boolean isok = userServiceImpl.register(username, password);
		debugLog.debug("login/doRegister", username+" "+password);
//		boolean isok = false;
			try {
				PrintWriter write = response.getWriter();
				//response.setContentType("text/html;charset=UTF-8");
				if( isok ){
					write.write("<script>alert(\"恭喜，注册成功!\");window.close();</script>");
				}else{
					write.write("<script>alert(\"对不起，注册失败!\");window.close();</script>");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
/*	@RequestMapping("login/login2")
	public String doLoginSuccess(String username, String password,
			ModelMap model, HttpServletRequest request,HttpServletResponse response) {

//		DbLogAPI.logger("select", "-1", "query");
		debugLog.debug("doLoginSuccess", "username"+username);
		String json = cookieUtils.getCookie(request, response, "user");
		if(json!=null){
			debugLog.debug("dologinSuccess", "cookie登入成功");
			HttpSession session = request.getSession();
			session.setAttribute("username", JSONObject.fromObject(json).get("username"));
			debugLog.debug("dologinSuccess", "username and password登入成功 session="+session.getAttribute("username"));
			return "index/main";
		}else if (username.equals("pzr") && password.equals("pzr")) {
			//如果用户登入成功，那么记录用户的session
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			debugLog.debug("dologinSuccess", "username and password登入成功 session="+session.getAttribute("username"));
			return "index/main";
		}
		return "login/login";
	}
	
	
	
	@RequestMapping("login/index2")
	public String doLogin(String username, String password,String cookie, ModelMap model,
			HttpServletRequest request,HttpServletResponse response) {
		DbLogAPI.logger("login/index", "ok", "test");
		if(username.equals("pzr") && password.equals("pzr")){
			System.out.println("cookie is open:"+cookie);
			if(StringUtils.equals(cookie, "true")){
				JSONObject json = new JSONObject();
				json.put("username", username);
				json.put("password", password);
				cookieUtils.addCookie(request, response, json);
			}
			model.addAttribute("username", username);
			//加入缓存
			UserMap.setUser(username);
			return "index/main";
		}
//		DbLogAPI.logger("login/index", "login/login", "test");
		debugLog.debug("doLogin", "登入");
		return "login/login";
		
	}
	
	@RequestMapping("index/index")
	public String toIndex(ModelMap model,
			HttpServletRequest request) {
		return "index/main";
	}
	
	
	
	@RequestMapping("login/login1")
	public String doLogin1(String username, String password,
			ModelMap model, HttpServletRequest request) {

//		DbLogAPI.logger("login1", "ok", "test");
		debugLog.debug("dologin1", "登入");
		return "login/login";
	}
	*/
	
	
	@RequestMapping("login/logout")
	public void doLogout(String username, String password,
			ModelMap model, HttpServletRequest request, HttpServletResponse respon) {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
//		DbLogAPI.logger("select", "-1", "query");
		debugLog.debug("dologout", "登出 session="+session.getAttribute("username"));
		/*if (username.equals("pzr") && password.equals("pzr")) {
			// model.addAttribute("name", "");
			return "index/index1";
		}*/
//		return "index/main";
		try {
			respon.sendRedirect("/offer/index");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
