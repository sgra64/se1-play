package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit 5 test class of Numbers class, sum() method.
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_1_sum_Tests {

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
    @Order(100)
    void test100_sum_regular() {
        assertEquals(30, testObj.sum(testData.getArr("numb")));
    }

    @Test
    @Order(101)
    void test101_sum_regular() {
        assertEquals(50, testObj.sum(testData.getArr("numb_1")));
    }

    @Test
    @Order(102)
    void test102_sum_regular() {
        assertEquals(10984, testObj.sum(testData.getArr("numb_2")));
    }

    @Test
    @Order(103)
    void test103_sum_regular() {
        assertEquals(141466, testObj.sum(testData.getArr("numb_3")));
    }
}