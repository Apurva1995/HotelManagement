<%@page import="choubey.apurva.hotel.model.Room"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>

<%! String bookRoomControllerPath;
	int i = 0;
	ArrayList rooms;
%>

<%
	bookRoomControllerPath 	= request.getContextPath() + "/controller/user/book";
	rooms = (ArrayList<Room>) session.getAttribute("rooms");
%>

<%!
	private String flushCounter() {
		
		i = i%rooms.size();
		return "";
	}
%>

<html>
<head>
<meta charset="utf-8">
<title>See Room Details Page</title>
</head>
<body bgcolor="cyan">
	<%@include file="header.jsp"%>
	
	<c:if test="${empty rooms }">
		<br><br><br><center><h3 style="color: blue">Sorry, no rooms are available on the selected dates</h3></center>
		<br><br><br><center><h3 style="color: blue">Please try for some other dates.</h3></center>
	</c:if>
	
	<c:if test="${not empty rooms}">
	<center>
		<h3 style="color: blue">
			<br> <br> <br> <br> <br>
			<c:out value="Available Rooms"></c:out>
			<br> <br>

			<form action="<%= bookRoomControllerPath%>" method="post">
				<table border="2">
					<tr>
						<th>Select Rooms</th>
						<th>Room No</th>
						<th>Room Type</th>
						<th>Room Capacity</th>
					</tr>
					<c:forEach var="room" items="${ rooms }" varStatus="loop">
						<tr>
							<td><input type="checkbox" name="bookedRooms" value="<%=flushCounter()%><%=((Room)(rooms.get(i++))).getRoomNumber()%>"></td>
							<td><c:out value="${room.roomNumber}"></c:out></td>
							<td><c:out value="${room.roomType}"></c:out></td>
							<td><c:out value="${room.roomCapacity}"></c:out></td>
						</tr>
					</c:forEach>
				</table>
				<br>
				<center>
					<input type="reset" value="Reset"> <input type="submit"
						value="Book">
				</center>
			</form>
		</h3>
	</center>
	</c:if>
	<%@include file="footer.jsp"%>
</body>
</html>