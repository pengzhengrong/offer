package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loggerUtils.debugLog;
import service.AdminService;
import service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import entrty.CompanyPo;
import entrty.UserDetailPo;
import utils.GlobalUtil;

@Controller
public class AdminAction {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userServiceImpl;
	
	@RequestMapping("admin/index")
	public String index() {
		debugLog.debug("admin/index", "index/main");
		return "index/admin";
	}

	@RequestMapping("admin/login")
	public String adminLogin() {
		debugLog.debug("admin/login", "login/login");
		return "admin/login";
	}

	@RequestMapping("admin/Login")
	public String Login() {
		debugLog.debug("admin/Login", "admin/Login");
		return "admin/login";
	}

	@RequestMapping("admin/dologin")
	public void loginHandler(String username, String password, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter os = null;
		int islogin = 0;
		debugLog.debug("admin", "dologin");
		String js = "<script type=\"text/javascript\" src=\"../ui/static/js/jquery.min.js\"></script><script> $( function(){ 	window.opener.location.reload();window.close(); });</script>";
			// 如果用户登入成功，那么记录用户的session
			islogin = adminService.checkUser(username, password);
			if (islogin > 0) {
				GlobalUtil.setSession("username", username, request);
			}else{
				js = "<script>alert(\"登入失败，请重新登入!\");window.location.href=\"/offer/admin/login\";</script>";
				debugLog.debug("admin/dologin", "js="+js);
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
	
	@RequestMapping("admin/list")
	public String list(ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String sql = "select * from `company`";
		int nowPage = 1;
		int pageSize = 2;
		String nowpage = request.getParameter("nowPage");
		String pagesize = request.getParameter("pageSize");
		int totalRows = adminService.getMaxRows(sql);
		List<CompanyPo> poList =  adminService.queryByPage(sql, nowPage, pageSize,CompanyPo.class);
		model.addAttribute("poList", poList );
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalRows", totalRows);
		return "admin/list";
	}
	
	@RequestMapping("admin/add")
	public String add(ModelMap model,HttpServletRequest request, HttpServletResponse response){
		return "admin/add";
	}
	
	@RequestMapping("admin/delete")
	public void delete(int id ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		int res = adminService.delete(id);
		try {
			response.sendRedirect("/offer/admin/list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("admin/save")
	public void save(String username , String password ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String sql = "insert into `company`(`username`,`password`) values('"+username+"','"+password+"')";
		adminService.saveOrUpdate(sql);
		try {
			response.sendRedirect("/offer/admin/list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("admin/update")
	public void update(int id,int statu ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String sql = "update `company` set `statu`="+statu+" where `id`="+id;
		adminService.saveOrUpdate(sql);
		try {
			response.sendRedirect("/offer/admin/list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("admin/List")
	public String List(ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String sql = "select * from `user_detail`";
		int nowPage = 1;
		int pageSize = 2;
		String nowpage = request.getParameter("nowPage");
		String pagesize = request.getParameter("pageSize");
		int totalRows = adminService.getMaxRows(sql);
		if( nowpage != null){
			nowPage = Integer.parseInt( nowpage );
			pageSize = Integer.parseInt( pagesize );
		}
		List<UserDetailPo> poList =  adminService.queryByPage(sql, nowPage, pageSize,UserDetailPo.class);
		model.addAttribute("poList", poList );
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalRows", totalRows);
		return "admin/user_list";
	}
	
	@RequestMapping("admin/Update")
	public void Update(int id,int statu ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String sql = "update `user_detail` set `statu`="+statu+" where `id`="+id;
		adminService.saveOrUpdate(sql);
		try {
			response.sendRedirect("/offer/admin/List");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("admin/Delete")
	public void Delete(int id ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String sql = "delete from `user_detail` where `id`="+id;
		adminService.saveOrUpdate(sql);
		try {
			response.sendRedirect("/offer/admin/List");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
