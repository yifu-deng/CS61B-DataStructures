package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> rightAnswer = new AListNoResizing<>();
        BuggyAList<Integer> falseAnswer = new BuggyAList<>();

        for (int i = 4; i < 7; i++) {
            rightAnswer.addLast(i);
            falseAnswer.addLast(i);
        }

        for (int i = 0; i < falseAnswer.size(); i++) {
            assertEquals(rightAnswer.removeLast(), falseAnswer.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> rightAnswer = new AListNoResizing<>();
        BuggyAList<Integer> falseAnswer = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                rightAnswer.addLast(randVal);
                falseAnswer.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int f_size = falseAnswer.size();
                int r_size = rightAnswer.size();
                assertEquals(f_size, r_size);
            } else  if (operationNumber == 2) {
                // getLast
                if (rightAnswer.size() > 0 && falseAnswer.size() > 0) {
                    int r_num = rightAnswer.getLast();
                    int f_num = falseAnswer.getLast();
                    assertEquals(f_num, r_num);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (rightAnswer.size() > 0 && falseAnswer.size() > 0) {
                    int r_num = rightAnswer.removeLast();
                    int f_num = falseAnswer.removeLast();
                    assertEquals(f_num, r_num);
                }
            }
        }
    }
}
