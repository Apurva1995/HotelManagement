<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!String indexPathWhenBooked; %>
<%indexPathWhenBooked = request.getContextPath() + "/index"; %>

<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3; URL=<%= indexPathWhenBooked%>">
<meta >
<title>Page Displayed after room is successfully booked</title>
</head>
<body>
<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
</body>
</html>