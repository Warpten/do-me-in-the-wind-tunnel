<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<form:form method="POST" modelAttribute="accountPasswordDTO" cssClass="body-block flex-container flex-direction-column">
			<h1 class=body-block-title>Change password</h1>
			
			<ul>
				<li>
					<form:label path="oldPassword">Old password</form:label>
					<form:password path="oldPassword" />
					<span><spring:message code="label.form.login.field.username.details" /></span>
				</li>
				<li>
					<form:label path="newPassword">New password</form:label>
					<form:password path="newPassword" />
					<span><spring:message code="label.form.login.field.password.details" /></span>
				</li>
				<li>
					<form:label path="newPasswordConfirmation">Repeat new password</form:label>
					<form:password path="newPasswordConfirmation" />
					<span><spring:message code="label.form.login.field.password.details" /></span>
				</li>
				<spring:message code="label.form.login.submit" var="form_submit" />
	
				<li>
					<input type="submit" value="${form_submit}" />
					<br />
					<sub>This action will log you out.</sub>
				</li>
			</ul>
		</form:form>