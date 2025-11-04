package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code long sum(int[] numbers)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_1_sum_Tests {

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
    Numbers_1_sum_Tests() {
        this.testObj = Numbers.getInstance();
        this.testData = new NumbersData();
    }

    /**
     * Tests for 'regular' cases.
     */
    @Test @Order(100)
    void test100_sum_regular() {
        int[] testData_ = testData.getArr("numb");
        long expected = 30L;    // expected result of test
        long actual = testObj.sum(testData_);   // invoke sum()
        //
        // compare test results, test passes if expected==actual
        // make sure to compare 'long' values
        assertEquals(expected, actual);
    }

    @Test @Order(101)
    void test101_sum_regular() {
        assertEquals(50L, testObj.sum(testData.getArr("numb_1")));
    }

    @Test @Order(102)
    void test102_sum_regular() {
        assertEquals(10984L, testObj.sum(testData.getArr("numb_2")));
    }

    @Test @Order(103)
    void test103_sum_regular() {
        assertEquals(141466L, testObj.sum(testData.getArr("numb_3")));
    }

    /**
     * Tests for 'corner' cases.
     */
    @Test @Order(110)
    void test110_sum_corner_empty_array() {
        int[] testData = {};        // empty array
        assertEquals(0L, testObj.sum(testData));

        testData = new int[0];      // array of length 0
        assertEquals(0L, testObj.sum(testData));

        testData = new int[1];      // array of length 1
        testData[0] = 1;
        assertEquals(1L, testObj.sum(testData));
    }

    @Test @Order(120)
    void test120_sum_corner_big_array() {
        int big = Integer.MAX_VALUE;        // 32-bit, 0x7fffffff, 2147483647
        // --> java.lang.OutOfMemoryError: Requested array size exceeds VM limit
        // --> java.lang.OutOfMemoryError: Java heap space
        big = 2147483647;                   // Integer.MAX_VALUE for comparison
        big = 1000000000;                   // reduce to not throw heap space exception
        // big =  100000000;                   // reduce further to speed up test
        int[] testData = new int[big];      // big array
        for(int i=0; i < big; i++) {
            testData[i] = 1;    // initialize with 1's
        }
        long expected = big;
        long actual = testObj.sum(testData);
        assertEquals(expected, actual);
    }

    @Test @Order(122)
    void test122_sum_corner_big_array_number_series() {
        long big = 1000000000;
        // big =  100000000;                       // reduce to speed up test
        int[] testData = new int[Long.valueOf(big).intValue()];
        for(int i=0; i < big; i++) {
            testData[i] = i;
        }
        // big=1000000000: 499,999,999,500,000,000
        // big= 100000000:   4,999,999,950,000,000 (reduced to big=100,000,000)
        long expected = big * (big - 1) / 2;
        long actual = testObj.sum(testData);
        // System.out.println(String.format("-exp-> %d\n-act-> %d", expected, actual));
        assertEquals(expected, actual);
    }

    /**
     * Tests for 'exception' cases.
     */
    @Test @Order(130)
    void test130_sum_exception_null_arg() {
        IllegalArgumentException ex =
            assertThrows(IllegalArgumentException.class,
                () -> testObj.sum(null));
        // 
        assertEquals("illegal argument: null", ex.getMessage());
    }
}
