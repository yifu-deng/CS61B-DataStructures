package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int tableSize = 16;
    private double loadFactor = 0.75;
    private int size = 0;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(tableSize);
    }

    public MyHashMap(int initialSize) {
        tableSize = initialSize;
        buckets = createTable(tableSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        tableSize = initialSize;
        loadFactor = maxLoad;
        buckets = createTable(tableSize);

    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        buckets = createTable(tableSize);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node getNode(K key) {
        int index = hash(key);
        Collection<Node> bucket = buckets[index];
        if (bucket == null) {
            return null;
        }
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    private int hash(K key) {
        return Math.floorMod(key.hashCode(), tableSize);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        Collection<Node> bucket = buckets[index];
        if (bucket == null) {
            bucket = createBucket();
            buckets[index] = bucket;
        }
        Node node = getNode(key);
        if (node == null) {
            bucket.add(createNode(key, value));
            size++;
        } else {
            node.value = value;
        }

        // Check if resizing is needed after the put operation
        if ((double) size / tableSize > loadFactor) {
            resize();
        }
    }

    private void resize() {
        Collection<Node>[] oldBuckets = buckets;
        tableSize *= 2;
        buckets = createTable(tableSize);
        size = 0;  // Reset the size to 0 when resizing

        for (Collection<Node> bucket : oldBuckets) {
            if (bucket != null) {
                for (Node node : bucket) {
                    put(node.key, node.value); // Reinsert all elements
                }
            }
        }
    }


    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) {
                for (Node node : bucket) {
                    keySet.add(node.key);
                }
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        return remove(key, get(key));
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            return null;
        }
        int index = hash(key);
        Node node = getNode(key);
        if (node == null || !node.value.equals(value)) {
            return null;
        }
        buckets[index].remove(node);
        size--;
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
