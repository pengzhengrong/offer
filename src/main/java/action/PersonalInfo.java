package action;

import java.io.IOException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loggerUtils.debugLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.GlobalDao;
import entrty.UserDetailPo;
import entrty.UserPo;
import service.UserDetailService;
import service.UserService;
import utils.GlobalUtil;

@Controller
public class PersonalInfo {

	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@RequestMapping("person/list")
	public String getPersonalInfo(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		int userId = (int) GlobalUtil.getSession("userId", request);
		String userName = GlobalUtil.getUserName(request);
		if( userId == -1 || userId == 0){
			userId = userServiceImpl.getId(userName);
			GlobalUtil.setSession("userId", userId, request);
		}
		debugLog.debug("person/list", "id="+userId);
		UserDetailPo userDetailPo = userDetailService.getUserDetail(userId);
		if( userDetailPo == null ) return "person/add";
		model.addAttribute("po", userDetailPo);
		return "person/list";
	}

	@RequestMapping("person/save")
	public void add(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, UserDetailPo userDetailPo) {

		int nowTime = (int) new Date().getTime();
		int userId = (int)GlobalUtil.getSession("userId", request);
		debugLog.debug("perosn/add", "id="+userId);
		String sql = "insert into `user_detail`(`user_id`,`project_name`,`project_desc`,`name`,`age`,`sex`,`address`,`qq`,`mail`,`hope_salay`,`tel`,`key_value`,`create_time`,`update_time`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] args = {userId,userDetailPo.getProjectName(),userDetailPo.getProjectDesc(),userDetailPo.getName(), userDetailPo.getAge(),userDetailPo.getSex(),userDetailPo.getAddress(),userDetailPo.getQq(),
				userDetailPo.getMail(),userDetailPo.getHopeSalay(),userDetailPo.getTel(),userDetailPo.getKeyValue(),nowTime,nowTime};
		int varchar = Types.VARCHAR;
		int _char = Types.CHAR;
		int tinyint = Types.TINYINT;
		int _int = Types.INTEGER;
		int[] argTypes = { _int , varchar , varchar, varchar, _int , tinyint , varchar , _char , 
				varchar , varchar , varchar , varchar , _int , _int 
		};
		int save = userDetailService.saveOrUpdate(sql, args, argTypes);
		debugLog.debug("person/add", "res:"+save);
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("person/update")
	public void update(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, UserDetailPo userDetailPo){
		int nowTime = (int) new Date().getTime();
		String sql = "update `user_detail` set `project_name`=?,`project_desc`=?,`name`=?,`age`=?,`sex`=?,`address`=?,`qq`=?,`mail`=?,`hope_salay`=?,`tel`=?,`key_value`=?,`update_time`=?,`statu`=? where `id`=?";
		Object[] args = {userDetailPo.getProjectName(),userDetailPo.getProjectDesc(),userDetailPo.getName(),userDetailPo.getAge(),userDetailPo.getSex(),userDetailPo.getAddress(),userDetailPo.getQq(),
				userDetailPo.getMail(),userDetailPo.getHopeSalay(),userDetailPo.getTel(),userDetailPo.getKeyValue(),nowTime,userDetailPo.getStatu(),userDetailPo.getId()};
		int varchar = Types.VARCHAR;
		int _char = Types.CHAR;
		int tinyint = Types.TINYINT;
		int _int = Types.INTEGER;
		int[] argTypes = { varchar , varchar ,varchar, _int , tinyint , varchar , _char , 
				varchar , varchar , varchar , varchar , _int , tinyint ,_int 
		};
		int update = userDetailService.saveOrUpdate(sql, args, argTypes);
		debugLog.debug("person/update", "res:"+update);
		try {
			response.sendRedirect("/offer/person/list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
