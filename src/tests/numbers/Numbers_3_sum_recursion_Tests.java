package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code long sum_recursive(int[] numbers, int i)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_3_sum_recursion_Tests {

    /*
     * tested object, instance that implements the {@link Numbers} interface
     */
    private final Numbers testObj;

    /*
     * test data used in tests
     */
    private final NumbersData testData;

    /**
     * Constructor that initializes test instances.
     */
    Numbers_3_sum_recursion_Tests() {
        this.testObj = Numbers.getInstance();
        this.testData = new NumbersData();
    }

    /**
     * Tests for 'regular' cases.
     */
    @Test @Order(300)
    void test300_sum_recursion_regular() {
        int[] testData_ = testData.getArr("numb");
        long expected = 30L;    // expected result of test
        long actual = testObj.sum_recursive(testData_, 0);
        //
        // compare test results, test passes if expected==actual
        // make sure to compare 'long' values
        assertEquals(expected, actual);
    }

    @Test @Order(301)
    void test301_sum_recursion_regular() {
        assertEquals(50L, testObj.sum_recursive(testData.getArr("numb_1"), 0));
    }

    @Test @Order(302)
    void test102_sum_recursion_regular() {
        assertEquals(10984L, testObj.sum_recursive(testData.getArr("numb_2"), 0));
    }

    @Test @Order(303)
    void test103_sum_recursion_regular() {
        assertEquals(141466L, testObj.sum_recursive(testData.getArr("numb_3"), 0));
    }

    /**
     * Tests for 'corner' cases.
     */
    @Test @Order(300)
    void test300_sum_recursion_corner_empty_array() {
        int[] testData = {};        // empty array
        assertEquals(0L, testObj.sum_recursive(testData, 0));

        testData = new int[0];      // array of length 0
        assertEquals(0L, testObj.sum_recursive(testData, 0));

        testData = new int[1];      // array of length 1
        testData[0] = 1;
        assertEquals(1L, testObj.sum_recursive(testData, 0));
    }

    @Test @Order(310)
    void test310_sum_recursion_corner_big_array() {
        int big = Integer.MAX_VALUE;        // 32-bit, 0x7fffffff, 2147483647
        // --> java.lang.OutOfMemoryError: Requested array size exceeds VM limit
        // --> java.lang.OutOfMemoryError: Java heap space
        big = 2147483647;                   // Integer.MAX_VALUE for comparison
        big = 1000000000;                   // reduce to not throw heap space exception
        big =       3600;                   // > 6800: java.lang.StackOverflowError
        int[] testData = new int[big];      // big array
        for(int i=0; i < big; i++) {
            testData[i] = 1;    // initialize with 1's
        }
        long expected = big;
        long actual = testObj.sum_recursive(testData, 0);
        assertEquals(expected, actual);
    }

    @Test @Order(312)
    void test312_sum_recursion_corner_big_array_number_series() {
        long big = 1000000000;
        big =            3600;  // > 3600: java.lang.StackOverflowError, terminal test runner
        int[] testData = new int[Long.valueOf(big).intValue()];
        for(int i=0; i < big; i++) {
            testData[i] = i;
        }
        // big = 3600, actuel: 6,478,200 (> java.lang.StackOverflowError, terminal test runner)
        long expected = big * (big - 1) / 2;
        long actual = testObj.sum_recursive(testData, 0);
        // System.out.println(String.format("-exp-> %d\n-act-> %d", expected, actual));
        assertEquals(expected, actual);
    }

    /**
     * Tests for 'exception' cases.
     */
    @Test @Order(340)
    void test340_sum_recursion_exception_null_arg() {
        IllegalArgumentException ex =
            assertThrows(IllegalArgumentException.class,
                () -> testObj.sum_recursive(null, 0));
        // 
        assertEquals("illegal argument: null", ex.getMessage());
    }
}
