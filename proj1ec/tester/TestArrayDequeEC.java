package tester;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.util.ArrayDeque;

import static org.junit.Assert.*;

public class TestArrayDequeEC {

    @Test
    public void testRandomizedOperations() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();

        StringBuilder operations = new StringBuilder(); // Track operations performed

        // Perform random operations
        for (int i = 0; i < 1000; i++) {
            double randomNumber = StdRandom.uniform();

            if (randomNumber < 0.25) {
                // AddFirst operation
                Integer value = StdRandom.uniform(100);
                arrayDeque.addFirst(value);
                studentDeque.addFirst(value);
                operations.append("addFirst(").append(value).append(")\n");
            } else if (randomNumber < 0.5) {
                // AddLast operation
                Integer value = StdRandom.uniform(100);
                arrayDeque.addLast(value);
                studentDeque.addLast(value);
                operations.append("addLast(").append(value).append(")\n");
            } else if (randomNumber < 0.75) {
                // RemoveFirst operation
                if (!arrayDeque.isEmpty() && !studentDeque.isEmpty()) {
                    Integer expected = arrayDeque.removeFirst();
                    Integer actual = studentDeque.removeFirst();
                    operations.append("removeFirst()\n");
                    assertEquals(operations.toString(), expected, actual);
                }
            } else {
                // RemoveLast operation
                if (!arrayDeque.isEmpty() && !studentDeque.isEmpty()) {
                    Integer expected = arrayDeque.removeLast();
                    Integer actual = studentDeque.removeLast();
                    operations.append("removeLast()\n");
                    assertEquals(operations.toString(), expected, actual);
                }
            }
        }
    }
}
