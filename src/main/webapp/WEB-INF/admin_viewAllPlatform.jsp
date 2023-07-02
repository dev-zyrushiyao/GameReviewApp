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
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
	
	<div id="back-button">
		<a href="/admin/commands" style="text-decoration:none;">
			<span id="back-arrow">&#8249;</span>
			<span id="back-link"> Go back</span>
		</a>
	</div>
	
		<p class="h1" style="text-align:center;">Platform DataList</p>
		<label style="text-align:center; color:red;"><c:out value="${errorMsg}"/></label>
		
 	<table class="table table-striped tbl-size">
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
  <c:forEach var="DataList" items="${listOfPlatform}">
	    <tr class="tbl-data">
	      <th scope="row"> <c:out value="${DataList.getId()}"/></th>
	       <td><c:out value="${DataList.getPlatformName()}"/></td>
	       <td><c:out value="${DataList.getCreatedAt()}"/></td>
	       <td><c:out value="${DataList.getUpdatedAt()}"/></td>
	         <td>
	      
	         	<form action="/admin/update/platform/id/${DataList.getId()}">
	         		<input type="submit" class="btn btn-info" value="UPDATE">
	         	</form>
	         	
	         	<form:form action="/admin/delete/platform/${DataList.getId()}">
	         		<input type="hidden" name="_method" value="DELETE">
	         		<input type="submit" class="btn btn-danger"  value="DELETE" onClick="return confirm('Are you sure you want to delete [ID:' + ${DataList.getId()} + ']')">
	         	</form:form>
	         </td>
	    </tr>
	</c:forEach>
  </tbody>
</table>
 
 <p class="page-item-of"> Page <c:out value="${currentPage + 1}"/> of <c:out value="${totalPages}"/> </p>
<div aria-label="Page navigation" id="pagination-position">
  <ul class="pagination">
  
    <!--PREVIOUS: first page value is 0; currentPage - 1; if previous link will equal to -1 then display the first page link; else continue -1 on previous page link -->
    <li class="page-item">
      <c:set var="previousPage" value="${currentPage-1}"></c:set>
      	<c:if test="${previousPage == -1}">
	      	<a class="page-link" href="/admin/view/list/platform/page/0" aria-label="Previous">
	        	<span aria-hidden="true">« Previous</span>
	      	</a>
      	</c:if>
      	
      	<c:if test="${previousPage != -1}">
      		<a class="page-link" href="/admin/view/list/platform/page/${previousPage}" aria-label="Previous">
	        	<span aria-hidden="true">« Previous</span>
	      	</a>
      	</c:if>
    </li>
   
    <!-- Pages Content -->
	 <c:forEach begin="1" end="${totalPages}" step="1" varStatus="loop">
	 	<c:set var="pageCount" value="${loop.count-1}"></c:set> <!-- loop for pageTarget Route -->
   	 	<li class="page-item"><a class="page-link" href="/admin/view/list/platform/page/${pageCount}">${loop.count}</a></li>
	</c:forEach>
	

	<!-- NEXT -->
    <li class="page-item">
    	 <c:set var="nextPage" value="${currentPage+1}"></c:set>
      		<c:if test="${nextPage < totalPages}">
			   <a class="page-link" href="/admin/view/list/platform/page/${nextPage}" aria-label="Next">
			      <span aria-hidden="true"> Next »</span>
			    </a>
     		 </c:if>
     		 
     		 <c:if test="${nextPage == totalPages}">
     		 	<c:set var="endOfNextPage" value="${totalPages-1}"></c:set>
			   <a class="page-link" href="/admin/view/list/platform/page/${endOfNextPage}" aria-label="Next">
			      <span aria-hidden="true"> Next »</span>
			    </a>
     		 </c:if>
    </li>
  </ul>
  
</div>
 
</body>
</html>