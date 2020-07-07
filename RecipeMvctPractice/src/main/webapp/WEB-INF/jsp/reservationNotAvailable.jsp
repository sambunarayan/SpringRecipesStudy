<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reservation Not Available</title>
</head>
<body>

	Your reservation for ${exception.courtName} is not available on
	<fmt:formatDate value="${exception.date }" pattern="yyyy-MM-dd" /> at ${exception.hour }:00.

</body>
</html>