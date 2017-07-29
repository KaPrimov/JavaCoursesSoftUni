package softunicourse.springadvancequeries.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import softunicourse.springadvancequeries.domain.Author;
import softunicourse.springadvancequeries.domain.Book;
import softunicourse.springadvancequeries.domain.Category;
import softunicourse.springadvancequeries.domain.enums.AgeRestriction;
import softunicourse.springadvancequeries.domain.enums.EditionType;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    Book findByAuthor(Author author);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal moreThan);

    @Query("SELECT b FROM Book AS b WHERE year(b.releaseDate) <> :year")
    List<Book> findAllBooksReleaseDateYearNotEqualsTo(@Param("year") int year);

    @Query("SELECT b FROM Book AS b INNER JOIN b.categories AS c WHERE c.name IN :categories")
    List<Book> findDistinctByCategoriesIn(@Param("categories") Set<String> categories);

    List<Book> findAllByReleaseDateBefore(Date releaseDate);

    @Query("SELECT b FROM Book AS b WHERE lower(b.title) LIKE lower(concat('%', :ending, '%')) ")
    List<Book> findAllBooksEndingWith(@Param("ending") String ending);

    @Query("SELECT b FROM Book AS b INNER JOIN b.author AS a WHERE lower(a.lastName) LIKE lower(concat(:ending, '%')) " +
            "ORDER BY a.firstName, a.lastName, b.title")
    List<Book> findAllBooksWithAuthorsLastNameStartingWith(@Param("ending") String ending);

    @Query("SELECT count(*) FROM Book AS b WHERE length(b.title) > :num")
    Integer countAllBooksWithTitleLongerThan(@Param("num") int length);

    @Query(value = "SELECT c, COUNT(b) AS bookCount FROM Book AS b INNER JOIN b.categories AS c GROUP BY c.name ORDER BY COUNT(b) DESC ")
    Iterable<Object[]> findCountOfBooksByCategories();

    Iterable<Book> findTop3ByCategoriesOrderByReleaseDateDescTitleAsc(Category category);

    Book findBookByTitle(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book AS b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int increaseBookCopies(@Param("copies") int copies,@Param("date") Date date);


    @Modifying
    @Transactional
    @Query("DELETE FROM Book AS b WHERE b.copies < :copies ")
    Integer removeBookWithCopiesLessThan(@Param("copies") Integer copies);
}
