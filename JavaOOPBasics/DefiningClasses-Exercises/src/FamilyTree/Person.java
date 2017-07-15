package FamilyTree;

import java.util.LinkedList;
import java.util.List;

public class Person {
    private String name;
    private String date;
    private List<Person> children;
    private List<Person> parents;

    public Person(String firstName, String lastName, String date) {
        this.name = firstName + " " + lastName;
        this.date = date;
        this.children = new LinkedList<>();
        this.parents = new LinkedList<>();
    }

    public Person(String date) {
        this(null, null, date);
    }

    public Person(String firstName, String lastName) {
        this(firstName, lastName, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addChild(Person child) {
        this.getChildren().add(child);
    }

    public void addParent(Person parent) {
        this.getParents().add(parent);
    }

    public List<Person> getChildren() {
        return children;
    }

    public List<Person> getParents() {
        return parents;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append(name).append(" ").append(date).append(System.lineSeparator());
        answer.append("Parents:").append(System.lineSeparator());
        parents.forEach( p -> answer.append(p.getName()).append(" ").append(p.getDate()).append(System.lineSeparator()));
        answer.append("Children:").append(System.lineSeparator());
        children.forEach(c -> answer.append(c.getName()).append(" ").append(c.getDate()).append(System.lineSeparator()));
        return answer.toString();
    }
}
