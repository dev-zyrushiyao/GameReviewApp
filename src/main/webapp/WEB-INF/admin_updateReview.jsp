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
	
	
	<a href="/admin/view/game/info/id/${gameReview.getGameEntity().getId()}">Go Back</a>
	<h1>Update comment</h1>
	
	
	 <label style="color:green"><c:out value="${reviewUpdateMessage}"/></label> 
	<form:form action="/admin/update/review/info/${gameReview.getId()}" method="POST" modelAttribute="updateReviewForm">
				<input type="hidden" name="_method" value="put">
				<table class="table">
				  <thead>
				
				  </thead>
				  <tbody>
					
					
					<%-- Transparency Data for User non-binded --%>
				    <tr>
				      	<td><label>Game Title:</label></td>
				      	<td><input type="text" value="${gameReview.getGameEntity().getTitle()}" readonly></td>
				    </tr>
				    
				    <tr>
					 	<td><label>Review By:</label></td>
				      	<td><input type="text" value="${gameReview.getUserEntity().getUserName()}" readonly></td>
				    </tr>
				   	
				    <tr>
				      	<td><form:label path="rating">Game Rating:</form:label></td>
				      	<td><form:input type="number" min="1" max="5" path="rating"/></td>
				      	<td><form:errors path="rating" class="text-danger" style="color:red"/></td>
				    </tr>	
				    
				    <tr>
				      	<td><form:label path="comment">Comment:</form:label></td>
				      	<td><form:input type="text" path="comment"/></td>
				      	<td style="color:red"><form:errors path="comment" class="text-danger"/></td>
				
				    </tr>
				    
				     <tr style="display:none">
					 	<td><form:label path="gameEntity">Game ID:</form:label></td>
				      	<td><form:input type="text" path="gameEntity"/></td>
				      	<td style="color:red"><form:errors path="gameEntity" class="text-danger"/></td>
				    </tr>
				    
				    <tr style="display:none">
					 	<td><form:label path="userEntity">Review By [User ID]:</form:label></td>
				      	<td><form:input type="text" path="userEntity"/></td>
				      	<td style="color:red"><form:errors path="userEntity" class="text-danger"/></td>
				    </tr>
				    
				  </tbody>
				</table>
				<input type="submit" value="Update Review"> 
			</form:form>
			
			
</body>
</html>