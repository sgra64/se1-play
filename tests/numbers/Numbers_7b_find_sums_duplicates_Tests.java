package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit 5 test class of Numbers class, findSums() method (duplicates).
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_7b_find_sums_duplicates_Tests {

    /*
     * Tested object as instance of the {@link Numbers} class.
     * Must not be static due to parallel execution of test methods.
     */
    private Numbers testObj;

    /*
     * Immutable test data, arrays with duplicates (can be static).
     */
    private static final int[] n10 = new int[] {5, 5, 1, 1, 1, 5, 1, 5, 5, 1, 1, 5};
    private static final int[] n11 = new int[] {3, 3, 3, 3, 3, 1, 1, 3};
    private static final int[] n12 = new int[] {3, 5, 5, 1, 1, 1, 5, 1, 5, 5, 1, 1, 5, 3};
    private static final int[] n13 = new int[] {5, 4, 3, 2, 2, 3, 5, 1, 5, 1, 5, 1, 1, 5};


    /**
     * Setup method executed before each @Test method is executed.
     * @throws Exception if any exception occurs
     */
    @BeforeEach
    public void setUpBeforeEach() throws Exception {
        testObj = Numbers.getInstance();
    }

    @Test
    @Order(710)
    void test710_find_sums_duplicates() {
        var actual = testObj.findSums(n10, 6);
        int[][] expected = {{1, 5}};    // or {5, 1}, both match
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(711)
    void test711_find_sums_same_duplicates() {
        var actual = testObj.findSums(n11, 6);
        int[][] expected = {{3, 3}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(712)
    void test712_find_sums_mirror_duplicates() {
        var actual = testObj.findSums(n12, 6);
        int[][] expected = {{1, 5}, {3, 3}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(713)
    void test713_find_sums_regular_duplicates() {
        var actual = testObj.findSums(n13, 6);
        int[][] expected = {{1, 5}, {3, 3}, {2, 4}};
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }
}