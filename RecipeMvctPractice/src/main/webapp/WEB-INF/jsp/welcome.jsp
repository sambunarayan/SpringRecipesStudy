<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="welcome.title" text="Welcome(text)"/></title>
</head>
<body>
	<h2><spring:message code="welcome.message"/></h2>
	<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>
	
	<h2>Handling Time : ${handlingTime} ms</h2>
</body>
</html>