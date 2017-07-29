package app.services.api;

import java.util.List;

public interface AuthorService<Author, Long> extends ServiceInterface<Author, Long> {

    List<Author> findAuthorsWithBooksBeforeYear(int year);

    List<Object[]> getAllAuthorsOrderedByCountOfBooks();
}
