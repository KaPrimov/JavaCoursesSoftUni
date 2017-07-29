package softunicourse.springadvancequeries.service.api;

import softunicourse.springadvancequeries.domain.Category;
import softunicourse.springadvancequeries.domain.enums.AgeRestriction;
import softunicourse.springadvancequeries.dto.BookDto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BookService<Book, Long> extends ServiceInterface<Book, Long> {


    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllGoldPopularBooks();

    List<Book> finnBooksLessThan5AndMoreThan40();

    List<Book> findAllBooksReleaseDateYearNotEqualsTo(int year);

    List<softunicourse.springadvancequeries.domain.Book> findAllByCategoriesIn(Set<String> categories);

    List<softunicourse.springadvancequeries.domain.Book> findAllByReleaseDateBefore(Date releaseDate);

    List<softunicourse.springadvancequeries.domain.Book> findAllBooksEndingWith(String ending);

    List<softunicourse.springadvancequeries.domain.Book> findAllBooksWithAuthorsLastNameStartingWith(String ending);

    Integer countAllBooksWithTitleLongerThan(int length);

    Iterable<Object[]> findCountOfBooksByCategories();

    Iterable<softunicourse.springadvancequeries.domain.Book> findTop3ByCategoriesOrderByReleaseDate(Category category);

    BookDto findByTitle(String title);

    int increaseBookCopies(int copies, Date date);

    Integer removeBookWithCopiesLessThan(int copies);
}
