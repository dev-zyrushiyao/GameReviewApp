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
<title>Create Platform | Game Review </title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
<!-- borrowing CSS for Form -->
<link rel ="stylesheet" type="text/css" href="/css/login.css">
<link rel ="stylesheet" type="text/css" href="/css/register.css">
<link rel ="stylesheet" type="text/css" href="/css/genre-style.css"> 

<link rel="icon" type="image/x-icon" href="/icon/favicon.ico">	
</head>
<body>
	
	<div id="back-button">
		<a href="/admin/commands">
			<span id="back-arrow">&#8249;</span>
			<span id="back-link"> Go back</span>
		</a>
	</div>
	
	<div id="genre-div">
		<form:form action="/admin/add/platform" method="POST" modelAttribute="platformForm">
			<p class="h3 app-title">Create Game Platform</p>
				<label style="color:green"><c:out value="${platformMessage}"/></label>
				<label style="color:red"><c:out value="${platformMessageError}"/></label>
				
				<label><form:errors path="platformName" style="color:red"/></label>
			<br>
			<table class="table table-borderless">
			  <thead>
			  	<tr>
			  	</tr>
			  </thead>
			  <tbody>
			  
			  
			    <tr>
			      <td><form:input type="text" path="platformName" placeholder="Nintendo Swtich; PS4..etc"/></td>
			      <td><input type="submit" value="SUBMIT" class="btn btn-primary" id="align-button"></td>
			    </tr>
				
			  </tbody>
			</table>
		</form:form>
	</div>

</body>
</html>