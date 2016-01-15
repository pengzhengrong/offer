package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
	
	@ResponseBody
	@RequestMapping("person/upload")
	public String upload(@RequestParam("file") CommonsMultipartFile[] files,ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException{
//		String userName = (String) GlobalUtil.getSession("userName", request);
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = "demoUpload" + file.getOriginalFilename();  
                        //定义上传路径  
                        String path = "/home/save/" + fileName;  
                        File localFile = new File(path);  
                        file.transferTo(localFile);  
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        }  
        return "success";  
	}
	
	@RequestMapping("test/toupload")
	public String initupload(ModelMap model, HttpServletRequest request,
			HttpServletResponse response){
		return "person/upload";
	}
	
	 @RequestMapping("test/upload"   )  
	    public String addUser(@RequestParam("file") CommonsMultipartFile[] files,ModelMap model, HttpServletRequest request){  
	          
	        for(int i = 0;i<files.length;i++){  
	            System.out.println("fileName---------->" + files[i].getOriginalFilename());  
	          
	            if(!files[i].isEmpty()){  
	                int pre = (int) System.currentTimeMillis();  
	                try {  
	                    //拿到输出流，同时重命名上传的文件  
	                    FileOutputStream os = new FileOutputStream("/home/save/" + new Date().getTime() + files[i].getOriginalFilename());  
	                    //拿到上传文件的输入流  
	                    InputStream is =  files[i].getInputStream();  
	                    BufferedInputStream bis = new BufferedInputStream( is );
	                    BufferedOutputStream bos = new BufferedOutputStream(os);
	                      
	                    //以写字节的方式写文件  
	                    int b = 0;  
	                    while( bis.read() > -1 ){  
	                        bos.write(b);  
	                    }  
	                    bos.flush();  
	                    bos.close();  
	                    bis.close();  
	                    int finaltime = (int) System.currentTimeMillis();  
	                    System.out.println(finaltime - pre);  
	                      
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                    System.out.println("上传出错");  
	                }  
	        }  
	        }  
	        return "/success";  
	    }  
	      
	      
	    @RequestMapping("test/upload2"  )  
	    public String upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
	        //创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                //记录上传过程起始时的时间，用来计算上传时间  
	                int pre = (int) System.currentTimeMillis();  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	                        System.out.println(myFileName);  
	                        //重命名上传后的文件名  
	                        String fileName = "demoUpload" + file.getOriginalFilename();  
	                        //定义上传路径  
	                        String path = "/home/save/" + fileName;  
	                        File localFile = new File(path);  
	                        file.transferTo(localFile);  
	                    }  
	                }  
	                //记录上传该文件后的时间  
	                int finaltime = (int) System.currentTimeMillis();  
	                System.out.println(finaltime - pre);  
	            }  
	              
	        }  
	        return "/success";  
	    }  
	      
}
