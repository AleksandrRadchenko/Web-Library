<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/sign_up" method="post">
    Name:
    <input type="text" name="name"/><br>
    Lastname:
    <input type="text" name="lastname"/><br>
    Role:
    <select name="role">
        <option value="user">User</option>
        <option value="librarian">Librarian</option>
    </select><br>
    Email:
    <input type="text" name="email"/><br>
    Password:
    <input type="text" name="password"/><br>
    <!--Please enter password again:
    <input type="text" name="passwordSecondAttempt"/><br>-->
    <input type="submit" value="Sign up">
</form>

</body>
</html>
