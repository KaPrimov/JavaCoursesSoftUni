package com.softuni.books.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.softuni.books.entities.Book;

public class BookRepositoryImpl implements BookRepository {

    private static BookRepositoryImpl bookRepository;

    private List<Book> books;

    private BookRepositoryImpl() {
        this.books = new ArrayList<>();
    }

    public static BookRepository getInstance() {
        if (bookRepository == null) {
            bookRepository = new BookRepositoryImpl();
        }

        return bookRepository;
    }

	public void saveBook(Book book) {
		this.books.add(book);

	}

	public List<Book> getAllBooks() {
		return this.books;
	}

	public void deleteBookByTitle(String title) {
		this.books.removeIf(b -> b.getTitle().equals(title));
	}

	public Book findBookByTitle(String title) {
		Optional<Book> book = this.books.stream().filter(b -> b.getTitle().equals(title)).findFirst();
		return book.isPresent() ? book.get() : null;
	}

}
