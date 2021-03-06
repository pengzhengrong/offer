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
	<jsp:include page="/ui/views/index/head.jsp" />
<div>
	<div class="_position" style="float:right;margin-right: 50px;"	>
		<span>当前位置：填写个人信息</span>
	</div>
	
	<div style="float:left;margin-left: 250px;">
		<form action="${rc.contextPath}/person/save" id="personForm" method="post">
			<table class="easyTable">
			<tr><td>姓名:</td><td><input required="required" name="name" > </td></tr>
			<tr><td>年龄:</td><td><input name="age" ></td></tr>
			<tr><td>性别:</td><td>
				<select name="sex">
					<option value="0">保密</option>
					<option value="1">男</option>
					<option value="2">女</option>
				</select>
			</td></tr>
			<tr><td>地址:</td><td><input name="address" ></td></tr>
			<tr><td>qq:</td><td><input name="qq" ></td></tr>
			<tr><td>邮箱:</td><td><input type="email"  name="mail" ></td></tr>
			<tr><td>期望月薪:</td><td><input name="hopeSalay" ></td></tr>
			<tr><td>联系方式:</td><td><input type="tel" name="tel" ></td></tr>
			<tr><td>关键字:</td><td><input required="required" name="keyValue" ></td></tr>
			<tr><td colspan="2"><input type="submit" value="提交" > <input type="reset" value="重置"></td></tr>
		</table>
		</form>
	</div>
	<div class="_center" style="padding-left:30px;">
		<input form="personForm" name="projectName" placeholder="项目名称" value="" ><br><br>
		<textarea style="width: 500px;" name="projectDesc" form="personForm" rows="10" cols="10" placeholder="请描述您的项目" >${po.projectDesc}</textarea>
		<br><br>
		<input type="hidden" form="personForm" name="thumb" value="" >
			<form  id="thumbForm" method="post" action="/offer/person/upload"  enctype="multipart/form-data">
		<!-- 	<input type="file" name="filename" multiple="multiple" />  -->
			<input id="upload" type="submit"  value="开始上传" /> <!-- onclick="doSubmit()" -->
		</form>
		<br>
		<img alt="" src="${rc.contextPath}/ui/static/images/default.png">
	</div>
	
</div>
	<!-- last div can't delete -->
</div>
</body>

</html>