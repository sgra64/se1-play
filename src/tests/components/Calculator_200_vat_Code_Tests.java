package components;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import components.Calculator_010_setup_Tests.TestData;
import datamodel.VAT;

/**
 * {@link Calculator} <i>200'er</i> unit tests for method:
 * <pre>
 * - 200: VAT vat_Code(Article article);
 * </pre>
 */
public class Calculator_200_vat_Code_Tests {

    /**
     * Unit under test.
     */
    final Calculator calculator = Calculator_010_setup_Tests.testData.calculator;

    /**
     * Test data shared between tests.
     */
    final TestData td = Calculator_010_setup_Tests.testData;


    @Test
    void test_200_vat_Code_tests() {
        assertEquals(VAT.A, calculator.vat_Code(td.teller));
        assertEquals(VAT.A, calculator.vat_Code(td.becher));
        assertEquals(VAT.C, calculator.vat_Code(td.buch_oop));
        assertEquals(VAT.A, calculator.vat_Code(td.tasse));
    }

    @Test
    void test_201_vat_Code_extra_tests() {
        assertEquals(VAT.A, calculator.vat_Code(td.waescheleine));
        assertEquals(VAT.B, calculator.vat_Code(td.roggenbrot));
        assertEquals(VAT.C, calculator.vat_Code(td.book));
    }

    @Test
    void test_210_vat_Code_exception_tests() {
        // 
        assertEquals("argument 'article' is null", assertThrows(
            IllegalArgumentException.class, () -> {
                calculator.vat_Code(null);
            // 
            }).getMessage());
    }
}
