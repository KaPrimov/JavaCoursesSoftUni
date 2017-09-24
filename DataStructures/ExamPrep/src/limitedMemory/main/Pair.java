package limitedMemory.main;

public class Pair<K, V> {
    private K key;
    private V value;
    Pair<K, V> nextNode;
    Pair<K, V> prevNode;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
