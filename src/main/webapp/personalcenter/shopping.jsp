<%@ page import="model.User" %>
<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; utf-8"
         pageEncoding="utf-8" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#btn-logout").click(function () {
            $.ajax({
                url:encodeURI("/dologout"),
                type:"get",
                success:function () {
                    window.location.href = "/login.jsp";
                },
            });
        });
    });
</script>
<body>
<%
    Object o = session.getAttribute("user");
    if(o != null){
        out.println("当前登录用户是:<font style='color:blue'><b>" + ((User)o).getUsername() + "</b></font>\t<a href='/personalcenter/mycart.jsp' >购物车</a>\t<button onclick='logout()' id='btn-logout'>退出</button><br/>");
    }
%>
<h1>购物界面</h1>

<form method="get" action="doshopping">
    <h3>请在购物篮加入想要购买的东西</h3>
    <input type="text" name="item" />
    <input type="submit" value="加入购物车"/>
</form>
</body>
</html>