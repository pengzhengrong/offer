package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loggerUtils.debugLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.UserDetailService;
import service.UserJobService;
import utils.GlobalUtil;
import entrty.UserDetailPo;
import entrty.UserJobPo;

@Controller
public class UserJobAction {

	@Autowired
	private UserJobService userJobService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@ResponseBody
	@RequestMapping("job/resume")
	public int resume(String companyName ,String jobName ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String userName = (String) GlobalUtil.getSession("userName", request);
		if( userName == null ){
			return 0;
		}
		int userId = (int) GlobalUtil.getSession("userId", request);
		debugLog.debug("job/resume", userName+" "+companyName+" "+jobName);
		int res = userJobService.save(userId,userName, companyName, jobName);
		return res;
	}
	
	@RequestMapping("job/handler")
	public String handler(int statu,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		debugLog.debug("job/handler", "");
		if( GlobalUtil.getUserName(request) == null){
			debugLog.debug("job/handler", "usreName=null");
			return "index/main";
		}
		int nowPage = 1;
		int pageSize = 2;
		String nowpage = request.getParameter("nowPage");
		String pagesize = request.getParameter("pageSize");
		String companyName = (String) GlobalUtil.getSession("companyName", request);
		String sql = "select * from `user_job` where `statu`="+statu+" and `company_name`='"+companyName+"'";
		int totalRows = userJobService.getMaxRows(sql);
		if( nowpage != null){
			nowPage = Integer.parseInt( nowpage );
			pageSize = Integer.parseInt( pagesize );
		}
		List<UserJobPo> poList = userJobService.queryByPage(sql, nowPage, pageSize);
		model.addAttribute("poList", poList );
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalRows", totalRows);
		model.addAttribute("statu", statu);
		return "job/handler";
		
	}
	
	@ResponseBody
	@RequestMapping("job/operater")
	public int changeStatu(int id,int key,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		int res = 0;
		if( key == 1 ){
			res = userJobService.update(id,1);
		}else{
			res = userJobService.update(id, -1);
		}
		return res;
	}
	
	@RequestMapping("job/person")
	public String personDetail(int userId ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		UserDetailPo po = userDetailService.getUserDetail(userId);
		model.addAttribute("po", po);
		return "job/person";
	}
	
	@RequestMapping("userjob/list")
	public String handler(ModelMap model, HttpServletRequest request,
			HttpServletResponse response){
		String userName = (String) GlobalUtil.getSession("userName", request);
		debugLog.debug("person/handler", "userName="+userName);
		if( userName == null){
			debugLog.debug("person/handler", "usreName=null");
			return "index/main";
		}
		int nowPage = 1;
		int pageSize = 2;
		String nowpage = request.getParameter("nowPage");
		String pagesize = request.getParameter("pageSize");
		String sql = "select * from `user_job` where `user_name`='"+userName+"'";
		int totalRows = userJobService.getMaxRows(sql);
		if( nowpage != null){
			nowPage = Integer.parseInt( nowpage );
			pageSize = Integer.parseInt( pagesize );
		}
		List<UserJobPo> poList = userJobService.queryByPage(sql, nowPage, pageSize);
		model.addAttribute("poList", poList );
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalRows", totalRows);
		return "person/handler";
	}
	
	@ResponseBody
	@RequestMapping("userjob/delete")
	public int delete(int id,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		int res = userJobService.delete(id);
		return res;
	}
}
