<%--
  Created by IntelliJ IDEA.
  User: 董政儒
  Date: 2023/5/9
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>微竞赛-登录</title>
    <style>
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
<div class="container">
    <form action="login" method="post">
        <h1 style="text-align: center">欢迎使用微竞赛管理系统</h1>
        <label><b>用户名</b></label>
        <input type="text" placeholder="请输入用户名" name="username" required>
        <label><b>密码</b></label>
        <input type="password" placeholder="请输入密码" name="password" required>
        <input type="submit" value="登录" style="margin-left: 45%">
    </form>
</div>
</body>
