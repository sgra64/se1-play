package components;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import components.Calculator_010_setup_Tests.TestData;
import datamodel.Order;

/**
 * {@link Calculator} <i>700'er</i> unit tests for method:
 * <pre>
 * - 700: long calculateOrderValue(Order order);
 * </pre>
 */
public class Calculator_700_calculateOrderValue_Tests {

    /**
     * Unit under test.
     */
    final Calculator calculator = Calculator_010_setup_Tests.testData.calculator;

    /**
     * Test data shared between tests.
     */
    final TestData td = Calculator_010_setup_Tests.testData;


    @Test
    void test_500_calculateOrderValue_regular_order_5234_tests() {
        assertEquals( 1999L, calculator.calculateOrderValue(td.order_5234));
    }

    @Test
    void test_501_calculateOrderValue_regular_order_6173_tests() {
        assertEquals( 5685L, calculator.calculateOrderValue(td.order_6173));
    }

    @Test
    void test_502_calculateOrderValue_regular_order_8592_tests() {
        assertEquals(12979L, calculator.calculateOrderValue(td.order_8592));
    }

    @Test
    void test_503_calculateOrderValue_regular_order_6135_tests() {
        assertEquals(20773L, calculator.calculateOrderValue(td.order_6135));
    }

    @Test
    void test_504_calculateOrderValue_regular_order_3563_tests() {
        assertEquals( 1896L, calculator.calculateOrderValue(td.order_3563));
    }

    @Test
    void test_505_calculateOrderValue_regular_order_7372_tests() {
        assertEquals(17595L, calculator.calculateOrderValue(td.order_7372));
    }

    @Test
    void test_506_calculateOrderValue_regular_order_4450_tests() {
        assertEquals( 3343L, calculator.calculateOrderValue(td.order_4450));
    }

    @Test
    void test_710_calculateOrderValue_exception_tests() {
        // 
        assertEquals("argument 'order' is null", assertThrows(
            IllegalArgumentException.class, () -> {
                calculator.calculateOrderValue((Order)null);
            // 
            }).getMessage());
    }
}
