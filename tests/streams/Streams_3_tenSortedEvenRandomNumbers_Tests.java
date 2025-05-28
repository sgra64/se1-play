package streams;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_3_tenSortedEvenRandomNumbers_Tests {

    /*
     * Tested object as instance of the {@link Streams} class.
     * Must not be static due to parallel execution of test methods.
     */
    private Streams testObj;


    /**
     * Setup method executed before each @Test method is executed.
     * @throws Exception if any exception occurs
     */
    @BeforeEach
    public void setUpBeforeEach() throws Exception {
        testObj = Streams.getInstance();
    }

    @Test
    @Order(300)
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