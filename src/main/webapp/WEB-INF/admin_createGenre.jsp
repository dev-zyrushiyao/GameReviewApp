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
<title>Genre DataList | (Admin)</title>
</head>
<body>
	
	<a href="/admin">Go Back</a>
	<a href="/admin/view/list/genre">View Genre DataList</a>

	<br>
	<form:form action="/admin/add/genre" method="POST" modelAttribute="genreForm">
		<h1>Add Game Genre:</h1>
			<label style="color:green"><c:out value="${genreMessage}"/></label>
		<table class="table table-hover">
		  <thead>
		  </thead>
		  <tbody>
		  
		  
		  
		    <tr>
		      <td><label>Genre: </label></td>
		      <td><form:input type="text" path="genre"/></td>
		    </tr>
		   	
		   	<tr>
			    <td></td>
			    <td><form:errors path="genre" style="color:red"/></td>
		  	</tr>
			
		  </tbody>
		</table>
		
		<input type="submit" value="ADD GENRE"> <input type="reset" value="Reset">
	</form:form>

</body>
</html>