<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Orders</title>
</head>
<body>
<h1>User order table</h1>
<table border="1">
    <jsp:useBean id="userorders" scope="request" type="java.util.List"/>
    <c:forEach items="${userorders}" var="userorder">
        <tr>
            <td>id: ${userorder.id}</td>
            <td>book id: ${userorder.bookId}</td>
            <td>user id: ${userorder.userId}</td>
            <td>order status: ${userorder.status}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
