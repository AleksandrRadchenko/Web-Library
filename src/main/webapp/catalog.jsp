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
<div align="center">
<table border="1">
    <form action="${pageContext.request.contextPath}/userorderfromcatalog" method="GET">
        <tr>
            <td><b>User ID </b></td>
            <td><b>Book ID</b></td>
            <td><b></b></td>
            <td><b></b></td>
        </tr>
<jsp:useBean id="identification" scope="request" type="java.util.List"/>
<c:forEach items="${identification}" var="usersid">
        <td>
                ${usersid}
        </td>
</c:forEach>
        <td colspan="1">
            <input name="bookid" type="text">
        </td>
        <td colspan="1">
            <input type="submit">
        </td>
        <td></td>
    </form>
    <tr>
        <td><b>Book ID </b></td>
        <td><b>Author</b></td>
        <td><b>Title</b></td>
        <td><b>Year</b></td>
    </tr>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.author}</td>
            <td>${book.title}</td>
            <td>${book.year}</td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
