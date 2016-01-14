package action;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loggerUtils.debugLog;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import entrty.CompanyPo;
import entrty.JobPo;
import entrty.PageInfo;
import service.SearchService;
import utils.GlobalUtil;

@Controller
public class SearchAction {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("search/list")
	public String list(String key ,String value , ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Map<String, String> map = GlobalUtil.getKey(key);
		if( map == null ){
			debugLog.debug("search/list", "map is null");
		}
		int nowPage = PageInfo.getNowPage();
		int pageSize = PageInfo.getPageSize();
		String nowpage = request.getParameter("nowPage");
		String pagesize = request.getParameter("pageSize");
		if( nowpage != null){
			nowPage = Integer.parseInt( nowpage );
			pageSize = Integer.parseInt( pagesize );
		}
		if( StringUtils.isNotBlank( map.get("jobs")) ){
			List<JobPo> jobList = (List<JobPo>) searchService.searchByPage(map, value, nowPage, pageSize, JobPo.class);
			model.addAttribute("type", "job");
			model.addAttribute("poList", jobList);
		}else{
			List<CompanyPo> companyList = (List<CompanyPo>) searchService.searchByPage(map, value, nowPage, pageSize, CompanyPo.class);
			model.addAttribute("type", "company");
			model.addAttribute("poList", companyList);
		}
		int totalRows = searchService.getMaxRows();
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		model.addAttribute("totalRows", totalRows);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("pageSize", pageSize);
		return "search/list";
	}
	
}
