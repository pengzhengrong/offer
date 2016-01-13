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
		
		if( nowpage != null){
			nowPage = Integer.parseInt( nowpage );
			pageSize = Integer.parseInt( pagesize );
		}
		
		List<JobPo> poList = jobService.queryByPage(sql, nowPage, pageSize);
		int totalRows = jobService.getMaxRows();
		
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
	
	//--------------------------------------//
	
	@RequestMapping("job/joblist")
	public String joblist(String name ,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		
		int nowPage = 1;
		int pageSize = 2;
		String nowpage = request.getParameter("nowPage");
		String pagesize = request.getParameter("pageSize");
		String sql = "select * from `jobs` where `statu`=1 and `company_name`='"+name+"'";
		int totalRows = jobService.getMaxRows();
		if( nowpage != null){
			nowPage = Integer.parseInt( nowpage );
			pageSize = Integer.parseInt( pagesize );
		}
		List<JobPo> poList = jobService.queryByPage(sql, nowPage, pageSize);
		model.addAttribute("poList", poList );
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalRows", totalRows);
		model.addAttribute("name", name);
		return "job/joblist";
	}
	
}
