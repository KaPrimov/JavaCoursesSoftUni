package collectionsHierarchy;

import java.util.LinkedList;
import java.util.List;

public class AddCollection implements IAddCollection {
    private List<String> list;

    public AddCollection() {
        this.list = new LinkedList<>();
    }

    @Override
    public int add(String element) {
        list.add(element);
        return list.size()-1;
    }
}
