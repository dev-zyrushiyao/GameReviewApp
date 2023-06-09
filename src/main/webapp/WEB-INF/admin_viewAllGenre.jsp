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
<title>Add Genre (Admin)</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
	
	<a href="/admin/new/genre">Go Back</a>
	
		<h1>Genre DataList</h1>
		
		
	<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Genre</th>
      <th scope="col">Created At:</th>
      <th scope="col">Updated At:</th>
      <th scope="col">Action: </th>
     
    </tr>
  </thead>
  <tbody>
  <c:forEach var="DataList" items="${listOfGenre}">
	    <tr>
	      <th scope="row"> <c:out value="${DataList.getId()}"/></th>
	       <td><c:out value="${DataList.getGenre()}"/></td>
	       <td><c:out value="${DataList.getCreatedAt()}"/></td>
	       <td><c:out value="${DataList.getUpdatedAt()}"/></td>
	         <td>
	         	
	         	<form action="/admin/update/genre/id/${DataList.getId()}">
	         		<input type="submit" value="UPDATE">
	         	</form>
	         	
	         	<form action="/admin/delete/genre/${DataList.getId()}">
	         		<input type="submit" value="DELETE" onClick="return confirm('Are you sure you want to delete [ID:' + ${GenreDataList.getId()} + ']')">
	         	</form>
	         </td>
	    </tr>
	</c:forEach>
  </tbody>
</table>

</body>
</html>