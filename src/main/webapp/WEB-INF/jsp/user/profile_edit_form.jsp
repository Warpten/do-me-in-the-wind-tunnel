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
	
	<div class="flex-item-grow flex-container flex-direction-row flex-wrap" id="body-block">
		<jsp:include page="../_includes/edit_password_form.jsp"></jsp:include>
		<jsp:include page="../_includes/personal_details_form.jsp"></jsp:include>
		<jsp:include page="../_includes/personal_details_form.jsp"></jsp:include>
		<jsp:include page="../_includes/personal_details_form.jsp"></jsp:include>
		<jsp:include page="../_includes/personal_details_form.jsp"></jsp:include>
		<jsp:include page="../_includes/personal_details_form.jsp"></jsp:include>
		<jsp:include page="../_includes/personal_details_form.jsp"></jsp:include>
		<jsp:include page="../_includes/personal_details_form.jsp"></jsp:include>
	</div>
</body>
</html>