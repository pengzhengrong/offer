package action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import utils.cookieUtils;

@Controller
public class ClearCookie {

	@RequestMapping("cookie/clear")
	public String clear(HttpServletRequest request,HttpServletResponse response){
		cookieUtils.clearCookie("user");
		return "index/main";
	}
	
}
