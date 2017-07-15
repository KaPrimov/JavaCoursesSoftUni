package google;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private Company company;
    private List<Pokemon> pokemons;
    private List<Parent> parents;
    private List<Child> children;
    private Car car;

    public Person(String name) {
        this.name = name;
        this.pokemons = new ArrayList<>();
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void addChild(Child child){
        this.children.add(child);
    }

    public void addParent(Parent parent){
        this.parents.add(parent);
    }

    public void addPokemon(Pokemon pokemon){
        this.pokemons.add(pokemon);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(this.name).append(System.lineSeparator());
        output.append("Company:").append(System.lineSeparator());
        if (this.company != null){
            output.append(this.company);
            output.append(System.lineSeparator());
        }

        output.append("Car:").append(System.lineSeparator());
        if (this.car != null){
            output.append(this.car);
            output.append(System.lineSeparator());
        }

        output.append("Pokemon:").append(System.lineSeparator());
        for (Pokemon pokemon : pokemons) {
            output.append(pokemon);
            output.append(System.lineSeparator());
        }
        output.append("Parents:").append(System.lineSeparator());
        for (Parent parent : parents) {
            output.append(parent);
            output.append(System.lineSeparator());
        }
        output.append("Children:").append(System.lineSeparator());
        for (Child child : children) {
            output.append(child);
            output.append(System.lineSeparator());
        }

        return output.toString();
    }
}
