<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Orders</title>
</head>
<body>
<h1>Book order table</h1>
<table border="1">
    <jsp:useBean id="bookorders" scope="request" type="java.util.List"/>
    <tr>
        <td>id</td>
        <td>book instance id</td>
        <td>order id</td>
        <td>Book option</td>

    </tr>
    <c:forEach items="${bookorders}" var="bookorder">
        <tr>
            <td>${bookorder.id}</td>
            <td>${bookorder.bookInstanceId}</td>
            <td>${bookorder.orderId}</td>
            <td>${bookorder.bookOption}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
