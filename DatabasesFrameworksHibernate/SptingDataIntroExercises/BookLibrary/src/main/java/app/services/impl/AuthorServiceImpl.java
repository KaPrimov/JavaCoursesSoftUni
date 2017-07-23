package app.services.impl;

import app.entities.Author;
import app.repositories.AuthorRepository;
import app.services.api.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService<Author, Long> {

    private AuthorRepository dao;

    @Autowired
    public AuthorServiceImpl(AuthorRepository bookRepository) {
        this.dao = bookRepository;
    }

    @Override
    public Author findByID(Long id) {
        return this.dao.findOne(id);
    }

    @Override
    public void remove(Author object) {
        this.dao.delete(object);
    }

    @Override
    public List<Author> findAll() {
        return this.dao.findAll();
    }

    @Override
    public void save(Author object) {
        this.dao.save(object);
    }

    @Override
    public List<Author> findAuthorsWithBooksBeforeYear(int year) {
        return this.dao.findAuthorsWithBookReleasedBeforeYear(year);
    }

    @Override
    public List<Object[]> getAllAuthorsOrderedByCountOfBooks() {
        return this.dao.getAllAuthorsOrderedByBooksCount();
    }


}
