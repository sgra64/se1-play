package numbers;

import java.util.Set;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code List<Integer> findAll(int[] numbers, int x)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_6_find_all_Tests {

    /*
     * tested object, instance that implements the {@link Numbers} interface
     */
    private final Numbers testObj;

    /*
     * test data used in tests
     */
    private final int[] numb;
    private final int[] numb_1;
    private final int[] numb_2;
    // private final int[] numb_3;

    /**
     * Constructor that initializes test instances.
     */
    Numbers_6_find_all_Tests() {
        this.testObj = Numbers.getInstance();
        var data = new NumbersData();
        this.numb = data.getArr("numb");
        this.numb_1 = data.getArr("numb_1");
        this.numb_2 = data.getArr("numb_2");
        // this.numb_3 = data.getArr("numb_3");
    }

    /**
     * Tests for 'regular' cases.
     */
    @Test
    @Order(600)
    void test600_find_all_regular() {
        var actual = testObj.findAll(numb, 4);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(1, 3, 5), actual));
    }

    @Test
    @Order(601)
    void test601_find_all_regular() {
        var actual = testObj.findAll(numb, 9);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(6, 2), actual));
        actual = testObj.findAll(numb, -3);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(4), actual));
    }

    @Test
    @Order(602)
    void test602_find_all_regular() {
        var actual = testObj.findAll(numb, -2);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(0), actual));
        actual = testObj.findAll(numb_1, 8);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(0), actual));
        actual = testObj.findAll(numb_1, 4);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(6), actual));
        actual = testObj.findAll(numb_2, 600);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(6), actual));
    }

    @Test
    @Order(603)
    void test603_find_all_regular() {
        var actual = testObj.findAll(numb, 1);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(), actual));
        actual = testObj.findAll(numb_1, 6);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(), actual));
        actual = testObj.findAll(numb_2, 601);
        assertTrue(Matchers.matchIgnoreOrder(Set.of(), actual));
    }

    /**
     * Tests for 'exception' cases.
     */
    @Test @Order(640)
    void test640_find_all_exception_null_arg() {
        IllegalArgumentException ex =
            assertThrows(IllegalArgumentException.class,
                () -> testObj.findLast(null, 0));
        // 
        assertEquals("illegal argument: null", ex.getMessage());
    }
}
