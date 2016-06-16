<%--
  Created by IntelliJ IDEA.
  User: wudan-mac
  Date: 16/5/27
  Time: 下午5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>400</title>
</head>
<body>
    参数绑定异常</br>
    <%=request.getAttribute("msg") == null ?"":(String)request.getAttribute("msg")%>
</body>
</html>
