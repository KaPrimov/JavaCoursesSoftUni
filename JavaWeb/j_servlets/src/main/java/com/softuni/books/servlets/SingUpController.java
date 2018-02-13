package com.softuni.books.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softuni.books.models.bindingModels.LoginModel;
import com.softuni.books.services.UserService;
import com.softuni.books.services.UserServiceImpl;

@WebServlet("/signup")
public class SingUpController extends HttpServlet {
	
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		this.userService = new UserServiceImpl();
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("templates/signup.jsp").forward(request,response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    if(username != null && password != null){
	    	LoginModel loginModel = new LoginModel(username, password);
	        this.userService.createUser(loginModel);
	        response.sendRedirect("./signin");
	    }
	}


}
