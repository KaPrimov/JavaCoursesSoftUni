package app.dao.api;

import app.domain.Author;

public interface AuthorsDao extends Dao {

    Author findAuthorByFirstName(String firstName);

    Author findAuthorByLastName(String lastName);

}
