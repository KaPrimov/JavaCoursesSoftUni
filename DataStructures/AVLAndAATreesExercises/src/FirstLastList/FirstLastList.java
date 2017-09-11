package FirstLastList;

import java.util.*;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {
    private ArrayList<T> elements = new ArrayList<>();
    private Map<T, ArrayList<T>> elementsCount = new TreeMap<>();

    @Override
    public void add(T element) {
        this.elements.add(element);

        this.elementsCount.putIfAbsent(element, new ArrayList<T>());
        this.elementsCount.get(element).add(element);
    }


    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Iterable<T> first(int count) {

        if (count < 0 || count > this.elements.size()) {
            throw new IllegalArgumentException();
        }

        List<T> firstElements = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            firstElements.add(this.elements.get(i));
        }
        return firstElements;
    }

    @Override
    public Iterable<T> last(int count) {
        if (count < 0 || count > this.elements.size()) {
            throw new IllegalArgumentException();
        }

        List<T> lastElements = new ArrayList<>();

        for (int i = elements.size() - 1; count > 0 ; i--, count--) {
            lastElements.add(this.elements.get(i));
        }

        return lastElements;
    }

    @Override
    public Iterable<T> min(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }
        ArrayList<T> output = new ArrayList<>();

        if (count == 0) {
            return output;
        }

        for (T key : this.elementsCount.keySet()) {
            for (int i = 0; i < this.elementsCount.get(key).size() && count > 0; i++, count--) {
                if (count == 0) {
                    return output;
                }
                output.add(this.elementsCount.get(key).get(i));
            }
        }

        return output;
    }

    @Override
    public Iterable<T> max(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }
        Deque<T> output = new ArrayDeque<>();
        if (count == 0) {
            return output;
        }

        for (T key : this.elementsCount.keySet()) {
            for (int i = this.elementsCount.get(key).size() - 1; i >= 0  ; i--) {
                output.push(this.elementsCount.get(key).get(i));
            }
        }
        List<T> answer = new ArrayList<>();
        for (int i = 0; count > 0; i++, count--) {
            answer.add(output.pop());
        }
        return answer;
    }

    @Override
    public void clear() {
        this.elements.clear();
        this.elementsCount.clear();
    }

    @Override
    public int removeAll(T element) {
        if(!this.elementsCount.containsKey(element)) {
            return 0;
        }

        int count = this.elementsCount.get(element).size();

        this.elementsCount.remove(element);
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).compareTo(element) == 0) {
                this.elements.remove(i);
                i--;
            }
        }

        return count;
    }
}