<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!String indexPathWhenCancelled;%>
<%
	indexPathWhenCancelled = request.getContextPath() + "/index";
%>

<html>
<head>
<meta charset="UTF-8" http-equiv="refresh"
	content="3; URL=<%=indexPathWhenCancelled%>">
<title>Cancellation success page</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="footer.jsp"%>
</body>
</html>