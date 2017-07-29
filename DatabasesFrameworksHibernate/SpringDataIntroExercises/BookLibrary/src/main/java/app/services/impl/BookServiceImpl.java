package app.services.impl;

import app.entities.Book;
import app.repositories.BookRepository;
import app.services.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService<Book, Long> {

    private BookRepository dao;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.dao = bookRepository;
    }

    @Override
    public Book findByID(Long id) {
        return this.dao.findOne(id);
    }

    @Override
    public void remove(Book object) {
        this.dao.delete(object);
    }

    @Override
    public List<Book> findAll() {
        return this.dao.findAll();
    }

    @Override
    public void save(Book object) {
        this.dao.save(object);
    }

    @Override
    public List<String> findTitlesAfterYear(int year) {
        return this.dao.findAllTitlesAfterSpecificYear(year);
    }

    @Override
    public List<Book> findBooksFromAuthor(String firstName, String lastName) {
        return this.dao.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName);
    }
}
