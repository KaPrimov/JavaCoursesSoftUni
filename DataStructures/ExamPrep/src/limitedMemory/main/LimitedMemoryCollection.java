package limitedMemory.main;


import java.util.*;

public class LimitedMemoryCollection<K, V> implements ILimitedMemoryCollection<K, V> {

    private int capacity;
    private int count;
    private CustomLinkedList<K, V> orderOfPairs;
    private Map<K, Pair<K, V>> pairKeys;

    public LimitedMemoryCollection(int capacity) {
        this.capacity = capacity;
        this.orderOfPairs = new CustomLinkedList<>();
        this.pairKeys = new HashMap<>();
    }

    @Override
    public V get(K key) {
        if(!this.pairKeys.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        Pair<K, V> node = this.pairKeys.get(key);
        this.orderOfPairs.remove(node);
        this.orderOfPairs.addFirst(node);
        return node.getValue();
    }

    @Override
    public void set(K key, V value) {
        if(this.pairKeys.containsKey(key)) {
            Pair<K, V> kvPair = this.pairKeys.get(key);
            kvPair.setValue(value);
            this.moveForward(kvPair);
        } else {
            Pair<K, V> kvPair = new Pair<>(key, value);
            if (this.count >= this.capacity) {
                Pair<K, V> toDelete = this.orderOfPairs.removeLast();
                this.pairKeys.remove(toDelete.getKey());
            }
            this.orderOfPairs.addFirst(kvPair);
            this.pairKeys.put(kvPair.getKey(), kvPair);
            count++;
        }
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return this.orderOfPairs.iterator();
    }

    private void moveForward(Pair<K, V> kvPair) {
        this.orderOfPairs.remove(kvPair);
        this.orderOfPairs.addFirst(kvPair);
    }
}
