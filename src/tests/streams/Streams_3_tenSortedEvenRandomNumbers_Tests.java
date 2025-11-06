package streams;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Streams} interface.
 * Method under test: {@code Stream<Integer> tenSortedEvenRandomNumbers()}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_3_tenSortedEvenRandomNumbers_Tests {

    /*
     * tested object, instance that implements the {@link Streams} interface
     */
    private final Streams testObj;

    /**
     * Constructor to initialize test instance.
     */
    Streams_3_tenSortedEvenRandomNumbers_Tests() {
        this.testObj = Streams.getInstance();
    }

    @Test @Order(300)
    void test300_tenSortedEvenRandomNumbers_regular() {
        //
        List<Integer> actual = testObj.tenSortedEvenRandomNumbers().toList();
        //
        assertEquals(10, actual.size());
        //
        boolean testAllNumbers = actual.stream()
                .map(n -> n >= 0 && n < 1000 && n % 2 == 0)
                .reduce(true, (accumulator, n) -> accumulator && n);
        //
        assertTrue(testAllNumbers);
        //
        for(int i=1; i < actual.size(); i++) {
            assertTrue(actual.get(i-1) <= actual.get(i));
        }
        //
        IntStream.range(1, actual.size())
            .forEach(i -> assertTrue(actual.get(i-1) <= actual.get(i)));
    }
}
