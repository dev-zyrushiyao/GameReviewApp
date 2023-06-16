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
 <!-- borrowing CSS for NAV -->
 <link rel ="stylesheet" type="text/css" href="/css/dashboard-style.css">

<link rel="icon" type="image/x-icon" href="/icon/favicon.ico">	
</head>
<body>
	
	<!-- TO BE ADD VIEW BOOKMARKS + delete game -->
	<div id="sticky-header">
		<div id="header-flexbox">
			<a href="/">GO BACK</a> 
				<span class="link-separator"> / </span>
					<c:forEach var="userRole" items="${currentUser.getRoles()}">
						<c:if test="${userRole.getName().contains('ROLE_ADMIN')}">
							<a href="/admin/update/game/${gameInfo.getId()}">Update Game</a>
								<span class="link-separator"> / </span>
							 <a href="/admin/delete/game/id/${gameInfo.getId()}" onClick="return confirm('All data of the game including the comments will be deleted, are you sure you want to delete?')">Delete Game</a> 
								<span class="link-separator"> / </span>
						</c:if>
				</c:forEach>
				
			<a href="#game-section">Game Info</a>
				<span class="link-separator"> / </span>
			<a href="#pv-section">Trailer</a>
				<span class="link-separator"> / </span>
			<a href="#game_review_title">Reviews</a>
						<span class="link-separator"> / </span>
			<a href="/view/bookmark/">View Bookmarks</a>
		</div>
	</div>
	
	<div id="game-section">
		<div id="game-container">
			<ul>
				<li><label class="h1" title="Game Title" id="game_title"> <c:out value="${gameInfo.getTitle()}"/></label></li>
				<li><img src="${gameInfo.getImageUrl()}" style="object-fit: cover;" alt="video game cover" title="${gameInfo.getTitle()}">
				<li><label class="h4" title="Genre"> <c:out value="${gameInfo.getGenreEntity().getGenre()}"/></label></li>
				<li><label class="h4" title="Platform"> <c:out value="${gameInfo.getPlatformEntity().getPlatformName()}"/></label></li>
				<li>
					<c:if test="${currentUser.getGames().contains(gameInfo)}">
						<a style="color:blue;" href="/bookmark/remove/game/id/${gameInfo.getId()}" class="h3">Remove to bookmark</a>
					</c:if>
					
					<c:if test="${!currentUser.getGames().contains(gameInfo)}">
								<a style="color:blue;" href="/bookmark/add/game/id/${gameInfo.getId()}" class="h3">Add to bookmark</a>
					</c:if>
				</li>
			</ul>
			
			<div id="rate-section">
					<div class="container" style="text-align: center;" >
						<form:form action="/post/review" method="POST" modelAttribute="reviewForm">
							<label>Rate the game </label>
								<form:select path="rating">
									<option value="5">5 &#9733;</option>
									<option value="4">4 &#9733;</option>
									<option value="3">3 &#9733;</option>
									<option value="2">2 &#9733;</option>
									<option value="1">1 &#9733;</option>
								</form:select>
							<br>
							<form:textarea path="comment" class="container" style="width:50%;" placeholder="Enter comment here"/>
							<c:if test="${reviewForm != null}">
									<br>
								<form:errors path="comment" class="text-danger" style="color:red"/>
									<br>
							</c:if>
							<input type="submit" class="btn btn-primary" value="SUBMIT">
								<br>
							<form:input type="hidden" value="${currentUser.getId()}" path="userEntity" title="User: ${currentUser.getUserName()}"/>
							<form:input type="hidden" value="${gameInfo.getId()}" path="gameEntity" title="Game: ${gameInfo.getTitle()}"/>
			 			</form:form>
					</div>
			</div>
		
		</div>		
	</div>	

	
	<!-- PV  -->
	<div id="pv-section">
				<ul>
					<li> 
						<p id="game_pv_title" class="h1">Game PV:</p>
						<br>
						<iframe width="560" height="315" src="https://www.youtube.com/embed/${gameInfo.getTrailerUrl()}" title="YouTube video player"></iframe>
					</li>
				</ul>
	</div>
			
	
	<!-- Comment List -->
	<div id="game-comment">
		<div id="review-header">
			<p class="h1" id="game_review_title">Review <span id="comment-count"> (<c:out value="${commentList.size()}"/> comments)</span></p>
			<div id="rate-filter-div">
				<label>Filter by rating</label>
					<form action="/search/rating/${gameInfo.getId()}#game_review_title" method="GET"> <!-- Added Page Target at the end of URL (not included on controller ROUTE)  -->
						 <select name="rating-filter" id="rate-dropdown">
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
			
			
		</div>
			<c:forEach var="commentLoop" items="${commentList}">
					<c:forEach var="userRole" items="${currentUser.getRoles()}">
						<c:if test="${userRole.getName().contains('ROLE_ADMIN')}">	
							<div style="width:50%; margin:auto; position:relative; top:20px;">
							 	<a style="text-decoration:underline; color:blue;" href="/admin/update/review/${commentLoop.getId()}">EDIT</a> | <a style="text-decoration:underline; color:blue;" href="/admin/delete/review/${commentLoop.getId()}"> REMOVE</a>
							 </div>
						 </c:if>
					</c:forEach>
						 <ul class="comment-content">
							<li><span class="username-comment"><c:out value="${commentLoop.getUserEntity().getUserName()}"/></span><span class="comment-date"><c:out value="${commentLoop.getCreatedAt()}"/></span></li>
							<li>Rating: <b><c:out value="${commentLoop.getRating()}"/> Star</b></li>
							<li class="user-comment">
								<p>- <c:out value="${commentLoop.getComment()}"/></p>
							</li>
						</ul>
					
			</c:forEach>
	</div>
	


</body>
</html>