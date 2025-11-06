package streams;

import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Streams} interface.
 * Method under test: {@code Stream<Integer> tenEvenRandomNumbers()}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_2_tenEvenRandomNumbers_Tests {

    /*
     * tested object, instance that implements the {@link Streams} interface
     */
    private final Streams testObj;

    /**
     * Constructor to initialize test instance.
     */
    Streams_2_tenEvenRandomNumbers_Tests() {
        this.testObj = Streams.getInstance();
    }

    @Test @Order(200)
    void test200_tenEvenRandomNumbers_regular() {
        //
        List<Integer> actual = testObj.tenEvenRandomNumbers().toList();
        //
        assertEquals(10, actual.size());
        //
        boolean testAllNumbers = actual.stream()
                .map(n -> n >= 0 && n < 1000 && n % 2 == 0)
                .reduce(true, (accumulator, n) -> accumulator && n);
        //
        assertTrue(testAllNumbers);
    }
}
