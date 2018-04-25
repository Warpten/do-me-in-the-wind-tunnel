<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<div	>
		<div class="body-block flex-align-self-stretch" id="menu">
			<h1 class="body-block-title">Administration</h1>
			
			<ul class="list-no-bullet menu-link-list">
				<sec:authorize access="hasRole('ADMIN')">
				<li>
					<a href="<c:url value="/admin/users" />">
						<img src="<c:url value="/img/user_edit.png" />" /><spring:message code="label.menu.admin.user_mgmt" />
					</a>
				</li>
				<li>
				<img src="<c:url value="/img/key_add.png" />" /><spring:message code="label.menu.admin.perm" />
				</li>
				</sec:authorize>
				<li><img src="<c:url value="/img/image_edit.png" />" /><spring:message code="label.menu.admin.art" /></li>
				<li><img src="<c:url value="/img/map_edit.png" />" /><spring:message code="label.menu.admin.visits" /></li>
				<li><img src="<c:url value="/img/group_edit.png" />" /><spring:message code="label.menu.admin.exhibits" /></li>
			</ul>
	
			<h1 class="body-block-title">Your profile</h1>
			
			<ul class="list-no-bullet menu-link-list">
				<li><img src="<c:url value="/img/newspaper_add.png" />" /><spring:message code="label.menu.user.subscriptions" /></li>
				<li><img src="<c:url value="/img/cart_go.png" />" /><spring:message code="label.menu.user.account_history" /></li>
				<li>
					<a href="<c:url value="/user/edit-profile" />">
						<img src="<c:url value="/img/cog.png" />" /><spring:message code="label.menu.user.settings" />
					</a>
				</li>
				<li><img src="<c:url value="/img/bug_go.png" />" /><spring:message code="label.menu.user.signal_bug" /></li>
				<li>
					<a href="<c:url value="/logout" />">
						<img src="<c:url value="/img/logout.png" />" /><spring:message code="label.menu.user.logout" />
					</a>
				</li>
			</ul>
		</div>
<!-- 		<p align="center">
			<a href="?locale=fr"><img src="/img/flags/fr.png" /></a>
			<a href="?locale=en"><img src="/img/flags/gb.png" /></a>
		</p> -->
	</div>