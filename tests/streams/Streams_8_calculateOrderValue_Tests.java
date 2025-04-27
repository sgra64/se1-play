package streams;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_8_calculateOrderValue_Tests {

    /*
     * tested object implements the Streams interface
     */
    private static Streams testObj;

    private final long orderValue = 20562L;


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
    @Order(800)
    void test800_calculateValue_regular() {
        //
        long actual = testObj.calculateOrderValue(Streams.orders);
        assertEquals(orderValue, actual);
    }

    @Test
    @Order(801)
    void test801_calculateValue_regular() {
        //
        var extendedOrders = new ArrayList<Streams.Order>(Streams.orders);
        extendedOrders.addAll(List.of(
            new Streams.Order("Teller", 4,  649),   //  4x  649 = 2596
            new Streams.Order("Glas",  12,  249)    // 12x  249 = 2988
        ));
        long actual = testObj.calculateOrderValue(extendedOrders);
        long expected = orderValue + 2596 + 2988;
        assertEquals(expected, actual);
    }

    @Test
    @Order(810)
    void test810_calculateValue_emptyOrders() {
        //
        var emptyOrders = new ArrayList<Streams.Order>();
        long actual = testObj.calculateOrderValue(emptyOrders);
        assertEquals(0L, actual);
    }

    @Test
    @Order(890)
    void test890_calculateValue_irregular_orders_Null() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testObj.calculateOrderValue(null);    // throw exception if orders arg is null
        });
        assertEquals("orders argument is null.", thrown.getMessage());
    }
}