package genericBox;

public class Box<T> {
    private T data;

    public Box(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", data.getClass().toString().replace("class ", ""), data  );
    }
}
