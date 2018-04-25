<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>MuseeCP - User management</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/user_management.css" />" />
</head>
<body class="flex-container flex-align-start">
	<jsp:include page="../_includes/menu.jsp"></jsp:include>
	
	<div class="barebones flex-item-grow flex-container flex-direction-row flex-wrap" id="body-block">
		<form:form method="POST" modelAttribute="accountInfo" cssClass="fit-content-height body-block flex-container flex-direction-column">
			<h1 class=body-block-title>Personal details</h1>
			
			<div class="barebones flex-container flex-direction-row">
				<div class="body-block flex-container flex-direction-column">
					<h3 class="body-block-title">User details</h3>
					<ul>
						<li>
							<form:label path="name"><spring:message code="label.form.user.settings.field.name" /></form:label>
							<form:input path="name" />
							<span><spring:message code="label.form.user.settings.field.name.details" /></span>
						</li>
						<li>
							<form:label path="surname"><spring:message code="label.form.user.settings.field.surname" /></form:label>
							<form:input path="surname" />
							<span><spring:message code="label.form.user.settings.field.surname.details" /></span>
						</li>
						<li>
							<form:label path="preferredLocale"><spring:message code="label.form.user.settings.field.locale" /></form:label>
							<form:select path="preferredLocale">
								<c:forEach items="${locales}" var="locale">
									<c:set var="locale_key" value="label.lang.${locale.key}"/>
									<option value="${locale.id}"><spring:message code="${locale_key}" text="label.lang.${locale_key}" /></option>
								</c:forEach>
							</form:select>
						</li>
					</ul>		
				</div>
				
				<div class="body-block flex-container flex-direction-column">
					<h3 class="body-block-title">Password settings<span class="inline-warning"><img src="<c:url value="/img/error.png" />" /><spring:message code="label.form.notification.logout" /></span>
					</h3>
					<ul>
						<li>
							<form:label path="newPassword"><spring:message code="label.form.user.settings.field.newPassword" /></form:label>
							<form:password path="newPassword" />
							<span><spring:message code="label.form.user.settings.field.newPassword.details" /></span>
						</li>
						<li>
							<form:label path="newPasswordConfirmation"><spring:message code="label.form.user.settings.field.newPasswordConfirm" /></form:label>
							<form:password path="newPasswordConfirmation" />
							<span><spring:message code="label.form.user.settings.field.newPassword.details" /></span>
						</li>
					</ul>
					<c:set var="error_popin" value="${global_error_mismatch ne null ? 'error-popin' : 'error-popin hidden'}" />
					<div class="${error_popin}">
						<img src="<c:url value="/img/error.png" />" /><spring:message code="${global_error_mismatch ne null ? global_error_mismatch : 'label.form.user.settings.error.passwordMismatch'}" />
					</div>
				</div>
			</div>
			
						
			<ul style="margin: 20px auto;">
				<li>
					<form:label path="oldPassword"><spring:message code="label.form.user.settings.field.old_password" /></form:label>
					<form:password path="oldPassword" />
					<span><spring:message code="label.form.user.settings.field.old_password.details" /></span>
				</li>
			</ul>
			
			<spring:message code="label.form.user.settings.submit" var="form_submit" />
			<input type="submit" value="${form_submit}" style="width: fit-content; margin: auto;" />
			
			<c:if test="${global_error ne null}">
			<div class="error-popin"><spring:message code="${global_error}" /></div>
			</c:if>
			
		</form:form>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">
	$(function() {
		String.prototype.isEmpty = function(ignoreWhitespace) {
		    if (ignoreWhitespace && this.trim() == "") return true;
		    if (this.length == 0) return true;
		    return false;
		}

		var newPwTimeout = null;
		var pwTimeout = null;
		
		$("form").submit(function(e) {
			var node = $("input#oldPassword");
			var oldPassword = node.val(); console.log(oldPassword);
			if (oldPassword === null || oldPassword.isEmpty()) {
				node.parent().addClass("error"); //.delay(2000).removeClass("error");

				clearTimeout(pwTimeout);
				pwTimeout = setTimeout(function() {
					node.parent().removeClass("error");
				}, 1000);
				
				e.preventDefault();
				return;
			}

		    var newPassword = $("input#newPassword").val();
			var newPasswordConfirm = $("input#newPasswordConfirmation").val();
			if ((!newPassword.isEmpty() || !newPasswordConfirm.isEmpty()) && newPassword != newPasswordConfirm) {

				$(".error-popin").removeClass("hidden").fadeIn().delay(1500).slideUp();
				e.preventDefault();
				return;
			}
		});

		setTimeout(function() {
			$(".error-popin").slideUp(400, function() { $(this).addClass("hidden"); });
		}, 5000);
	});
	</script>
</body>
</html>