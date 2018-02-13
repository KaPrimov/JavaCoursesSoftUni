package com.softuni.books.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softuni.books.models.viewModels.ViewBookModel;
import com.softuni.books.services.BookService;
import com.softuni.books.services.BookServiceImpl;

@WebServlet("/shelves")
public class ShelfController extends HttpServlet {

	private BookService bookService;
	
	@Override
	public void init() throws ServletException {
		this.bookService = new BookServiceImpl();
		super.init();
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<ViewBookModel> viewBookModels = this.bookService.getAllBooks();
        request.setAttribute("books", viewBookModels);
        request.getRequestDispatcher("templates/shelves.jsp").forward(request, response);
    }
}
