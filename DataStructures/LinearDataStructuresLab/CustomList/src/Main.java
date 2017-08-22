public class Main {
    public static void main(String[] args) {
        CustomList<Integer> ints = new CustomList<>();

        ints.add(4);
        ints.add(5);
        ints.add(6);

        ints.add(7);
        ints.add(5);
        ints.add(5);

        ints.add(4);
        ints.add(5);
        ints.add(5);

        ints.add(4);
        ints.add(5);
        ints.add(5);

        ints.removeAt(2);
        ints.removeAt(2);
        ints.removeAt(2);
        ints.removeAt(2);
        ints.removeAt(2);
        ints.removeAt(2);
        ints.removeAt(2);
        ints.removeAt(2);
        ints.removeAt(2);
        ints.removeAt(2);
        ints.removeAt(2);
        System.out.println(ints.getCount());

        System.out.println(ints);
    }
}
