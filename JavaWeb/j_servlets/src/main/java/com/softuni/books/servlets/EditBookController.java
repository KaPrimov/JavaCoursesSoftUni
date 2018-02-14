package com.softuni.books.servlets;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softuni.books.models.bindingModels.AddBookModel;
import com.softuni.books.models.viewModels.ViewBookModel;
import com.softuni.books.services.BookService;
import com.softuni.books.services.BookServiceImpl;

@WebServlet("/shelves/edit/*")
public class EditBookController extends HttpServlet {

	private BookService bookService;
	private ViewBookModel viewBookModel;
	@Override
	public void init() throws ServletException {
		this.bookService = new BookServiceImpl();
		super.init();
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tokens[] = request.getRequestURI().split("/");
        String title = URLDecoder.decode(tokens[4], "UTF-8");
        ViewBookModel viewBookModel = this.bookService.findBookByTitle(title);
        if(viewBookModel != null){
        	this.viewBookModel = viewBookModel;        	
        }
        request.setAttribute("book", this.viewBookModel);
        request.getRequestDispatcher("/templates/edit-book.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 String title = req.getParameter("title");
         String author = req.getParameter("author");
         String pagesString = req.getParameter("pages");
         String oldTitle = req.getParameter("oldTitle");
         if (title != null && author != null && pagesString != null) {
             this.bookService.deleteBookByTitle(oldTitle);
             this.bookService.saveBook(new AddBookModel(title, author, Integer.parseInt(pagesString)));
             resp.sendRedirect("/book-shop/shelves");
         }
    }
}
