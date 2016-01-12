<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>来我招聘</title>
<link type="text/css" rel="stylesheet" href="${rc.contextPath}/ui/static/css/global.css" />
</head>
<body>
	<!-- <#include "views/index/companyHead.jsp" > -->
	<jsp:include page="/ui/views/index/companyHead.jsp" />
	<div class="_position" style="padding-right: 10px;"	>
		<span>当前位置：修改工作</span>
	</div>
	<form style="text-align: center;" action="${rc.contextPath}/job/update" id="companyForm" method="post">
	<input type="hidden" name="id" value="${po.id}">
	<table class="easyTable">
		<tr><td>名称:</td><td><input required="required" name="name" value="${po.name}" > </td></tr>
		<tr><td>介绍:</td><td><textarea style="width: 500px;" rows="10" cols="10" name="desc" placeholder="工作介绍" >${po.desc}</textarea></td></tr>
		<tr><td>地址:</td><td><input name="address" value="${po.address}" ></td></tr>
		<tr><td>月薪:</td><td><input name="salay" value="${po.salay}" ></td></tr>
		<tr><td colspan="2"><input type="submit" value="提交" > <input type="reset" value="重置"></td></tr>
	</table>
	</form>
	
	<!-- last div can't delete -->
</div>
</body>

</html>