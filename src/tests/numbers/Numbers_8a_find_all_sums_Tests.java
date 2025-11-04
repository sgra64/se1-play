package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code Set<Set<Integer>> findAllSums(int[] numbers, int sum)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_8a_find_all_sums_Tests {

    /*
     * tested object, instance that implements the {@link Numbers} interface
     */
    private final Numbers testObj;

    /*
     * test data used in tests
     */
    private final int[] numb_1;
    private final int[] numb_2;

    /**
     * Constructor that initializes test instances.
     */
    public Numbers_8a_find_all_sums_Tests() {
        this.testObj = Numbers.getInstance();
        var data = new NumbersData();
        this.numb_1 = data.getArr("numb_1");
        this.numb_2 = data.getArr("numb_2");
    }

    @Test @Order(800)
    void test800_find_all_sums_regular() {
        var actual = testObj.findAllSums(numb_1, 10);
        int[][] expected = {
            {10}, {8, 2}
        };
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(801)
    void test801_find_all_sums_regular() {
        var actual = testObj.findAllSums(numb_1, 14);
        int[][] expected = {
            {14}, {4, 10}, {2, 4, 8}, {2, 5, 7}
        };
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(802)
    void test802_find_all_sums_regular() {
        var actual = testObj.findAllSums(numb_1, 15);
        int[][] expected = {
            {7, 8}, {5, 10}, {2, 5, 8}
        };
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(810)
    void test802_find_all_sums_regular_no_match() {
        var actual = testObj.findAllSums(numb_1, 100);
        int[][] expected = { };     // empty set
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(821)
    void test821_find_all_sums_regular_numb_2_sum999() {
        var actual = testObj.findAllSums(numb_2, 999);
        int[][] expected = {
            {27, 972},
            {226, 773},
            {371, 87, 541},
            {170, 190, 639},
            {226, 27, 541, 205},
            {163, 170, 125, 541},
            {163, 170, 27, 639},
            {163, 7, 190, 639},
            {226, 371, 170, 27, 205},
            {226, 371, 7, 205, 190},
            {226, 371, 87, 125, 190},
            {226, 163, 371, 7, 27, 205},
            {226, 163, 371, 87, 27, 125}
        };
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    /**
     * Tests for 'exception' cases.
     */
    @Test @Order(830)
    void test830_find_all_sums_exception_null_arg() {
        IllegalArgumentException ex =
            assertThrows(IllegalArgumentException.class,
                () -> testObj.findAllSums(null, 0));
        // 
        assertEquals("illegal argument: null", ex.getMessage());
    }
}
