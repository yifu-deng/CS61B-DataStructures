package deque;

public class ArrayDeque<T> {
    private T[] items = (T[]) new Object[8];
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private ArrayDeque(T item) {
        items[3] = item;
        size = 1;
        nextFirst = 2;
        nextLast = 4;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size += 1;
        nextFirst -= 1;
        if (nextFirst == -1) {
            resize(items.length * 2);
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size += 1;
        nextLast += 1;
        if (nextLast == items.length) {
            resize(items.length * 2);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + "");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / 4) && (size > 4)) {
            resize(items.length / 2);
        }
        nextFirst += 1;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / 4) && (size > 4)) {
            resize(items.length / 2);
        }
        nextLast -= 1;
        T item = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return item;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int startIndex = Math.abs(capacity - size) / 2;
        System.arraycopy(items, nextFirst + 1, newItems, startIndex, size);
        items = newItems;
        nextFirst = startIndex - 1;
        nextLast = startIndex + size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int itemIndex = nextFirst + 1 + index;
        return items[itemIndex];
    }

}
