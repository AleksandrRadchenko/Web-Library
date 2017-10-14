<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body link=#4483e2 vlink=#09607c alink=#f20e56>
<div class="header">
    <img src="img/logo.jpg" alt="logo">
    <hr>
</div>
<h1>Welcome to your profile, User</h1>
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
<hr>
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
            <input type="submit">
        </td>
    </tr>
</form>
<hr>
<a href="http://localhost:8080/catalog">Welcome to out catalog of books!</a><br>
<table border="1">
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.book.author}</td>
            <td> ${book.book.title}</td>
            <td> ${book.book.year}</td>
            <td> ${book.status}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
