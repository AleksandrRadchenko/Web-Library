<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="/login" method="post">

    Please enter your email:
    <input type="text" name="email"/><br>

    Please enter your password:
    <input type="text" name="password"/><br>

    <input type="checkbox" name="is_librarian"/> Librarian

    <input type="submit" value="Sign in">

</form>

</body>
</html>
