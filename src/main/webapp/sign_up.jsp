<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
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

<form action="/sign_up" id='send' method='POST'>
    <fieldset>
        <label>Name</label>
        <input type='text' name='name' placeholder='Name' required><span></span>
        <br>
        <label>Last name</label>
        <input type='text' name='last_name' placeholder='Last name' required><span></span>
        <br>
        <label>Role</label>
        <div class="option">
        <input type="radio" name="role" value="user" checked>Lib. User<br>
        </div>
        <div class="option">
        <input type="radio" name="role" value="librarian"> Librarian
        </div>
    </fieldset>
    <fieldset>
        <label>E-mail</label>
        <input type='email' name='email' placeholder='Email' required><span></span>
        <br>
        <label>Password</label>
        <input type='password' name='password' placeholder='Password' minlength="6" required><span></span>
        <br>
        <label>Repeat</label>
        <input type='password' name='password_repeat' placeholder='Repeat' minlength="6" required><span></span>
        <br>
    </fieldset>
    <fieldset>
        <p>Enter captcha</p>
        <img src="https://c22blog.files.wordpress.com/2010/10/input-black.gif">
        <input type='text' name='captcha' placeholder='Captcha' required><span></span>
    </fieldset>
</form>
<div class="sum">
    <input type="submit" value="Sign up" form='send'>
</div>
</body>
</div>
</html>
