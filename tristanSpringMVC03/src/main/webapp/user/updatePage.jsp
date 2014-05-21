<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<script type="text/javascript">

</script>
</head>
<body>
	<h>编辑用户</h>
	<form name="userForm" action="./user/update">
		<input type="hidden" name="id" value="${user.id }">
		姓名：<input type="text" name="userName" value="${user.userName }">
		年龄：<input type="text" name="age" value="${user.age }">
		性别：<input type="text" name="gender" value="${user.gender }">
		<br>
		身高：<input type="text" name="height" value="${user.height }">
		体重：<input type="text" name="weight" value="${user.weight }">
		城市：<input type="text" name="city" value="${user.city }">
		<br>
		特长：<input type="text" name="speciality" value="${user.speciality }">
		兴趣：<input type="text" name="interest" value="${user.interest }">
		<br>
		
		<input type="submit" value="保存" >
		
		
	
	</form>
	
	
	
</body>
</html>