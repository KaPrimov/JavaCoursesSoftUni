package app.service.impl;

import app.domain.Book;
import app.service.api.BookService;
import app.transaction.Command;
import app.transaction.MultiCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class BookServiceImpl extends AbstractService implements BookService {
    @Override
    public Book bestSellerOfAllTimes() {
        return (Book) runInTransaction(new Command() {
            @Override
            public Object execute() {
                List<Book> books = dao.findAll(Book.class);
                Book book = books.stream()
                        .sorted((a, b) -> Long.compare(b.getCopies(), a.getCopies()))
                        .findFirst().orElse(null);
                return book;
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> mostExpensiveBooks(int count) {
        return runInTransaction(new MultiCommand() {
            @Override
            public List execute() {
                List<Book> books = dao.findAll(Book.class);
                return books.stream()
                        .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                        .limit(5)
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        });
    }

    @Override
    public Book findBookByTitle(String title) {
        return (Book) runInTransaction(new Command() {
            @Override
            public Object execute() {
                List<Book> books = dao.findAll(Book.class);
                Book book = books.stream()
                        .filter(b -> title.equals(b.getTitle()))
                        .findFirst().orElse(null);
                return book;
            }
        });
    }
}
