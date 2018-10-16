<%@page import="choubey.apurva.hotel.model.Room"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<title>Rooms available for removing</title>
</head>

<%! String removeRoomsControllerPath;
	int i = 0;
	ArrayList rooms;
%>

<%
	removeRoomsControllerPath 	= request.getContextPath() + "/controller/room/remove";
	rooms = (ArrayList<Room>)session.getAttribute("cancelableRooms");
%>

<%!
	private String flushCounter() {
	
		i=0;
		return "";
	}
%>

<body bgcolor="cyan">
	<%@include file="header.jsp"%>
	
	<c:if test="${empty rooms }">
		<br><br><br><center><h3 style="color: blue">There are no rooms available for removing</h3></center>
	</c:if>
	
	<c:if test="${not empty rooms}">
	<center>
		<h3 style="color: blue">
			<br> <br> <br> 
			<c:out value="Select the ones you want to remove"></c:out>
			<br> <br>

			<form action="<%= removeRoomsControllerPath%>" method="post">
				<table border="2">
					<tr>
						<th>Select Rooms</th>
						<th>Room No</th>
						<th>Room Type</th>
						<th>Room Capacity</th>
					</tr>
					<%=flushCounter()%>
					<c:forEach var="room" items="${ rooms }" varStatus="loop">
						<tr>
							<td><input type="checkbox" name="removes" value="<%=((Room)(rooms.get(i++))).getRoomNumber()%>"></td>
							<td><c:out value="${room.roomNumber}"></c:out></td>
							<td><c:out value="${room.roomType}"></c:out></td>
							<td><c:out value="${room.roomCapacity}"></c:out></td>
						</tr>
					</c:forEach>
				</table>
				<br>
				<center>
					<input type="reset" value="Reset"> <input type="submit"
						value="Remove">
				</center>
			</form>
		</h3>
	</center>
	</c:if>
	<%@include file="footer.jsp"%>
</body>
</html>