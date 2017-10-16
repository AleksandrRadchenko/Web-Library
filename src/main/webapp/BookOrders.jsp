<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<jsp:useBean id="bookorders" scope="request" type="java.util.List"/>
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
<h3>Orders of ${bookorders.get(0).userOrder.user.name} ${bookorders.get(0).userOrder.user.lastName}</h3>
<table border="1">
    <tr>
        <td><b>Book order id</b></td>
        <td><b>Book instance id</b></td>
        <td><b>Book title</b></td>
        <td><b>Book author</b></td>
        <%--<td><b>Order id</b></td>--%>
        <%--<td><b>Book option</b></td>--%>
    </tr>
    <c:forEach items="${bookorders}" var="bookorder">
        <tr>
            <td>${bookorder.id}</td>
            <td>${bookorder.bookInstance.id}</td>
            <td>${bookorder.bookInstance.book.title}</td>
            <td>${bookorder.bookInstance.book.author}</td>
            <%--<td>${bookorder.userOrderId}</td>--%>
            <%--<td>${bookorder.bookOption}</td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
