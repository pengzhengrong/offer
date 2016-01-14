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
	function handler(id,key){
		$.post(
			"${rc.contextPath}/userjob/update",
			{
				"id":id,
				"key":key
			},
			function(data){
				if( data == 0){
					alert("操作失败!");
					return;
				}
				alert("操作成功!");
			}
		);
	}	
	
	function userDetail(userId){
		window.location.href="${rc.contextPath}/userjob/person?userId="+userId;
	}
</script>
</head>
<body>
	<!-- <#include "views/index/companyHead.jsp" > -->
	<jsp:include page="/ui/views/index/companyHead.jsp" />
	<div class="_position">
		<span>当前位置：申请处理</span>
	</div>
	<form id="searchForm" method="post">
		<input type="hidden" value="${statu }" name="statu" >
	</form>
	<div>
		<span onclick="jobHandler('1');" style="float:right;padding-right:10px">申请成功</span>
		<span onclick="jobHandler('-1');" style="float:right;padding-right:10px">申请失败</span>
	</div>
<div  class="easyTable">
	<table>
	<th>序号</th>
	<th>申请人</th>
	<th>职位</th>
	<th>状态</th>
	<th>操作</th>
	<c:forEach var="po" items="${poList }" varStatus="status">
		<tr>
		<td>${status.index + 1} </td>
		<%-- <td>${po.id}</td> --%>
		<td><a href="javascript:" onclick="userDetail('${po.userId}');">${po.userName}</a></td>
		<td>${po.jobName }</td>
		<td>
			<c:if test="${po.statu==0 }">未处理</c:if>	
			<c:if test="${po.statu==1 }">接受</c:if>	
			<c:if test="${po.statu==-1 }">拒绝</c:if>	
		</td>
		<td><a href="javascript:" onclick="handler('${po.id}',1);">接受申请</a>|<a href="javascript:" onclick="handler('${po.id}',-1);">遗憾错过</a></td>
	</tr>
	</c:forEach>
	</table>
</div>
<div>
<p:Page dataSourceUrl="${rc.contextPath}/userjob/handler" form="searchForm" pageSize="${pageSize }" totalRows="${totalRows}" nowPage="${nowPage }" />
</div>
	<!-- last div can't delete -->
	</div>
</body>

</html>