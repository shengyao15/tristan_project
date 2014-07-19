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
	function search(){
		var form = document.getElementById("myForm");
		form.submit();
	}
</script>

</head>
<body>
	<form:form id="myForm" action="./mongo/search" commandName="student" method="post">
	<h3>大于</h3> 英文: <form:input path="score.english"/>
	数学: <form:input path="score.math"/>
	语文: <form:input path="score.chinese"/>
	<h3>
	<a href="javascript:search()">查询</a></h3>
	</form:form>
	
	
	<table>
		<tbody>
			<tr>
				<th width="50" align="left"></th>
				<th width="200" align="left">学生姓名</th>
				<th width="200" align="left">学生年龄</th>
				<th width="200" align="left">国籍</th>
				<th width="200" align="left">英文</th>
				<th width="200" align="left">数学</th>
				<th width="200" align="left">中文</th>
			</tr>
			
			<tr>
				<th colspan="7"><hr>
				</th>
			</tr>
			<c:forEach items="${list}" var="s">
			<tr>
				<td></td>
			    <td>${s.name }</td>
				<td>${s.age }</td>
				<td>${s.country }</td>
				<td>${s.score.english }</td>
				<td>${s.score.math }</td>
				<td>${s.score.chinese }</td>
			</tr>
			</c:forEach>
			<tr>
				<th colspan="7"><hr>
				</th>
			</tr>
		</tbody>
	</table>
</body>
</html>