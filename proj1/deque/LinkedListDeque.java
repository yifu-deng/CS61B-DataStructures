package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private class TNode {
        private TNode prev;
        private final T item;
        private TNode next;

        public TNode(T i, TNode p, TNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private final TNode sentinel = new TNode(null, null, null);
    private int size;

    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    private LinkedListDeque(T x) {
        sentinel.next = new TNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
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

}
