package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loggerUtils.debugLog;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.CompanyService;
import service.JobService;
import service.UserDetailService;
import service.UserJobService;
import utils.GlobalUtil;
import entrty.CompanyPo;
import entrty.JobPo;
import entrty.UserDetailPo;
import entrty.UserJobPo;

@Controller
public class JobAction {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private UserJobService userJobService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@RequestMapping("job/list")
	public String pubJob(ModelMap model,HttpServletRequest request, HttpServletResponse response ){
		int companyId = (int) GlobalUtil.getSession("companyId", request);
		
		int nowPage = 1;
		int pageSize = 2;
		String nowpage = request.getParameter("nowPage");
		String pagesize = request.getParameter("pageSize");
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		String sql = "select * from `jobs` where `company_id`="+companyId;
		
		if( StringUtils.isNotBlank( key ) && StringUtils.isNotBlank(value)){
			if( GlobalUtil.isNumber( value )){
				sql += " and `"+key+"`="+value;
			}else{
				sql += " and `"+key+"` like '%"+value+"%' ";
			}
		}
		
		debugLog.debug("job/list", "sql="+sql);
		int totalRows = jobService.getMaxRows(sql);
		
		if( nowpage != null){
			nowPage = Integer.parseInt( nowpage );
			pageSize = Integer.parseInt( pagesize );
		}
		
		List<JobPo> poList = jobService.queryByPage(sql, nowPage, pageSize);
		model.addAttribute("poList", poList);
		model.addAttribute("totalRows", totalRows);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		return "job/list";
	}
	
	@RequestMapping("job/add")
	public String add(ModelMap model,HttpServletRequest request, HttpServletResponse response){
		return "job/add";
	}
	
	@RequestMapping("job/edit")
	public String edit(int id ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		JobPo po = jobService.getJob(id);
		model.addAttribute("po", po);
		return "job/edit";
	}
	
	@RequestMapping("job/save")
	public void save(JobPo po ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		int companyId = (int) GlobalUtil.getSession("companyId", request);
		String companyName = (String) GlobalUtil.getSession("companyName", request);
		int res = jobService.save(po, companyId ,companyName);
		try {
			response.sendRedirect("/offer/job/list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("job/update")
	public void update(JobPo po ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		int companyId = (int) GlobalUtil.getSession("companyId", request);
		int res = jobService.update(po);
		try {
			response.sendRedirect("/offer/job/list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("job/delete")
	public void update(int id,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		int companyId = (int) GlobalUtil.getSession("companyId", request);
		int res = jobService.delete(id);
		try {
			response.sendRedirect("/offer/job/list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
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
			return "";
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
	
}
