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
	$("#form1").attr("action","${rc.contextPath}/company/updatePwd");
 	$("#form1").submit();
}

</script>
</head>
<body style="text-align: center; " >
	<form   method="post" id="form1" >	
	<table>
		<tr>
			<td><label>旧密码：</label></td>
			<td>  <input type="password" name="oldPwd" placeholder="请输入旧密码" value=""></td>
		</tr>
		<tr>
			<td><label>新密码：</label></td>
			<td>  <input type="password" name="newPwd" placeholder="请输入新密码" value=""></td>
		</tr>
		<tr>
			<td><input type="reset" value="重置">	
			<input type="button"  onclick="dosubmit()" value="确认"></td>
		</tr>
	</table>
	</form>
</body>
</html>