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
<h3>Orders of ${bookorders.get(0).name} ${bookorders.get(0).lastName}</h3>
<table border="1">
    <tr>
        <td><b>Book order id</b></td>
        <td><b>Book instance id</b></td>
        <td><b>Book title</b></td>
        <td><b>Book author</b></td>
        <td><b>User order id</b></td>
        <td><b>Book option</b></td>
        <td><b>Order status</b></td>
        <td></td>
    </tr>
    <c:forEach items="${bookorders}" var="bookorder">
        <tr>
            <td>${bookorder.id}</td>
            <td>${bookorder.bookInstanceId}</td>
            <td>${bookorder.title}</td>
            <td>${bookorder.author}</td>
            <td>${bookorder.userOrderId}</td>
            <td>${bookorder.bookOption}</td>
            <td>${bookorder.status}</td>
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
</body>
</html>
