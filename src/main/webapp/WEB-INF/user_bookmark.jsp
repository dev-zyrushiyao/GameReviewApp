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
<title> <c:out value="${currentUser.getUserName()}"/> | Game Review</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
<link rel ="stylesheet" type="text/css" href="/css/register.css">
<link rel ="stylesheet" type="text/css" href="/css/bookmark.css">

<link rel="icon" type="image/x-icon" href="/icon/favicon.ico">	
</head>
<body>

<!-- BACK URL TO BE ADJUSTED -->
<div id="back-button">
		<a href="/admin/commands">
			<span id="back-arrow">&#8249;</span>
			<span id="back-link"> Go back</span>
		</a>
</div>

<p class="h1" style="text-align:center">Your Bookmarks</p>

	<c:forEach var="userBookmark" items="${userBookmark}">
	<div class="bookmark-list"> 
				<div class="bookmark-details">
			 		<div><img src="${userBookmark.getImageUrl()}" alt="video game photo"></div> 
					<div class="bookmark-title"><a href="/view/game/info/id/${userBookmark.getId()}"><c:out value="${userBookmark.getTitle()}"/></a></div>
					<div class="bookmark-action"><a href="/remove/bookmarklist/${userBookmark.getId()}">Remove Bookmark</a></div>
				</div>
	</div>
	<br>
	
	</c:forEach>
	



</body>
</html>