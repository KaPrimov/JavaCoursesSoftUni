package arrGenerator;

public class Main {
    public static void main(String[] args) {
        Object[] strings =ArrayCreator.create(10, "none");
        System.out.println(strings.length);
        Integer[] integers = ArrayCreator.create(Integer.class, 10, 5);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}
