<%@ page import="top.zhengru.vcm.bean.User" %>
<%@ page import="top.zhengru.vcm.bean.Competition" %><%--
  Created by IntelliJ IDEA.
  User: 董政儒
  Date: 2023/5/9
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>微竞赛-学科竞赛</title>
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.0.1/js/bootstrap.min.js"></script>
    <style>
        .sidebar {
            background-color: #f8f9fa;
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            width: 250px;
            padding: 20px;
            border-right: 1px solid #dee2e6;
        }
        .content {
            margin-left: 250px;
            padding: 20px;
        }
        .menu-item {
            display: block;
            color: #212529;
            font-size: 16px;
            font-weight: bold;
            text-decoration: none;
            margin-bottom: 10px;
        }
        .current-user {
            display: block;
            color: #212529;
            font-size: 16px;
            text-decoration: none;
            margin-top: 10px;
            margin-bottom: 10px;
        }
        .menu-item:hover {
            color: #007bff;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            text-align: center;
            padding: 8px;
        }
        th {
            background-color: #68b96b;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .add-item {
            display: block;
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="sidebar">
    <h3>微竞赛管理系统</h3>
    <a class="current-user">当前登录：<%=user.getName()%></a>
    <hr>
    <a href="/vcm/compmana" class="menu-item" style="color: #007bff"><i class="fas fa-plus-square"></i> 学科竞赛</a>
    <a href="/vcm/teampass" class="menu-item"><i class="fas fa-clipboard-check"></i> 队伍审核</a>
    <a href="/vcm/materialpass" class="menu-item"><i class="fas fa-file-alt"></i> 作品审核</a>
    <a href="/vcm/usermana" class="menu-item"><i class="fas fa-users"></i> 用户管理</a>
    <a href="/vcm/allaward" class="menu-item"><i class="fas fa-users"></i> 获奖情况</a>
    <hr>
    <a href="/vcm/logout" class="menu-item">退出登录</a>
</div>
<div class="content">
    <h3>首页 > 学科竞赛</h3><br>
    <h5><a href="addcomp.jsp" class="add-item">添加竞赛</a></h5>
    <hr>
    <table>
        <thead>
        <tr>
            <th>竞赛ID</th>
            <th>竞赛名称</th>
            <th>指导教师数量</th>
            <th>学生人数限制</th>
            <th>最大指导队伍数量</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${competitionList}" var="competition" varStatus="status">
            <tr>
                <td>${competition.id}</td>
                <td>${competition.name}</td>
                <td>${competition.teacherNum}</td>
                <td>${competition.studentNum}</td>
                <td>${competition.teachMax}</td>
                <td><a href="/vcm/compmana/edit">修改</a> | <a href="/vcm/compmana/delete?id=${competition.id}">删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
