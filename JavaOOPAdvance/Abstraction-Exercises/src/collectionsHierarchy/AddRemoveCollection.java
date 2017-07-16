package collectionsHierarchy;

import java.util.LinkedList;

public class AddRemoveCollection extends AddCollection implements IAddRemoveCollection {

    LinkedList<String> list;

    public AddRemoveCollection() {
        list = new LinkedList<>();
    }

    @Override
    public int add(String element) {
        list.addFirst(element);
        return 0;
    }

    @Override
    public String remove() {
        return list.removeLast();
    }
}
