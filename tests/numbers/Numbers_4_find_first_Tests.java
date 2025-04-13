package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit 5 test class of Numbers class, findFirst() method.
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_4_find_first_Tests {

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
    @Order(400)
    void test400_find_first_regular() {
        int[] numbers = testData.getArr("numb");
        assertEquals(1, testObj.findFirst(numbers, 4));    // find element with duplicates
        assertEquals(0, testObj.findFirst(numbers, -2));   // find first element
        assertEquals(numbers.length-1, testObj.findFirst(numbers, 5));    // find last element
        assertEquals(-1, testObj.findFirst(numbers, 77));  // find element not present
        assertEquals(-1, testObj.findFirst(numbers, 0));   // find 0 element
        assertEquals(4, testObj.findFirst(numbers, -3));   // find negative element
        assertEquals(-1, testObj.findFirst(numbers, -33)); // find negative element
    }

    @Test
    @Order(401)
    void test401_find_first_regular() {
        int[] numbers = testData.getArr("numb_1");
        assertEquals(4, testObj.findFirst(numbers, 14));      // find element with duplicates
        assertEquals(0, testObj.findFirst(numbers, 8));       // find first element
        assertEquals(-1, testObj.findFirst(numbers, 77));     // find element not present
        assertEquals(-1, testObj.findFirst(numbers, 0));      // find 0 element
        assertEquals(-1, testObj.findFirst(numbers, -3));     // find non-present negative element
    }

    @Test
    @Order(402)
    void test402_find_first_regular() {
        int[] numbers = testData.getArr("numb_2");
        assertEquals(13, testObj.findFirst(numbers, 7));      // find element with duplicates
        assertEquals(0, testObj.findFirst(numbers, 371));     // find first element
        assertEquals(23, testObj.findFirst(numbers, 636));    // find last element
        assertEquals(-1, testObj.findFirst(numbers, 77));     // find element not present
        assertEquals(-1, testObj.findFirst(numbers, 0));      // find 0 element
        assertEquals(-1, testObj.findFirst(numbers, -3));     // find non-present negative element
    }

    @Test
    @Order(403)
    void test403_find_first_regular() {
        int[] numbers = testData.getArr("numb_3");
        assertEquals(13, testObj.findFirst(numbers, 7));      // find element with duplicates
        assertEquals(0, testObj.findFirst(numbers, 799));     // find first element
        assertEquals(62, testObj.findFirst(numbers, 500));    // find last element
        assertEquals(-1, testObj.findFirst(numbers, 77));     // find element not present
        assertEquals(-1, testObj.findFirst(numbers, 0));      // find 0 element
        assertEquals(-1, testObj.findFirst(numbers, -3));     // find non-present negative element
    }
}