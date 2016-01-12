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
	<!-- <#include "views/index/companyHead.jsp" > -->
	<jsp:include page="/ui/views/index/companyHead.jsp" />
	<div class="_position" style="padding-right: 10px;">
		<span>当前位置：公司信息</span>
	</div>
	<br>
	<div class="_left">
		<form id="companyForm" action="${rc.contextPath}/company/update" method="post">
			<input type="hidden" name="id" value="${po.id}" />
			<table class="easyTable">
				<tr>
					<td>是否可见:</td>
					<td>
						<input <c:if test="${po.statu==1}">checked="checked"</c:if>  type="radio" name="statu" value="1">可见
					 	<input <c:if test="${po.statu==0}">checked="checked"</c:if> type="radio" name="statu" value="0">不可见
					</td>
				</tr>
				<tr>
					<td>名称:</td>
					<td><input required="required" name="companyName" value="${po.companyName}">
					</td>
				</tr>
				<tr>
					<td>地址:</td>
					<td><input name="address" value="${po.address}"></td>
				</tr>
				<tr>
					<td>联系方式:</td>
					<td><input type="tel" name="tel" value="${po.tel}"></td>
				</tr>
				<tr><td colspan="2"><input type="submit" value="提交" > <input type="reset" value="重置"></td></tr>
			</table>
		</form>
	</div>
	
	<div class="_center">
		<textarea name="desc" style="width: 500px;" form="companyForm" rows="10" cols="10" placeholder="公司描述" >${po.desc}</textarea>
		<br><br>
		<input type="hidden"  form="companyForm" name="thumb" value="${po.thumb }" >
		<form  id="thumbForm"  enctype="multipart/form-data">
			<input type="file" name="filename" multiple="multiple" /> 
			<input id="upload" type="button" onclick="doUpload()" value="开始上传" />
		</form>
		<br>
		<img alt="" src="${rc.contextPath}/ui/static/images/default.png">
	</div>
	
	<!-- last div can't delete -->
</div>
</body>

</html>