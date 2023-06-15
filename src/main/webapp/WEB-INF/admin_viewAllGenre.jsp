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
<title>DataList | GameReview</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
<link rel ="stylesheet" type="text/css" href="/css/dataList-style.css">

<link rel="icon" type="image/x-icon" href="/icon/favicon.ico">	
</head>
<body>
	
	<div id="back-button">
		<a href="/admin/commands">
			<span id="back-arrow">&#8249;</span>
			<span id="back-link"> Go back</span>
		</a>
	</div>
		
		
		<p class="h1" style="text-align:center;">Genre DataList</p>
			<p class="h4" style="text-align:center; color:red;"><c:out value="${errorMsg}"/></p>
		
	<table class="table table-striped">
  <thead>
    <tr id="tbl-title">
      <th scope="col">ID</th>
      <th scope="col">Genre</th>
      <th scope="col">Created At:</th>
      <th scope="col">Updated At:</th>
      <th scope="col">Action: </th>
     
    </tr>
  </thead>
  <tbody>
  <c:forEach var="DataList" items="${listOfGenre}">
	    <tr class="tbl-data">
	      <th scope="row"> <c:out value="${DataList.getId()}"/></th>
	       <td><c:out value="${DataList.getGenre()}"/></td>
	       <td><c:out value="${DataList.getCreatedAt()}"/></td>
	       <td><c:out value="${DataList.getUpdatedAt()}"/></td>
	         <td>
	         	
	         	<form action="/admin/update/genre/id/${DataList.getId()}">
	         		<input type="submit" class="btn btn-info" value="UPDATE">
	         	</form>
	         	
	         	<form action="/admin/delete/genre/${DataList.getId()}">
	         		<input type="submit" class="btn btn-danger" value="DELETE" onClick="return confirm('Are you sure you want to delete [ID:' + ${DataList.getId()} + ']')">
	         	</form>
	         </td>
	    </tr>
	</c:forEach>
  </tbody>
</table>

</body>
</html>