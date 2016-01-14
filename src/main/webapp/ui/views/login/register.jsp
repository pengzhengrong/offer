<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<link rel="stylesheet" type="style/css" href="${rc.contextPath }/ui/static/css/login.css">
<script type="text/javascript" src="${rc.contextPath }/ui/static/js/jquery.min.js"></script>
<script type="text/javascript">
function dosubmit(){
	$("#form1").attr("action","${rc.contextPath}/register/doRegister");
 	$("#form1").submit();
}

function checkUserName( username ){
	$.ajax({
		url: '${rc.contextPath}/register/checkName',
		type: 'post',
		data:{
			"username":username
		},
		success: function (data){
			//alert(data);
			if( data == 1 ){
				$("#warn").html("此用户名已被使用!");
			}else{
				$("#warn").html("");
			}
		}
	});
}

function doValid(){
	if( $("#warn").val().trim() != '' ){
		alert("请重新输入用户名!");
		return false;
	}
	if( $("input[name=username]").val().trim() =='' ){
		alert("用户名不能为空!");
		return false;
	}
	if( $("input[name=password]").val().trim() == '' ){
		alert("密码不能为空!");
		return false;
	}
	return true;
}

</script>
</head>
<body>
	<form   method="post" id="form1"  onsubmit="return doValid();">	
	<table>
		<tr>
			<td><span style="color: red;">*</span><label>姓名：</label></td>
			<td> <input name="username" onblur="checkUserName(this.value)" value="" placeholder="请输入姓名" ><span id="warn" style="color:red;"></span></td>
		</tr>
		<tr>
			<td><span style="color: red;">*</span><label>密码：</label></td>
			<td>  <input type="password" name="password" placeholder="请输入密码" value=""></td>
		</tr>
		<tr>
			<td><input type="reset" value="重置">	<input type="button"  onclick="dosubmit()" value="注册"></td>
		</tr>
	</table>
	</form>
</body>
</html>