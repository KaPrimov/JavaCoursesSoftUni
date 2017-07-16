package GenericScale;

public class Main {
    public static void main(String[] args) {
        Scale<String> stringScale = new Scale<>("a", "c");
        System.out.println(stringScale.getHeavier());

        Scale<Integer> integerScale = new Scale<>(2, 2);
        System.out.println(integerScale.getHeavier());
    }
}
