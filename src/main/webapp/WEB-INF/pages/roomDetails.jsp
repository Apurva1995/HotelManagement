<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>See Room Details Page</title>
</head>

<%! String roomDetailsControllerPath;
%>

<% roomDetailsControllerPath 	= request.getContextPath() + "/controller/user/details";
%>

<body bgcolor="cyan">
	<%@include file="header.jsp"%>
	<center>
		<h3>
			<form action=<%= roomDetailsControllerPath %> method="post">
				<table border="2">
					<tr>
						<td>Book From :</td>
						<td><input type="date" name="bookFrom" required />
					</tr>
					<tr>
						<td>Book Till :</td>
						<td><input type="date" name="bookTill" required />
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="reset"
							value="reset"> <input type="submit" value="See Rooms"></td>
					</tr>
				</table>
			</form>
		</h3>
	</center>
	<%@include file="footer.jsp"%>
</body>
</html>