package streams;

import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_2_tenEvenRandomNumbers_Tests {

    /*
     * tested object implements the Streams interface
     */
    private static Streams testObj;


    /**
     * Static setup method executed once for all tests. Creates
     * the test object.
     * @throws Exception when test creation fails
     */
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        testObj = Streams.getInstance();
    }

    @Test
    @Order(200)
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