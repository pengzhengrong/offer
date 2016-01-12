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
		if( StringUtils.isNotBlank( map.get("jobs")) ){
			List<JobPo> jobList = (List<JobPo>) searchService.searchFor(map, value);
			model.addAttribute("type", "job");
			model.addAttribute("poList", jobList);
		}else{
			List<CompanyPo> companyList = (List<CompanyPo>) searchService.searchFor(map, value);
			model.addAttribute("type", "company");
			model.addAttribute("poList", companyList);
		}
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		
		return "search/list";
	}
	
}
