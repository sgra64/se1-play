package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit 5 test class of Numbers class, sum_rec() method.
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_3_sum_recursion_Tests {

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
    @Order(300)
    void test300_sum_recursion_regular() {
        assertEquals(30, testObj.sum_recursive(testData.getArr("numb"), 0));
    }

    @Test
    @Order(301)
    void test301_sum_recursion_regular() {
        assertEquals(50, testObj.sum_recursive(testData.getArr("numb_1"), 0));
    }

    @Test
    @Order(302)
    void test302_sum_recursion_regular() {
        assertEquals(10984, testObj.sum_recursive(testData.getArr("numb_2"), 0));
    }

    @Test
    @Order(303)
    void test303_sum_recursion_regular() {
        assertEquals(141466, testObj.sum_recursive(testData.getArr("numb_3"), 0));
    }
}