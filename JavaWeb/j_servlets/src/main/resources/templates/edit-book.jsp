<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="header-partial.jsp"></jsp:include>
	<body>
        <jsp:include page="menu.jsp"></jsp:include>
        <c:set var="books" value="${books}" />
        <form method="POST">
        	<label for="title">
        		Title
        	</label>
        	<input type="text" name="title" id="title" required value="${book.title}"/>
        	<label for="author">
        		Author
        	</label>
        	<input type="text" name="author" id="author" required value="${book.author}"/>
        	<label for="pages">
        		Pages
        	</label>
        	<input type="number" name="pages" id="pages" required value="${book.pages}"/>
        	<input type="submit" value="Add Book"/>
        </form>
    </body>
</html>