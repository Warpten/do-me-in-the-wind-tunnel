<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hello</title>
</head>
<body>
    <form:form method="POST" modelAttribute="account">
    	Username <form:input path="username" /> <form:errors path="username" />
    	Password <form:input path="password" /> <form:errors path="password" />
    	Date <form:input type="date" path="registrationDate" />
    	Rank <form:select multiple="true" path="ranks"><form:options items="${rankList}" itemValue="id" itemLabel="name" /></form:select>
    	<input type="submit" value="Create" />
    </form:form>
</body>
</html>