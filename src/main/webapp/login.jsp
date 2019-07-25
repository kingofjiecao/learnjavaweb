<%@ page language="java" contentType="text/html; utf-8"
        pageEncoding="utf-8" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
<h1>登录界面</h1>
<%
    Object o = request.getAttribute("message");
    if (o != null){
        out.println((String)o);
    }
%>
<form method="post" action="/dologin">
    <kbd>账号：</kbd><input type="text" name="username"/><br/>
    <kbd>密码：</kbd><input type="password" name="password"/><br/>
    <input type="submit"/>
</form>
没有账号？点击这里<a href="/register.jsp">注册</a>
</body>
</html>