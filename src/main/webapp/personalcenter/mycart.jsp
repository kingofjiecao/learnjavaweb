<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page import="model.User" %>
<%@ page language="java" contentType="text/html; utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                url:encodeURI(encodeURI("/dolist")),
                type:"get",
                dataType:"json",
                success:function (list) {
                    $.each(list, function (i, val) {
                        var html = "<tr><td>" + val.name + "</td><td>" +val.count + "</td><td><a href='#'>修改</a> <a href='#'>删除</a></td></tr>";
                        $("table").append(html);
                    });
                }
            });
        });
    </script>
    <style>
        table,th,td
        {
            border:1px solid black;
        }
    </style>
</head>
<body>
<%
    Object o = session.getAttribute("user");
    if(o != null){
        out.println("当前登录用户是:<font style='color:blue'><b>" + ((User)o).getUsername() + "</b></font>\t<a href='/personalcenter/mycart.jsp' >购物车</a>\t<button onclick='logout()' id='btn-logout'>退出</button><br/>");
    }
%>
<h1>我的购物车</h1>
<table>
    <tr>
        <th>名字</th>
        <th>数量</th>
        <th>操作</th>
    </tr>
</table>
</body>
</html>