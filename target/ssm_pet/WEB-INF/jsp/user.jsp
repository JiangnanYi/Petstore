<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mr.黎
  Date: 2018/11/5
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户</title>
</head>
<style>
    table.gridtable {
        font-family: verdana, arial, sans-serif;
        color: #333333;
        border-width: 1px;
        border-color: #666666;
        border-collapse: collapse;
    }

    table.gridtable th {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #dedede;
    }

    table.gridtable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #ffffff;
    }
</style>
<body>
<table class="gridtable">
    <tr>
        <th>编号</th>
        <th>用户名</th>
        <th>姓</th>
        <th>名字</th>
        <th>邮箱</th>
        <th>密码</th>
        <th>电话</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <c:forEach var="u" items="${user}">
    <tr>
        <td> ${u.uId} </td>
        <td> ${u.userName} </td>
        <td> ${u.firstName} </td>
        <td> ${u.lastName} </td>
        <td>${u.email}</td>
        <td>${u.password}</td>
        <td>${u.phone}</td>
        <td>${u.userStatus}</td>
        <td><a href="/pet/del/${u.uId}">删除</a></td>
        </c:forEach>
    </tr>
    <a href="/user/userinser">添加</a>
</table>
</body>
</html>
