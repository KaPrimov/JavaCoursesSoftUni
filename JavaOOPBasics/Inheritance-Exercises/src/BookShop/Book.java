package BookShop;

public class Book {
    private String author;
    private String title;
    private double price;

    public Book(String author, String title, double price) {
        setAuthor(author);
        setTitle(title);
        setPrice(price);
    }

    private String getAuthor() {
        return author;
    }

    private String getTitle() {
        return title;
    }

    protected double getPrice() {
        return price;
    }

    private void setAuthor(String author) throws IllegalArgumentException {
        if(author == null || !this.isAuthorNameValid(author)){
            throw new IllegalArgumentException("Author not valid!");
        }
        this.author = author;
    }

    private void setTitle(String title) {
        if (title == null || title.trim().length() < 3) {
            throw new IllegalArgumentException("Title not valid!");
        }

        this.title = title;
    }

    private void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price not valid!");
        }

        this.price = price;
    }

    private boolean isAuthorNameValid(String author) {
        String[] authorParams = author.split("\\s+");
        if (authorParams.length > 1 && Character.isDigit(authorParams[1].charAt(0))){
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Type: ").append(this.getClass().getSimpleName())
                .append(System.lineSeparator())
                .append("Title: ").append(this.getTitle())
                .append(System.lineSeparator())
                .append("Author: ").append(this.getAuthor())
                .append(System.lineSeparator())
                .append("Price: ").append(this.getPrice())
                .append(System.lineSeparator());
        return sb.toString();

    }
}
