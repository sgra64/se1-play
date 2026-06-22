package components;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import components.Calculator_010_setup_Tests.TestData;
import datamodel.Order;

/**
 * {@link Calculator} <i>500'er</i> unit tests for method:
 * <pre>
 * - 500: long calculateIncludedVAT(Order order);
 * </pre>
 */
public class Calculator_500_calculateIncludedVAT_Tests {

    /**
     * Unit under test.
     */
    final Calculator calculator = Calculator_010_setup_Tests.testData.calculator;

    /**
     * Test data shared between tests.
     */
    final TestData td = Calculator_010_setup_Tests.testData;


    @Test
    void test_500_calculateIncludedVAT_regular_order_5234_tests() {
        assertEquals(  319L, calculator.calculateIncludedVAT(td.order_5234));
    }

    @Test
    void test_501_calculateIncludedVAT_regular_order_6173_tests() {
        assertEquals(  371L, calculator.calculateIncludedVAT(td.order_6173));
    }

    @Test
    void test_502_calculateIncludedVAT_regular_order_8592_tests() {
        assertEquals( 1318L, calculator.calculateIncludedVAT(td.order_8592));
    }

    @Test
    void test_503_calculateIncludedVAT_regular_order_6135_tests() {
        assertEquals( 2092L, calculator.calculateIncludedVAT(td.order_6135));
    }

    @Test
    void test_504_calculateIncludedVAT_regular_order_3563_tests() {
        assertEquals(  302L, calculator.calculateIncludedVAT(td.order_3563));
    }

    @Test
    void test_505_calculateIncludedVAT_regular_order_7372_tests() {
        assertEquals( 2743L, calculator.calculateIncludedVAT(td.order_7372));
    }

    @Test
    void test_506_calculateIncludedVAT_regular_order_4450_tests() {
        assertEquals(  533L, calculator.calculateIncludedVAT(td.order_4450));
    }

    @Test
    void test_510_calculateIncludedVAT_exception_tests() {
        // 
        assertEquals("argument 'order' is null", assertThrows(
            IllegalArgumentException.class, () -> {
                calculator.calculateIncludedVAT((Order)null);
            // 
            }).getMessage());
    }
}
