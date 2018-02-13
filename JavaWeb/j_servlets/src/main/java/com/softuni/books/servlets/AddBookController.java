package com.softuni.books.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softuni.books.models.bindingModels.AddBookModel;
import com.softuni.books.services.BookService;
import com.softuni.books.services.BookServiceImpl;

@WebServlet("/add")
public class AddBookController extends HttpServlet {

	private BookService bookService;
	
	@Override
	public void init() throws ServletException {
		this.bookService = new BookServiceImpl();
		super.init();
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("templates/add-book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String pagesString = req.getParameter("pages");
        if (title != null && author != null && pagesString != null) {
            this.bookService.saveBook(new AddBookModel(title, author, Integer.parseInt(pagesString)));
            resp.sendRedirect("/book-shop/add");
        }
    }
}
