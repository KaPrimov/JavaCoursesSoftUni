package pr02MultipleImplementation;

public interface Person extends Birthable, Identifiable {

    String getName();
    Integer getAge();
}
