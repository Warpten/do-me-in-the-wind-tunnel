<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
	
	<div class="body-block flex-item-grow" id="body-block">
		<h1 class="body-block-title">User management</h1>
	
		<table id="user-list">
			<tr>
				<th>#</th>
				<th>Username</th>
				<th>Registration date</th>
				<th></th>
				<th class="paging-cell">
					<c:if test="${pageId != 0}">
					<a href="?page=0"><img src="<c:url value="/img/arrow_stop_left.png" />" /></a>
					</c:if>
				
					<c:if test="${pageId * resultPerPage > 0}">
					<a href="?page=${pageId - 1}"><img src="<c:url value="/img/arrow_left.png" />" /></a>
					</c:if>
					
					<c:if test="${pageId * resultPerPage + users.size() < totalResults}">
					<a href="?page=${pageId + 1}"><img src="<c:url value="/img/arrow_right.png" />" /></a>
					</c:if>
					
					<fmt:formatNumber var="lastPageId" value="${totalResults div resultPerPage}" pattern="#"/>
					<c:if test="${pageId != lastPageId - 1}">
					<a href="?page=${lastPageId - 1}"><img src="<c:url value="/img/arrow_stop_right.png" />" /></a>
					</c:if>
				</th>
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
		<p>
			<sub>Results ${pageId * resultPerPage + 1} - ${pageId * resultPerPage + users.size()} (of ${totalResults})</sub>

		</p>
	</div>
</body>
</html>