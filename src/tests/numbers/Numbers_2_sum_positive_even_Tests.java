package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code long sumPositiveEvenNumbers(int[] numbers)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_2_sum_positive_even_Tests {

    /*
     * tested object, instance that implements the {@link Numbers} interface
     */
    private final Numbers testObj;

    /*
     * test data used in tests
     */
    private final TestData testData;

    /**
     * Constructor that initializes test instances.
     */
    Numbers_2_sum_positive_even_Tests() {
        this.testObj = new NumbersImpl();
        this.testData = new TestData();
    }

    /**
     * Tests for 'regular' cases.
     */
    @Test @Order(200)
    void test200_sumPositiveEvenNumbers_regular() {
        int[] testData_ = testData.getArray("numb");
        long expected = 12L;    // expected result of test
        long actual = testObj.sumPositiveEvenNumbers(testData_);   // invoke sum()
        //
        // compare test results, test passes if expected==actual
        // make sure to compare 'long' values
        assertEquals(expected, actual);
    }

    @Test @Order(201)
    void test201_sumPositiveEvenNumbers_regular() {
        assertEquals(38L, testObj.sumPositiveEvenNumbers(testData.getArray("numb_1")));
    }

    @Test @Order(202)
    void test202_sumPositiveEvenNumbers_regular() {
        assertEquals(6492L, testObj.sumPositiveEvenNumbers(testData.getArray("numb_2")));
    }

    @Test @Order(203)
    void test203_sumPositiveEvenNumbers_regular() {
        assertEquals(80012L, testObj.sumPositiveEvenNumbers(testData.getArray("numb_3")));
    }

    /**
     * Tests for 'corner' cases.
     */
    @Test @Order(210)
    void test210_sumPositiveEvenNumbers_corner_empty_array() {
        int[] testData = {};        // empty array
        assertEquals(0L, testObj.sumPositiveEvenNumbers(testData));

        testData = new int[0];      // array of length 0
        assertEquals(0L, testObj.sumPositiveEvenNumbers(testData));

        testData = new int[1];      // array of length 1
        testData[0] = 1;            // 1 is odd number -> expected: 0
        assertEquals(0L, testObj.sumPositiveEvenNumbers(testData));
    }

    @Test @Order(220)
    void test220_sumPositiveEvenNumbers_corner_big_array() {
        int big = Integer.MAX_VALUE;        // 32-bit, 0x7fffffff, 2147483647
        // --> java.lang.OutOfMemoryError: Requested array size exceeds VM limit
        // --> java.lang.OutOfMemoryError: Java heap space
        big = 2147483647;                   // Integer.MAX_VALUE for comparison
        big = 1000000000;                   // reduce to not throw heap space exception
        big =  100000000;                   // reduce further to speed up test
        int[] testData = new int[big];      // big array
        for(int i=0; i < big; i++) {
            testData[i] = i % 4;    // initialize with sequence: 0, 1, 2, 3, 0, 1, 2, 3 ...
        }
        long expected = 500000000L;         // 500,000,000
        expected = 50000000L;               //  50,000,000
        long actual = testObj.sumPositiveEvenNumbers(testData);
        assertEquals(expected, actual);
    }

    @Test @Order(222)
    void test222_sumPositiveEvenNumbers_corner_big_array_number_series() {
        long big = 1000000000;
        big =       100000000;              // reduce to speed up test
        int[] testData = new int[Long.valueOf(big).intValue()];
        for(int i=0; i < big; i++) {
            testData[i] = i;        // initialize with sequence: 0, 1, 2, 3, 4, 5, 6 ...
        }
        long expected = 249999999500000000L;    // 249,999,999,500,000,000
        expected =        2499999950000000L;    //   2,499,999,950,000,000 (reduced)
        long actual = testObj.sumPositiveEvenNumbers(testData);
        // System.out.println(String.format("-exp-> %d\n-act-> %d", expected, actual));
        assertEquals(expected, actual);
    }

    /**
     * Tests for 'exception' cases.
     */
    @Test @Order(230)
    void test230_sumPositiveEvenNumbers_exception_null_arg() {
        IllegalArgumentException ex =
            assertThrows(IllegalArgumentException.class,
                () -> testObj.sumPositiveEvenNumbers(null));
        // 
        assertEquals("illegal argument: null", ex.getMessage());
    }
}
