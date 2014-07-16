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
	<form:form id="myForm" action="./student/search" commandName="studentVO" method="post">
	学生号: <form:input path="sno"/>
	学生名: <form:input path="sname"/>
	课程号: <form:input path="cno"/>
	课程名: <form:input path="cname"/>
		<h3>
	<a href="javascript:search()">查询</a></h3>
	</form:form>

	<table>
		<tbody>
			<tr>
				<th width="50" align="left"></th>
				<th width="200" align="left">学生号</th>
				<th width="200" align="left">学生姓名</th>
				<th width="200" align="left">课程号</th>
				<th width="200" align="left">课程名</th>
				<th width="200" align="left">分数</th>
			</tr>
			
			<tr>
				<th colspan="6"><hr>
				</th>
			</tr>
			<c:forEach items="${studentVOList}" var="s">
			<tr>
				<td></td>
			    <td>${s.sno }</td>
				<td>${s.sname }</td>
				<td>${s.cno }</td>
				<td>${s.cname }</td>
				<td>${s.grade }</td>
			</tr>
			</c:forEach>
			<tr>
				<th colspan="6"><hr>
				</th>
			</tr>
		</tbody>
	</table>
</body>
</html>