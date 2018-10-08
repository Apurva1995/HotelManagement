<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%! String loginControllerPath;
%>

<% loginControllerPath 	= request.getContextPath() + "/controller/user/login";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body bgcolor="cyan">

	<%@include file="header.jsp"%>
	<center>
		<form action=<%= loginControllerPath %> method="post">
			<h3>
				<table border="2">
					<tr>
						<td>Email :</td>
						<td><input type="text" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="Invalid email" required/>
					</tr>
					<tr>
						<td>Password :</td>
						<td><input type="password" name="password" required/>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Login" /> <input type="reset" value="Reset" /></td>
					</tr>
				</table>
			</h3>
		</form>
	</center>
	<%@include file="footer.jsp"%>
</body>
</html>