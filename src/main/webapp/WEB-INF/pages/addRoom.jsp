<%@page import="choubey.apurva.hotel.model.User"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!String addRoomControllerPath;%>

<%
	addRoomControllerPath = request.getContextPath() + "/controller/user/addRoom";
	if ((User) session.getAttribute("user") != null) {

		if (((User) session.getAttribute("user")).getIsAdmin() != (short) 1) {
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
	}
%>

<html>
<head>
<meta charset="utf-8">
<title>Add Room Page</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<br>
	<br>
	<br>
	<center>
		<form action=<%=addRoomControllerPath%> method="post">
			<h3>
				<table border="2">
					<tr>
						<td>Room Number :</td>
						<td><input type="text" name="roomNumber" required />
					</tr>
					<tr>
						<td>Room Type :</td>
						<td><input type="text" name="roomType" required />
					</tr>
					<tr>
						<td>Room Capacity :</td>
						<td><input type="text" name="roomCapacity" required />
					</tr>
					<tr>
						<td>Availability :</td>
						<td><input type="number" name="roomAvailability" required />
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Add Room" /> <input type="reset" value="Reset" /></td>
					</tr>
				</table>
			</h3>
		</form>
	</center>
	<%@include file="footer.jsp"%>
</body>
</html>