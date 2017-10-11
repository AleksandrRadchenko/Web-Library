<%@ page import="com.epam.wl.services.BookOrderService" %><%--
  Created by IntelliJ IDEA.
  User: ara
  Date: 11.10.2017
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello webapp</title>
</head>
<body>
Number of BookOrders: <%String.valueOf(BookOrderService.getAll().size());%>!
</body>
</html>
