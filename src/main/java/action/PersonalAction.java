package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loggerUtils.debugLog;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.GlobalDao;
import entrty.UserDetailPo;
import entrty.UserPo;
import service.UserDetailService;
import service.UserService;
import utils.GlobalUtil;

@Controller
public class PersonalAction {

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
	
	@RequestMapping("person/upload")
	public int upload(ModelMap model, HttpServletRequest request,
			HttpServletResponse response){
		
		DiskFileItemFactory factroy = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factroy);
		boolean isM = upload.isMultipartContent(request);
		if(!isM){
			System.out.println("不是符合的格式！");
		}
		
		BufferedInputStream bis = null;
		FileOutputStream fos =null;
		BufferedOutputStream bos = null;
		try {
				List list = upload.parseRequest(request);
				Iterator it = list.iterator();
				while(it.hasNext()){
					FileItem item = (FileItem)it.next();
					String name = item.getName(); //这是上传的文件名称
					String fieldname = item.getFieldName(); 
					System.out.println(name+"   "+fieldname);
					InputStream is = item.getInputStream();
					long size2 = item.getSize();
					System.out.println(size2+"=size2");
					bis = new BufferedInputStream(is);
					fos= new FileOutputStream("/offer/ui/static/upload/"+name);
					bos = new BufferedOutputStream(fos);
					byte[] by = new byte[1024]; 
					
					int len = 0;
					while((len = bis.read(by)) != -1){
						bos.write(by, 0, len);
					}
					
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally {
			try {
				bos.close();
				fos.close();
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 1;
	}

}
