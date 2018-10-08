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
						<td>User Name :</td>
						<td><input type="text" name="userName" required/>
					</tr>
					<tr>
						<td>Email :</td>
						<td><input type="text" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="Invalid email" required/>
					</tr>
					<tr>
						<td>Password :</td>
						<td><input type="password" name="password" pattern=""(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"required/>
					</tr>
					<tr>
						<td>Mobile Number :</td>
						<td><input type="number" name="mobileNumber" pattern="[1-9]{1}[0-9]{9}" title="Only numbers allowed" required/>
					</tr>
					<tr>
						<td>Age :</td>
						<td><input type="text" name="age" required/>
					</tr>
					<tr>
						<td>Sex :</td>
						<td><input type="text" name="sex" required/>
					</tr>
					<tr>
						<td>Aadhar Number :</td>
						<td><input type="number" name="aadharNumber" pattern="[1-9]{1}[0-11]{9}" title="Invalid addhar number" required/>
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