package app.runners;

import app.entities.Author;
import app.entities.Book;
import app.entities.Category;
import app.enums.AgeRestriction;
import app.enums.EditionType;
import app.services.api.AuthorService;
import app.services.api.BookService;
import app.services.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private BookService<Book, Long> bookService;

    @Autowired
    private AuthorService<Author, Long> authorService;

    @Autowired
    private CategoryService<Category, Long> categoryService;

    @Override
    public void run(String... strings) throws Exception {

        // Problem 2 seed Data
        //this.seedDatabase();

        //Problem 3:
        //WriteQueries();

        //Problem 4:
        //RelatedBooks();

    }

    private void RelatedBooks() {
        List<Book> books = bookService.findAll();
        List<Book> threeBooks = books.subList(0, 3);

        threeBooks.get(0).getRelatedBooks().add(threeBooks.get(1));
        threeBooks.get(1).getRelatedBooks().add(threeBooks.get(0));
        threeBooks.get(0).getRelatedBooks().add(threeBooks.get(2));
        threeBooks.get(2).getRelatedBooks().add(threeBooks.get(0));

        for (Book book : threeBooks) {
            bookService.save(book);
        }

        for (Book book : threeBooks) {
            System.out.printf("--%s\n", book.getTitle());
            for (Book relatedBook : book.getRelatedBooks()) {
                System.out.println(relatedBook.getTitle());
            }
        }
    }

    private void WriteQueries() {
        List<String> booksAfter2000 = this.bookService.findTitlesAfterYear(2000);
        booksAfter2000.forEach(System.out::println);

        List<Author> authorsWithBooksBeforeYear = this.authorService.findAuthorsWithBooksBeforeYear(1990);
        authorsWithBooksBeforeYear.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));

        List<Object[]> authorsOrderedByNumberOfBooks = this.authorService.getAllAuthorsOrderedByCountOfBooks();
        authorsOrderedByNumberOfBooks.forEach(a -> System.out.printf("%s %s %s%n", a[0], a[1], a[2]));

        this.bookService.findBooksFromAuthor("George", "Powell").forEach(b -> System.out.printf("%s %s %d%n", b.getTitle(), new SimpleDateFormat("dd-MM-yyyy").format(b.getReleaseDate()) , b.getCopies()));
    }

    private void seedDatabase() throws IOException, ParseException {

        List<Author> authors = new ArrayList<>();

        BufferedReader authorsReader = new BufferedReader(new FileReader("src/main/resources/authors.txt"));
        String line = authorsReader.readLine();
        while ((line = authorsReader.readLine()) != null) {
            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authors.add(author);

            authorService.save(author);
        }

        List<Category> categories = new ArrayList<>();

        BufferedReader categoriesReader = new BufferedReader(new FileReader("src/main/resources/categories.txt"));
        while ((line = categoriesReader.readLine()) != null) {
            Category category = new Category();
            category.setName(line);

            categories.add(category);

            categoryService.save(category);
        }

        Random random = new Random();

        BufferedReader booksReader = new BufferedReader(new FileReader("src/main/resources/books.txt"));
        line = booksReader.readLine();
        while ((line = booksReader.readLine()) != null) {
            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);

            Set<Category> bookCategories = new HashSet<>();

            for (int i = 0; i < random.nextInt(categories.size()); i++) {
                int categoryIndex = random.nextInt(categories.size());
                bookCategories.add(categories.get(categoryIndex));
            }
            book.setCategories(bookCategories);

            bookService.save(book);
        }
    }
}
