package streams;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Streams} interface.
 * Method under test: {@code long calculateOrderValue(List<Order> orders)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_8_calculateOrderValue_Tests {

    /*
     * Value of orders defined in the {@link Streams} interface.
     */
    private final long expectedOrderValue = 20562L;

    /*
     * tested object, instance that implements the {@link Streams} interface
     */
    private final Streams testObj;

    /**
     * Constructor to initialize test instance.
     */
    Streams_8_calculateOrderValue_Tests() {
        this.testObj = Streams.getInstance();
    }

    @Test @Order(800)
    void test800_calculateValue_regular() {
        //
        long actual = testObj.calculateOrderValue(Streams.orders);
        assertEquals(expectedOrderValue, actual);
    }

    @Test @Order(801)
    void test801_calculateValue_regular() {
        //
        var extendedOrders = new ArrayList<Streams.Order>(Streams.orders);
        extendedOrders.addAll(List.of(
            new Streams.Order("Teller", 4,  649),   //  4x  649 = 2596
            new Streams.Order("Glas",  12,  249)    // 12x  249 = 2988
        ));
        long actual = testObj.calculateOrderValue(extendedOrders);
        long expected = expectedOrderValue + 2596 + 2988;
        assertEquals(expected, actual);
    }

    @Test @Order(810)
    void test810_calculateValue_emptyOrders() {
        //
        var emptyOrders = new ArrayList<Streams.Order>();
        long actual = testObj.calculateOrderValue(emptyOrders);
        assertEquals(0L, actual);
    }

    @Test @Order(890)
    void test890_calculateValue_irregular_orders_Null() {
        //
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testObj.calculateOrderValue(null);    // throw exception if orders arg is null
        });
        assertEquals("orders argument is null.", thrown.getMessage());
    }
}
