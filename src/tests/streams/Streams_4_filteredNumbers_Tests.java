package streams;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Streams} interface.
 * Method under test: {@code List<Integer> filteredNumbers(String filter, int limit)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_4_filteredNumbers_Tests {

    /**
     * Immutable set of three-digit prime numbers to validate the "prime3"
     * filter function.
     * See, https://prime-numbers.info/list/first-1000-primes
     */
    private static final Set<Integer> threeDigitPrimes = Arrays.asList(
        /* 2,  3,   5,   7,  11,  13,  17,  19,  23,  29,
         31,  37,  41,  43,  47,  53,  59,  61,  67,  71,
         73,  79,  83, 89, 97,*/ 101, 103, 107, 109, 113,
        127, 131, 137, 139, 149, 151, 157, 163, 167, 173,
        179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
        233, 239, 241, 251, 257, 263, 269, 271, 277, 281,
        283, 293, 307, 311, 313, 317, 331, 337, 347, 349,
        353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
        419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
        467, 479, 487, 491, 499, 503, 509, 521, 523, 541,
        547, 557, 563, 569, 571, 577, 587, 593, 599, 601,
        607, 613, 617, 619, 631, 641, 643, 647, 653, 659,
        661, 673, 677, 683, 691, 701, 709, 719, 727, 733,
        739, 743, 751, 757, 761, 769, 773, 787, 797, 809,
        811, 821, 823, 827, 829, 839, 853, 857, 859, 863,
        877, 881, 883, 887, 907, 911, 919, 929, 937, 941,
        947, 953, 967, 971, 977, 983, 991, 997)
        //
        .stream().collect(Collectors.toSet());

    /**
     * Names of filter functions.
     */
    private static final String even = "even";
    private static final String div3 = "div3";
    private static final String prime3 = "prime3";

    /*
     * tested object, instance that implements the {@link Streams} interface
     */
    private final Streams testObj;

    /**
     * Constructor to initialize test instance.
     */
    Streams_4_filteredNumbers_Tests() {
        this.testObj = Streams.getInstance();
    }

    @Test @Order(400)
    void test400_filteredNumbers_50evenNumbers_regular() {
        //
        // 50 even numbers
        List<Integer> actual = testObj.filteredNumbers(even, 50);
        assertEquals(50, actual.size());
        //
        // verify all numbers are even
        boolean testAllNumbers = actual.stream()
            .map(n -> n >= 0 && n < 1000 && n % 2 == 0)
            .reduce(true, (accumulator, n) -> accumulator && n);
        //
        assertTrue(testAllNumbers);
    }

    @Test @Order(410)
    void test410_filteredNumbers_50divisibleBy3Numbers_regular() {
        //
        // 50 numbers divisible by 3
        List<Integer> actual = testObj.filteredNumbers(div3, 50);
        assertEquals(50, actual.size());
        //
        // verify all numbers are divisible by 3
        boolean testAllNumbers = actual.stream()
            .map(n -> n >= 0 && n < 1000 && n % 3 == 0)
            .reduce(true, (accumulator, n) -> accumulator && n);
        //
        assertTrue(testAllNumbers);
    }

    @Test @Order(420)
    void test420_filteredNumbers_50primeNumbers_regular() {
        //
        List<Integer> actual = testObj.filteredNumbers(prime3, 50);
        assertEquals(50, actual.size());
        //
        // verify all numbers are three-digit prime numbers
        boolean testAllNumbers = actual.stream()
            .map(n -> n >= 0 && n < 1000 && threeDigitPrimes.contains(n))
            .reduce(true, (accumulator, n) -> accumulator && n);
        //
        assertTrue(testAllNumbers);
    }

    @Test @Order(430)
    void test430_filteredNumbers_different_even_numbers_returned() {
        int limit = 5;
        List<Integer> l1 = testObj.filteredNumbers(even, limit);
        //
        // other three-digit prime numbers
        List<Integer> l2 = testObj.filteredNumbers(even, limit);
        //
        assertEquals(limit, l1.size());
        assertEquals(limit, l2.size());
        //
        // verify l1 and l2 contain different numbers
        boolean different = true;
        for(int i=0; different && i < limit; i++) {
            different = l1.get(i) != l2.get(i);
        }
        assertTrue(different);
    }

    @Test @Order(431)
    void test431_filteredNumbers_different_div_by_three_numbers_returned() {
        int limit = 5;
        List<Integer> l1 = testObj.filteredNumbers(div3, limit);
        //
        // other three-digit prime numbers
        List<Integer> l2 = testObj.filteredNumbers(div3, limit);
        //
        assertEquals(limit, l1.size());
        assertEquals(limit, l2.size());
        //
        // verify l1 and l2 contain different numbers
        boolean different = true;
        for(int i=0; different && i < limit; i++) {
            different = l1.get(i) != l2.get(i);
        }
        assertTrue(different);
    }

    @Test @Order(432)
    void test432_filteredNumbers_different_prime_numbers_returned() {
        int limit = 5;
        // three-digit prime numbers
        List<Integer> l1 = testObj.filteredNumbers(prime3, limit);
        //
        // other three-digit prime numbers
        List<Integer> l2 = testObj.filteredNumbers(prime3, limit);
        //
        assertEquals(limit, l1.size());
        assertEquals(limit, l2.size());
        //
        // verify l1 and l2 contain different numbers
        boolean different = true;
        for(int i=0; different && i < limit; i++) {
            different = l1.get(i) != l2.get(i);
        }
        assertTrue(different);
    }

    @Test @Order(490)
    void test490_filteredNumbers_50evenNumbers_illegalFilter_null() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            // Code under test is supposed to throw exception for illegal filter
            testObj.filteredNumbers(null, 50);    // null is an illegal filter key
        });
        assertEquals("filter null, empty or unknown: \"null\"", thrown.getMessage());
    }

    @Test @Order(491)
    void test491_filteredNumbers_50evenNumbers_illegalFilter_empty() {
        var thrown = assertThrows(IllegalArgumentException.class, () -> {
            // Code under test is supposed to throw exception for illegal index
            testObj.filteredNumbers("", 50);    // "" is an illegal filter key
        });
        assertEquals("filter null, empty or unknown: \"\"", thrown.getMessage());
    }

    @Test @Order(492)
    void test492_filteredNumbers_50evenNumbers_illegalFilter_unknown() {
        var thrown = assertThrows(IllegalArgumentException.class, () -> {
            // Code under test is supposed to throw exception for illegal index
            testObj.filteredNumbers("no-key", 50);    // "" is an illegal filter key
        });
        assertEquals("filter null, empty or unknown: \"no-key\"", thrown.getMessage());
    }

    @Test @Order(495)
    void test495_filteredNumbers_50evenNumbers_illegalLimit_negativ() {
        var thrown = assertThrows(IllegalArgumentException.class, () -> {
            // Code under test is supposed to throw exception for illegal limit
            testObj.filteredNumbers("even", -2);    // -2 is an illegal limit
        });
        assertEquals("negative limit: -2", thrown.getMessage());
    }
}
