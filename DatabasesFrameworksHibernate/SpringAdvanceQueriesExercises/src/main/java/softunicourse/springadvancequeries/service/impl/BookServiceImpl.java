package softunicourse.springadvancequeries.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softunicourse.springadvancequeries.dao.api.BookDao;
import softunicourse.springadvancequeries.domain.Book;
import softunicourse.springadvancequeries.domain.Category;
import softunicourse.springadvancequeries.domain.enums.AgeRestriction;
import softunicourse.springadvancequeries.domain.enums.EditionType;
import softunicourse.springadvancequeries.dto.BookDto;
import softunicourse.springadvancequeries.service.api.BookService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService<Book, Long> {

    private BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book findById(Long id) {
        return this.bookDao.findOne(id);
    }

    @Override
    public void remove(Book object) {
        this.bookDao.delete(object);
    }

    @Override
    public List<Book> findAll(Class<Book> object) {
        return this.bookDao.findAll();
    }

    @Override
    public void save(Book object) {
        this.bookDao.save(object);
    }

    @Override
    public List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookDao.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> findAllGoldPopularBooks() {
        return this.bookDao.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000);
    }
    @Override
    public List<Book> finnBooksLessThan5AndMoreThan40() {
        return this.bookDao.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
    }
    @Override
    public List<Book> findAllBooksReleaseDateYearNotEqualsTo(int year) {
       return this.bookDao.findAllBooksReleaseDateYearNotEqualsTo(year);
    }

    @Override
    public List<Book> findAllByCategoriesIn(Set<String> categories) {
        return this.bookDao.findDistinctByCategoriesIn(categories);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(Date releaseDate) {
        return this.bookDao.findAllByReleaseDateBefore(releaseDate);
    }

    @Override
    public List<Book> findAllBooksEndingWith(String ending) {
        return this.bookDao.findAllBooksEndingWith(ending);
    }

    @Override
    public List<Book> findAllBooksWithAuthorsLastNameStartingWith( String ending) {
        return this.bookDao.findAllBooksWithAuthorsLastNameStartingWith(ending);
    }

    @Override
    public Integer countAllBooksWithTitleLongerThan(int length) {
        return this.bookDao.countAllBooksWithTitleLongerThan(length);
    }
    @Override
    public Iterable<Object[]> findCountOfBooksByCategories() {
        return this.bookDao.findCountOfBooksByCategories();
    }
    @Override
    public Iterable<Book> findTop3ByCategoriesOrderByReleaseDate(Category category) {
        return this.bookDao.findTop3ByCategoriesOrderByReleaseDateDescTitleAsc(category);
    }

    @Override
    public BookDto findByTitle(String title) {
        Book book = bookDao.findByTitle(title);
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAgeRestriction(book.getAgeRestriction());
        bookDto.setEditionType(book.getEditionType());
        bookDto.setPrice(book.getPrice());
        return bookDto;
    }

    @Override
    public int increaseBookCopies(int copies, Date date) {
        return this.bookDao.increaseBookCopies(copies, date);
    }

    @Override
    public Integer removeBookWithCopiesLessThan(int copies) {
        return this.bookDao.removeBookWithCopiesLessThan(copies);
    }
}
