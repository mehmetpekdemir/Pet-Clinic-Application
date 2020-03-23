<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Sign In</title>
</head>
<body>

	<form action="login" method="post">
		Username:<input name="username" type="text" /><br /> 
		Password:<input name="password" type="password" /><br /> 
		Remember Me:<input name="remember-me" type="checkbox"> <br />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> 
		<input type="submit" value="Login">
	</form>
	<font color="red"> <c:if test="${not empty param.loginFailed}">
			<p><c:out value="Login Failed, Incorrect Username or Password"></c:out></p>
		</c:if>
		
	<font color="green"><c:if test="${param.logout != null}">
			<p><c:out value="You have been logged out."></c:out></p>
		</c:if>
</body>
</html>