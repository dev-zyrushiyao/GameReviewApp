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
<title>Update Game</title>
</head>
<body>
	
	<h1>Update <c:out value="${gameInfo.getTitle()}"/> </h1>
	<a href="/admin/view/game/info/id/${gameInfo.getId()}">GO BACK</a> <!-- TEMPORARY ROUTE -->
	
	<label style="color:red"><c:out value="${gameUpdateError}"/></label>
	<label style="color:green"><c:out value="${gameUpdateMessage}"/></label>
	<form:form action="/admin/update/game/info/${gameInfo.getId()}" method="POST" modelAttribute="updateGameForm">
				<input type="hidden" name="_method" value="put">
				<table class="table">
				  <thead>
				
				  </thead>
				  <tbody>
				    
				    <tr>
				      	<td><form:label path="title">Game Title:</form:label></td>
				      	<td><form:input type="text" path="title"/></td>
				      	<td><form:errors path="title" class="text-danger" style="color:red"/></td>
				      
				    </tr>
				   	
				     	<tr>
				   	  	<td><form:label path="genreEntity">Genre:</form:label></td>
				      	<td>
				      		<form:select path="genreEntity">
					      		<c:forEach var="genreOption" items="${genreList}">
					  				<option value="${genreOption.getId()}"> <c:out value="${genreOption.getGenre()}"/> </option>
					  			</c:forEach>
				  			</form:select>
				      	</td> 
				      	<td style="color:red"><form:errors path="genreEntity" class="text-danger"/></td>
				    </tr>
				    
				    <tr>
				      	<td><form:label path="imageUrl">Image Url:</form:label></td>
				      	<td><form:input type="text" path="imageUrl"/></td>
				      	<td style="color:red"><form:errors path="imageUrl" class="text-danger"/></td>
				
				    </tr>
				    
				    <tr>
					 	<td><form:label path="trailerUrl">Trailer Url:</form:label></td>
				      	<td><form:input type="text" path="trailerUrl" title="https://youtu.be/[dQw4w9WgXcQ]" placeholder="Youtube video ID"/></td>
				      	<td style="color:red"><form:errors path="trailerUrl" class="text-danger"/></td>
				    </tr>
				    
				   <tr>
					 	<td><form:label path="platformEntity">Platform</form:label></td>
				      	<td>
				      		<form:select path="platformEntity">
								<c:forEach var="platformOption" items="${platformList}">
									<option value="${platformOption.getId()}"> <c:out value="${platformOption.getPlatformName()}"/> </option> 
								</c:forEach>
							</form:select>
				      	</td> 
				      	<td style="color:red"><form:errors path="platformEntity" class="text-danger"/></td>
				    </tr>
				    
				  </tbody>
				</table>
				<input type="submit" value="Submit"> <input type="reset" value="Clear">
				
			</form:form>
</body>
</html>