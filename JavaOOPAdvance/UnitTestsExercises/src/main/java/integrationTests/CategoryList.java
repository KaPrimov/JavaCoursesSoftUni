package integrationTests;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CategoryList {

    private Map<String, Category> categoriesMap;
    private Map<String, User> usersMap;

    public CategoryList() {
        this.categoriesMap = new HashMap<String, Category>();
        this.usersMap = new HashMap<String, User>();
    }

    public void addCategory(String name) {
        Category category = new Category(name);
        this.categoriesMap.put(name, category);
    }

    public void addSubCategory(String categoryToAdd, String categoryToAddTo) {
        if(!this.categoriesMap.containsKey(categoryToAdd) || !this.categoriesMap.containsKey(categoryToAddTo)) {
            throw new IllegalArgumentException("One of the categories does not exist");
        }
        this.categoriesMap.get(categoryToAddTo).addCategory(this.categoriesMap.get(categoryToAdd));
        this.categoriesMap.get(categoryToAdd).assignToCategory(this.categoriesMap.get(categoryToAddTo));
    }

    public void removeCategory(String name) {
        this.categoriesMap.get(name).removeCategory();
        this.categoriesMap.remove(name);
    }

    public void addUserToCategory(String categoryName, String username) {
        if(!this.categoriesMap.containsKey(categoryName)) {
            throw new IllegalArgumentException("The category does not exist");
        }
        User user = null;
        if (!usersMap.containsKey(username)) {
            user = new User(username);
            this.usersMap.put(username, user);
        } else {
            user = this.usersMap.get(username);
        }
        this.categoriesMap.get(categoryName).addUser(user);
        user.addCategory(this.categoriesMap.get(categoryName));
    }

    public Map<String, Category> getCategoriesMap() {
        return Collections.unmodifiableMap(this.categoriesMap);
    }

    public Map<String, User> getUsersMap() {
        return Collections.unmodifiableMap(this.usersMap);
    }
}
