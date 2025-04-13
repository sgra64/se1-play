package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit 5 test class of Numbers class, findLast() method.
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_5_find_last_Tests {

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
    @Order(500)
    void test500_find_last_regular() {
        int[] numbers = testData.getArr("numb");
        assertEquals(5, testObj.findLast(numbers, 4));    // find element with duplicates
        assertEquals(0, testObj.findLast(numbers, -2));   // find first element
        assertEquals(numbers.length-1, testObj.findLast(numbers, 5));    // find last element
        assertEquals(-1, testObj.findLast(numbers, 77));  // find element not present
        assertEquals(-1, testObj.findLast(numbers, 0));   // find 0 element
        assertEquals(4, testObj.findLast(numbers, -3));   // find negative element
        assertEquals(-1, testObj.findLast(numbers, -33)); // find negative element
    }

    @Test
    @Order(501)
    void test501_find_last_regular() {
        int[] numbers = testData.getArr("numb_1");
        assertEquals(4, testObj.findLast(numbers, 14));      // find element with duplicates
        assertEquals(numbers.length-1, testObj.findLast(numbers, 4));    // find last element
        assertEquals(-1, testObj.findLast(numbers, 77));     // find element not present
        assertEquals(-1, testObj.findLast(numbers, 0));      // find 0 element
        assertEquals(-1, testObj.findLast(numbers, -3));     // find non-present negative element
    }

    @Test
    @Order(502)
    void test502_find_last_regular() {
        int[] numbers = testData.getArr("numb_2");
        assertEquals(13, testObj.findLast(numbers, 7));      // find element with duplicates
        assertEquals(0, testObj.findLast(numbers, 371));     // find first element
        assertEquals(numbers.length-1, testObj.findLast(numbers, 636));    // find last element
        assertEquals(-1, testObj.findLast(numbers, 77));     // find element not present
        assertEquals(-1, testObj.findLast(numbers, 0));      // find 0 element
        assertEquals(-1, testObj.findLast(numbers, -3));     // find non-present negative element
    }

    @Test
    @Order(503)
    void test503_find_last_regular() {
        int[] numbers = testData.getArr("numb_3");
        assertEquals(13, testObj.findLast(numbers, 7));      // find element with duplicates
        assertEquals(0, testObj.findLast(numbers, 799));     // find first element
        assertEquals(numbers.length-1, testObj.findLast(numbers, 500));    // find last element
        assertEquals(-1, testObj.findLast(numbers, 77));     // find element not present
        assertEquals(-1, testObj.findLast(numbers, 0));      // find 0 element
        assertEquals(-1, testObj.findLast(numbers, -3));     // find non-present negative element
    }
}