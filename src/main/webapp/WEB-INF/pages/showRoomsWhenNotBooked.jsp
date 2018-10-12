<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!String showRoomsPathWhenNotBooked;
%>
<%showRoomsPathWhenNotBooked = request.getContextPath() + "/showRooms";
%>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3 URL=<%= showRoomsPathWhenNotBooked%>">
<title>Show page when room not booked</title>
</head>
<body>
<%@include file="header.jsp" %>

<%@include file="footer.jsp" %>
</body>
</html>