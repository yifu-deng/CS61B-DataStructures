package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private final TNode sentinel = new TNode(null, null, null);
    private int size;

    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        TNode firstNode = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = firstNode;
        sentinel.next = firstNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        TNode lastNode = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = lastNode;
        sentinel.prev = lastNode;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        TNode currNode = sentinel.next;
        while (!currNode.equals(sentinel)) {
            System.out.print(currNode);
            System.out.print(" ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T lastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return lastItem;
    }

    @Override
    public T get(int index) {
        if (index > size) {
            return null;
        } else {
            TNode currNode = sentinel.next;
            while (index > 0) {
                currNode = currNode.next;
                index--;
            }
            return currNode.item;
        }

    }

    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            return getRecursive(sentinel.next, index);
        }
    }

    private T getRecursive(TNode node, int index) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursive(node.next, index - 1);
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<T> lld = (LinkedListDeque<T>) o;
        if (lld.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (lld.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    private class TNode {
        private final T item;
        private TNode prev;
        private TNode next;

        private TNode(T i, TNode p, TNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;

        private LinkedListDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size();
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next one.");
            }
            T item = get(wizPos);
            wizPos += 1;
            return item;
        }
    }
}
