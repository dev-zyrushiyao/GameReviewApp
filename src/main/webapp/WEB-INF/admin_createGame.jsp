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
<title>Create Game | Game Review</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
<!-- borrowing CSS for Form -->
<link rel ="stylesheet" type="text/css" href="/css/login.css">
<link rel ="stylesheet" type="text/css" href="/css/register.css">
<link rel ="stylesheet" type="text/css" href="/css/genre-style.css">
<!-- Own CSS for Dropdown Design -->
<link rel ="stylesheet" type="text/css" href="/css/createGame-style.css">

<link rel="icon" type="image/x-icon" href="/icon/favicon.ico">	
</head>
<body>
	
	<div id="back-button">
		<a href="/admin/commands">
			<span id="back-arrow">&#8249;</span>
			<span id="back-link"> Go back</span>
		</a>
	</div>
	
	<form:form action="/admin/post/new/game" method="POST" modelAttribute="gameForm">
				<p class="h3 app-title">Create Game</p>
				<p style="color:green" class="alert-msg"><c:out value="${gameCreateMessage}"/></p>
				<p style="color:red" class="alert-msg"><c:out value="${gameCreateMessageError}"/></p>
				<table class="table table-borderless">
				  <thead>
				  	<tr>
				  	</tr>
				  </thead>
				  <tbody>
				    
				    <tr>
				      	<td><form:errors path="title" class="text-danger" style="color:red"/></td>
				      	<td><form:label path="title">Game Title:</form:label></td>
				      	<td><form:input type="text" path="title"/></td>
				    </tr>
				   	
				   	<tr>
				   		<td style="color:red"><form:errors path="genreEntity" class="text-danger"/></td>
				   	  	<td><form:label path="genreEntity">Genre:</form:label></td>
				      	<td>
				      		<form:select path="genreEntity">
					      		<c:forEach var="genreOption" items="${genreList}">
					  				<option value="${genreOption.getId()}"> <c:out value="${genreOption.getGenre()}"/> </option>
					  			</c:forEach>
				  			</form:select>
				      	</td> 
				    </tr>
				    
				    <tr>
				    	<td style="color:red"><form:errors path="imageUrl" class="text-danger"/></td>
				      	<td><form:label path="imageUrl">Image Url:</form:label></td>
				      	<td><form:input type="text" path="imageUrl"/></td>
				    </tr>
				    
				    <tr>
				    	<td style="color:red"><form:errors path="trailerUrl" class="text-danger"/></td>
					 	<td><form:label path="trailerUrl">Trailer Url:</form:label></td>
				      	<td><form:input type="text" path="trailerUrl" title="https://youtu.be/[dQw4w9WgXcQ]" placeholder="Youtube video ID"/></td>
				    </tr>
				    
				    <tr>
				    	<td style="color:red"><form:errors path="platformEntity" class="text-danger"/></td>
					 	<td><form:label path="platformEntity">Platform</form:label></td>
				      	<td>
				      		<form:select path="platformEntity">
								<c:forEach var="platformOption" items="${platformList}">
									<option value="${platformOption.getId()}"> <c:out value="${platformOption.getPlatformName()}"/> </option> 
								</c:forEach>
							</form:select>
				      	</td> 
				      	
				    	<td><input type="submit" value="SUBMIT" class="btn btn-primary" id="align-button"></td>
				    </tr>
				    
				  </tbody>
				</table>				
			</form:form>
</body>
</html>