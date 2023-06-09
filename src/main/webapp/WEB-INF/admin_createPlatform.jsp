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
<title>Add Platform (Admin)</title>
</head>
<body>
	
	<a href="/admin">Go Back</a>
	<a href="/admin/view/list/platform">View Platform DataList</a> <!-- TO CHANGE TO LIST PLATFORM -->

	<br>
	<form:form action="/admin/add/platform" method="POST" modelAttribute="platformForm">
		<h1>Add Game Platform:</h1>
			<label style="color:green"><c:out value="${platformMessage}"/></label>
		<table class="table table-hover">
		  <thead>
		  </thead>
		  <tbody>
		  
		  
		    <tr>
		      <td><label>Platform: </label></td>
		      <td><form:input type="text" path="platformName"/></td>
		    </tr>
		   	
		   	<tr>
			    <td></td>
			    <td><form:errors path="platformName" style="color:red"/></td>
		  	</tr>
			
		  </tbody>
		</table>
		
		<input type="submit" value="ADD PLATFORM"> <input type="reset" value="Reset">
	</form:form>

</body>
</html>