<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>

<h1>Welcome Mobile</h1>
<p>
  Your User-Agent header: <c:out value="${header['User-Agent']}" />
</p>
<p>
  Your type of device: <c:out value="${requestScope.currentDevice}"/>
</p>
<p>
  Your site preferences <c:out value="${requestScope.currentSitePreferecnce }" />
</p>
</body>
</html>