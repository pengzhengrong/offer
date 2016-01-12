<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>来我招聘</title>
<link type="text/css" rel="stylesheet"
	href="${rc.contextPath}/ui/static/css/global.css" />
<script type="text/javascript"
	src="${rc.contextPath}/ui/static/js/jquery.min.js"></script>
</head>
<body>
	<!-- <#include "views/index/head.jsp" > -->
	<jsp:include page="/ui/views/index/companyHead.jsp" />
	<div class="_position" style="padding-right: 10px;">
		<span>当前位置：个人信息</span>
	</div>
	<br>
	<div class="_left">
			<table class="easyTable">
				<tr>
					<td>姓名:</td>
					<td>${po.name}</td>
				</tr>
				<tr>
					<td>年龄:</td>
					<td>${po.age}</td>
				</tr>
				<tr>
					<td>性别:</td>
					<td>
							<c:if test="${po.sex==0}">保密</c:if>
							<c:if test="${po.sex==1}">男</c:if>
							<c:if test="${po.sex==2}">女</c:if>
					</td>
				</tr>
				<tr>
					<td>地址:</td>
					<td>${po.address}</td>
				</tr>
				<tr>
					<td>qq:</td>
					<td>${po.qq}</td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td>${po.mail}</td>
				</tr>
				<tr>
					<td>期望月薪:</td>
					<td>${po.hopeSalay}</td>
				</tr>
				<tr>
					<td>联系方式:</td>
					<td>${po.tel}</td>
				</tr>
				<tr>
					<td>关键字:</td>
					<td>${po.keyValue}</td>
				</tr>
			</table>
	</div>
	
	<div class="_center">
		<input form="personForm" readonly="readonly"  value="${po.projectName}" ><br><br>
		<textarea style="width: 500px;"  rows="10" cols="10" >${po.projectDesc}</textarea>
		<br><br>
		<br>
		<img alt="" src="${rc.contextPath}/ui/static/images/default.png">
	</div>
	
	<!-- last div can't delete -->
</div>
</body>

</html>