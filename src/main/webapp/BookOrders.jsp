<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book orders</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="header">
    <img src="img/logo.jpg" alt="logo">
    <hr>
</div>
<jsp:useBean id="bookorders" scope="request" type="java.util.List"/>
<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<jsp:useBean id="allusers" scope="request" type="java.util.List"/>

<div align="center">
<form action="${pageContext.request.contextPath}/book_order" method="GET">
    <div class="select">Filter by user:<select name="userid">
        <option disabled selected></option>
        <c:forEach items="${allusers}" var="user">
            <option value=${user.id}>${user.name} ${user.lastname}, ${user.email}</option>
        </c:forEach>
    </select><input type="submit" value="Filter"></div>
</form>

Orders of ${username}:
<table border="1">
    <tr>
        <td><b>Book order id</b></td>
        <td><b>Book instance id</b></td>
        <td><b>Book title</b></td>
        <td><b>Book author</b></td>
        <%--<td><b>Order id</b></td>--%>
        <%--<td><b>Book option</b></td>--%>
        <td></td>
    </tr>
    <c:forEach items="${bookorders}" var="bookorder">
        <tr>
            <td>${bookorder.id}</td>
            <td>${bookorder.bookInstance.id}</td>
            <td>${bookorder.bookInstance.book.title}</td>
            <td>${bookorder.bookInstance.book.author}</td>
            <%--<td>${bookorder.userOrderId}</td>--%>
            <%--<td>${bookorder.bookOption}</td>--%>
            <td>
                <form action="${pageContext.request.contextPath}/close_book_order" method="GET">
                    <input type="hidden" value="${bookorder.id}" name="book_orderid">
                    <div class="button">
                        <input type="submit" value="Close order">
                    </div>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
