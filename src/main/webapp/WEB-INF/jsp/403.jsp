<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Access Denied</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css" />" />
</head>
<body>
	<form action="/logout" method="POST" class="form-style-7">
		<h1 id="title">MuseeCP <span>v0.0.1-SNAPSHOT</span></h1>
		
		<div id="login-error">You do not have permission to access this page!</div>
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

		<ul>
			<li>
				<input type="submit" class="button red big" value="Sign in as different user" />
			</li>
		</ul>
		
	</form>
</body>
</html>