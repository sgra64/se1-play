package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit 5 test class of Numbers class, sum_pen() method.
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_2_sum_positive_even_Tests {

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
    @Order(200)
    void test200_sum_positive_even_numbers_regular() {
        assertEquals(12, testObj.sum_positive_even_numbers(testData.getArr("numb")));
    }

    @Test
    @Order(201)
    void test201_sum_positive_even_numbers_regular() {
        assertEquals(38, testObj.sum_positive_even_numbers(testData.getArr("numb_1")));
    }

    @Test
    @Order(202)
    void test202_sum_positive_even_numbers_regular() {
        assertEquals(6492, testObj.sum_positive_even_numbers(testData.getArr("numb_2")));
    }

    @Test
    @Order(203)
    void test203_sum_positive_even_numbers_regular() {
        assertEquals(80012, testObj.sum_positive_even_numbers(testData.getArr("numb_3")));
    }
}