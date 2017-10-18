<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="users" scope="request" type="com.epam.wl.entities.User"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>${users.name}'s profile</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body link=#4483e2 vlink=#09607c alink=#f20e56>
<div class="header">
    <a href="${pageContext.request.contextPath}/"><img src="img/logo.jpg" alt="logo"></a>
    <hr>
</div>
<form action="${pageContext.request.contextPath}/log_out" method="post">
    <div class="sum">
        <input type="submit" value="Log out">
    </div>
</form>
<div align="center">
    <h1>Welcome to your profile, ${users.name}!</h1><br>
    <h3>${users.name} ${users.lastname} <br>e-mail: ${users.email}</h3>
    <hr>
    <div align="center">
        <h4>Your current orders</h4>
        <table border="1">
            <jsp:useBean id="userorders" scope="request" type="java.util.List"/>
            <tr>
                <td><b>Author</b></td>
                <td><b>Title</b></td>
                <td><b>Year</b></td>
                <td><b>Status</b></td>
                <td><b></b></td>
            </tr>
            <c:forEach items="${userorders}" var="userorder">
                <tr>
                    <td>${userorder.book.author}</td>
                    <td> ${userorder.book.title}</td>
                    <td> ${userorder.book.year}</td>
                    <td> ${userorder.status}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/usercancel" method="POST">
                            <input type="hidden" value="${userorder.id}" name="userOrderId">
                            <div class="button">
                                <c:choose><c:when test="${userorder.status eq 'NEW'}">
                                    <input type="submit" value="Cancel order">
                                </c:when><c:otherwise>
                                    <input type="submit" value="Cancel order" disabled>
                                </c:otherwise>
                                </c:choose>
                            </div>
                        </form>
                    </td>
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
