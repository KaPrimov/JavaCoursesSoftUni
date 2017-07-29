package app.services.api;

import java.util.List;

public interface BookService<Book, Long> extends ServiceInterface<Book, Long> {

    List<String> findTitlesAfterYear(int year);

    public List<Book> findBooksFromAuthor(String firstName, String lastName);

}
