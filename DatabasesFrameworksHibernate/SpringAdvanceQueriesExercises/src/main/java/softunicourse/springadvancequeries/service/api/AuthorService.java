package softunicourse.springadvancequeries.service.api;

import softunicourse.springadvancequeries.domain.Author;

import java.util.List;

public interface AuthorService<Author, Long> extends ServiceInterface<Author, Long> {
    List<softunicourse.springadvancequeries.domain.Author> findAllAuthorsFirstNameEndingWith(String suffix);

    List<Object[]> totalBookCopies();

    Integer getAuthorsBooksCountProcedure(String firstName, String lastName);
}
