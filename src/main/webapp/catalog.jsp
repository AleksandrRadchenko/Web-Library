<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalog of books</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
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
    </form>

    <tr>
        <td>id </td>
        <td>author</td>
        <td>title</td>
        <td>year</td>
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
