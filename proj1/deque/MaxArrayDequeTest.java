package deque;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {

    @Test
    public void maxTest() {
        Comparator<Integer> cmp = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(cmp);

        deque.addLast(5);
        deque.addLast(2);
        deque.addFirst(7);
        deque.addLast(1);

        assertEquals(7, (int) deque.max());
    }

    @Test
    public void maxStringLength() {
        Comparator<String> cmp = new Comparator<>() {
            @Override
            public int compare(String strA, String strB) {
                return strA.length() - strB.length();
            }
        };


        MaxArrayDeque<String> deque = new MaxArrayDeque<>(cmp);

        deque.addLast("apple");
        deque.addLast("cherry");
        deque.addLast("banana");
        deque.addLast("date");

        assertEquals("cherry", deque.max());
    }

    @Test
    public void maxWithComparatorTest() {
        Comparator<String> cmp = new Comparator<>() {
            @Override
            public int compare(String strA, String strB) {
                return strA.compareTo(strB);
            }
        };

        MaxArrayDeque<String> deque = new MaxArrayDeque<>(cmp);

        deque.addLast("apple");
        deque.addLast("banana");
        deque.addLast("cherry");
        deque.addLast("date");

        assertEquals("date", deque.max());
    }

    @Test
    public void maxWithEmptyDequeTest() {
        Comparator<Integer> cmp = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(cmp);

        assertNull(deque.max());
    }

    @Test
    public void maxWithComparatorAndEmptyDequeTest() {
        Comparator<String> cmp = new Comparator<>() {
            @Override
            public int compare(String strA, String strB) {
                return strA.length() - strB.length();
            }
        };

        MaxArrayDeque<String> deque = new MaxArrayDeque<>(cmp);

        assertNull(deque.max());
    }
}
