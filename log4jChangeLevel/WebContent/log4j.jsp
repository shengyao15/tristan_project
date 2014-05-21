<%@ page import="org.apache.log4j.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>Log4J级别控制</title></head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<body>
<h1>Log4J级别控制</h1>
<% String logName = request.getParameter("log");
    if (null != logName) {
        Logger log = ("".equals(logName) ?
                Logger.getRootLogger() : Logger.getLogger(logName));
        log.setLevel(Level.toLevel(request.getParameter("level"), Level.DEBUG));
    }
%>

<c:set var="rootLogger" value="<%= Logger.getRootLogger() %>"/>
<form>
    <table border="1">
        <tr>
            <th>Level</th>
            <th>Logger</th>
            <th>Set New Level</th>
        </tr>
        <tr>
            <td>${rootLogger.level}</td>
            <td>${rootLogger.name}</td>
            <td>
                <c:forTokens var="level" delims="," items="DEBUG,INFO,WARN,ERROR,OFF">
                    <a href="log4j.jsp?log=&level=${level}">${level}</a>
                </c:forTokens>
            </td>
        </tr>
    </table>
</form>
</body>
</html>