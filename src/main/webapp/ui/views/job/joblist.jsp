<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.handlewell.com/adp/testTag" prefix="p" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>来我招聘</title>
<link rel="stylesheet" type="text/css" media="all"
	href="${rc.contextPath}/ui/static/css/metro.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${rc.contextPath}/ui/static/css/global.css" />
<script type="text/javascript" src="${rc.contextPath}/ui/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/ui/static/js/jquery.plugins.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/ui/static/js/global.js"></script>
<script type="text/javascript">
var username = '${username}';
var _path = '${rc.contextPath}';

function resume(companyName,jobName){
	$.post("${rc.contextPath}/job/resume",
		{
			"companyName":companyName,
			"jobName":jobName
		},
		function(data){
			if( data == 1){
				alert("申请成功,请留意回复状态!");
				return;
			}
			alert("对不起，申请失败(1.没有登入,2.重复申请)!");
		    }
		);
}

function companyInfo(){
	
}

</script>
</head>
<body>
	<div class="metro-layout horizontal">

		<div class="header">

				<h1 onclick="mainToIndex();" id="mainToIndex">来我招聘</h1>

				<label style="font-size: 15px;float: right;padding-right: 10px;color: #DDD;" onclick="loginWindow()" id="loginbtn">登入系统</label>
				<label style="font-size: 15px;float: right;padding-right: 10px;color: #DDD;" onclick="logout()" id="logoutbtn">退出系统</label>
				<label style="font-size: 15px;float: right;padding-right: 20px;color: #DDD;" onclick="myInfo()" id="myinfo">个人信息</label>
				<label style="font-size: 15px;float: right;padding-right: 20px;color: #DDD;" onclick="myHandler()" id="myHandler">我的申请</label>
				
				<span>welcome ${username}</span>
		</div>
		<div class="searchText">
			<form id="searchFormHead" action="${rc.contextPath }/search/list" method="post">
				<input name="key" list="key_list" placeholder="关键字" id="key" value="${key }">=<input id="value" placeholder="值" name="value" value="${value }">
				<datalist id="key_list">
					<option label="职位" value="职位" />
					<option label="公司名称" value="公司名称" />
					<option label="薪资" value="薪资" />
					<option label="地点" value="地点" />
				</datalist>
				<input type="hidden" value="${name }" name="name">
				<input type="submit" value="搜索">
			</form>
		</div>
	<hr>
	<div class="easyTable">
	<div class="_position" style="padding-right: 10px;"	>
		<span>当前位置：职位信息</span>
	</div>
	<table>
	<th>公司名称</th><th>职位</th><th>薪资</th><th>地址</th><th>操作</th>
	<c:forEach var="po" items="${poList}">
		<tr>
			<td>${po.companyName }</td>
			<td>${po.name }</td>
			<td>${po.salay }</td>
			<td>${po.address}</td>
			<td><a href="javascript:" onclick="resume('${po.companyName}','${po.name}')">投递简历</a></td>
		</tr>
	</c:forEach>
	</table>
	</div>
	<div>
		<p:Page nowPage="${nowPage}" totalRows="${totalRows }" pageSize="${pageSize}" form="searchFormHead" dataSourceUrl="${rc.contextPath }/job/joblist"/>
	</div>
	
</div>


</body>
</html>