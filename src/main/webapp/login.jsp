<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<style>
    <%@include file='sign_up.css' %>
</style>

<body link=#4483e2 vlink=#09607c alink=#f20e56>
<div class="header">
    <img src="img/logo.jpg" alt="logo">
    <hr>
</div>
<div align="center">

<form action="/login" method="post">
    <fieldset>
        <label>E-mail</label>
        <input type="email" name="email" required/><span></span>
        <br>
        <label>Password</label>
        <input type="password" name="password" minlength="6" required/><span></span>
        <br>
        <div class="sumsum">
            <input type="submit" value="Sign in">
        </div>
    </fieldset>
</form>

<form action="/redir" method="post">
    <div class="sum">
        <input type="submit" value="Sign up">
    </div>
</form>
</div>
</div>
<div class="footer">
    <hr>
    &copy;&nbsp;2017. All rights reserved.
</div>
</body>
</html>
