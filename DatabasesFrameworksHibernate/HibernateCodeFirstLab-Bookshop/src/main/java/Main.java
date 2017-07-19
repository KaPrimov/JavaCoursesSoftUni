import app.domain.Author;
import app.domain.Book;
import app.domain.Category;
import app.service.impl.AuthorServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static final AuthorServiceImpl authorService = new AuthorServiceImpl();

    public static void main(String[] args) {
        Author author = new Author("Karl", "Mai");
        Book vinetu1 = new Book("Vinety 1", author);
        Book vinetu2 = new Book("Vinety 2", author);
        Set<Category> categories = new HashSet<>();

        categories.add(new Category("Adventure"));
        categories.add(new Category("Wild West"));
        categories.add(new Category("Indians"));

        vinetu1.setCategories(categories);

        Set<Book> books = new HashSet<>();

        books.add(vinetu1);
        books.add(vinetu2);

        author.setBooksByAuthor(books);

        authorService.save(author);
        List<Author> authors = authorService.findAll(Author.class);

        System.out.println(authors);
    }
}
