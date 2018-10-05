<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%! String registerControllerPath;
%>

<% registerControllerPath 	= request.getContextPath() + "/controller/user/register";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign up page</title>
</head>
<body bgcolor="cyan">

	<%@include file="header.jsp"%>
	<center>
		<form action=<%= registerControllerPath%> method="post">
			<h3>
				<table border="2">
					<tr>
						<td>User Id :</td>
						<td><input type="text" name="userId" />
					</tr>
					<tr>
						<td>User Name :</td>
						<td><input type="text" name="userName" />
					</tr>
					<tr>
						<td>Email :</td>
						<td><input type="text" name="email" />
					</tr>
					<tr>
						<td>Mobile Number :</td>
						<td><input type="number" name="mobileNumber" />
					</tr>
					<tr>
						<td>Age :</td>
						<td><input type="text" name="age" />
					</tr>
					<tr>
						<td>Sex :</td>
						<td><input type="text" name="sex" />
					</tr>
					<tr>
						<td>Aadhar Number :</td>
						<td><input type="number" name="aadharNumber" />
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Register" /> <input type="reset" value="Reset" /></td>
					</tr>
				</table>
			</h3>
		</form>
	</center>
	<%@include file="footer.jsp"%>
</body>
</html>