package streams;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Streams} interface.
 * Method under test: {@code List<String> sortedNamesByLength(List<String> names)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_7_sortedNamesByLength_Tests {

    /*
     * tested object, instance that implements the {@link Streams} interface
     */
    private final Streams testObj;

    /**
     * Constructor to initialize test instance.
     */
    Streams_7_sortedNamesByLength_Tests() {
        this.testObj = Streams.getInstance();
    }

    @Test @Order(700)
    void test700_sortedNamesByLength_regular() {
        //
        List<String> expected = List.of(
            "Ray", "Case", "Gill", "Hall", "Howe", "Lott", "Pena", "Soto", "Witt", "Brock",
            "Casey", "Crane", "Gomez", "Petty", "Vance", "Duncan", "Graham", "Hardin", "Joyner", "Strong",
            "Talley", "Bernard", "Buckner", "Marquez", "Navarro", "Nielsen", "Raymond", "Gonzalez", "Hamilton", "Rutledge",
            "Cleveland", "Hendricks", "Richardson"
        );
        List<String> actual = testObj.sortedNamesByLength(Streams.names);
        //
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test @Order(710)
    void test710_sortedNamesByLength_emptyNames() {
        //
        List<String> expected = List.of();
        var emptyNames = new ArrayList<String>();
        List<String> actual = testObj.sortedNamesByLength(emptyNames);
        //
        assertEquals(0, actual.size());
        assertEquals(expected, actual);
    }

    @Test @Order(790)
    void test790_sortedNamesByLength_irregular_names_Null() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testObj.sortedNamesByLength(null);    // throw exception if names arg is null
        });
        assertEquals("names argument is null.", thrown.getMessage());
    }
}
