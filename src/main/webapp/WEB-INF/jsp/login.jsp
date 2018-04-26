<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>MuseeCP - Login</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css" />" />
</head>
<body>
	<form action="/login" method="POST" class="body-block">
		<h1 class="body-block-title">MuseeCP <span>v0.0.1-SNAPSHOT</span></h1>
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	<c:if test="${error ne null}">
		<div id="login-error"><img src="<c:url value="/img/error.png" />" /><spring:message code="${error ne null ? error : label.login.error}" /></div>
	</c:if>

	<c:if test="${param.logout ne null}">
		<div id="logout-success"><img src="<c:url value="/img/disconnect.png" />" /><spring:message code="label.logout.success" /></div>
	</c:if>

		<ul>
			<li>
				<label for="name"><spring:message code="label.form.login.field.username" /></label>
				<input type="text" name="username" maxlength="100">
				<span><spring:message code="label.form.login.field.username.details" /></span>
			</li>
			<li>
				<label for="password"><spring:message code="label.form.login.field.password" /></label>
				<input type="password" name="password">
				<span><spring:message code="label.form.login.field.password.details" /></span>
			</li>
			<spring:message code="label.form.login.submit" var="form_submit" />
			<spring:message code="label.form.login.button.register" var="form_register" />

			<li>
				<input type="submit" value="${form_submit}" />
				<input type="button" value="${form_register}" />
			</li>
		</ul>
	</form>
	<p>
		<a href="?locale=fr"><img src="/img/flags/fr.png" /></a>
		<a href="?locale=en"><img src="/img/flags/gb.png" /></a>
	</p>
	
	<c:url value="/register" var="register_url"/>
	<script type="text/javascript">
	document.querySelector("input[type='button']").onclick = function(e) {
		
		window.location.href = "${register_url}";
	};
	</script>
</body>
</html>