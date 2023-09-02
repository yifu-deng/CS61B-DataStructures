package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size = 0;
    /** Removes all of the mappings from this map. */

    private class Node {
        private final K key;
        private V value;
        private Node left, right;

        public Node(K k, V v) {
            key = k;
            value = v;
        }
    }
    public void clear(){
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return containsKey(root, key);
    }

    private boolean containsKey(Node n, K key) {
        if (n == null) {
            return false;
        }
        if (n.key.equals(key)) {
            return true;
        }
        if (n.key.compareTo(key) > 0) {
            return containsKey(n.left, key);
        } else {
            return containsKey(n.right, key);
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return get(root, key);
    }

    private V get(Node n, K key) {
        if (n == null) {
            return null;
        }
        if (n.key.equals(key)) {
            return n.value;
        }
        if (n.key.compareTo(key) > 0) {
            return get(n.left, key);
        } else {
            return get(n.right, key);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        root = put(root, key, value);
        size++;
    }

    private Node put(Node n, K key, V value) {
        if (n == null) {
            return new Node(key, value);
        }
        if (n.key == key) {
            n.value = value;
        }
        if (n.key.compareTo(key) > 0) {
            n.left = put(n.left, key, value);
        } else {
            n.right = put(n.right, key, value);
        }
        return n;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySet(root, set);
        return set;
    }

    private void keySet(Node n, Set<K> set) {
        if (n == null) {
            return;
        }
        set.add(n.key);
        keySet(n.left, set);
        keySet(n.right, set);
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        if (containsKey(key)) {
            root = remove(root, key);
            V value = get(key);
            size--;
            return value;
        }
        return null;
    }

    private Node remove(Node n, K key) {
        if (n == null) {
            return null;
        }
        if (n.key.compareTo(key) > 0) {
            n.left = remove(n.left, key);
        } else if (n.key.compareTo(key) < 0) {
            n.right = remove(n.right, key);
        } else {
            if (n.left == null) {
                return n.right;
            } else if (n.right == null) {
                return n.left;
            } else {
                Node temp = n;
                n = min(temp.right);
                n.left = temp.left;
                n.right = remove(temp.right, n.key);
            }
        }
        return n;
    }

    private Node min(Node n) {
        if (n.left == null) {
            return n;
        }
        return min(n.left);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        if (containsKey(key) && get(key) == value) {
            root = remove(root, key);
            size--;
            return value;
        }
        return null;
    }

    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node n) {
        if (n == null) {
            return;
        }
        printInOrder(n.left);
        System.out.println(n.key);
        printInOrder(n.right);
    }
}
