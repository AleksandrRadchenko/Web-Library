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
<div align="center">
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <h1>Welcome to your profile, ${users.get(0).name}!</h1>
    <table border="1">
        <tr>
            <td><b>id</b></td>
            <td><b>name</b></td>
            <td><b>lastname</b></td>
            <td><b>email</b></td>
            <td><b>role</b></td>
        </tr>
        <tr>
            <td>${users.get(0).id}</td>
            <td>${users.get(0).name}</td>
            <td>${users.get(0).lastname}</td>
            <td>${users.get(0).email}</td>
            <td>${users.get(0).role}</td>
        </tr>
    </table>
    <hr>
    <div>
        <h4>You can change your parameters here:</h4><br>
        <form action="${pageContext.request.contextPath}/useredit" method="POST">
            <b>Name: </b>
            <input name="name" type="text">
            <b>Lastname: </b>
            <input name="lastname" type="text">
            <b>Email: </b>
            <input name="email" type="text">
            <b>Password: </b>
            <input name="passwordhash" type="text">
            <input type="submit">
        </form>
    </div>

    <hr>
    <br>
    <a href="${pageContext.request.contextPath}/catalog">Welcome to out catalog of books!</a><br>
    <br>
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
</div>
<div class="footer">
    <hr>
    &copy;&nbsp;2017. All rights reserved.
</div>
</body>
</html>
