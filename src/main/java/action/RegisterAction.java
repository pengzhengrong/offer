package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loggerUtils.debugLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.UserService;

@Controller
public class RegisterAction {
	
	@Autowired
	private UserService userServiceImpl;
	
	@RequestMapping("register/register")
	public String register(){
		return "login/register";
	}
	
	@ResponseBody
	@RequestMapping("register/checkName")
	public int checkName(String username,
			ModelMap model, HttpServletRequest request,HttpServletResponse response){
		int ishas = userServiceImpl.checkName( username );
		return ishas;
	}
	
	@RequestMapping("register/doRegister")
	public void doregister(String username, String password,
			ModelMap model, HttpServletRequest request,HttpServletResponse response){
		boolean isok = userServiceImpl.register(username, password);
		debugLog.debug("login/doRegister", username+" "+password);
		StringBuffer sb = new StringBuffer();
		PrintWriter write = null;
			try {
				response.setHeader("Content-Type", "text/html;charset=UTF-8");
				write = response.getWriter();
				if( isok ){
					sb.append("<script>alert(\"恭喜，注册成功!\");window.close();</script>");
				}else{
					sb.append("<script>alert(\"对不起，注册失败!\");window.location.href=\"/${rc.contextPath}/login/register\";</script>");
				}
				write.write(sb.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				write.close();
			}
			
	}
	
	
	
}
