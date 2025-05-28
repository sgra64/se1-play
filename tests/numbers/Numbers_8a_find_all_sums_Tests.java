package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit 5 test class of Numbers class, findAllSums() method.
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_8a_find_all_sums_Tests {

    /*
     * Tested object as instance of the {@link Numbers} class.
     * Must not be static due to parallel execution of test methods.
     */
    private Numbers testObj;

    /*
     * Immutable test data (can be static).
     */
    private static NumbersData testData;


    /**
     * Static setup method executed once befor all tests, often
     * used to setup immutable test data that can be static.
     * @throws Exception when test creation fails
     */
    @BeforeAll
    public static void setUpBeforeTests() throws Exception {
        testData = new NumbersData();
    }

    /**
     * Setup method executed before each @Test method is executed.
     * @throws Exception if any exception occurs
     */
    @BeforeEach
    public void setUpBeforeEach() throws Exception {
        testObj = Numbers.getInstance();
    }

    @Test
    @Order(800)
    void test800_find_all_sums_regular() {
        var actual = testObj.findAllSums(testData.getArr("numb_1"), 10);
        int[][] expected = {
            {10},
            {8, 2}
        };
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(821)
    void test821_find_all_sums_regular_nbrs_2_sum999() {
        var actual = testObj.findAllSums(testData.getArr("numb_2"), 999);
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
}