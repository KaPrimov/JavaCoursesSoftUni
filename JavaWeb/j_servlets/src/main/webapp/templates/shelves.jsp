<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="header-partial.jsp"></jsp:include>
	<body>
	<jsp:include page="menu.jsp"></jsp:include>
    	<table class="tb">
		    <thead>
		        <th>Title</th>
		        <th>Author</th>
		        <th>Pages</th>
		        <th>Edit Book</th>
		        <th>Delete Book</th>
		    </thead>
		    <tbody>
		        <c:set var="books" value="${books}" />
		        <c:forEach var="book" items="${books}">
		            <tr>
		                <td>
		                    <c:out value="${book.title}"/>
		                </td>
		                <td>
		                	<c:out value="${book.author}"/>
	                	</td>
	                	<td>
		                	<c:out value="${book.pages}"/>
	                	</td>
		                <td>
		                    <a href="/shelves/edit/${book.title}">Edit</a>
		                </td>
		                <td>
		                    <a href="/shelves/delete/${book.title}">Delete</a>
		                </td>
		            </tr>
		        </c:forEach>
		    </tbody>
		</table>

    </body>
</html>