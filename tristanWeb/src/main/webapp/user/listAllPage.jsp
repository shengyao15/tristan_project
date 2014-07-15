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
	function del(id){
		$.get("./user/delete?id=" + id,function(data){
			alert(data.result);
			if("success" == data.result){
				alert("删除成功!");
				window.location.reload(); 
			}else{
				alert("删除失败!")
			}
		});
	}

	function search(){
		var form = document.getElementById("myForm");
		form.submit();
	}
</script>
</head>
<body>
	<form:form id="myForm" action="./user/search" commandName="user" method="post">
	姓名: <form:input path="userName"/>
	年龄: <input name="age"/>
	性别: <form:input path="gender"/>
	特长: <form:input path="speciality"/>
	
	<h3>
	<a href="javascript:search()">查询</a>&nbsp;&nbsp;&nbsp;<a href="./user/find">新增</a></h3>
	</form:form>

	<table>
		<tbody>
			<tr>
				<th width="50" align="left"></th>
				<th width="200" align="left">姓名</th>
				<th width="200" align="left">年龄</th>
				<th width="200" align="left">性别</th>
				<th width="200" align="left">身高</th>
				<th width="200" align="left">体重</th>
				<th width="200" align="left">城市</th>
				<th width="200" align="left">特长</th>
				<th width="200" align="left">兴趣</th>
				
				<th width="200" align="left"></th>
			</tr>
			<tr>
				<th colspan="10"><hr>
				</th>
			</tr>
			<c:if test="${!empty userList }">
			<c:forEach items="${userList }" var="u">
			<tr>
				<td></td>
				<td>${u.userName }</td>
				<td>${u.age }</td>
				<td>${u.gender }</td>
				<td>${u.height }</td>
				<td>${u.weight }</td>
				<td>${u.city }</td>
				<td>${u.speciality }</td>
				<td>${u.interest }</td>
				
				<td>
					<a href="./user/find?id=${u.id }">修改</a>
					<a href="javascript:del('${u.id }')">删除</a>
				</td>
			</tr>
			</c:forEach>
			</c:if>
			<tr>
				<th colspan="10"><hr>
				</th>
			</tr>
		</tbody>
	</table>
</body>
</html>