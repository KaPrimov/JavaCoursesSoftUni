package app.repositories;

import app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT DISTINCT a FROM Author AS a INNER JOIN a.books AS b WHERE year(b.releaseDate) > :year")
    List<Author> findAuthorsWithBookReleasedBeforeYear(@Param("year") int year);


    @Query("SELECT DISTINCT a.firstName, a.lastName, count(*) AS book_count FROM Author AS a " +
            "INNER JOIN a.books " +
            "GROUP BY a.firstName, a.lastName " +
            "ORDER BY book_count DESC ")
    List<Object[]> getAllAuthorsOrderedByBooksCount();

}
