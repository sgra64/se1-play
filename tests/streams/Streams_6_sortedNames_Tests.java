package streams;

import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_6_sortedNames_Tests {

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
    @Order(600)
    void test600_sortedNames_regular() {
        //
        int limit = 8;
        List<String> expected = List.of(
            "Bernard", "Brock", "Buckner", "Case",
            "Casey", "Cleveland", "Crane", "Duncan"
        );
        List<String> actual = testObj.sortedNames(Streams.names, limit);
        //
        assertEquals(limit, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @Order(601)
    void test601_sortedNames_regular() {
        //
        int limit = 12;
        List<String> expected = List.of(
            "Bernard", "Brock", "Buckner", "Case",
            "Casey", "Cleveland", "Crane", "Duncan",
            "Gill", "Gomez", "Gonzalez", "Graham"
        );
        List<String> actual = testObj.sortedNames(Streams.names, limit);
        //
        assertEquals(limit, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @Order(610)
    void test610_sortedNames_emptyNames() {
        //
        List<String> expected = List.of();
        List<String> actual = testObj.sortedNames(Streams.names, 0);
        //
        assertEquals(0, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @Order(690)
    void test690_sortedNames_irregularNamesNull() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testObj.sortedNames(null, 8);    // throw exception if names arg is null
        });
        assertEquals("names argument is null.", thrown.getMessage());
    }

    @Test
    @Order(691)
    void test691_sortedNames_irregularLimitNegativ() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testObj.sortedNames(Streams.names, -10);    // throw exception if limit is negative
        });
        assertEquals("limit argument is negative: -10.", thrown.getMessage());
    }

    @Test
    @Order(692)
    void test692_sortedNames_irregularNamesNullAndLimitNegativ() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testObj.sortedNames(null, -10);    // throw exception if both args are null
        });
        assertEquals("names argument is null.", thrown.getMessage());
    }
}