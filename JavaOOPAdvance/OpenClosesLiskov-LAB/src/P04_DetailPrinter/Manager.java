package P04_DetailPrinter;

public class Manager extends Employee {

    private Iterable<String> documents;

    public Manager(String name, Iterable<String> documents) {
        super(name);
        this.documents = documents;
    }

    @Override
    public String toString() {
        return super.toString() + " Documents: " + this.documents;
    }
}
