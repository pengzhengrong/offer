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
<script type="text/javascript" src="${rc.contextPath}/ui/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/ui/static/js/jquery.plugins.min.js"></script>
<script type="text/javascript">
	function handler(id){
		$.post(
			"${rc.contextPath}/userjob/delete",
			{
				"id":id
			},
			function(data){
				if( data == 1){
					alert("删除成功!");
					window.location.reload();
					return;
				}
				alert("删除失败!");
			}
		);
	}	
	
</script>
</head>
<body>
	<!-- <#include "views/index/companyHead.jsp" > -->
	<jsp:include page="/ui/views/index/head.jsp" />
	<div class="_position">
		<span>当前位置：我的申请</span>
	</div>
<div  class="easyTable">
	<table>
	<th>序号</th>
	<th>公司</th>
	<th>职位</th>
	<th>状态</th>
	<th>操作</th>
	<c:forEach var="po" items="${poList }" varStatus="status">
		<tr>
		<td>${status.index + 1} </td>
		<td>${po.companyName}</td>
		<td>${po.jobName }</td>
		<td>
			<c:if test="${po.statu==0 }">未处理</c:if>	
			<c:if test="${po.statu==1 }">接受</c:if>	
			<c:if test="${po.statu==-1 }">拒绝</c:if>	
		</td>
		<td><a href="javascript:" onclick="handler('${po.id}');">删除</a></td>
	</tr>
	</c:forEach>
	</table>
</div>
<div>
<p:Page dataSourceUrl="${rc.contextPath}/userjob/list" pageSize="${pageSize }" totalRows="${totalRows}" nowPage="${nowPage }" />
</div>
	<!-- last div can't delete -->
	</div>
</body>

</html>