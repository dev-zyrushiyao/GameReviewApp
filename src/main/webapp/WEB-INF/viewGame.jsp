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
<title><c:out value="${gameInfo.getTitle()}"/> Information</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
<link rel ="stylesheet" type="text/css" href="/css/viewgame-style.css">
</head>
<body>
	
	<div id="sticky-header">
		<div id="header-flexbox">
			<a href="/">GO BACK</a>
		</div>
	</div>

	
<div id="master-div">
	<div id="game-container">
		<ul>
			<li><img src="${gameInfo.getImageUrl()}">
			<li><label>Title:<c:out value="${gameInfo.getTitle()}"/></label></li>
			<li><label>Genre:<c:out value="${gameInfo.getGenreEntity().getGenre()}"/></label></li>
			<li><label>Platform:<c:out value="${gameInfo.getPlatformEntity().getPlatformName()}"/></label></li>
			<c:choose>
			     <c:when test="${currentUser.getGames().contains(gameInfo)}">
						<li><a href="/bookmark/remove/game/id/${gameInfo.getId()}">Remove Bookmark</a></li>
				</c:when>
							      			
				<c:otherwise>
						<li><a href="/bookmark/add/game/id/${gameInfo.getId()}">Add Bookmark</a></li>
				</c:otherwise>
			</c:choose>  
			<li>
				<form:form action="/post/review" method="POST" modelAttribute="reviewForm">
					<label>Rating:</label>
						<form:select path="rating">
							<option value="5">5</option>
							<option value="4">4</option>
							<option value="3">3</option>
							<option value="2">2</option>
							<option value="1">1</option>
						</form:select>
				<li>
					<label>Review:</label>
					<br>
					<form:textarea path="comment" placeholder="Enter comment here"/>
					<br>
					<form:errors path="comment" class="text-danger" style="color:red"/>
				</li>
				<li>
					<input type="submit" value="Submit review">
				</li>	
					<form:input type="hidden" value="${currentUser.getId()}" path="userEntity" title="User: ${currentUser.getUserName()}"/>
					<form:input type="hidden" value="${gameInfo.getId()}" path="gameEntity" title="Game: ${gameInfo.getTitle()}"/>
	 			</form:form>
			</li>
		
			<li> 
				<label>Game PV:</label>
				<br>
				<iframe width="560" height="315" src="https://www.youtube.com/embed/${gameInfo.getTrailerUrl()}" title="YouTube video player"></iframe>
			</li>
		</ul>
	</div>		
			<!-- Comment List -->
	<div id="game-comment">
		<div id="review-header">
			<h4>Review</h4>
			<label>View per Rating</label>
			<br>
			<label> total of <c:out value="${commentList.size()}"/> comments </label>
			<form action="/search/rating/${gameInfo.getId()}" method="GET">
				 <select name="rating-filter">
							<option value="6">All</option>
							<option value="5">5</option>
							<option value="4">4</option>
							<option value="3">3</option>
							<option value="2">2</option>
							<option value="1">1</option>
				</select> 
				<input type="submit" value="Search">
			</form>
			
		</div>
			<c:forEach var="commentLoop" items="${commentList}">
						 <ul class="comment-content">
							<li>
								<span class="username-comment"><c:out value="${commentLoop.getUserEntity().getUserName()}"/>
								</span><span class="comment-date"><c:out value="${commentLoop.getCreatedAt()}"/></span>
							</li>
							<li>Rating: <b><c:out value="${commentLoop.getRating()}"/> Star</b></li>
							
							<li class="user-comment">
								<p>- <c:out value="${commentLoop.getComment()}"/></p>
							</li>
						</ul>
					
			</c:forEach>
		
	</div>
</div>		<!-- master div end tag -->
	
</body>
</html>