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
<title>Admin Commands | Game Review</title>
 <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
 <!-- borrowing CSS for NAV -->
 <link rel ="stylesheet" type="text/css" href="/css/dashboard-style.css">
 <!-- admin command CSS -->
 <link rel ="stylesheet" type="text/css" href="/css/admin-command-style.css">

<link rel="icon" type="image/x-icon" href="/icon/favicon.ico">

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

 <p class="h3 page-title"> Admin Commands </p>
 	
 <div class="grid-box">

	 	<div>
	 		<p>
	 			<a href="/admin/new/genre">Add Genre</a>
	 			<img src="/icon/sword-icon.png" class="blue-box" alt="sword icon">
	 		</p>
	 	</div>
	 	
		<div>
	 		<p>
	 			<a href="/admin/new/platform">Add Platform</a>
	 			<img src="/icon/d-pad.png" class="pink-box" alt="d-pad icon">
	 		</p>
	 	</div>
	 	
	 	<div>
	 		 <p>
	 			<a href="/admin/new/game">Add Game</a>
	 			<img src="/icon/controller.png" alt="controller icon">
	 		</p>
	 		
	 	</div>
	 	
	 	<div>
	 		<p>
	 			<a href="/admin/view/list/genre/page/0">Manage Genre Data</a>
	 			<img src="/icon/switch-pair.png" class="blue-box" alt="controller icon">
	 		</p>
	 	</div>
	 	
	 	<div>
	 		<p>
	 			<a href="/admin/view/list/platform">Manage Platform Data</a>
	 			<img src="/icon/sus-icon.png" class="pink-box" alt="sus icon">
	 		</p>
	 	</div>
	 	
	 	<div>
	 		<p>
	 			<a href="/">View Games</a>
	 			<img src="/icon/potion_icon.png" alt="potion icon">
	 		</p>
	 	</div>
	</div>
</body>
</html>