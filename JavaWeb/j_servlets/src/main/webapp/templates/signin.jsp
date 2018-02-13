<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="header-partial.jsp"></jsp:include>
	<body>
        <jsp:include page="menu.jsp"></jsp:include>
        <form method="POST">
        	<label for="username">
        		Username
        	</label>
        	<input type="text" name="username" id="username" required/>
        	<label for="password">
        		Password
        	</label>
        	<input type="password" name="password" id="password" required/>
        	<input type="submit" value="Sign In"/>
        </form>
    </body>
</html>