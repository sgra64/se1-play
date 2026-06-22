package components;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import components.Calculator_010_setup_Tests.TestData;
import datamodel.VAT;

/**
 * {@link Calculator} <i>100'er</i> unit tests for method:
 * <pre>
 * - 100: double taxRate(VAT vatCode);
 * </pre>
 */
public class Calculator_100_taxRate_Tests {

    /**
     * Unit under test.
     */
    final Calculator calculator = Calculator_010_setup_Tests.testData.calculator;

    /**
     * Test data shared between tests.
     */
    final TestData td = Calculator_010_setup_Tests.testData;


    @Test
    void test_100_taxRate() {
        assertEquals(0.19, calculator.taxRate(VAT.A));
        assertEquals(0.07, calculator.taxRate(VAT.B));
        assertEquals(0.07, calculator.taxRate(VAT.C));
    }

    @Test
    void test_110_taxRate_exception_tests() {
        // 
        assertEquals("argument 'vatCode' is null", assertThrows(
            IllegalArgumentException.class, () -> {
                calculator.taxRate(null);
            // 
            }).getMessage());
    }
}
