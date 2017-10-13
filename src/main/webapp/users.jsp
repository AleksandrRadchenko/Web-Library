<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<h1>Welcome to your table, User</h1>
<table border="1">
    <tr>
        <td>id </td>
        <td>name</td>
        <td>lastname </td>
        <td>email</td>
        <td>user role</td>
    </tr>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td> ${user.lastname}</td>
            <td> ${user.email}</td>
            <td> ${user.role}</td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/useredit" method="POST">
    <tr>
        <td colspan="1">
            <input name="name" type="text">
        </td>
        <td colspan="1">
            <input name="lastname" type="text">
        </td>
        <td colspan="1">
            <input name="email" type="text">
        </td>
        <td colspan="1">
            <input name="passwordhash" type="text">
        </td>
        <td colspan="1">
            <input name="userrole" type="text">
        </td>
        <td colspan="1">
            <input type="submit">
        </td>
    </tr>
</form>
<a href="http://localhost:8080/b">Welcome to out catalog of books!</a><br>
</body>
</html>
