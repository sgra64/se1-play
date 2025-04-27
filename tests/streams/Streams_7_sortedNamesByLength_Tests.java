package streams;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_7_sortedNamesByLength_Tests {

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
    @Order(700)
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

    @Test
    @Order(710)
    void test710_sortedNamesByLength_emptyNames() {
        //
        List<String> expected = List.of();
        var emptyNames = new ArrayList<String>();
        List<String> actual = testObj.sortedNamesByLength(emptyNames);
        //
        assertEquals(0, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @Order(790)
    void test790_sortedNamesByLength_irregular_names_Null() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testObj.sortedNamesByLength(null);    // throw exception if names arg is null
        });
        assertEquals("names argument is null.", thrown.getMessage());
    }
}