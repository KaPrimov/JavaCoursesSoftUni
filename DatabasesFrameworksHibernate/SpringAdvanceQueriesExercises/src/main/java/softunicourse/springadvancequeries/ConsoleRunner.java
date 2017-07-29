package softunicourse.springadvancequeries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import softunicourse.springadvancequeries.domain.Author;
import softunicourse.springadvancequeries.domain.Book;
import softunicourse.springadvancequeries.domain.Category;
import softunicourse.springadvancequeries.domain.enums.AgeRestriction;
import softunicourse.springadvancequeries.domain.enums.EditionType;
import softunicourse.springadvancequeries.service.api.AuthorService;
import softunicourse.springadvancequeries.service.api.BookService;
import softunicourse.springadvancequeries.service.api.CategoryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Transactional
public class ConsoleRunner implements CommandLineRunner {

    private AuthorService<Author, Long> authorService;

    private BookService<Book, Long> bookService;

    private CategoryService<Category, Long> categoryService;

    @Autowired
    public ConsoleRunner(AuthorService<Author, Long> authorService, BookService<Book, Long> bookService, CategoryService<Category, Long> categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... strings) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //this.seedDatabase();
        //1st task
        // String type = scanner.nextLine();
        // AgeRestriction ageRestriction = AgeRestriction.valueOf(type.toUpperCase());
        // this.bookService.findAllByAgeRestriction(ageRestriction).forEach(b -> System.out.println(b.getTitle()));

        //2nd task
        // this.bookService.findAllGoldPopularBooks().forEach(b -> System.out.println(b.getTitle()));

        //3rd Task
        // this.bookService.finnBooksLessThan5AndMoreThan40().forEach(b -> System.out.printf("%s - $%s%n", b.getTitle(), b.getPrice()));

        //4th Task
        //this.bookService.findAllBooksReleaseDateYearNotEqualsTo(scanner.nextInt())
        //    .forEach(b -> System.out.println(b.getTitle()));

        //5th Task
        //Set<Category> categories = Arrays.stream(scanner.nextLine().split("\\s+")) // With category objects
        //        .map(this.categoryService::findCategoryByName)
        //        .collect(Collectors.toSet());

        //Set<String> categories = Arrays.stream(scanner.nextLine().split("\\s+"))
        //      .collect(Collectors.toSet());

        //this.bookService.findAllByCategoriesIn(categories).forEach(b -> System.out.println(b.getTitle()));

        //6th Task
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //this.bookService.findAllByReleaseDateBefore(sdf.parse(scanner.nextLine()))
        //        .forEach(b -> System.out.println(b.getTitle()));

        //7th Task
        //this.authorService.findAllAuthorsFirstNameEndingWith(scanner.nextLine())
        //        .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));

        //8th Task
        //this.bookService.findAllBooksEndingWith("sK")
        //        .forEach(b -> System.out.println(b.getTitle()));

        // 9th Task'
        //this.bookService.findAllBooksWithAuthorsLastNameStartingWith("R")
        //        .forEach(b -> System.out.printf("%s (%s %s)%n",
        //                b.getTitle(),
        //                b.getAuthor().getFirstName(),
        //                b.getAuthor().getLastName()));

        //10th Task
        //System.out.println(this.bookService.countAllBooksWithTitleLongerThan(40));

        //11th Task
        //this.authorService.totalBookCopies().forEach(a -> {
        //    System.out.println(a[0] + " - " + a[1]);
        //});

        //12th Task
        //this.categoryService.findTotalProfitByCategory().forEach(c -> System.out.println(c[0] + " - $" + c[1]));

        //13th Task
       //Calendar calendar = Calendar.getInstance();
       //
       //Iterable<Object[]> obj = this.bookService.findCountOfBooksByCategories();
       //for (Object[] object : obj) {
       //    Category category = (Category) object[0];
       //    long bookCount = (long) object[1];
       //    System.out.printf("--%s: %d books%n", category.getName(), bookCount);
       //    Iterable<Book> books = this.bookService.findTop3ByCategoriesOrderByReleaseDate(category);
       //    for (Book book : books) {
       //        String title = book.getTitle();
       //        String bookYear = new SimpleDateFormat("yyyy").format(book.getReleaseDate());
       //        System.out.printf("%s (%s)%n", title, bookYear);
       //    }
       //}

        //14th Task
        //BookDto bookDto = this.bookService.findByTitle(scanner.nextLine());
        //System.out.printf("%s %s %s %s%n",
        //        bookDto.getTitle(),
        //        bookDto.getEditionType(),
        //        bookDto.getAgeRestriction(),
        //        bookDto.getPrice());

        //15th Task
        //SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        //Date date = sdf.parse(scanner.nextLine());
        //int copies = Integer.parseInt(scanner.nextLine());
        //int booksUpdated = this.bookService.increaseBookCopies(copies, date);
        //System.out.println(booksUpdated * copies);

        //16th Task
        //int copies = scanner.nextInt();
        //System.out.println(this.bookService.removeBookWithCopiesLessThan(copies));

        //17th Task
        String[] authorNames = scanner.nextLine().split("\\s+");
        System.out.println(this.authorService.getAuthorsBooksCountProcedure(authorNames[0],authorNames[1]));
    }

    private void seedDatabase() throws IOException, ParseException {
        List<Author> authors = this.seedAuthors();
        List<Category> categories = this.seedCategories();
        this.seedBooks(authors, categories);



    }

    private List<Author> seedAuthors() throws IOException {
        InputStream src = getClass().getResourceAsStream("/authors.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(src));
        String line = bufferedReader.readLine();
        List<Author> authorList = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] names = line.split("\\s+");
            String firstName = names[0];
            String lastName = names[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authorList.add(author);
            this.authorService.save(author);
        }
        return authorList;
    }

    private List<Category> seedCategories() throws IOException {
        InputStream src = getClass().getResourceAsStream("/categories.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(src));
        String line = bufferedReader.readLine();

        List<Category> categories = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            Category category = new Category();
            category.setName(line);

            categories.add(category);
            this.categoryService.save(category);
        }

        return categories;
    }

    private void seedBooks(List<Author> authors, List<Category> categories) throws IOException, ParseException {
        InputStream src = getClass().getResourceAsStream("/books.txt");
        BufferedReader booksReader = new BufferedReader(new InputStreamReader(src));
        String line = booksReader.readLine();
        Random random = new Random();
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

            Set<Category> booksCategory = new HashSet<>();

            for (int i = 0; i < 3; i++) {
                Category category = categories.get(random.nextInt(categories.size()));
                booksCategory.add(category);
            }

            book.setCategories(booksCategory);

            bookService.save(book);
        }
    }
}
