package integrationTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTests {

    public static final String FIRST_USER_STRING = "Pesho";
    public static final String SECOND_USER_STRING = "Gosho";
    public static final String CATEGORY_STRING = "Games";
    public static final String SUBCATEGORY_STRING = "Advertise";
    private CategoryList categoryList;
    private String user1;
    private String user2;
    private String category;
    private String subCategory;

    @Before
    public void initializeObjects() {
        this.categoryList = new CategoryList();
        this.user1 = FIRST_USER_STRING;
        this.user2 = SECOND_USER_STRING;
        this.category = CATEGORY_STRING;
        this.subCategory = SUBCATEGORY_STRING;
    }

    @Test
    public void categoryCanBeAddedToTheList() {
        this.categoryList.addCategory(this.category);
        Assert.assertEquals("The category is not added", 1, this.categoryList.getCategoriesMap().size());
    }

    @Test
    public void subCategoryIsAddedToTheCategory() {
        this.categoryList.addCategory(this.category);
        this.categoryList.addCategory(this.subCategory);
        this.categoryList.addSubCategory(this.subCategory, this.category);
        Assert.assertEquals("The category is not added", 1,
                this.categoryList.getCategoriesMap().get(category).getSubCategories().size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenNonExistentSubCategoryIsAdded() {
        this.categoryList.addCategory(this.category);
        this.categoryList.addSubCategory(this.subCategory, this.category);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenNonExistentParentCategoryIsAdded() {
        this.categoryList.addCategory(this.subCategory);
        this.categoryList.addSubCategory(this.subCategory, this.category);
    }

    @Test
    public void userCanBeAddedToTheCategory() {
        this.categoryList.addCategory(this.category);
        this.categoryList.addUserToCategory(this.category, this.user1);
        Assert.assertEquals("User was not added", 1, this.categoryList.getCategoriesMap().get(this.category).getUsers().size());
    }

    @Test
    public void userCanBeAddedToSubCategory() {
        this.categoryList.addCategory(this.category);
        this.categoryList.addCategory(this.subCategory);
        this.categoryList.addSubCategory(this.subCategory, this.category);
        this.categoryList.addUserToCategory(this.subCategory, this.user1);
        Assert.assertEquals("User was not added", 1, this.categoryList.getCategoriesMap().get(this.subCategory).getUsers().size());
    }

    @Test
    public void categoryIsDeletedWhenIsRemoved() {
        this.categoryList.addCategory(this.category);
        Assert.assertEquals(1, this.categoryList.getCategoriesMap().size());
        this.categoryList.removeCategory(this.category);
        Assert.assertEquals(0, this.categoryList.getCategoriesMap().size());
    }

    @Test
    public void usersAreMovedWhenCategoryIsDeleted() {
        this.categoryList.addCategory(this.category);
        this.categoryList.addCategory(this.subCategory);
        this.categoryList.addSubCategory(this.subCategory, this.category);
        this.categoryList.addUserToCategory(this.category, this.user1);
        this.categoryList.addUserToCategory(this.category, this.user2);
        Assert.assertEquals("Users were not added", 2, this.categoryList.getCategoriesMap().get(this.category).getUsers().size());
        this.categoryList.removeCategory(this.category);
        Assert.assertEquals("Users were not moved", 2, this.categoryList.getCategoriesMap().get(this.subCategory).getUsers().size());
    }
}
