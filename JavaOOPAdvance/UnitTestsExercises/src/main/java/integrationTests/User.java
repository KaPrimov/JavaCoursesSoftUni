package integrationTests;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {

    private String name;
    private Set<Category> categories;

    public User(String name) {
        this.name = name;
        this.categories = new HashSet<Category>();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(this.categories);
    }
}
