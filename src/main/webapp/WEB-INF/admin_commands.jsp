<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- JSTL Tag import -->
<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Data Binding -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- BindingResult -->
<%@ page isErrorPage="true" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Game Review</title>
 <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
 <!-- borrowing CSS for NAV -->
 <link rel ="stylesheet" type="text/css" href="/css/dashboard-style.css">
 <!-- admin command CSS -->
 <link rel ="stylesheet" type="text/css" href="/css/admin-command-style.css">


<!-- GOOGLE API FONT -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

<div id="sticky-header">
	<div id="header-flexbox">
		<h1 id="user-greeting">Welcome, <span id="user"><c:out value="${currentUser.getUserName()}"/></span></h1>
		
		<form id="logoutForm" method="POST" action="/logout">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input type="submit" class="btn btn-primary" id="logout-btn" value="LOGOUT" />
    	</form>
	</div>
	
</div>
	
</body>
</html>