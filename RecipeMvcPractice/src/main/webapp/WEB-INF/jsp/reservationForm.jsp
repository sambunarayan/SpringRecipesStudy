<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reservation Form</title>
<style>
.error {
	color:#ff0000;
	font-weight:bold;
}
</style>
</head>
<body>
<form:form method="post" modelAttribute="reservation">
<form:errors path="*" cssClass="error" />

<table>
	<tr>
		<td>Court Name</td>
		<td><form:input path="courtName"/></td>
		<td><form:errors path="courtName" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Date</td>
		<td><form:input path="date"/></td>
		<td><form:errors path="date" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Hour</td>
		<td><form:input path="hour"/></td>
		<td><form:errors path="hour" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Player Name</td>
		<td><form:input path="player.name"/></td>
		<td><form:errors path="player.name" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Player Phone</td>
		<td><form:input path="player.phone"/></td>
		<td><form:errors path="player.phone" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Sport Type</td>
		<td><form:select path="sportType" items="${sportTypes}" itemValue="id" itemLabel="name"/></td>
	</tr>	
	<tr>
		<td colspan="3"><input type="submit" /></td>
	</tr>
</table>
</form:form>
</body>
</html>