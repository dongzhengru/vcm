<%@ page import="top.zhengru.vcm.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: 董政儒
  Date: 2023/5/9
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>微竞赛-用户管理</title>
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
      height: 100vh;
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
  </style>
</head>
<body>
  <div class="sidebar">
    <h3>微竞赛管理系统</h3>
    <a class="current-user">当前登录：<%=user.getName()%></a>
    <hr>
    <a href="/vcm/compmana" class="menu-item"><i class="fas fa-plus-square"></i> 学科竞赛</a>
    <a href="/vcm/teampass" class="menu-item"><i class="fas fa-clipboard-check"></i> 队伍审核</a>
    <a href="/vcm/materialpass" class="menu-item"><i class="fas fa-file-alt"></i> 作品审核</a>
    <a href="/vcm/usermana" class="menu-item" style="color: #007bff"><i class="fas fa-users"></i> 用户管理</a>
    <a href="/vcm/allaward" class="menu-item"><i class="fas fa-users"></i> 获奖情况</a>
    <hr>
    <a href="/vcm/logout" class="menu-item">退出登录</a>
  </div>
  <div class="content">
    <h3>首页 > 用户管理</h3><br>
    <table>
      <thead>
      <tr>
        <th>用户ID</th>
        <th>姓名</th>
        <th>用户名</th>
        <th>联系方式</th>
        <th>角色</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${userList}" var="user" varStatus="status">
        <tr>
          <td>${user.id}</td>
          <td>${user.name}</td>
          <td>${user.username}</td>
          <td>${user.phone}</td>
          <td>${user.role == 1 ? "教师":"学生"}</td>
          <td><a href="usermana/delete?id=${user.id}">删除</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</body>
</html>
