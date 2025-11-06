package streams;

import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Streams} interface.
 * Method under test: {@code List<String> filteredNames(List<String> names, String regex)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_5_filteredNames_Tests {

    /*
     * tested object, instance that implements the {@link Streams} interface
     */
    private final Streams testObj;

    /**
     * Constructor to initialize test instance.
     */
    Streams_5_filteredNames_Tests() {
        this.testObj = Streams.getInstance();
    }

    @Test @Order(500)
    void test500_filteredNames_regular() {
        //
        List<String> expected = List.of("Gonzalez", "Gomez", "Marquez");
        List<String> actual = testObj.filteredNames(Streams.names, ".*ez$");
        //
        assertEquals(3, actual.size());
        assertEquals(expected, actual);
    }

    @Test @Order(590)
    void test590_filteredNames_irregularNamesNull() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testObj.filteredNames(null, ".*ez$");    // throw exception if names arg is null
        });
        assertEquals("names or regex argument is null.", thrown.getMessage());
    }

    @Test @Order(591)
    void test591_filteredNames_irregularRegexNull() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testObj.filteredNames(Streams.names, null);    // throw exception if regex arg is null
        });
        assertEquals("names or regex argument is null.", thrown.getMessage());
    }

    @Test @Order(592)
    void test592_filteredNames_irregularNamesAndRegexNull() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testObj.filteredNames(null, null);    // throw exception if both args are null
        });
        assertEquals("names or regex argument is null.", thrown.getMessage());
    }
}
