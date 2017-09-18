import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashTable<TKey, TValue> implements Iterable<KeyValue<TKey, TValue>> {

    private static final int INITIAL_CAPACITY = 16;
    public static final double LOAD_FACTOR = 0.75;

    private int size;
    private int capacity;
    private List<KeyValue<TKey, TValue>>[] slots;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.slots = new LinkedList[capacity];
    }

    public void add(TKey key, TValue value) {
        this.growIfNeeded();
        int slotNumber = this.findSlotNumber(key);
        if(this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<TKey, TValue> element : this.slots[slotNumber]) {
            if(element.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exists " + key);
            }
        }
        KeyValue<TKey, TValue> newElement = new KeyValue<TKey, TValue>(key, value);
        this.slots[slotNumber].add(newElement);
        this.size++;
    }

    private int findSlotNumber(TKey key) {
        return Math.abs(key.hashCode()) % this.slots.length;
    }

    public int size() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean addOrReplace(TKey key, TValue value) {
        this.growIfNeeded();
        int slotNumber = this.findSlotNumber(key);
        if(this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<TKey, TValue> element : this.slots[slotNumber]) {
            if(element.getKey().equals(key)) {
               element.setValue(value);
               return false;
            }
        }
        KeyValue<TKey, TValue> newElement = new KeyValue<TKey, TValue>(key, value);
        this.slots[slotNumber].add(newElement);
        this.size++;
        return true;
    }

    public TValue get(TKey key) {
        KeyValue<TKey, TValue> element = this.find(key);
        if(element == null) {
            throw new IllegalArgumentException("The " + key + " does not exist");
        }
        return element.getValue();
    }

    public boolean tryGetValue(TKey key, TValue value) {
        KeyValue<TKey, TValue> element = this.find(key);
        if (element == null) {
            return false;
        }
        if(element.getValue().equals(value)) {
            return true;
        }
        return false;
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        int slotNumber = this.findSlotNumber(key);
        List<KeyValue<TKey, TValue>> elements = this.slots[slotNumber];
        if(elements != null) {
            for (KeyValue<TKey, TValue> element : elements) {
                if(element.getKey().equals(key)) {
                    return element;
                }
            }
        }
        return null;
    }

    public boolean containsKey(TKey key) {
        return this.find(key) != null;
    }

    public boolean remove(TKey key) {
        int slotNumber = this.findSlotNumber(key);
        List<KeyValue<TKey, TValue>> elements = this.slots[slotNumber];
        boolean isFound = false;
        if(elements != null) {
            for (KeyValue<TKey, TValue> element : elements) {
                if(element.getKey().equals(key)) {
                    elements.remove(element);
                    this.size--;
                    isFound = true;
                    break;
                }
            }
        }
        return isFound;
    }

    public void clear() {
        this.size = 0;
        this.slots = new LinkedList[INITIAL_CAPACITY];
    }

    public Iterable<TKey> keys() {
        List<TKey> keys = new ArrayList<>();
        for (List<KeyValue<TKey, TValue>> slot : this.slots) {
            if(slot != null) {
                for (KeyValue<TKey, TValue> element : slot) {
                    keys.add(element.getKey());
                }
            }
        }
        return keys;
    }

    public Iterable<TValue> values() {
        List<TValue> values = new ArrayList<>();
        for (List<KeyValue<TKey, TValue>> slot : this.slots) {
            if(slot != null) {
                for (KeyValue<TKey, TValue> element : slot) {
                    values.add(element.getValue());
                }
            }
        }
        return values;
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        List<KeyValue<TKey, TValue>> elements = new ArrayList<>();
        for (List<KeyValue<TKey, TValue>> slot : slots) {
            if(slot != null) {
                elements.addAll(slot);
            }
        }
        return elements.iterator();
    }

    private void growIfNeeded() {
        if((this.size + 1) / this.capacity > LOAD_FACTOR) {
            HashTable<TKey, TValue> newHashTable = new HashTable<>(this.capacity  * 2);
            for (KeyValue<TKey, TValue> element : this) {
                newHashTable.add(element.getKey(), element.getValue());
            }
            this.slots = newHashTable.slots;
            this.capacity = newHashTable.capacity;
        }
    }
}
