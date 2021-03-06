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
	<form:form modelAttribute="registrationModel" action="/register" method="POST" class="body-block flex-container flex-direction-column">
		<h1 class="body-block-title">MuseeCP <span>v0.0.1-SNAPSHOT</span></h1>
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	<c:if test="${param.error ne null}">
		<div id="login-error"><img src="<c:url value="/img/error.png" />" /><spring:message code="${param.error}" /></div>
	</c:if>

		<div class="barebones flex-container flex-direction-row flex-align-center">
		
			<ul>
				<li class="${error_username ne null ? 'error' : ''}">
					<label for="name"><spring:message code="label.form.register.field.username" /></label>
					<form:input path="username" />
					<span><spring:message code="${error_username ne null ? error_username : 'label.form.register.field.username.details'}" /></span>
				</li>
				<li class="${error_password ne null ? 'error' : ''}">
					<label for="password"><spring:message code="label.form.register.field.password" /></label>
					<form:password path="password" />
					<meter max="4" id="password-strength-meter">
						<span class="tooltip">
							Password strength
							<span class="arrow-right"></span>
						</span>
					</meter>
					<span><spring:message code="${error_password ne null ? error_password : 'label.form.register.field.password.details'}" /></span>
				</li>
				<li class="${error_email ne null ? 'error' : ''}">
					<label for="email"><spring:message code="label.form.register.field.email" /></label>
					<form:input path="email" />
					<span><spring:message code="${error_email ne null ? error_email : 'label.form.register.field.email.details'}" /></span>
				</li>

			</ul>
			
			<ul style="border-left: 1px solid #dddddd; margin: 0; padding: 10px; margin-bottom: 5px;">
				<li class="${error_name ne null ? 'error' : ''}">
					<label for="name"><spring:message code="label.form.register.field.name" /></label>
					<form:input path="name" />
					<span><spring:message code="${error_name ne null ? error_name : 'label.form.register.field.name.details'}" /></span>
				</li>
				<li class="${error_surname ne null ? 'error' : ''}">
					<label for="surname"><spring:message code="label.form.register.field.surname" /></label>
					<form:input path="surname" />
					<span><spring:message code="${error_surname ne null ? error_surname : 'label.form.register.field.surname.details'}" /></span>
				</li>
				
				<li>
					<label for="preferredLocale"><spring:message code="label.form.register.field.locale" /></label>
					<form:select path="preferredLocale">
						<c:forEach items="${locales}" var="locale">
							<c:set var="locale_key" value="label.lang.${locale.key}"/>
							<option value="${locale.id}"><spring:message code="${locale_key}" text="label.lang.${locale_key}" /></option>
						</c:forEach>
					</form:select>
					<span><spring:message code="label.form.register.field.locale.details" /></span>
				</li>
			</ul>
		</div>
		
		<spring:message code="label.form.register.submit" var="form_submit" />
		<input type="submit" value="${form_submit}" style="width: fit-content; margin: auto;" />
	</form:form>
	<p>
		<a href="?locale=fr"><img src="/img/flags/fr.png" /></a>
		<a href="?locale=en"><img src="/img/flags/gb.png" /></a>
	</p>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>
	<script>
	var password = document.getElementById('password');
	var meter = document.getElementById('password-strength-meter');

	$("#password").keyup(function() {
	  var val = $(this).val();
	  var result = zxcvbn(val);

	  // Update the password strength meter
	  meter.value = result.score;
	  $(meter).attr("class", "pw-str-" + result.score);
	  
	});

	</script>
</body>
</html>