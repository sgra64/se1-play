package components;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import components.Calculator_010_setup_Tests.TestData;

/**
 * {@link Calculator} <i>300'er</i> unit tests for method:
 * <pre>
 * - 300: long calculateIncludedVAT(long grossPrice, double vatRate);
 * </pre>
 */
public class Calculator_300_calculateIncludedVAT_Tests {

    /**
     * Unit under test.
     */
    final Calculator calculator = Calculator_010_setup_Tests.testData.calculator;

    /**
     * Test data shared between tests.
     */
    final TestData td = Calculator_010_setup_Tests.testData;


    @Test
    void test_300_calculateIncludedVAT_regular_tests() {
        assertEquals( 1900L, calculator.calculateIncludedVAT(11900L, 0.19));
        assertEquals( 1597L, calculator.calculateIncludedVAT(10000L, 0.19));
        assertEquals( 1667L, calculator.calculateIncludedVAT(10000L, 0.20));
        assertEquals(  654L, calculator.calculateIncludedVAT(10000L, 0.07));
    }

    /**
     * Example from order '8592356245'
     */
    @Test
    void test_301_calculateIncludedVAT_regular_tests() {
        assertEquals( 414L, calculator.calculateIncludedVAT(2596L, 0.19));
        assertEquals( 190L, calculator.calculateIncludedVAT(1192L, 0.19));
        assertEquals( 523L, calculator.calculateIncludedVAT(7995L, 0.07));
        assertEquals( 191L, calculator.calculateIncludedVAT(1196L, 0.19));
    }

    @Test
    void test_310_calculateIncludedVAT_corner_tests() {
        assertEquals( 0L, calculator.calculateIncludedVAT(  0L, 0.19));
        assertEquals( 0L, calculator.calculateIncludedVAT(  3L, 0.19));  // 0.004789916
        assertEquals( 1L, calculator.calculateIncludedVAT(  4L, 0.19));  // 0.006386555
        assertEquals( 1L, calculator.calculateIncludedVAT(  6L, 0.19));  // 0.009579832
        assertEquals( 1L, calculator.calculateIncludedVAT(  9L, 0.19));  // 0.014369748
        assertEquals( 2L, calculator.calculateIncludedVAT( 10L, 0.19));  // 0.015966387
    }

    @Test
    void test_311_calculateIncludedVAT_corner_tests() {
        assertEquals( 342875540L, calculator.calculateIncludedVAT(td.max_int, 0.19));
        assertEquals( 342875540L, calculator.calculateIncludedVAT(td.max_int-4, 0.19));
        assertEquals( 342875539L, calculator.calculateIncludedVAT(td.max_int-5, 0.19));
    }

    @Test
    void test_312_calculateIncludedVAT_corner_tests() {
        assertEquals(          0L, calculator.calculateIncludedVAT(td.max_int, 0.00));
        assertEquals(   21262214L, calculator.calculateIncludedVAT(td.max_int, 0.01));
        assertEquals( 1068346136L, calculator.calculateIncludedVAT(td.max_int, 0.99));
    }

    @Test
    void test_320_calculateIncludedVAT_corner_values_tests() {
        assertEquals( 0L, calculator.calculateIncludedVAT(-10000L,  0.19));
        assertEquals( 0L, calculator.calculateIncludedVAT( 10000L, -0.19));
        assertEquals( 0L, calculator.calculateIncludedVAT(-10000L, -0.19));
    }

    @Test
    void test_321_calculateIncludedVAT_corner_neg_values_tests() {
        assertEquals( 0L, calculator.calculateIncludedVAT(    -1L,  0.19));
        assertEquals( 0L, calculator.calculateIncludedVAT( 10000L, -0.01));
    }
}
