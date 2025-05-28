package streams;

import java.util.List;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_1_tenRandomNumbers_Tests {

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
    @Order(100)
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