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
<title>Update Genre (Admin)</title>
</head>
<body>
	
	<a href="/admin/view/list/genre">Go Back</a>


	<br>
	<form:form action="/admin/update/info/genre/id/${gameGenreModel.getId()}" method="POST" modelAttribute="genreForm">
		<input type="hidden" name="_method" value="put">
		<h1>Update Game Genre ID: <c:out value="${gameGenreModel.getId()}"/></h1>
			<label style="color:green"><c:out value="${genreMessage}"/></label>
		<table class="table table-hover">
		  <thead></thead>
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
		
		<input type="submit" value="UPDATE GENRE" onClick="return confirm('Are you sure you want to update [ID:' + ${gameGenreModel.getId()} + ']')"> <input type="reset" value="RESET">
	</form:form>

</body>
</html>