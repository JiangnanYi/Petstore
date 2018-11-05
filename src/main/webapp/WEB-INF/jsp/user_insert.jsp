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
    <title>用户创建</title>
</head>
<body>
<form method="post" action="/user/insertuser">
    <input type="text" name="userName" placeholder="用户名">
    <input type="text" name="firstName" placeholder="性">
    <input type="text" name="lastName" placeholder="名字">
    <input type="text" name="email" placeholder="邮箱">
    <input type="password" name="password" placeholder="密码">
    <input type="text" name="phone" placeholder="电话">
    <select name="userStatus">
        <option>---请选择---</option>
        <option value="0">可用</option>
        <option value="1">不可用</option>
    </select>
    <input type="submit" >
</form>
</body>
</html>
