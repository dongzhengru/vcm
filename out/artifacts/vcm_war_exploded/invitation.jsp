<%@ page import="top.zhengru.vcm.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: 董政儒
  Date: 2023/5/11
  Time: 11:09
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
    <title>微竞赛-邀请查询</title>
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
    <a href="/vcm/stucomp" class="menu-item"><i class="fa fa-list-ul"></i> 所有竞赛</a>
    <a href="/vcm/register" class="menu-item"><i class="fa fa-plus"></i> 新建队伍</a>
    <a href="/vcm/teamstatus" class="menu-item"><i class="fa fa-eye"></i> 队伍状态</a>
    <a href="/vcm/invitation" class="menu-item" style="color: #007bff"><i class="fas fa-users"></i> 邀请查询</a>
    <a href="/vcm/material" class="menu-item"><i class="fa fa-upload"></i> 作品上传</a>
    <hr>
    <a href="/vcm/logout" class="menu-item">退出登录</a>
</div>
<div class="content">
    <h3>首页 > 邀请查询</h3>
    <h1 style="text-align: center">收到的组队邀请</h1>
    <table>
        <thead>
        <tr>
            <th>邀请ID</th>
            <th>竞赛名称</th>
            <th>队伍名称</th>
            <th>队长姓名</th>
            <th>队员一</th>
            <th>队员二</th>
            <th>队员三</th>
            <th>队员四</th>
            <th>队员五</th>
            <th>指导老师</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${invitations}" var="invitation" varStatus="status">
            <tr>
                <td>${invitation.id}</td>
                <td>${invitation.team.cname}</td>
                <td>${invitation.team.teamName}</td>
                <td>${invitation.team.captainName}</td>
                <td>${invitation.team.members[0].name == null ? "无":invitation.team.members[0].name}</td>
                <td>${invitation.team.members[1].name == null ? "无":invitation.team.members[1].name}</td>
                <td>${invitation.team.members[2].name == null ? "无":invitation.team.members[2].name}</td>
                <td>${invitation.team.members[3].name == null ? "无":invitation.team.members[3].name}</td>
                <td>${invitation.team.members[4].name == null ? "无":invitation.team.members[4].name}</td>
                <td>
                        ${invitation.team.teachers[0].name == null ? "无":invitation.team.teachers[0].name}
                        ${invitation.team.teachers[1].name == null ? "":","}
                        ${invitation.team.teachers[1].name == null ? "":invitation.team.teachers[1].name}
                        ${invitation.team.teachers[2].name == null ? "":","}
                        ${invitation.team.teachers[2].name == null ? "":invitation.team.teachers[2].name}
                </td>
                <td><a href="invitation/accept?iid=${invitation.id}&tid=${invitation.team.tid}&cid=${invitation.team.cid}">同意</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
