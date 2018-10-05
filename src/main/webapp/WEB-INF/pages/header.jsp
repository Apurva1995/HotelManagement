<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!
	String contextPath;
	String loginPath;
	String registerPath;
	String cssPath;
	String indexPath;
%>

<%
	contextPath = request.getContextPath();
	loginPath = contextPath + "/login";
	registerPath = contextPath + "/register";
	indexPath = contextPath + "/index";
	cssPath = contextPath + "/styles/layout.css";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header page</title>
<link href=<%= cssPath%> rel="stylesheet" />
</head>
<body bgcolor="cyan">
	<div>
		<span id="headername">Welcome to Hyson International</span>
	</div>
	<nav id="navigation" class="backgroundgradient">
	<ul>
		<li><a class="navlink" href=<%= indexPath%>>Home</a></li>
		<%-- <c:if test="${sessionScope.user == null}"> --%>
			<li><a id="login" class="navlink float-right" href=<%= loginPath%>>Login</a></li>
			<li><a id="signup" class="navlink float-right" href=<%= registerPath%>>Sign
					Up</a></li>
		<%-- </c:if> --%>
		<c:if test="{not empty sessionScope.user}">
			<li class="navlink_right">welcome : ${sessionScope.user.userName }</li>
			<li class="navlink_right"><a class="navlink_right" href="logout">Logout</a></li>
		</c:if>
	</ul>
	</nav>
</body>
</html>