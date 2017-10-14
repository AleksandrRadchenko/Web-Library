<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <a href="http://localhost:8080/book_order"><h2>Current book orders>></h2></a>
    <hr>
    <h2>Current user orders</h2>
    <table>
        <tr>
            <td><b>
                <center>№</center>
            </b></td>
            <td><b>
                <center>User<br>ID</center>
            </b></td>
            <td><b>
                <center>Name</center>
            </b></td>
            <td><b>
                <center>Email</center>
            </b></td>
            <td><b>
                <center>Title</center>
            </b></td>
            <td><b>
                <center>Author</center>
            </b></td>
            <td><b>
                <center>Year</center>
            </b></td>
            <td><b>
                <center>Status</center>
            </b></td>
            <td><b>
                <center>Available<br>exemplars</center>
            </b></td>
            <td><b>
                <center>Subcription/<br>Reading room</center>
            </b></td>
            <td></td>
        </tr>
        <jsp:useBean id="userOrderMap" scope="request" type="java.util.Map"/>
        <c:forEach items="${userOrderMap}" var="order">
            <tr>
                <td>${order.key.id}</td>
                <td>${order.key.userId}</td>
                <td>${order.key.userName} ${order.key.userLastname}</td>
                <td>${order.key.userEmail}</td>
                <td>${order.key.bookTitle}</td>
                <td>${order.key.bookAuthor}</td>
                <td>${order.key.bookYear}</td>
                <td>${order.key.status}</td>
                <form action="${pageContext.request.contextPath}/makebookorder" method="GET">
                    <td>
                        <input type="hidden" value="${order.key.userId}" name="userId">
                        <div class="select"><select name="bookInstanceId">
                            <option disabled selected>select exemplar</option>
                            <c:forEach items="${order.value}" var="bookInstance">
                                <option value="${bookInstance}">${bookInstance}</option>
                            </c:forEach>
                        </select></div>
                    </td>
                    <td>
                        <div class="select"><select name="bookOption">
                            <option value="reading">Reading room</option>
                            <option value="subscription">Subcription</option>
                        </select></div>
                    </td>
                    <td>
                        <div class="button">
                            <input type="submit" value="OK">
                        </div>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="footer">
    <hr>
    &copy;&nbsp;2017. Полное или частичное копирование информации с&nbsp;этого сайта запрещено.
</div>
</body>
</html>
