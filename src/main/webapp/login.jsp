<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<!--<table border="1">
    <form action="/login" method="POST">
        <tr>
            <td colspan="2">
                <input name="txt" type="text">
            </td>
            <td colspan="2">
                <input name="txt" type="text">
            </td>
            <td colspan="2">
                <input type="submit">
            </td>
        </tr>
    </form>
</table>-->

<form action="/login" method="get">

    Please enter your email:
    <input type="text" name="email"/><br>

    Please enter your password:
    <input type="text" name="password"/>

    <input type="submit" value="Sign in">

</form>

<form action="/sign_up" method="get">
    <input type="submit" value="Sign up">
</form>

</body>
</html>
