package org.cis120;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Use this file to test your implementation of Pixel.
 * 
 * We will manually grade this file to give you feedback
 * about the completeness of your test cases.
 */

public class MyPixelTest {

    /*
     * Remember, UNIT tests should ideally have one point of failure. Below we
     * give you two examples of unit tests for the Pixel constructor, one that
     * takes in three ints as arguments and one that takes in an array. We use
     * the getRed(), getGreen(), and getBlue() methods to check that the values
     * were set correctly. These two tests do not comprehensively test all of
     * Pixel so you must add your own.
     * 
     * You might want to look into assertEquals, assertTrue, assertFalse, and
     * assertArrayEquals at the following:
     * http://junit.sourceforge.net/javadoc/org/junit/Assert.html
     *
     * Note, if you want to add global variables so that you can reuse Pixels
     * in multiple tests, feel free to do so.
     */

    @Test
    public void testConstructInBounds() {
        Pixel p = new Pixel(40, 50, 60);
        assertEquals(40, p.getRed());
        assertEquals(50, p.getGreen());
        assertEquals(60, p.getBlue());
    }

    @Test
    public void testConstructArrayLongerThan3() {
        int[] arr = { 10, 20, 30, 40 };
        Pixel p = new Pixel(arr);
        assertEquals(10, p.getRed());
        assertEquals(20, p.getGreen());
        assertEquals(30, p.getBlue());
    }

    @Test
    public void testGetComponents() {
        Pixel p = new Pixel(10, 20, 30);
        int[] exp = {10, 20, 30};
        assertArrayEquals(exp, p.getComponents());
    }

    @Test
    public void testDistanceToNull() {
        Pixel p = new Pixel(125, 255, 0);
        assertEquals(-1, p.distance(null));
    }

    @Test
    public void testDistanceToSame() {
        Pixel p = new Pixel(125, 255, 0);
        Pixel x = new Pixel(125, 255, 0);
        assertEquals(0, p.distance(x));
    }

    @Test
    public void testDistanceDiff() {
        Pixel p = new Pixel(125, 255, 0);
        Pixel x = new Pixel(120, 200, 100);
        assertEquals(160, p.distance(x));
    }

    @Test
    public void testToString() {
        Pixel p = new Pixel(10, 20, 30);
        assertEquals("(10, 20, 30)", p.toString());
    }

    @Test
    public void testSameRGBDiffR() {
        Pixel p = new Pixel(125, 255, 0);
        Pixel x = new Pixel(120, 255, 0);
        assertFalse(p.sameRGB(x));
    }

    @Test
    public void testSameRGBDiffG() {
        Pixel p = new Pixel(125, 255, 0);
        Pixel x = new Pixel(125, 200, 0);
        assertFalse(p.sameRGB(x));
    }

    @Test
    public void testSameRGBDiffB() {
        Pixel p = new Pixel(125, 255, 0);
        Pixel x = new Pixel(125, 255, 50);
        assertFalse(p.sameRGB(x));
    }

    @Test
    public void testSameRGBSame() {
        Pixel p = new Pixel(125, 255, 0);
        Pixel x = new Pixel(125, 255, 0);
        assertTrue(p.sameRGB(x));
    }

}
