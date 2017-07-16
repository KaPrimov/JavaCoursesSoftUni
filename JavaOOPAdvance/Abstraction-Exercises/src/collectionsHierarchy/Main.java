package collectionsHierarchy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = reader.readLine().split(" ");

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyList myList = new MyList();

        for (int i = 0; i < strings.length; i++) {
            System.out.print(addCollection.add(strings[i]) + " ");
        }

        System.out.println();

        for (int i = 0; i < strings.length; i++) {
            System.out.print(addRemoveCollection.add(strings[i]) + " ");
        }

        System.out.println();

        for (int i = 0; i < strings.length; i++) {
            System.out.print(myList.add(strings[i]) + " ");
        }

        System.out.println();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            System.out.print(addRemoveCollection.remove() + " ");
        }

        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(myList.remove() + " ");
        }
    }
}
