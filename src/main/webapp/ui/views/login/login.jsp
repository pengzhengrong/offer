<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入页面</title>
<link rel="stylesheet" type="style/css" href="${rc.contextPath }/ui/static/css/login.css">
<script type="text/javascript" src="${rc.contextPath }/ui/static/js/jquery.min.js"></script>
<script type="text/javascript">
function dosubmit(){
	$("#form1").attr("action","${rc.contextPath}/login/dologin");
 	$("#form1").submit();
}

function regiseter(){
	$("#form1").attr("action","${rc.contextPath}/register/register");
 	$("#form1").submit();
}
</script>
</head>
<body style="text-align: center; " >
	<form   method="post" id="form1" >	
	<table>
		<tr>
			<td><label>姓名：</label></td>
			<td> <input name="username" value="" placeholder="请输入姓名" ></td>
		</tr>
		<tr>
			<td><label>密码：</label></td>
			<td>  <input type="password" name="password" placeholder="请输入密码" value=""></td>
		</tr>
		<tr>
			<td><input type="checkbox" name="cookie" value="true">自动登入</td>
			<td><input type="reset" value="重置">	<input type="button"  onclick="dosubmit()" value="游客登入"><input type="button"  onclick="regiseter()" value="游客注册"></td>
		</tr>
	</table>
	</form>
</body>
</html>