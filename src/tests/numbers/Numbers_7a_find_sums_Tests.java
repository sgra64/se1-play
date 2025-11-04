package numbers;

import java.util.Collection;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import numbers.Numbers.Pair;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code Set<Pair> findSums(int[] numbers, int sum)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_7a_find_sums_Tests {

    /*
     * tested object, instance that implements the {@link Numbers} interface
     */
    private final Numbers testObj;

    /*
     * test data used in tests
     */
    // private final int[] numb;
    private final int[] numb_1;
    private final int[] numb_2;
    private final int[] numb_3;

    /**
     * Constructor that initializes test instances.
     */
    Numbers_7a_find_sums_Tests() {
        this.testObj = Numbers.getInstance();
        var data = new NumbersData();
        // this.numb = data.getArr("numb");
        this.numb_1 = data.getArr("numb_1");
        this.numb_2 = data.getArr("numb_2");
        this.numb_3 = data.getArr("numb_3");
    }

    @Test @Order(700)
    void test700_find_sums_regular() {
        Collection<Pair> actual = testObj.findSums(numb_1, 10);
        int[][] expected = {{8, 2}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(701)
    void test701_find_sums_regular() {
        Collection<Pair> actual = testObj.findSums(numb_1, 12);
        int[][] expected = {{10, 2}, {4, 8}, {7, 5}};   // also matches: {{8, 4}, {10, 2}, {7, 5}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(702)
    void test702_find_sums_regular() {
        Collection<Pair>  actual = testObj.findSums(numb_1, 15);
        int[][] expected = {{5, 10}, {7, 8}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(703)
    void test703_find_sums_regular() {
        Collection<Pair>  actual = testObj.findSums(numb_1, 150);
        int[][] expected = {};  // no solution
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(704)
    void test704_find_sums_regular() {
        Collection<Pair> actual = testObj.findSums(numb_2, 663);
        int[][] expected = {{636, 27}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(705)
    void test705_find_sums_regular() {
        Collection<Pair> actual = testObj.findSums(numb_3, 961);
        int[][] expected = {{521, 440}, {498, 463}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(706)
    void test706_find_sums_regular() {
        Collection<Pair> actual = testObj.findSums(numb_3, 2286);
        int[][] expected = {};  // no solution
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    /**
     * Tests for 'exception' cases.
     */
    @Test @Order(720)
    void test720_find_sums_exception_null_arg() {
        IllegalArgumentException ex =
            assertThrows(IllegalArgumentException.class,
                () -> testObj.findSums(null, 0));
        // 
        assertEquals("illegal argument: null", ex.getMessage());
    }
}
