<%@ page import="top.zhengru.vcm.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: 董政儒
  Date: 2023/5/9
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp"%>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>微竞赛管理系统</title>
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
        .homeWelcome{
            text-align: center;
            font-family: 华文行楷;
            padding-top: 120px;
        }
    </style>
</head>
<body>
<div class="sidebar">
    <h3>微竞赛管理系统</h3>
    <a class="current-user">当前登录：<%=user.getName()%></a>
    <hr>
    <a href="/vcm/stucomp" class="menu-item"><i class="fa fa-list-ul"></i> 所有竞赛</a>
    <a href="/vcm/register" class="menu-item"><i class="fa fa-plus"></i> 新建队伍</a>
    <a href="/vcm/teamstatus" class="menu-item"><i class="fa fa-eye"></i> 队伍状态</a>
    <a href="/vcm/invitation" class="menu-item"><i class="fas fa-users"></i> 邀请查询</a>
    <a href="/vcm/material" class="menu-item"><i class="fa fa-upload"></i> 作品上传</a>
    <hr>
    <a href="/vcm/logout" class="menu-item">退出登录</a>
</div>
<div class="content">
    <div class="homeWelcome">
        <h1>欢迎使用微竞赛管理系统!</h1>
        <h2>当前权限：学生</h2>
    </div>
</div>
</body>
</html>