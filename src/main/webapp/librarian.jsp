<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Librarian's page</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body link=#4483e2 vlink=#09607c alink=#f20e56>
<div class="header">
    <img src="img/logo.jpg" alt="logo">
    <hr>
</div>
<form action="${pageContext.request.contextPath}/log_out" method="post">
    <div class="sum">
        <input type="submit" value="Log out">
    </div>
</form>
<div align="center">
    <a href="${pageContext.request.contextPath}/book_order"><h2>Current book orders>></h2></a>
    <hr>
    <h2>New user orders</h2>
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
                <td>${order.key.user.id}</td>
                <td>${order.key.user.name} ${order.key.user.lastname}</td>
                <td>${order.key.user.email}</td>
                <td>${order.key.book.title}</td>
                <td>${order.key.book.author}</td>
                <td>${order.key.book.year}</td>
                <c:choose>
                    <c:when test="${!empty order.value}">
                        <form action="${pageContext.request.contextPath}/makeBookOrder" method="POST">
                            <td>
                                <input type="hidden" value="${order.key.id}" name="userOrderId">
                                <div class="select" align="center"><select name="bookInstanceId">
                                    <c:forEach items="${order.value}" var="bookInstance">
                                        <option value="${bookInstance}">${bookInstance}</option>
                                    </c:forEach>
                                </select></div>
                            </td>
                            <td>
                                <div class="select"><select name="bookOption">
                                    <option value="READING_ROOM">Reading room</option>
                                    <option value="SUBSCRIPTION">Subcription</option>
                                </select></div>
                            </td>
                            <td>
                                <div class="button">
                                    <input type="submit" value="OK">
                                </div>
                            </td>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <td><center>No free<br>exemplars</center></td>
                        <td></td>
                        <td></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="footer">
    <hr>
    &copy;&nbsp;2017. All rights reserved.
</div>
</body>
</html>
