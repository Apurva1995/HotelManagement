<%@page import="choubey.apurva.hotel.model.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Show Bookings Page</title>
</head>

<%! String cancelBookingControllerPath;
	int i = 0;
	ArrayList bookings;
%>

<%
	cancelBookingControllerPath 	= request.getContextPath() + "/controller/user/cancel";
	bookings = (ArrayList<Booking>)session.getAttribute("bookings");
%>

<%!
	private String flushCounter() {
	
		i=0;
		return "";
	}
%>

<body bgcolor="cyan">
	<%@include file="header.jsp"%>
	
	<c:if test="${empty bookings }">
		<br><br><br><center><h3 style="color: blue">There are currently no bookings under your name.</h3></center>
	</c:if>
	
	<c:if test="${not empty bookings}">
	<center>
		<h3 style="color: blue">
			<br> <br> <br> 
			<c:out value="Select the ones you want to cancel"></c:out>
			<br><br>
			<c:out value="Your bookings"></c:out>
			<br> <br>

			<form action="<%= cancelBookingControllerPath%>" method="post">
				<table border="2">
					<tr>
						<th>Select Rooms</th>
						<th>Room No</th>
						<th>Boooked From</th>
						<th>Booked Till</th>
					</tr>
					<%=flushCounter()%>
					<c:forEach var="booking" items="${ bookings }" varStatus="loop">
						<tr>
							<td><input type="checkbox" name="userBookings" value="<%=((Booking)(bookings.get(i++))).getBookingId()%>"></td>
							<td><c:out value="${booking.roomNumber}"></c:out></td>
							<td><c:out value="${booking.bookedFrom}"></c:out></td>
							<td><c:out value="${booking.bookedTill}"></c:out></td>
						</tr>
					</c:forEach>
				</table>
				<br>
				<center>
					<input type="reset" value="Reset"> <input type="submit"
						value="Cancel">
				</center>
			</form>
		</h3>
	</center>
	</c:if>
	<%@include file="footer.jsp"%>
</body>
</html>