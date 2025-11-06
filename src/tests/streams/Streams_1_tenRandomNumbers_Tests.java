package streams;

import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Streams} interface.
 * Method under test: {@code Stream<Integer> tenRandomNumbers()}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_1_tenRandomNumbers_Tests {

    /*
     * tested object, instance that implements the {@link Streams} interface
     */
    private final Streams testObj;

    /**
     * Constructor to initialize test instance.
     */
    Streams_1_tenRandomNumbers_Tests() {
        this.testObj = Streams.getInstance();
    }

    @Test @Order(100)
    void test100_tenRandomNumbers_regular() {
        //
        List<Integer> actual = testObj.tenRandomNumbers().toList();
        //
        assertEquals(10, actual.size());
        //
        boolean testAllNumbers = actual.stream()
                .map(n -> n >= 0 && n < 1000)
                .reduce(true, (accumulator, n) -> accumulator && n);
        //
        assertTrue(testAllNumbers);
    }
}
