<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Orders</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="header">
    <img src="img/logo.jpg" alt="logo">
    <hr>
</div>
<div align="center">
    <h3>User order table</h3>
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
        <jsp:useBean id="userorders" scope="request" type="java.util.List"/>
        <c:forEach items="${userorders}" var="userorder">
            <tr>
                <td>${userorder.id}</td>
                <td>${userorder.userId}</td>
                <td>${userorder.userName} ${userorder.userLastname}</td>
                <td>${userorder.userEmail}</td>
                <td>${userorder.bookTitle}</td>
                <td>${userorder.bookAuthor}</td>
                <td>${userorder.bookYear}</td>
                <td>${userorder.status}</td>
                <form action="${pageContext.request.contextPath}/makebookorder" method="GET">
                    <td>
                        <input type="hidden" value="${userorder.userId}" name="userId">
                        <div class="select"><select name="exemlarNum">
                            <option disabled selected>select exemplar</option>
                            <option value="1"># 1</option>
                            <option value="2"># 2</option>
                            <option value="3"># 3</option>
                            <option value="4"># 4</option>
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
