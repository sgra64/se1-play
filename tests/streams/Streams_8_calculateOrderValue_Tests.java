package streams;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_8_calculateOrderValue_Tests {

    /*
     * Value of orders defined in the {@link Streams} interface.
     */
    private final long expectedOrderValue = 20562L;

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
    @Order(800)
    void test800_calculateValue_regular() {
        //
        long actual = testObj.calculateOrderValue(Streams.orders);
        assertEquals(expectedOrderValue, actual);
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
        long expected = expectedOrderValue + 2596 + 2988;
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