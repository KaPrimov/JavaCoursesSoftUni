package com.softuni.books.servlets;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softuni.books.services.BookService;
import com.softuni.books.services.BookServiceImpl;

@WebServlet("/shelves/delete/*")
public class DeleteBookController extends HttpServlet {

	private BookService bookService;
	
	@Override
	public void init() throws ServletException {
		this.bookService = new BookServiceImpl();
		super.init();
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String tokens[] = request.getRequestURI().split("/");
        String title = URLDecoder.decode(tokens[4], "UTF-8");
        this.bookService.deleteBookByTitle(title);
        response.sendRedirect("/book-shop/shelves");
    }
}

