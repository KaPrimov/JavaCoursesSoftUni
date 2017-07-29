package softunicourse.springadvancequeries.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softunicourse.springadvancequeries.dao.api.AuthorsDao;
import softunicourse.springadvancequeries.domain.Author;
import softunicourse.springadvancequeries.service.api.AuthorService;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService<Author, Long> {
    private AuthorsDao authorsDao;

    @Autowired
    public AuthorServiceImpl(AuthorsDao authorsDao) {
        this.authorsDao = authorsDao;
    }

    @Override
    public Author findById(Long id) {
        return this.authorsDao.findOne(id);
    }

    @Override
    public void remove(Author object) {
        this.authorsDao.delete(object);
    }

    @Override
    public List<Author> findAll(Class<Author> object) {
        return this.authorsDao.findAll();
    }

    @Override
    public void save(Author object) {
        this.authorsDao.save(object);
    }

    @Override
    public List<Author> findAllAuthorsFirstNameEndingWith(String suffix) {
        return this.authorsDao.findAllByFirstNameEndingWith(suffix);
    }

    @Override
    public List<Object[]> totalBookCopies() {
        return this.authorsDao.totalBookCopies();
    }

    @Override
    public Integer getAuthorsBooksCountProcedure(String firstName, String lastName) {
        return this .authorsDao.getAuthorsBooksCountProcedure(firstName, lastName);
    }
}
