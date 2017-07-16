package integrationTests;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Category {

    private String name;
    private Set<User> users;
    private Set<Category> subCategories;
    private Category parent;

    public Category(String name) {
        this.name = name;
        this.users = new HashSet<User>();
        this.subCategories = new LinkedHashSet<Category>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void transferUsers(Set<User> users) {
        this.users.addAll(users);
    }

    public void addCategory(Category category) {
        this.subCategories.add(category);
    }

    public void assignToCategory(Category category) {
        this.parent = category;
    }

    public void removeCategory() {
        if(this.users.size() > 0) {
            if(this.subCategories.size() > 0) {
                for (Category subCategory : subCategories) {
                    subCategory.transferUsers(this.users);
                    break;
                }
            }
        }
    }

    public Set<User> getUsers() {
        return Collections.unmodifiableSet(this.users);
    }

    public Set<Category> getSubCategories() {
        return Collections.unmodifiableSet(this.subCategories);
    }

    public Category getParent() {
        return this.parent;
    }
}
