<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Game Review</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
<link rel ="stylesheet" type="text/css" href="/css/login.css">

<link rel="icon" type="image/x-icon" href="/icon/favicon.ico">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
</head>
<body>
		
		<div id="login-div">	

			<div id="grid-box">
					<img src="/icon/switch-left-hover.png" id="img-1"  alt="console-left-controller">
				<p class="h1 app-title">Game Review</p>
					<img src="/icon/switch-right-hover.png" alt="console-left-controller">
			</div> 
	
		<div id="content">	
			
			<c:if test="${logoutMessage != null}">
				<div class="alert alert-success login-message" role="alert">
		  			<c:out value="${logoutMessage}"/>
				</div>
			</c:if>
			
			<c:if test="${errorMessage != null}">
			     <div class="alert alert-danger login-message" role="alert">
		  			<c:out value="${errorMessage}"/>
				</div>
			</c:if>
		    
			<form method="POST" action="/login">
				   	<label for="formControlInput" class="form-label login-label">Username:</label>
						<br>
					<input type="text" name="username" class="form-control-sm" id="formControlInput">
						<br>
					<label for="formControlInput" class="form-label login-label">Password:</label>
						<br>
					<input type="password" name="password" class="form-control-sm" id="formControlInput">
						<br>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="submit" class="btn btn-primary" value="LOGIN"/>
					<p id="reg-link">Don't have account yet? Sign-up <a href="/registration">here</a> </p>
					<p style="font-family: var(--themeFont) ; font-weight:300 ; font-size:14px">Full-Stack Project by Zyrus Hiyao</p>
			</form>
		</div>
		
	</div>
	
	
</body>
</html>