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
	<jsp:include page="/ui/views/index/head.jsp" />
	<div class="_position" style="padding-right: 10px;">
		<span>当前位置：个人信息</span>
	</div>
	<br>
	<div class="_left">
		<form id="personForm" action="${rc.contextPath}/person/update" method="post">
			<input type="hidden" name="id" value="${po.id}" />
			<table class="easyTable">
				<tr>
					<td>是否可见:</td>
					<td>
						<input <c:if test="${po.statu==1}">checked="checked"</c:if>  type="radio" name="statu" value="1">可见
					 	<input <c:if test="${po.statu==0}">checked="checked"</c:if>  type="radio" name="statu" value="0">不可见
					</td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input required="required" name="name" value="${po.name}">
					</td>
				</tr>
				<tr>
					<td>年龄:</td>
					<td><input name="age" value="${po.age}"></td>
				</tr>
				<tr>
					<td>性别:</td>
					<td><select id="sex" name="sex">
							<option <c:if test="${po.sex==0}">selected="selected"</c:if> value="0" >保密</option>
							<option <c:if test="${po.sex==1}">selected="selected"</c:if>  value="1" > 男</option>
							<option <c:if test="${po.sex==2}">selected="selected"</c:if> value="2" >女</option>
					</select></td>
				</tr>
				<tr>
					<td>地址:</td>
					<td><input name="address" value="${po.address}"></td>
				</tr>
				<tr>
					<td>qq:</td>
					<td><input name="qq" value="${po.qq}"></td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input type="email" name="mail" value="${po.mail}"></td>
				</tr>
				<tr>
					<td>期望月薪:</td>
					<td><input name="hopeSalay" value="${po.hopeSalay}"></td>
				</tr>
				<tr>
					<td>联系方式:</td>
					<td><input name="tel" value="${po.tel}"></td>
				</tr>
				<tr>
					<td>关键字:</td>
					<td><input required="required" name="keyValue"
						value="${po.keyValue}"></td>
				</tr>
				<tr><td colspan="2"><input type="submit" value="提交" > <input type="reset" value="重置"></td></tr>
			</table>
		</form>
	</div>
	
	<div class="_center">
		<input form="personForm" name="projectName" placeholder="项目名称" value="${po.projectName}" ><br><br>
		<textarea style="width: 500px;" name="projectDesc" form="personForm" rows="10" cols="10" placeholder="请描述您的项目" >${po.projectDesc}</textarea>
		<br><br>
		<input type="hidden" form="personForm" name="thumb" value="${po.thumb }" >
		<form  id="thumbForm" action="/offer/person/upload"  enctype="multipart/form-data">
			<input type="file" name="filename" multiple="multiple" /> 
			<input id="upload" type="submit"  value="开始上传" /> <!-- onclick="doSubmit()" -->
		</form>
		<br>
		
		<c:if test="${po.thumb != null }"><img alt="" src="${rc.contextPath}/ui/static/images/default.png"></c:if>
		<c:if test="${po.thumb == null }"><img alt="" src="${rc.contextPath}/ui/static/images/default.png"></c:if>
	</div>
	
	<!-- last div can't delete -->
</div>
</body>

</html>