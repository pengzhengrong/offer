package utils;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageTagComponentTemp extends SimpleTagSupport{

	
	private int totalRows;//总的记录
	private int nowPage;//当前页数
	private int pageSize;//容量大小
	private int jumpPage;//跳转页面
	private String dataSourceUrl;//请求地址
	private String form;
	int allPage = 0;//总的页数
	@Override
	public void doTag() throws JspException, IOException {
		
		JspWriter out = getJspContext().getOut();
		//<a>首页</a><a>上一页</a><a>3</a><a>下一页</a><a>尾页</a> 跳至<a>34</a>
		//总的页数
		if (totalRows%pageSize ==0) {
			allPage = totalRows/pageSize;
		}else {
			allPage = totalRows/pageSize+1;
		}
		//处理URL
		dataSourceUrl += "?pageSize="+pageSize;
		
		//处理首页
		StringBuffer firstPage = new StringBuffer();
		firstPage.append("<a onclick='doJump2(1)'>首页</a>");
		//处理尾页
		StringBuffer endPage = new StringBuffer();
		endPage.append("<a onclick='doJump2("+allPage+")'>尾页</a>");
		//处理当前页数
		StringBuffer totalPageStr = new StringBuffer();
		
		if (allPage <= 1 ) {
			totalPageStr.append("<a onclick='doJump(1)'>"+1+"</a>");
		}
		if (allPage > 1) {
			
			if(allPage <= 5 ){
				for(int j = 1;j<=allPage ;j++){
					doNowPage(totalPageStr,j);
				}
			}else{
				if(nowPage > 3 && nowPage < allPage - 2){
					for (int j = nowPage-2; j <= nowPage+2; j++) {
						if (j >= 1 && j<=allPage) {
							doNowPage(totalPageStr,j);
						}
					}
				}else{
					if(nowPage >= allPage -2 ){
						for (int j = allPage-4; j <= allPage; j++) {
							if (j >= 1 && j<=allPage) {
								doNowPage(totalPageStr,j);
							}
						}
					}else{
						for (int j = 1; j <= 5; j++) {
							if (j >= 1 && j<=allPage) {
								doNowPage(totalPageStr,j);
							}
						}
					}
				}
			}
		}
		
		//处理上一页
		StringBuffer prePage = new StringBuffer();
		if (hasPre()) {
			prePage.append("<a onclick='doJump2("+(nowPage-1)+")' >上一页</a>");
		}else{
			prePage.append("<a>上一页</a>");
		}
		//处理下一页
		StringBuffer nextPage = new StringBuffer();
		if (hasNext()) {
			nextPage.append("<a onclick='doJump2("+(nowPage+1)+")'>下一页</a>");
		}else{
			nextPage.append("<a>下一页</a>");
		}
		//处理跳页
		StringBuffer jumpPage = new StringBuffer();
		StringBuffer js = new StringBuffer();
		if(form!=null){
			if(!form.trim().equals("")){
				
					jumpPage.append("<input size='4' id='jump' name='jump'><input type='button' onclick='doJump()' value='跳至'>");
					//处理javascript
					js.append("<script type=\"text/javascript\">");
					js.append("function doJump(){ ");
					js.append("var num = document.getElementById('jump').value;");
					js.append("if(isNaN(num) || num > "+allPage+" || num < 1){");
					js.append("num="+nowPage+";}");
					js.append("var form1 = document.getElementById(\""+form+"\");");
					js.append("form1.action=\""+dataSourceUrl+"&nowPage=\"+num;");
					js.append("form1.submit();}");
					
					js.append("function doJump2(j){ ");
					js.append("if(isNaN(j) || j > "+allPage+" || j < 1){");
					js.append("j="+nowPage+";}");
					js.append("var form1 = document.getElementById(\""+form+"\");");
					js.append("form1.action=\""+dataSourceUrl+"&nowPage=\"+j;");
					js.append("form1.submit();}");
					js.append("</script>");
				}
		}else{
			jumpPage.append("<form id='MyTagForm' method='post'><input size='4' id='jump' name='jump'><input type='button' onclick='doJump()' value='跳至'></form>");
			//处理javascript
			js.append("<script type=\"text/javascript\">");
			js.append("function doJump(){ ");
			js.append("var num = document.getElementById('jump').value;");
			js.append("if(isNaN(num) || num > "+allPage+" || num < 1){");
			js.append("num="+nowPage+";}");
			js.append("var form1 = document.getElementById('MyTagForm');");
			js.append("form1.action=\""+dataSourceUrl+"&nowPage=\"+num;");
			js.append("form1.submit();}");
			
			js.append("function doJump2(j){ ");
			js.append("var form1 = document.getElementById('MyTagForm');");
			js.append("form1.action=\""+dataSourceUrl+"&nowPage=\"+j;");
			js.append("form1.submit();}");
			js.append("</script>");
		}
		
		
		
		StringBuffer html = new StringBuffer();
		defaultCss(html);
		html.append("<div id='TagDiv'>");
		html.append("<div id='TagDiv2'>");
		html.append(firstPage);
		html.append(prePage);
		html.append(totalPageStr);
		html.append(nextPage);
		html.append(endPage);
		html.append("总共"+allPage+"页");
		html.append(jumpPage);
		html.append("</div>");
		html.append("</div>");
		html.append(js);
		
		out.append(html.toString());
	}
	
	private void defaultCss(StringBuffer html){
		html.append("<style type='text/css'>");
		html.append("a{text-decoration: none;padding: 3px 6px;}");
		html.append("a:hover{background: #cddde4;border: #97b9c9 solid 1px;color: #067db5;padding: 5px 9px;}");
		html.append("a.currentPage{background: #FFF;border: #89bdd8 solid 1px;color: #067db5;}");
		html.append("#MyTagForm{float:right;}");
		html.append("#TagDiv{width:100%;position: absolute;bottom: 80px; }");
		html.append("#TagDiv2{width:500px;margin:0 auto; }");
		html.append("</style>");
	}
	private void doNowPage(StringBuffer totalPageStr,int j){
		if (nowPage == j) {
			totalPageStr.append("<a class='currentPage' onclick='doJump2("+j+")'>"+"<font color='red'>"+j+"</font></a>");
		}else {
			totalPageStr.append("<a class='currentPage' onclick='doJump2("+j+")'>"+j+"</a>");
		}
	}
	
	public boolean hasNext(){
		return nowPage < allPage ;
	}
	
	public boolean hasPre(){
		return nowPage > 1;
	}
	
	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getDataSourceUrl() {
		return dataSourceUrl;
	}

	public void setDataSourceUrl(String dataSourceUrl) {
		this.dataSourceUrl = dataSourceUrl;
	}

	public int getJumpPage() {
		return jumpPage;
	}

	public void setJumpPage(int jumpPage) {
		this.jumpPage = jumpPage;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}
	
}
	
	
	
