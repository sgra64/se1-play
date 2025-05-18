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
     * tested object is an instance of the Numbers class
     */
    private static Numbers testObj;

    /*
     * tested object is an instance of the Numbers class
     */
    private static NumbersData testData;


    /**
     * Static setup method executed once for all tests. Creates
     * the test object.
     * @throws Exception when test creation fails
     */
    @BeforeAll
    public static void setUpBeforeTests() throws Exception {
        testObj = Numbers.getInstance();
        testData = new NumbersData();
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