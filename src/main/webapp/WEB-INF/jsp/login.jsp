<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>MuseeCP - Login</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css" />" />
</head>
<body>
	<form action="/login" method="POST" class="form-style-7">
		<h1 id="title">MuseeCP <span>v0.0.1-SNAPSHOT</span></h1>
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	<c:if test="${param.error ne null}">
		<div id="login-error">Invalid username and/or password.</div>
	</c:if>

	<c:if test="${param.logout ne null}">
		<div id="logout-success">You have successfully logged out.</div>
	</c:if>

		<ul>
			<li>
				<label for="name"><spring:message code="label.form.login.field.username" text="???" /></label>
				<input type="text" name="username" maxlength="100">
				<span>Enter the username you wish to use.</span>
			</li>
			<li>
				<label for="password">Password</label>
				<input type="password" name="password">
				<span>Enter your password.</span>
			</li>
			<li><input type="submit" value="Send This"></li>
		</ul>
	</form>
</body>
</html>