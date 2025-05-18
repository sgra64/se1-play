package numbers;

import java.util.Set;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit 5 test class of Numbers class, findAll() method.
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_6_find_all_Tests {

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
    @Order(600)
    void test600_find_all_regular() {
        var actual = testObj.findAll(testData.getArr("numb"), 4);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(1, 3, 5), actual));
    }

    @Test
    @Order(601)
    void test601_find_all_regular() {
        var actual = testObj.findAll(testData.getArr("numb"), 9);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(6, 2), actual));
        actual = testObj.findAll(testData.getArr("numb"), -3);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(4), actual));
    }

    @Test
    @Order(602)
    void test602_find_all_regular() {
        var actual = testObj.findAll(testData.getArr("numb"), -2);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(0), actual));
        actual = testObj.findAll(testData.getArr("numb_1"), 8);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(0), actual));
        actual = testObj.findAll(testData.getArr("numb_1"), 4);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(6), actual));
        actual = testObj.findAll(testData.getArr("numb_2"), 600);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(6), actual));
    }

    @Test
    @Order(603)
    void test603_find_all_regular() {
        var actual = testObj.findAll(testData.getArr("numb"), 1);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(), actual));
        actual = testObj.findAll(testData.getArr("numb_1"), 6);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(), actual));
        actual = testObj.findAll(testData.getArr("numb_2"), 601);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(), actual));
    }
}