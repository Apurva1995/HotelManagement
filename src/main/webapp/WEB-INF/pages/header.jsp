<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>

<%!
	String contextPath;
	String loginPath;
	String registerPath;
	String cssPath;
	String indexPath;
	String roomDetailsPath;
	String logoutPath;
	String addRoomPath;
	String fetchUserBookings;
	String fetchRoomsAvailableForRemoving;
%>

<%
	contextPath = request.getContextPath();
	loginPath = contextPath + "/login";
	registerPath = contextPath + "/register";
	indexPath = contextPath + "/index";
	cssPath = contextPath + "/styles/layout.css";
	roomDetailsPath = contextPath + "/roomDetails";
	addRoomPath = contextPath + "/addRoom";
	logoutPath = contextPath + "/controller/user/logout";
	fetchUserBookings = contextPath + "/controller/user/bookings";
	fetchRoomsAvailableForRemoving = contextPath + "/controller/room/availableForRemoving";
%>

<html>
<head>
<meta charset="utf-8">
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
		<c:if test="${sessionScope.user == null}">
			<li><a id="login" class="navlink float-right" href=<%= loginPath%>>Login</a></li>
			<li><a id="signup" class="navlink float-right" href=<%= registerPath%>>Sign
					Up</a></li>
		</c:if>
		<c:if test="${not empty sessionScope.user}">
			<c:if test="${sessionScope.user.isAdmin == 1 }">
				<li><a class="navlink" href=<%= addRoomPath%>>Add Rooms</a></li>
				<li><a class="navlink" href=<%= fetchRoomsAvailableForRemoving%>>Remove Rooms</a></li>
			</c:if>
			<li><a class="navlink" href=<%= roomDetailsPath%>>Book Rooms</a></li>
			<li><a class="navlink" href=<%= fetchUserBookings%>>Cancel Bookings</a></li>
			<li class="navlink float-right"><a class="navlink_right" href=<%= logoutPath%>>Logout</a></li>
			<li class="navlink float-right">Welcome : <c:out value="${sessionScope.user.userName }"/> </li>
		</c:if>
	</ul>
	</nav>
</body>
</html>