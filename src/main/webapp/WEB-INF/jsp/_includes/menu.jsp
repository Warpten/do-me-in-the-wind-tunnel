<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<div class="flex-container">
		<div class="body-block flex-align-self-stretch" id="menu">
			<h1 class="body-block-title">Profile settings</h1>
			
			<ul class="list-no-bullet menu-link-list">
				<sec:authorize access="hasRole('ADMIN')">
				<li>
					<a href="<c:url value="/user/management" />">
						<img src="<c:url value="/img/user_edit.png" />" />User management
					</a>
				</li>
				</sec:authorize>
				<li><img src="<c:url value="/img/key_add.png" />" />Permissions</li>
				<li><img src="<c:url value="/img/image_edit.png" />" />Art management</li>
				<li><img src="<c:url value="/img/map_edit.png" />" />Guided visits</li>
				<li><img src="<c:url value="/img/group_edit.png" />" />Exhibitions</li>
			</ul>
	
			<h1 class="body-block-title">Your profile</h1>
			
			<ul class="list-no-bullet menu-link-list">
				<li><img src="<c:url value="/img/newspaper_add.png" />" />Subscriptions</li>
				<li><img src="<c:url value="/img/cart_go.png" />" />Invoices</li>
				<li>
					<a href="<c:url value="/user/edit-profile" />">
						<img src="<c:url value="/img/cog.png" />" />Change account settings
					</a>
				</li>
				<li><img src="<c:url value="/img/bug_go.png" />" />Signal a bug</li>
				<li>
					<a href="<c:url value="/logout" />">
						<img src="<c:url value="/img/logout.png" />" />Log out
					</a>
				</li>
			</ul>
		</div>
	</div>