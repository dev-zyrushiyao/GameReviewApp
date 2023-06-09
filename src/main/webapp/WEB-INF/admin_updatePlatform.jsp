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
<title>Update Platform | (Admin)</title>
</head>
<body>
	
	<a href="/admin/view/list/platform">Go Back</a>


	<br>
	<form:form action="/admin/update/info/platform/id/${gamePlatformModel.getId()}" method="POST" modelAttribute="platformForm">
		<input type="hidden" name="_method" value="put">
		<h1>Update Platform Genre ID: <c:out value="${gamePlatformModel.getId()}"/></h1>
			<label style="color:green"><c:out value="${platformMessage}"/></label>
		<table class="table table-hover">
		  <thead></thead>
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
		
		<input type="submit" value="UPDATE PLATFORM" onClick="return confirm('Are you sure you want to update [ID:' + ${gamePlatformModel.getId()} + ']')"> <input type="reset" value="RESET">
	</form:form>

</body>
</html>