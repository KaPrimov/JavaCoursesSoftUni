package g_DoublyLinkedList;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> ints = new DoublyLinkedList<>();
        ints.addFirst(1);
        ints.addFirst(2);
        ints.addFirst(3);
        ints.addLast(6);
        ints.addLast(7);
        System.out.println(ints.removeFirst() + " - deleted");
        Object[] elements = ints.toArray();

        for (Object anInt : elements) {
            System.out.println(anInt);
        }
    }
}
