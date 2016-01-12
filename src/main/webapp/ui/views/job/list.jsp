<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.handlewell.com/adp/testTag" prefix="p" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>来我招聘</title>
<link type="text/css" rel="stylesheet" href="${rc.contextPath}/ui/static/css/global.css" />
<script type="text/javascript">
	function pubjob(){
		window.location.href="${rc.contextPath}/job/add";
	}
</script>
</head>
<body>
	<!-- <#include "views/index/companyHead.jsp" > -->
	<jsp:include page="/ui/views/index/companyHead.jsp" />
	<div class="_position">
		<span>当前位置：工作列表</span>
		<span onclick="pubjob()" style="float:right;padding-right:40px;">发布工作</span>
	</div>
	<div class="searchText">
	<form action="${rc.contextPath }/job/list" id="searchForm" method="post">
		<input name="key" list="key_list" placeholder="关键字" value="${key }">=<input name="value" value="${value }" placeholder="值">
		<datalist id="key_list">
			<option label="名称" value="name" />
			<option label="薪资" value="salay" />
		</datalist>
		<input type="reset" value="清空"><input type="submit" value="搜索">
	</form>
	</div>
<div  class="easyTable">
	<table>
	<th>ID</th>
	<th>名称</th>
	<th>地址</th>
	<th>月薪</th>
	<th>操作</th>
	<c:forEach var="po" items="${poList }">
		<tr>
		<td>${po.id }</td>
		<td>${po.name}</td>
		<td>${po.address }</td>
		<td>${po.salay }</td>
		<td>
			<a href="${rc.contextPath}/job/edit?id=${po.id}">修改</a>|<a href="${rc.contextPath}/job/delete?id=${po.id}">删除</a>
		</td>
	</tr>
	</c:forEach>
	<%-- <#list poList as po>
	<tr>
		<td>${po.id }</td>
		<td>${po.name}</td>
		<td>${po.address }</td>
		<td>${po.salay }</td>
		<td>
			<a href="${rc.contextPath}/job/edit?id=${po.id}">修改</a>|<a href="${rc.contextPath}/job/delete?id=${po.id}">删除</a>
		</td>
	</tr>
	</#list> --%>
	
	</table>
</div>
<div>
<p:Page dataSourceUrl="${rc.contextPath}/job/list" pageSize="${pageSize }" totalRows="${totalRows}" nowPage="${nowPage }" form="searchForm" />
</div>
	<!-- last div can't delete -->
	</div>
</body>

</html>