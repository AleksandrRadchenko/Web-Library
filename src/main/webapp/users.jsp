<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="users" scope="request" type="java.util.List"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>${users.get(0).name}'s profile</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body link=#4483e2 vlink=#09607c alink=#f20e56>
<div class="header">
    <img src="img/logo.jpg" alt="logo">
    <hr>
</div>
<form action="/log_out" method="post">
    <div class="sum">
        <input type="submit" value="Log out">
    </div>
</form>
<div align="center">
    <br>
    <h1>Welcome to your profile, ${users.get(0).name}!</h1><br><br>
    <h3>${users.get(0).name} ${users.get(0).lastname} <br>e-mail: ${users.get(0).email}</h3>
    <hr>
    <div align="center">
        <h4>Your current orders</h4>
        <table border="1">
            <jsp:useBean id="books" scope="request" type="java.util.List"/>
            <tr>
                <td><b>Author</b></td>
                <td><b>Title</b></td>
                <td><b>Year</b></td>
                <td><b>Status</b></td>
            </tr>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.book.author}</td>
                    <td> ${book.book.title}</td>
                    <td> ${book.book.year}</td>
                    <td> ${book.status}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <hr>
    <a href="${pageContext.request.contextPath}/catalog"><h2>Book catalog>></h2></a><br>
</div>


<h4> Here you can change your parameters:</h4><br>
<div>
    <form action="${pageContext.request.contextPath}/useredit" method="POST">
        <b>Name: </b>
        <input name="name" type="text" required>
        <b>Lastname: </b>
        <input name="lastname" type="text" required>
        <b>Email: </b>
        <input name="email" type="text" required>
        <b>Password: </b>
        <input name="passwordhash" type="password" minlength="6" required>
        <input type="submit" value="Edit info">
    </form>
</div>


<div class="footer">
    <hr>
    &copy;&nbsp;2017. All rights reserved.
</div>
</body>
</html>
