<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="com.softuni.books.models.bindingModels.LoginModel" %>
<ul class="list-style">
    <li class="list-item"><a href="/book-shop">Home</a></li>    
    <%    	
        LoginModel loginModel = (LoginModel) session.getAttribute("LOGIN_MODEL");
    	if (loginModel != null) {
	   		String username = loginModel.getUsername();
	  	}
    %>
    <c:set var="username" value="${username}" scope="session"/>
    <c:choose>
        <c:when test="${username != null}">
            <li class="list-item"><a href="/book-shop/signout">Sign Out(${username})</a></li>        	
            <li class="list-item"><a href="/book-shop/add">Add Book</a></li>
    		<li class="list-item"><a href="/book-shop/shelves">Shelves</a></li>
        </c:when>
        <c:otherwise>
            <li class="list-item"><a href="/book-shop/signup">Sign Up</a></li>
    	<li class="list-item"><a href="/book-shop/signin">Sign In</a></li>
        </c:otherwise>
    </c:choose>
</ul>

