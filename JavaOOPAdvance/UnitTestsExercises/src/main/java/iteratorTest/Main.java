package iteratorTest;

import javax.naming.OperationNotSupportedException;

public class Main {
    public static void main(String[] args) throws OperationNotSupportedException {
        ListIterator<String> listIterator = new ListIterator<String>(new String[] {"Gosho", "Pesho", "Ivan"});

        listIterator.print();
        listIterator.move();
        System.out.println(listIterator.hasNext());
        listIterator.move();
        listIterator.move();
        listIterator.print();
        System.out.println(listIterator.hasNext());
    }
}
