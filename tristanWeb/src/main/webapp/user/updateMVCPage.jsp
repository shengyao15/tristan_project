<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
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
	<form:form action="./user/update" commandName="user">
		<form:input type="hidden" path="id"/>
		姓名：<form:input type="text" path="userName"/>
		年龄：<form:input type="text" path="age" />
		性别：<form:input type="text" path="gender"/>
		<br>
		身高：<form:input type="text" path="height"/>
		体重：<form:input type="text" path="weight" />
		城市：<form:input type="text" path="city"/>
		<br>
		特长：<form:input type="text" path="speciality"/>
		兴趣：<form:input type="text" path="interest" />
		<br>
		<input type="submit" value="保存" >
	</form:form>  
	
	
	
</body>
</html>