<%--
  Created by IntelliJ IDEA.
  User: kingofjiecao
  Date: 2019/7/24
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Object o = request.getAttribute("message");
    if (o != null){
        out.println((String)o);
    }
%>
<h1>注册页面</h1>
<hr/>
<form method="post" action="/doregister">
    <kbd>账号：</kbd><input type="text" name="username"/><br/>
    <kbd>密码：</kbd><input type="password" name="password"/><br/>
    <kbd>确认密码：</kbd><input type="password" name="confirmPassword"/><br/>
    <input type="submit"/>
</form>
</body>
</html>
