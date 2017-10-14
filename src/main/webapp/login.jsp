<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<style>
    <%@include file='sign_up.css' %>
</style>

<body>

<form action="/login" method="post">
    <fieldset>
        <label>E-mail</label>
        <input type="email" name="email" required/><span></span>
        <br>
        <label>Password</label>
        <input type="text" name="password" required/><span></span>
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

</body>
</html>
