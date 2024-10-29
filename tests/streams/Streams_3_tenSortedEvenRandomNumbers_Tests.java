package streams;

import application.Runtime;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_3_tenSortedEvenRandomNumbers_Tests {

    /*
     * tested object implements the Streams interface
     */
    private static Streams testObj;

    /**
     * Static setup method executed once for all tests. Creates
     * the test object.
     * 
     * @throws Exception is creation of test object fails
     */
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        testObj = Runtime.getInstance().getBean(Streams.class)
            .orElseThrow(() -> new Exception(String.format(
                "no test object for: %s", Streams.class.getName())));
    }

    @Test
    @Order(300)
    void test300_tenSortedEvenRandomNumbers_regular() {
        //
        List<Integer> actual = testObj.tenSortedEvenRandomNumbers();
        //
        assertEquals(10, actual.size());
        //
        boolean testAllNumbers = actual.stream()
                .map(n -> n > 0 && n < 1000 && n % 2 == 0)
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