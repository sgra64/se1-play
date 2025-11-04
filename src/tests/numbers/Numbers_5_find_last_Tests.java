package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code int findLast(int[] numbers, int x)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_5_find_last_Tests {

    /*
     * tested object, instance that implements the {@link Numbers} interface
     */
    private final Numbers testObj;

    /*
     * test data used in tests
     */
    private final int[] numb;
    private final int[] numb_1;
    private final int[] numb_2;
    private final int[] numb_3;

    /**
     * Constructor that initializes test instances.
     */
    Numbers_5_find_last_Tests() {
        this.testObj = Numbers.getInstance();
        var data = new NumbersData();
        this.numb = data.getArr("numb");
        this.numb_1 = data.getArr("numb_1");
        this.numb_2 = data.getArr("numb_2");
        this.numb_3 = data.getArr("numb_3");
    }

    /**
     * Tests for 'regular' cases.
     */
    @Test @Order(500)
    void test500_find_last_regular() {
        int[] testData_ = numb;
        int expected = 6;   // find last in 'numb' at index: 6
        int actual = testObj.findLast(testData_, 9);
        assertEquals(expected, actual);
    }

    @Test @Order(501)
    void test501_find_last_regular_neg_element() {
        // find negative element: '-2' -> index: 0
        assertEquals(4, testObj.findLast(numb, -3));
    }

    @Test @Order(502)
    void test502_find_last_regular_duplicates() {
        // find '4' (element with duplicates), last occurence at index: 5
        assertEquals(5, testObj.findLast(numb, 4));
    }

    @Test @Order(503)
    void test503_find_last_regular_last() {
        // find last element '5' (element with duplicates) -> index: 1
        assertEquals(numb.length-1, testObj.findLast(numb, 5));
    }

    @Test @Order(504)
    void test504_find_last_regular_not_present() {
        // attempt to find elements that are not present: 0, 77, -33 -> -1
        assertEquals(-1, testObj.findLast(numb, 0));
        assertEquals(-1, testObj.findLast(numb, 77));
        assertEquals(-1, testObj.findLast(numb, -33));
    }

    @Test @Order(510)
    void test510_find_last_regular_numb_1() {
        int[] numbers = numb_1;
        assertEquals(4, testObj.findLast(numbers, 14));      // find element with duplicates
        assertEquals(numbers.length-1, testObj.findLast(numbers, 4));       // find first element
        assertEquals(-1, testObj.findLast(numbers, 77));     // find element not present
        assertEquals(-1, testObj.findLast(numbers, 0));      // find 0 element
        assertEquals(-1, testObj.findLast(numbers, -3));     // find non-present negative element
    }

    @Test @Order(512)
    void test512_find_last_regular_numb_2() {
        int[] numbers = numb_2;
        assertEquals(13, testObj.findLast(numbers, 7));      // find element with duplicates
        assertEquals(0, testObj.findLast(numbers, 371));     // find first element
        assertEquals(numbers.length-1, testObj.findLast(numbers, 636));    // find last element
        assertEquals(-1, testObj.findLast(numbers, 77));     // find element not present
        assertEquals(-1, testObj.findLast(numbers, 0));      // find 0 element
        assertEquals(-1, testObj.findLast(numbers, -3));     // find non-present negative element
    }

    @Test @Order(514)
    void test514_find_last_regular_numb_3() {
        int[] numbers = numb_3;
        assertEquals(13, testObj.findLast(numbers, 7));      // find element with duplicates
        assertEquals(0, testObj.findLast(numbers, 799));     // find first element
        assertEquals(numbers.length-1, testObj.findLast(numbers, 500));    // find last element
        assertEquals(-1, testObj.findLast(numbers, 77));     // find element not present
        assertEquals(-1, testObj.findLast(numbers, 0));      // find 0 element
        assertEquals(-1, testObj.findLast(numbers, -3));     // find non-present negative element
    }

    /**
     * Tests for 'corner' cases.
     */
    @Test @Order(520)
    void test520_find_last_corner_empty_array() {
        int[] testData = {};        // empty array
        assertEquals(-1, testObj.findLast(testData, 0));

        testData = new int[0];      // array of length 0
        assertEquals(-1, testObj.findLast(testData, 0));

        testData = new int[1];      // array of length 1
        testData[0] = 1;
        assertEquals(0, testObj.findLast(testData, 1));
        assertEquals(-1, testObj.findLast(testData, 0));
    }

    @Test @Order(530)
    void test530_find_last_corner_big_array() {
        int big = Integer.MAX_VALUE;        // 32-bit, 0x7fffffff, 2147483647
        // --> java.lang.OutOfMemoryError: Requested array size exceeds VM limit
        // --> java.lang.OutOfMemoryError: Java heap space
        big = 2147483647;                   // Integer.MAX_VALUE for comparison
        big = 1000000000;                   // reduce to not throw heap space exception
        big =   10000000;                   // reduce further to speed up test
        int[] testData = new int[big];      // big array
        for(int i=0; i < big; i++) {
            testData[i] = i;    // initialize with i
        }
        // find numbers: 0..10
        assertEquals(0, testObj.findLast(testData, 0));
        for(int i=0; i <= 10; i++) {
            assertEquals(i, testObj.findLast(testData, i));
        }
        // find numbers: 1000..1010
        for(int i=1000; i <= 1010; i++) {
            assertEquals(i, testObj.findLast(testData, i));
        }
        // find last numbers
        assertEquals(big-3, testObj.findLast(testData, big-3));
        assertEquals(big-2, testObj.findLast(testData, big-2));
        assertEquals(big-1, testObj.findLast(testData, big-1));
        // 
        // attemt to find elements not present
        assertEquals(-1, testObj.findLast(testData, -1));
        assertEquals(-1, testObj.findLast(testData, -10));
        assertEquals(-1, testObj.findLast(testData, Integer.MAX_VALUE));
    }

    /**
     * Tests for 'exception' cases.
     */
    @Test @Order(540)
    void test540_find_last_exception_null_arg() {
        IllegalArgumentException ex =
            assertThrows(IllegalArgumentException.class,
                () -> testObj.findLast(null, 0));
        // 
        assertEquals("illegal argument: null", ex.getMessage());
    }
}
