<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Mr.黎
  Date: 2018/11/4
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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


    .txt{
        width:90px;
    }
    #div1{
        float: left;
        width: 210px;
        height: 200px;
    }
    #div2{
        width: 540px;
        height: 200px;
    }
</style>
<body>

<form action="" method="get" name="pay" enctype="multipart/form-data">

    <select name="status" id="petsel">
        <option>---请选择---</option>
        <option value="placed">placed</option>
        <option value="approved">approved</option>
        <option value="delivered">delivered</option>
    </select>
    <button onclick="wei()"/>状态</button>
    <input type="text" name="pid" id="ab">
    <button onclick="zhi()"/>按ID</button>
    <br><br>
    <table class="gridtable">
        <tr>
            <th>编号</th>
            <th>类别</th>
            <th>姓名</th>
            <th>描述</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach var="p" items="${pets}">
        <tr>
            <td> ${p.pId} </td>
            <c:forEach items="${categorys}" var="c">
                <c:if test="${p.cId == c.cId}">
                    <td> ${c.cName} </td>
                </c:if>
            </c:forEach>
            <td> ${p.pName} </td>
            <c:forEach items="${tags}" var="t">
                <c:if test="${p.tId == t.tId}">
                    <td> ${t.tName} </td>
                </c:if>
            </c:forEach>
            <td>${p.status}</td>
            <td><a href="/pet/del/${p.pId}">删除</a></td>
            </c:forEach>
        </tr>
    </table>
</form>

<div id="div1">
    <h3>添加</h3>
<form action="/pet/add" method="post">

    <input type="text" name="pName" placeholder="宠物" class="txt"/>

    <select name="cId">
        <option>---请选择---</option>
        <c:forEach items="${categorys}" var="c">
            <option value="${c.cId}">${c.cName}</option>
        </c:forEach>
    </select>
<br><br>
    <select name="tId">
        <option>---请选择---</option>
        <c:forEach items="${tags}" var="t">
            <option value="${t.tId}">${t.tName}</option>
        </c:forEach>
    </select>
    <select name="status">
        <option>---请选择---</option>
        <option value="placed">placed</option>
        <option value="approved">approved</option>
        <option value="delivered">delivered</option>
    </select>
    <br><br>
    <input type="submit"/>
</form>
</div>

<div id="div2">
    <h3>修改</h3>
<form action="/pet/update" method="post">
    <input type="text" name="pId" placeholder="编号" class="txt"/>
    <input type="text" name="pName" placeholder="宠物" class="txt"/>
    <select name="cId">
        <option>---请选择---</option>
        <c:forEach items="${categorys}" var="c">
            <option value="${c.cId}">${c.cName}</option>
        </c:forEach>
    </select>
    <br><br>
    <select name="tId">
        <option>---请选择---</option>
        <c:forEach items="${tags}" var="t">
            <option value="${t.tId}">${t.tName}</option>
        </c:forEach>
    </select>
    <select name="status">
        <option>---请选择---</option>
        <option value="placed">placed</option>
        <option value="approved">approved</option>
        <option value="delivered">delivered</option>
    </select>
    <br><br>
    <input type="submit"/>
</form>
</div>
<form:form action="/pet/photo" method="post" enctype="multipart/form-data">
    <input type="text" name="pid" />
    <input type="file" name="multipartFile" />
    <input type="submit"/>
</form:form>
<script>
    function wei(){
//        document.("表单的name值").action
//        document.("表单的name值").submit
        document.pay.action="/pet/petsta";
        document.pay.submit();
    }
    function zhi() {
        document.pay.action = "/pet/ptd";
        document.pay.submit();
    }
</script>
</body>
</html>
