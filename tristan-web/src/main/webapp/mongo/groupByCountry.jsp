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


</head>
<body>

	<table>
		<tbody>
			<tr>
				<th width="50" align="left"></th>
				<th width="200" align="left">国家</th>
				<th width="200" align="left">英文(平均分)</th>
				<th width="200" align="left">数学(平均分)</th>
				<th width="200" align="left">中文(平均分)</th>
			</tr>
			
			<tr>
				<th colspan="7"><hr>
				</th>
			</tr>
			<c:forEach items="${list}" var="s">
			<tr>
				<td></td>
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