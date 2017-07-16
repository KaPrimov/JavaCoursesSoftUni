package integrationTests;


public class Main {
    public static void main(String[] args) {
        CategoryList categoryList = new CategoryList();
        categoryList.addCategory("games");
        categoryList.addCategory("movies");
        categoryList.addCategory("plays");
        categoryList.addUserToCategory("games", "pesho");
        categoryList.addUserToCategory("games", "gosho");
        categoryList.addSubCategory("movies", "games");
        categoryList.removeCategory("games");
    }
}
