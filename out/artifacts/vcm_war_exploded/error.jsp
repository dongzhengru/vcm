<%@ page import="top.zhengru.vcm.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: 董政儒
  Date: 2023/5/9
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true"%>
<%@ page errorPage="error.jsp"%>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>微竞赛-错误</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
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
    </style>
</head>
<body>
<div class="sidebar">
    <h3>微竞赛管理系统</h3>
    <a class="current-user">当前登录：<%=user.getName()%></a>
    <hr>
    <a href="/vcm/logout" class="menu-item">退出登录</a>
</div>
<div class="content">
    <h1 align="center" style="margin-top: 20%">操作失败，<a href="javascript:void(0)" onclick="window.history.back()">返回</a></h1>
    <h1 align="center">${message}</h1>
</div>
</body>
</html>
