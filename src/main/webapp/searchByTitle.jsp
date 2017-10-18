<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:useBean id="titleName" scope="request" type="java.lang.String"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>"${titleName}" search results</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body link=#4483e2 vlink=#09607c alink=#f20e56>
<div class="header">
    <a href="${pageContext.request.contextPath}/"><img src="img/logo.jpg" alt="logo"></a>
    <hr>
</div>
<div align="center">
    <h2>"${titleName}" search results</h2>
    <table border="1">
        <tr>
            <td><b>Author</b></td>
            <td><b>Title</b></td>
            <td><b>Year</b></td>
            <td></td>
        </tr>
        <jsp:useBean id="books" scope="request" type="java.util.List"/>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.author}</td>
                <td>${book.title}</td>
                <td>${book.year}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/userorderfromcatalog" method="POST">
                        <input type="hidden" value="${book.id}" name="bookId">
                        <div class="button">
                            <input type="submit" value="Order">
                        </div>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h2><a href="${pageContext.request.contextPath}/catalog">&lt;&lt;To all books</a></h2>
</div>
</body>
</div>
<div class="footer">
    <hr>
    &copy;&nbsp;2017. All rights reserved.
</div>
</html>
