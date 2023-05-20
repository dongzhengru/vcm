<%@ page import="top.zhengru.vcm.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="top.zhengru.vcm.bean.Competition" %><%--
  Created by IntelliJ IDEA.
  User: 董政儒
  Date: 2023/5/10
  Time: 10:41
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
    <title>微竞赛-新建队伍</title>
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
        body {
            background-color: #f5f5f5;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80vh;
        }

        form {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
            border: none;
            border-bottom: 2px solid #007bff;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .comp_id{
            width: 100%;
            padding: 14px 20px;
            margin: 8px 0;
            border-color: #007bff;
            border-width: 2px;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="sidebar">
    <h3>微竞赛管理系统</h3>
    <a class="current-user">当前登录：<%=user.getName()%></a>
    <hr>
    <a href="/vcm/stucomp" class="menu-item"><i class="fa fa-list-ul"></i> 所有竞赛</a>
    <a href="/vcm/register" class="menu-item" style="color: #007bff"><i class="fa fa-plus"></i> 新建队伍</a>
    <a href="/vcm/teamstatus" class="menu-item"><i class="fa fa-eye"></i> 队伍状态</a>
    <a href="/vcm/invitation" class="menu-item"><i class="fas fa-users"></i> 邀请查询</a>
    <a href="/vcm/material" class="menu-item"><i class="fa fa-upload"></i> 作品上传</a>
    <hr>
    <a href="/vcm/logout" class="menu-item">退出登录</a>
</div>
<div class="content">
    <h3>首页 > 新建队伍</h3>
    <div class="container">
        <form action="register/addteam" method="post">
            <h1 style="text-align: center">新建队伍</h1>
            <label><b>请选择要报名的竞赛</b></label>
            <select name="comp_id" required class="comp_id">
                <c:forEach var="competition" items="${competitionList}">
                    <option value="${competition.id}">${competition.name}</option>
                </c:forEach>
            </select>
            <label><b>队伍名称</b></label>
            <input type="text" placeholder="请输入队伍名称" name="team_name" required>
            <label><b>队员ID</b></label>
            <input type="text" placeholder="请输入队员ID，若有多名队员请以英文逗号隔开" name="stu_id">
            <label><b>指导教师ID</b></label>
            <input type="text" placeholder="请输入教师ID，若有多名教师请以英文逗号隔开" name="teach_id" required>
            <input type="submit" value="新建队伍" style="margin-left: 45%">
        </form>
    </div>
</div>
</body>
</html>
