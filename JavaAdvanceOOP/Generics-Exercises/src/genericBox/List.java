package genericBox;

import java.util.ArrayList;
import java.util.Collections;

public class List<T> {

    private ArrayList<Box<T>> list;

    public List() {
        this.list = new ArrayList<>();
    }

    public void addBox(Box<T> box ){
        this.list.add(box);
    }

    public void swap(int index1, int index2) {
        Collections.swap(this.list, index1, index2);
    }

    public void print() {
        for (Box<T> tBox : list) {
            System.out.println(tBox);
        }
    }
}
