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
	function addCompany(){
		window.location.href="${rc.contextPath}/admin/add";
	}
</script>
</head>
<body>
	<!-- <#include "views/index/companyHead.jsp" > -->
	<jsp:include page="/ui/views/index/adminHead.jsp" />
	<div class="_position">
		<span>当前位置：用户列表</span>
	</div>
<div  class="easyTable">
	<table>
	<th>ID</th>
	<!-- <th>用户名</th> -->
	<th>姓名</th>
	<th>qq</th>
	<th>mail</th>
	<th>tel</th>
	<th>地址</th>
	<th>操作</th>
	<c:forEach var="po" items="${poList }">
		<tr>
		<td>${po.id }</td>
		<%-- <td>${po.userName}</td> --%>
		<td>${po.name}</td>
		<td>${po.qq}</td>
		<td>${po.mail}</td>
		<td>${po.tel}</td>
		<td>${po.address }</td>
		<td>
		<c:if test="${po.statu==1 }"><a href="${rc.contextPath}/admin/Update?id=${po.id}&statu=0">禁用</a></c:if>
		<c:if test="${po.statu==0 }"><a href="${rc.contextPath}/admin/Update?id=${po.id}&statu=1">启用</a></c:if>
			|<a href="${rc.contextPath}/admin/Delete?id=${po.id}">删除</a>
		</td>
	</tr>
	</c:forEach>
	</table>
</div>
<div>
<p:Page dataSourceUrl="${rc.contextPath}/admin/List" pageSize="${pageSize }" totalRows="${totalRows}" nowPage="${nowPage }"  />
</div>
	<!-- last div can't delete -->
	</div>
</body>

</html>