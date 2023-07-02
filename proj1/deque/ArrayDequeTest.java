package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> deque = new ArrayDeque<>();

        assertTrue("A newly initialized deque should be empty", deque.isEmpty());

        deque.addFirst("front");
        assertEquals(1, deque.size());
        assertFalse("The deque should now contain 1 item", deque.isEmpty());

        deque.addLast("middle");
        assertEquals(2, deque.size());

        deque.addLast("back");
        assertEquals(3, deque.size());
    }

    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        assertTrue("The deque should be empty upon initialization", deque.isEmpty());

        deque.addFirst(10);
        assertFalse("The deque should contain 1 item", deque.isEmpty());

        deque.removeFirst();
        assertTrue("The deque should be empty after removal", deque.isEmpty());
    }

    @Test
    public void removeEmptyTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addFirst(3);
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();

        assertEquals("Bad size returned when removing from empty deque.", 0, deque.size());
    }

    @Test
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        assertNull("Should return null when removeFirst is called on an empty deque", deque.removeFirst());
        assertNull("Should return null when removeLast is called on an empty deque", deque.removeLast());
    }

    @Test
    public void bigArrayDequeTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < 1000000; i++) {
            deque.addLast(i);
        }

        for (int i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (int) deque.removeFirst());
        }

        for (int i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (int) deque.removeLast());
        }
    }
}
