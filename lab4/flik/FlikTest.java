package flik;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        assertTrue(Flik.isSameNumber(1, 1));
        assertTrue(Flik.isSameNumber(0, 0));
        assertFalse(Flik.isSameNumber(1, 2));
        assertFalse(Flik.isSameNumber(0, 1));
    }
}
