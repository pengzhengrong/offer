package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loggerUtils.debugLog;
import net.sf.json.JSONObject;
import service.CompanyService;
import service.JobService;
import service.UserService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entrty.CompanyPo;
import entrty.JobPo;
import utils.GlobalUtil;
import utils.cookieUtils;

@Controller
public class CompanyAction {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("company/index")
	public String index() {
		debugLog.debug("company/index", "index/main");
		return "index/company";
	}

	@RequestMapping("company/login")
	public String companyLogin() {
		debugLog.debug("company/login", "login/login");
		return "company/login";
	}

	@RequestMapping("company/Login")
	public String Login() {
		debugLog.debug("company/Login", "company/Login");
		return "company/login";
	}

	@RequestMapping("company/dologin")
	public void loginHandler(String username, String password, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter os = null;
		int islogin = 0;
		debugLog.debug("company", "dologin");
		String js = "<script type=\"text/javascript\" src=\"../ui/static/js/jquery.min.js\"></script><script> $( function(){ 	window.opener.location.reload();window.close(); });</script>";
		String json = cookieUtils.getCookie(request, response, "user");
		if (json != null) {
			debugLog.debug("dologinSuccess", "cookie登入成功");
			islogin = 1;
			GlobalUtil.setSession("username",
					JSONObject.fromObject(json).get("username"), request);
		} else {
			// 如果用户登入成功，那么记录用户的session
			islogin = companyService.checkUser(username, password);
			if (islogin > 0) {
				GlobalUtil.setSession("username", username, request);
			}else{
				js = "<script>alert(\"登入失败，请重新登入!\");window.location.href=\"/offer/company/login\";</script>";
				debugLog.debug("company/dologin", "js="+js);
			}
		}
		if( islogin > 0){
			int companyId = (int) GlobalUtil.getSession("companyId", request);
			
			if( companyId == -1 || companyId == 0){
				CompanyPo po = companyService.getCompany(username);
				GlobalUtil.setSession("companyId", po.getId(), request);
				GlobalUtil.setSession("companyName", po.getCompanyName(), request);
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

	@RequestMapping("company/logout")
	public void doLogout(String username, String password, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		String[] args = {"username","companyId"};
		GlobalUtil.removeAll(args, request);
		debugLog.debug("dologout",
				"登出");
		try {
			response.sendRedirect("index");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("company/list")
	public String list(ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String userName = GlobalUtil.getUserName(request);
		CompanyPo po = companyService.getCompany(userName);
		model.addAttribute("po", po);
		return "company/list";
	}
	
	@RequestMapping("company/update")
	public void update(CompanyPo po ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		int res = companyService.update(po);
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
