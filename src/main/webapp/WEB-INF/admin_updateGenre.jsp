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
<title>Update Genre | Game Review</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
 <!-- borrowing CSS for Form -->
<link rel ="stylesheet" type="text/css" href="/css/login.css">
<link rel ="stylesheet" type="text/css" href="/css/register.css">
<link rel ="stylesheet" type="text/css" href="/css/genre-style.css"> 

<link rel="icon" type="image/x-icon" href="/icon/favicon.ico">
</head>
<body>
	
	<div id="back-button">
		<a href="/admin/view/list/genre/page/0">
			<span id="back-arrow">&#8249;</span>
			<span id="back-link"> Go back</span>
		</a>
	</div>

<div id="genre-div">
	<form:form action="/admin/update/info/genre/id/${gameGenreModel.getId()}" method="POST" modelAttribute="genreForm">
		<input type="hidden" name="_method" value="put">
		<p class="h3 app-title">Update Game Genre ID: <c:out value="${gameGenreModel.getId()}"/></p>
			<p style="color:green;"><c:out value="${genreMessage}"/></p>
			<label><form:errors path="genre" style="color:red"/></label>
		<table class="table table-borderless">
		  <thead></thead>
		  <tbody>
			
		    <tr>
		      <td><form:input type="text" class="form-control-sm" id="formControlInput" path="genre"/></td>
		      <td>	<input type="submit" value="SUBMIT" class="btn btn-primary" id="align-button" onClick="return confirm('Are you sure you want to update [ID:' + ${gameGenreModel.getId()} + ']')"></td>
		    </tr>
			
		  </tbody>
		</table>
	</form:form>
</div>

</body>
</html>