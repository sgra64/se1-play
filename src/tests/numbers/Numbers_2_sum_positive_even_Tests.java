package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code long sum_positive_even_numbers(int[] numbers)}.
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
    private final NumbersData testData;

    /**
     * Constructor that initializes test instances.
     */
    Numbers_2_sum_positive_even_Tests() {
        this.testObj = Numbers.getInstance();
        this.testData = new NumbersData();
    }

    /**
     * Tests for 'regular' cases.
     */
    @Test @Order(200)
    void test200_sum_positive_even_numbers_regular() {
        int[] testData_ = testData.getArr("numb");
        long expected = 12L;    // expected result of test
        long actual = testObj.sum_positive_even_numbers(testData_);   // invoke sum()
        //
        // compare test results, test passes if expected==actual
        // make sure to compare 'long' values
        assertEquals(expected, actual);
    }

    @Test @Order(201)
    void test201_sum_positive_even_numbers_regular() {
        assertEquals(38L, testObj.sum_positive_even_numbers(testData.getArr("numb_1")));
    }

    @Test @Order(202)
    void test202_sum_positive_even_numbers_regular() {
        assertEquals(6492L, testObj.sum_positive_even_numbers(testData.getArr("numb_2")));
    }

    @Test @Order(203)
    void test203_sum_positive_even_numbers_regular() {
        assertEquals(80012L, testObj.sum_positive_even_numbers(testData.getArr("numb_3")));
    }

    /**
     * Tests for 'corner' cases.
     */
    @Test @Order(210)
    void test210_sum_positive_even_numbers_corner_empty_array() {
        int[] testData = {};        // empty array
        assertEquals(0L, testObj.sum_positive_even_numbers(testData));

        testData = new int[0];      // array of length 0
        assertEquals(0L, testObj.sum_positive_even_numbers(testData));

        testData = new int[1];      // array of length 1
        testData[0] = 1;            // 1 is odd number -> expected: 0
        assertEquals(0L, testObj.sum_positive_even_numbers(testData));
    }

    @Test @Order(220)
    void test220_sum_positive_even_numbers_corner_big_array() {
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
        long actual = testObj.sum_positive_even_numbers(testData);
        assertEquals(expected, actual);
    }

    @Test @Order(222)
    void test222_sum_positive_even_numbers_corner_big_array_number_series() {
        long big = 1000000000;
        big =       100000000;              // reduce to speed up test
        int[] testData = new int[Long.valueOf(big).intValue()];
        for(int i=0; i < big; i++) {
            testData[i] = i;        // initialize with sequence: 0, 1, 2, 3, 4, 5, 6 ...
        }
        long expected = 249999999500000000L;    // 249,999,999,500,000,000
        expected =        2499999950000000L;    //   2,499,999,950,000,000 (reduced)
        long actual = testObj.sum_positive_even_numbers(testData);
        // System.out.println(String.format("-exp-> %d\n-act-> %d", expected, actual));
        assertEquals(expected, actual);
    }

    /**
     * Tests for 'exception' cases.
     */
    @Test @Order(230)
    void test230_sum_positive_even_numbers_exception_null_arg() {
        IllegalArgumentException ex =
            assertThrows(IllegalArgumentException.class,
                () -> testObj.sum_positive_even_numbers(null));
        // 
        assertEquals("illegal argument: null", ex.getMessage());
    }
}
