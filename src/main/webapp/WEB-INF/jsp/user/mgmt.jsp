<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>MuseeCP - User management</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/user_main.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/user_management.css" />" />
</head>
<body>
	<table id="user-list">
		<tr>
			<th>#</th><th>Username</th><th>Registration date</th><th colspan="2"></th>
		</tr>
		
		<c:forEach items="${users}" var="user">
		<tr>
			<td><c:out value="${user.id}" /></td>
			<td><c:out value="${user.username}" /></td>
			<td><fmt:formatDate pattern="${dateFormat}" value="${user.registrationDate}" /></td>
			<td>Edit</td>
			<td>Delete</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>