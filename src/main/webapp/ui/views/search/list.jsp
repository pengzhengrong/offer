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
			if( data == 0){
				alert("对不起，申请失败(查看您是否登入)!");
				return;
			}
			alert("申请成功,请留意回复状态!");
		    }
		);
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
				<input type="reset" onclick="resetKey();" value="重置"><input type="submit" value="搜索">
			</form>
		</div>
	<hr>
	<div class="easyTable">
		<c:if test="${type == 'company'}">
		<table>
		<th>公司名称</th>
		<th>地址</th>
		<th>联系方式</th>
		<th>操作</th>
		<c:forEach var="po" items="${poList}">
		<tr>
			<td>${po.companyName }</td>
			<td>${po.address }</td>
			<td>${po.tel }</td>
			<td>公司详情</td>
		</tr>
		</c:forEach>
		</table>
	</c:if>
	
	<c:if test="${type=='job' }">
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
	</c:if>
	</div>
	<div>
		<p:Page nowPage="1" pageSize="2" dataSourceUrl=""/>
	</div>
	
</div>


</body>
</html>