package com.softuni.books.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softuni.books.models.bindingModels.LoginModel;
import com.softuni.books.services.UserService;
import com.softuni.books.services.UserServiceImpl;

@WebServlet("/signin")
public class SignInController extends HttpServlet {

	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		this.userService = new UserServiceImpl();
		super.init();
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginModel loginModel = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username != null && password != null){
            if(this.userService.findByUsernameAndPassword(username, password) != null) {
            	loginModel = new LoginModel(username, password);
            }
        }

        if(loginModel != null) {
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_MODEL", loginModel);
            session.setAttribute("username", username);
            response.sendRedirect("/book-shop");
        } else {
            response.sendRedirect("/book-shop/signin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("templates/signin.jsp").forward(request,response);
    }
}
