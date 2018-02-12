package com.softuni.books.daos;

import java.util.List;

import com.softuni.books.entities.Book;

public interface BookRepository {

    void saveBook(Book book);

    List<Book> getAllBooks();

    void deleteBookByTitle(String title);

    Book findBookByTitle(String title);
}
