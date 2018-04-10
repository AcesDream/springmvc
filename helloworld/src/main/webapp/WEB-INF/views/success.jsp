<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2018/4/8
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>HelloWord</title>
</head>
<body>
spring mvc hello world
time：${requestScope.time}
name：${requestScope.name}
request user：${requestScope.user}
session user：${sessionScope.user}

request home：${requestScope.home}
session home：${sessionScope.home}

request score：${requestScope.score}
session score：${sessionScope.score}


<fmt:message key="i18n.username"></fmt:message>
<fmt:message key="i18n.password"></fmt:message>
</body>
</html>
