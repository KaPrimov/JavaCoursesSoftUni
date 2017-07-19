package app.service.api;

import app.domain.Book;

import java.util.List;

public interface BookService {

    Book bestSellerOfAllTimes();

    List<Book> mostExpensiveBooks(int count);

    Book findBookByTitle(String title);

}
