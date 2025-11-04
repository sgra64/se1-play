package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code Set<Pair> findSums(int[] numbers, int sum)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_7b_find_sums_duplicates_Tests {

    /*
     * tested object, instance that implements the {@link Numbers} interface
     */
    private final Numbers testObj;

    /*
     * test data used in tests
     */
    private static final int[] n10 = new int[] {5, 5, 1, 1, 1, 5, 1, 5, 5, 1, 1, 5};
    private static final int[] n11 = new int[] {3, 3, 3, 3, 3, 1, 1, 3};
    private static final int[] n12 = new int[] {3, 5, 5, 1, 1, 1, 5, 1, 5, 5, 1, 1, 5, 3};
    private static final int[] n13 = new int[] {5, 4, 3, 2, 2, 3, 5, 1, 5, 1, 5, 1, 1, 5};

    /**
     * Constructor that initializes test instances.
     */
    Numbers_7b_find_sums_duplicates_Tests() {
        this.testObj = Numbers.getInstance();
    }

    @Test @Order(710)
    void test710_find_sums_duplicates() {
        var actual = testObj.findSums(n10, 6);
        int[][] expected = {{1, 5}};    // or {5, 1}, both match
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(711)
    void test711_find_sums_same_duplicates() {
        var actual = testObj.findSums(n11, 6);
        int[][] expected = {{3, 3}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(712)
    void test712_find_sums_mirror_duplicates() {
        var actual = testObj.findSums(n12, 6);
        int[][] expected = {{1, 5}, {3, 3}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test @Order(713)
    void test713_find_sums_regular_duplicates() {
        var actual = testObj.findSums(n13, 6);
        int[][] expected = {{1, 5}, {3, 3}, {2, 4}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }
}
