package app.dao.api;

import app.domain.Author;
import app.domain.Book;

public interface BookDao extends Dao {

    Book findByTitle(String title);

    Book findByAuthor(Author author);

}
