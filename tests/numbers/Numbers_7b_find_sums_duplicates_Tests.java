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
     * tested object is an instance of the Numbers class
     */
    private static Numbers testObj;


    /**
     * Static setup method executed once for all tests. Creates
     * the test object.
     * @throws Exception when test creation fails
     */
    @BeforeAll
    public static void setUpBeforeTests() throws Exception {
        testObj = Numbers.getInstance();
    }

    // arrays with duplicates
    int[] n10 = new int[] {5, 5, 1, 1, 1, 5, 1, 5, 5, 1, 1, 5};
    int[] n11 = new int[] {3, 3, 3, 3, 3, 1, 1, 3};
    int[] n12 = new int[] {3, 5, 5, 1, 1, 1, 5, 1, 5, 5, 1, 1, 5, 3};
    int[] n13 = new int[] {5, 4, 3, 2, 2, 3, 5, 1, 5, 1, 5, 1, 1, 5};

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