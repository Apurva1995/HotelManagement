<%@page import="choubey.apurva.hotel.model.Room"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>

<%
	ArrayList rooms = (ArrayList<Room>) request.getAttribute("rooms");
%>

<html>
<head>
<meta charset="utf-8">
<title>See Room Details Page</title>
</head>
<body bgcolor="cyan">
	<%@include file="header.jsp"%>
	<center>

		<h3 style="color: blue">
			<br> <br> <br> <br> <br>
			<c:out value="Select the rooms you want to book"></c:out>
			<br> <br>

			<form>
				<table border="2">
					<tr>
						<th>Select Rooms</th>
						<th>Room No</th>
						<th>Room Type</th>
						<th>Room Capacity</th>
					</tr>
					<c:forEach var="room" items="${ rooms }" varStatus="loop">
						<tr>

							<td><input type="checkbox" name="bookedRooms"></td>
							<td><c:out value="${room.roomNumber}"></c:out></td>
							<td><c:out value="${room.roomType}"></c:out></td>
							<td><c:out value="${room.roomCapacity}"></c:out></td>
						</tr>
					</c:forEach>
					<!-- <tr>
						<td colspan="2" align="center"><input type="reset"
							value="Reset"> <input type="submit" value="Book"></td>
					</tr> -->
				</table>
				<br>
				<center>
					<input type="reset" value="Reset"> <input type="submit"
						value="Book">
				</center>
			</form>
		</h3>
	</center>


	<%@include file="footer.jsp"%>
</body>
</html>