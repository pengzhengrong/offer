<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>来我招聘</title>
<link rel="stylesheet" type="text/css" media="all"
	href="${rc.contextPath}/ui/static/css/metro.css" />
<script type="text/javascript" src="${rc.contextPath}/ui/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/ui/static/js/jquery.plugins.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/ui/static/js/global3.js"></script>
<script type="text/javascript">
var username = '${username}';
var _path = '${rc.contextPath}';
</script>
</head>
<body>
	<div class="metro-layout horizontal">

		<div class="header">

				<h1 onclick="mainToIndex();" id="mainToIndex">来我招聘</h1>

				<label style="font-size: 15px;float: right;padding-right: 10px;color: #DDD;" onclick="loginWindow()" id="loginbtn">登入系统</label>
				<label style="font-size: 15px;float: right;padding-right: 10px;color: #DDD;" onclick="logout()" id="logoutbtn">退出系统</label>
				<label style="font-size: 15px;float: right;padding-right: 20px;color: #DDD;" onclick="infoMan_comp()" id="company_info">公司信息</label>
				<label style="font-size: 15px;float: right;padding-right: 30px;color: #DDD;" onclick="infoMan_user()" id="user_info">用户信息</label>
				<span>welcome ${username}</span>
		</div>
	<hr>

</body>
</html>