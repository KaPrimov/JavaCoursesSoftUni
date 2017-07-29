package softunicourse.springadvancequeries.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softunicourse.springadvancequeries.domain.Author;

import java.util.List;

@Repository
public interface AuthorsDao extends JpaRepository<Author, Long> {

    Author findAuthorByFirstName(String firstName);

    Author findAuthorByLastName(String lastName);

    List<Author> findAllByFirstNameEndingWith(String firstName);

    @Query("SELECT concat(a.firstName, ' ', a.lastName), sum(b.copies) FROM Author AS a " +
            "INNER JOIN a.books AS b " +
            "GROUP BY a.firstName, a.lastName " +
            "ORDER BY sum(b.copies) DESC ")
    List<Object[]> totalBookCopies();

    @Procedure(name = "get_author_books_count")
    Integer getAuthorsBooksCountProcedure(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
