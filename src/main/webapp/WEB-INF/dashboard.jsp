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
<title>Game Review</title>
 <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
 <link rel ="stylesheet" type="text/css" href="/css/dashboard-style.css">


<!-- GOOGLE API FONT -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

<div id="sticky-header">
	<div id="header-flexbox">
		<h1 id="user-greeting">Welcome, <span id="user"><c:out value="${currentUser.getUserName()}"/></span></h1>
			
	<c:forEach var="userRole" items="${currentUser.getRoles()}">
		<c:if test="${userRole.getName().contains('ROLE_ADMIN')}">
			<form action="/admin/commands" method="POST">
		        <input type="submit" class="btn btn-primary" id="admin-control-btn" value="ADMIN CONTROLS"/>
	    	</form>
		</c:if>
    </c:forEach>
		
		<form id="logoutForm" method="POST" action="/logout">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input type="submit" class="btn btn-primary" id="logout-btn" value="LOGOUT" />
    	</form>
	</div>
	
		<form action="/search/" method="GET">

			<Label>Filter:</Label>
	  	
			 <select name="genre_value">
			 		<option value="0" selected> ALL GENRE </option>
				 <c:forEach var="genreListLoop" items="${genreList}">
					<option  value="${genreListLoop.getId()}"> <c:out value="${genreListLoop.getGenre()}"/> </option>			
				</c:forEach> 
			</select>
			
			<select name="platform_value">
	 				<option value="0" selected> ALL PLATFORM </option>
				<c:forEach var="platformListLoop" items="${platformList}">
					<option value="${platformListLoop.getId()}"> <c:out value="${platformListLoop.getPlatformName()}"/> </option>			
				</c:forEach>
			</select> 

			<input type="submit" value="Search">
		</form>	
</div>
	
	<c:if test="${filterByInfo != null}">
				<p class="h3"> Games filter: [<c:out value="${filterByInfo}"/>]</p>
	</c:if>
	
	<c:if test="${filterByInfo == null}">
		<p class="h3" style="text-align:center; padding:5px;">List of all Games </p>
	</c:if>
	
	<div class="grid-box">		   
	
		<div class="grid-card">
 		    <c:choose>
		     	<%-- Default Table(DISPLAY ALL) if session is not yet exist--%>
				<c:when test="${genreValueSession == null && platformValueSession == null}">
						<c:forEach var="listOfGames" items="${gameList}">
								 <div class="grid-content">
						      		<label> 
						      			<a href="/view/game/info/id/${listOfGames.getId()}">
						      				<img src="${listOfGames.getImageUrl()}" alt="game-image-cover">
						      			</a>
						      		</label>
						      			<br>
									<label class="game-title">
										<a href="/view/game/info/id/${listOfGames.getId()}">
											<c:out value="${listOfGames.getTitle()}"/>
										</a>
									</label>
							      		<div class="game-info">
								      		<label><c:out value="${listOfGames.getGenreEntity().getGenre()}"/></label>
								      			<br>
								      		<label><c:out value="${listOfGames.getPlatformEntity().getPlatformName()}"/></label> 
							    		</div>
						    	</div>
				    	</c:forEach>	
				</c:when>
				
					<%-- Default Table(DISPLAY ALL) if both session has a value of Zero(Dropdown: ALL) --%>
				<c:when test="${genreValueSession.equals('0') && platformValueSession.equals('0')}">
						<c:forEach var="listOfGames" items="${gameList}">
								<div class="grid-content">
						      		<label> 
						      			<a href="/view/game/info/id/${listOfGames.getId()}">
						      				<img src="${listOfGames.getImageUrl()}" alt="game-image-cover">
						      			</a>
						      		</label>
						      			<br>
									<label class="game-title">
										<a href="/view/game/info/id/${listOfGames.getId()}">
											<c:out value="${listOfGames.getTitle()}"/>
										</a>
									</label>
							      		<div class="game-info">
								      		<label><c:out value="${listOfGames.getGenreEntity().getGenre()}"/></label>
								      			<br>
								      		<label><c:out value="${listOfGames.getPlatformEntity().getPlatformName()}"/></label> 
							    		</div>
						    	</div>
				    	</c:forEach>
				</c:when>
					
						<%-- Search Specific Genre + All Platform (throws different value of list to JSP)--%>
				<c:when test="${genreValueSession.equals(genreId.toString()) && platformValueSession.equals('0')}">
						<c:forEach var="listOfGames" items="${gameList}">
								<div class="grid-content">
						      		<label> 
						      			<a href="/view/game/info/id/${listOfGames.getId()}">
						      				<img src="${listOfGames.getImageUrl()}" alt="game-image-cover">
						      			</a>
						      		</label>
						      			<br>
									<label class="game-title">
										<a href="/view/game/info/id/${listOfGames.getId()}">
											<c:out value="${listOfGames.getTitle()}"/>
										</a>
									</label>
							      		<div class="game-info">
								      		<label><c:out value="${listOfGames.getGenreEntity().getGenre()}"/></label>
								      			<br>
								      		<label><c:out value="${listOfGames.getPlatformEntity().getPlatformName()}"/></label> 
							    		</div>
						    	</div>
				    	</c:forEach>
				</c:when>
					
					<%-- Search All Genre + Specific Platform  (throws different value of list to JSP)--%>
				<c:when test="${genreValueSession.equals('0') && platformValueSession.equals(platformId.toString())}">
						<c:forEach var="listOfGames" items="${gameList}">
								<div class="grid-content">
						      		<label> 
						      			<a href="/view/game/info/id/${listOfGames.getId()}">
						      				<img src="${listOfGames.getImageUrl()}" alt="game-image-cover">
						      			</a>
						      		</label>
						      			<br>
									<label class="game-title">
										<a href="/view/game/info/id/${listOfGames.getId()}">
											<c:out value="${listOfGames.getTitle()}"/>
										</a>
									</label>
							      		<div class="game-info">
								      		<label><c:out value="${listOfGames.getGenreEntity().getGenre()}"/></label>
								      			<br>
								      		<label><c:out value="${listOfGames.getPlatformEntity().getPlatformName()}"/></label> 
							    		</div>
						    	</div>
				    	</c:forEach>
				</c:when>
				
				
		 			<%-- Search Specific Genre + Specific Platform (throws different value of list to JSP) --%>
				<c:otherwise>
						<c:forEach var="listOfGames" items="${gameList}">
								<div class="grid-content">
						      		<label> 
						      			<a href="/view/game/info/id/${listOfGames.getId()}">
						      				<img src="${listOfGames.getImageUrl()}" alt="game-image-cover">
						      			</a>
						      		</label>
						      			<br>
									<label class="game-title">
										<a href="/view/game/info/id/${listOfGames.getId()}">
											<c:out value="${listOfGames.getTitle()}"/>
										</a>
									</label>
							      		<div class="game-info">
								      		<label><c:out value="${listOfGames.getGenreEntity().getGenre()}"/></label>
								      			<br>
								      		<label><c:out value="${listOfGames.getPlatformEntity().getPlatformName()}"/></label> 
							    		</div>
						    	</div>
				    	</c:forEach>
				</c:otherwise> 
	
			</c:choose>
		  </div>	
	</div>
	
</body>
</html>