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
<script type="text/javascript" src="${rc.contextPath}/ui/static/js/metro.js"></script>
<script type="text/javascript" src="${rc.contextPath}/ui/static/js/global.js"></script>
<script type="text/javascript">
var username = '${username}'; 
var _path = '${rc.contextPath}';
</script>
</head>
<body>
	<div class="metro-layout horizontal">

		<div class="header">

			<h1 onclick="mainToIndex();" id="main_">来我招聘</h1>
				<label style="font-size: 15px;float: right;padding-right: 10px;color: #DDD;" onclick="loginWindow()" id="loginbtn">登入系统</label>
				<label style="font-size: 15px;float: right;padding-right: 10px;color: #DDD;" onclick="logout()" id="logoutbtn">退出系统</label>
				<label style="font-size: 15px;float: right;padding-right: 15px;color: #DDD;" onclick="myInfo()" id="myinfo">个人信息</label>
				<label style="font-size: 15px;float: right;padding-right: 20px;color: #DDD;" onclick="myHandler()" id="myHandler">我的申请</label>
				<span>welcome ${username}</span>
			
			<div class="controls">
				<span class="down" title="Scroll down"></span> 
				<span class="up"   title="Scroll up"></span> 
				<span class="next" title="Scroll left"></span>
				<span class="prev" title="Scroll right"></span> 
				<span class="toggle-view" title="Toggle layout"></span>
			</div>
		</div>
<div class="searchText">
			<form id="searchFormHead" action="${rc.contextPath }/search/list" method="post">
				<input name="key" list="key_list" placeholder="关键字" >=<input placeholder="值" name="value">
				<datalist id="key_list">
					<option label="职位" value="职位" />
					<option label="公司名称" value="公司名称" />
					<option label="薪资" value="薪资" />
					<option label="地点" value="地点" />
				</datalist>
				<input type="reset" onclick="resetKey();" value="重置"><input type="submit" value="搜索">
			</form>
</div>
		<div class="content clearfix">

			<div class="items">
				<a class="box" href="#"><span>Mail</span><img class="icon"
					src="${rc.contextPath}/ui/static/images/mail.png" alt="" /></a>
				<a class="box" href="#" style="background: #6b6b6b;"><span>Settings</span><img
					class="icon" src="${rc.contextPath}/ui/static/images/settings.png" alt="" /></a> <a
					class="box" href="#" style="background: #43b51f;"><span>Make
						a call</span><img class="icon" src="${rc.contextPath}/ui/static/images/phone.png" alt="" /></a>
				<a class="box width2 height2" href="http://www.16sucai.com/"><span>Photos</span><img
					class="cover" src="${rc.contextPath}/ui/static/images/gallery.jpg" alt="" /></a> <a
					class="box" href="#" style="background: #00aeef;"><span>Music</span><img
					class="icon" src="${rc.contextPath}/ui/static/images/music.png" alt="" /></a> <a
					class="box" href="#" style="background: #f58d00;"><span>Firefox</span><img
					class="icon" src="${rc.contextPath}/ui/static/images/firefox.png" alt="" /></a> <a
					class="box" href="#" style="background: #640f6c;"><span>Yahoo</span><img
					class="icon" src="${rc.contextPath}/ui/static/images/yahoo.png" alt="" /></a> <a
					class="box height2" href="#" style="background: #d32c2c;"><span>GMail</span><img
					class="icon big" src="${rc.contextPath}/ui/static/images/gmail.png" alt="" /></a> <a
					class="box" href="#" style="background: #3c5b9b;"><span>Facebook</span><img
					class="icon" src="${rc.contextPath}/ui/static/images/facebook.png" alt="" /></a> <a
					class="box" href="#" style="background: #ffc808;"><span>Winamp</span><img
					class="icon" src="${rc.contextPath}/ui/static/images/winamp.png" alt="" /></a> <a
					class="box" href="#" style="background: #00a9ec;"><span>Tasks</span><img
					class="icon" src="${rc.contextPath}/ui/static/images/tasks.png" alt="" /></a> <a
					class="box height2" href="#" style="background: #4c5e51;"><span>DeviantART</span><img
					class="icon big" src="${rc.contextPath}/ui/static/images/deviantart.png" alt="" /></a> <a
					class="box" href="#" style="background: #f874a4;"><span>Dribbble</span><img
					class="icon" src="${rc.contextPath}/ui/static/images/dribbble.png" alt="" /></a>
			</div>

		</div>

	</div>

</body>
</html>