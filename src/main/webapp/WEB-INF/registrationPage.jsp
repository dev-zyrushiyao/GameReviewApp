<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Game Review | Registration</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
<link rel ="stylesheet" type="text/css" href="/css/register.css">

<link rel="icon" type="image/x-icon" href="/icon/favicon.ico">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
</head>
<body>

	
	<div id="back-button">
		<a href="/login">
			<span id="back-arrow">&#8249;</span>
			<span id="back-link"> Go back</span>
		</a>
	</div>
	
	<div id="registration-div" >
		 <p class="app-title" style="font-size:18px;">Game Review App </p>
		 <p class="h1 app-title">Create Account </p>
		 	
		 	<c:if test="${userModel !=  null }">
		 	<div class="alert alert-danger login-message" role="alert">
		  		<p style="color:red"><form:errors path="user.*"/></p> 
			</div>
			</c:if>
			
			<c:if test="${registrationMessage != null }">
				<div class="alert alert-success login-success" role="alert">
			  		<label style="color:green"><c:out value="${registrationMessage}"/></label> 
				</div>
			</c:if>
	    
	   
	    
	    <form:form method="POST" action="/registration" modelAttribute="user">
	       	<table class="table table-borderless">
			  <thead>
			    <tr>
			    </tr>
			  </thead>
			  <tbody>
			  	<tr>
			  		<td><form:label path="userName" for="formControlInput" class="form-label reg-label">Username:</form:label></td>
			  		<td> <form:input path="userName" type="text" class="form-control-sm" id="formControlInput"/></td>
			  	</tr>
		        <tr>
		        	<td><form:label path="password" for="formControlInput" class="form-label reg-label">Password:</form:label></td>
		        	<td><form:input type="password" path="password" class="form-control-sm" id="formControlInput"/></td>
		        </tr>
		        <tr>
		        	<td><form:label path="passwordConfirmation" for="formControlInput" class="form-label reg-label">Confirm PW:</form:label></td>
		        	<td><form:input type="password" path="passwordConfirmation" class="form-control-sm" id="formControlInput"/></td>
		        </tr>
		      
		    </tbody>
		</table>     
		
	        <input type="submit" class="btn btn-primary" value="REGISTER"/>
	    </form:form>
	 </div>
	

</body>
</html>