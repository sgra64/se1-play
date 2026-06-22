package components;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import components.Calculator_010_setup_Tests.TestData;
import datamodel.Order;

/**
 * {@link Calculator} <i>400'er</i> unit tests for method:
 * <pre>
 * - 400: long calculateIncludedVAT(Order.Item item);
 * </pre>
 */
public class Calculator_400_calculateIncludedVAT_Tests {

    /**
     * Unit under test.
     */
    final Calculator calculator = Calculator_010_setup_Tests.testData.calculator;

    /**
     * Test data shared between tests.
     */
    final TestData td = Calculator_010_setup_Tests.testData;


    @Test
    void test_400_calculateIncludedVAT_regular_order_8592_tests() {
        assertEquals( 414L, calculator.calculateIncludedVAT(td.item_8592_1));
        assertEquals( 190L, calculator.calculateIncludedVAT(td.item_8592_2));
        assertEquals( 523L, calculator.calculateIncludedVAT(td.item_8592_3));
        assertEquals( 191L, calculator.calculateIncludedVAT(td.item_8592_4));
    }

    @Test
    void test_401_calculateIncludedVAT_regular_order_6135_tests() {
        assertEquals( 1243L, calculator.calculateIncludedVAT(td.item_6135_1));
        assertEquals(  326L, calculator.calculateIncludedVAT(td.item_6135_2));
        assertEquals(  523L, calculator.calculateIncludedVAT(td.item_6135_3));
    }

    @Test
    void test_410_calculateIncludedVAT_exception_tests() {
        // 
        assertEquals("argument 'item' is null", assertThrows(
            IllegalArgumentException.class, () -> {
                calculator.calculateIncludedVAT((Order.Item)null);
            // 
            }).getMessage());
    }
}
