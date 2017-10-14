<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Orders</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body link=#4483e2 vlink=#09607c alink=#f20e56>
<div class="header">
    <img src="img/logo.jpg" alt="logo">
    <hr>
</div>
<form>
    <input type="button" value="!Make an order!" onClick='location.href="http://localhost:8080/u"'>
</form>
<table border="1">
    <form action="${pageContext.request.contextPath}/book_order" method="POST">
        <td colspan="1">
            <input name="userrole" type="text">
        </td>
        <td colspan="1">
            <input type="submit">
        </td>
        <hr>
    </form>

    <tr>
        <td>Book ID </td>
        <td>Author</td>
        <td>Title</td>
        <td>Year</td>
        <td></td>
    </tr>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.author}</td>
            <td>${book.title}</td>
            <td>${book.year}</td>
            <td>
                <form>
                <input type="button" value="!Make an order!" onClick='location.href="http://localhost:8080/u"'>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
