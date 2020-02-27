<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: xiangxinli
  Date: 2020/2/26
  Time: 11:15 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

hello world
<br/>
<br>
time: ${requestScope.time}
<br>
names: ${requestScope.names}
<br>
request user: ${requestScope.user}
<br>
session user: ${sessionScope.user}
<br>
request school: ${requestScope.school}
<br>
session school: ${sessionScope.school}
<br>

<fmt:message key="i18n.username"></fmt:message>
<br><br>
<fmt:message key="i18n.password"></fmt:message>
</body>

</html>
