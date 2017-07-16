package collectionsHierarchy;

import java.util.LinkedList;

public class MyList extends AddRemoveCollection implements IMyList {

    LinkedList<String> list;

    public MyList() {
        list = new LinkedList<>();
    }

    @Override
    public int add(String element) {
        this.list.addFirst(element);
        return 0;
    }

    @Override
    public int used() {
        return list.size();
    }

    @Override
    public String remove() {
        return list.removeFirst();
    }
}
