package components;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import components.Calculator_010_setup_Tests.TestData;
import datamodel.Order;

/**
 * {@link Calculator} <i>600'er</i> unit tests for method:
 * <pre>
 * - 600: long calculateOrderItemValue(Order.Item item);
 * </pre>
 */
public class Calculator_600_calculateOrderItemValue_Tests {

    /**
     * Unit under test.
     */
    final Calculator calculator = Calculator_010_setup_Tests.testData.calculator;

    /**
     * Test data shared between tests.
     */
    final TestData td = Calculator_010_setup_Tests.testData;


    @Test
    void test_600_calculateOrderItemValue_regular_order8592_tests() {
        assertEquals( 2596L, calculator.calculateOrderItemValue(td.item_8592_1));
        assertEquals( 1192L, calculator.calculateOrderItemValue(td.item_8592_2));
        assertEquals( 7995L, calculator.calculateOrderItemValue(td.item_8592_3));
        assertEquals( 1196L, calculator.calculateOrderItemValue(td.item_8592_4));
    }

    @Test
    void test_601_calculateOrderItemValue_regular_order6135_tests() {
        assertEquals( 7788L, calculator.calculateOrderItemValue(td.item_6135_1));
        assertEquals( 4990L, calculator.calculateOrderItemValue(td.item_6135_2));
        assertEquals( 7995L, calculator.calculateOrderItemValue(td.item_6135_3));
    }

    @Test
    void test_610_calculateOrderItemValue_exception_tests() {
        // 
        assertEquals("argument 'item' is null", assertThrows(
            IllegalArgumentException.class, () -> {
                calculator.calculateOrderItemValue((Order.Item)null);
            // 
            }).getMessage());
    }
}
