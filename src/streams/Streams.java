package streams;

import java.util.List;
import java.util.stream.Stream;

import application.Runner;


/**
 * Public interface with functions for the <i>"b2-streams"</i> assignment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Streams {

    /**
     * Aufgabe 1: Return 10 random integer numbers generated from a Stream<Integer>.
     * @return stream with 10 random numbers
     */
    Stream<Integer> tenRandomNumbers();

    /**
     * Aufgabe 2: Return 10 even random integer numbers generated from a Stream<Integer>.
     * @return stream with 10 even random numbers
     */
    Stream<Integer> tenEvenRandomNumbers();

    /**
     * Aufgabe 3: Return 10 even sorted random integer numbers generated from a Stream<Integer>.
     * @return stream with 10 even sorted random numbers
     */
    Stream<Integer> tenSortedEvenRandomNumbers();

    /**
     * Aufgabe 4: Method applies function from {@link filterFunctions} to a stream
     * of integer numbers returning only numbers matching the selected filter:
     * <pre>
     *  - filter="even": filter even numbers
     *  - filter="div3": filter numbers divisible by 3
     *  - filter="prime3": filter 3-digit prime numbers
     * </pre>
     * @param filter name of filter function in {@link filterFunctions}
     * @param limit amount of numbers returned
     * @return numbers matching the selected filter
     */
    List<Integer> filteredNumbers(String filter, int limit);

    /**
     * Aufgabe 5: Return sub-list from input names filtered by a regular expression.
     * Order of names remains unchanged, regular expression refers to {@link java.util.regex.Pattern}.
     * @param names input names
     * @param regex regular expression according to {@link java.util.regex.Pattern}
     * @return sub-list of names
     */
    List<String> filteredNames(List<String> names, String regex);

    /**
     * Aufgabe 6: Return alphabetically sorted list of names up to limit.
     * @param names input names
     * @param limit maximum number of names returned
     * @return alphabetically sorted list of names up to limit
     */
    List<String> sortedNames(List<String> names, int limit);

    /**
     * Aufgabe 7: Return list of names sorted by name length (first criteria)
     * and alphabetically (second criteria) for names of equal length.
     * @param names input names
     * @return names sorted by name length
     */
    List<String> sortedNamesByLength(List<String> names);

    /*
     * Aufgabe 8: class 'Order' defines an order (Bestellung) of an
     * article of n units at a price per unit (in Cent).
     */
    class Order {
        private final String article;
        private final long units;
        private final long unitPrice;
        //
        public Order(String description, long units, long unitPrice) {
            this.article = description;
            this.units = units;
            this.unitPrice = unitPrice;
        }

        // public getter methods
        public String article() { return article; }

        public long units() { return units; }

        public long unitPrice() { return unitPrice; }

        // public text conversion method
        public String toString() {
            return String.format("%-7s %dx %4d = %6d", article + ",", units, unitPrice, units * unitPrice);
        }
    }
    // 
    // Alternatively, declare 'Order' as record:
    // 
    // record Order(String article, long units, long unitPrice) {
    //     //
    //     public String toString() {
    //         return String.format("%-7s %dx %4d = %6d", article + ",", units, unitPrice, units * unitPrice);
    //     }
    // };

    /**
     * Aufgabe 8: Calculate value of orders.
     * @param orders list of orders to process
     * @return value of orders
     */
    long calculateOrderValue(List<Order> orders);

    /**
     * Aufgabe 9: Return list of orders sorted by order value (highest-value first).
     * @param orders list to sort
     * @return orders sorted by order value (highest-value first)
     */
    List<Order> sortOrdersByValue(List<Order> orders);


    /*
     * Test data with list of names used by driver code and in JUnit tests.
     */
    static final List<String> names = List.of(
        "Hendricks", "Raymond", "Pena", "Gonzalez", "Nielsen", "Hamilton",
        "Graham", "Gill", "Vance", "Howe", "Ray", "Talley", "Brock", "Hall",
        "Gomez", "Bernard", "Witt", "Joyner", "Rutledge", "Petty", "Strong",
        "Soto", "Duncan", "Lott", "Case", "Richardson", "Crane", "Cleveland",
        "Casey", "Buckner", "Hardin", "Marquez", "Navarro"
    );

    /*
     * Test data with orders used by driver code and in JUnit tests.
     */
    static final List<Order> orders = List.of(
        new Order("Becher", 2,  199),   // 2x  199 =  398
        new Order("Tasse",  7,  249),   // 7x  249 = 1743
        new Order("Stift",  4,   49),   // 4x   49 =  196
        new Order("Vase",   2,  999),   // 2x  999 = 1998
        new Order("Kanne",  5, 1499),   // 5x 1499 = 7495
        new Order("Lampe",  2, 1999),   // 2x 1999 = 3998
        new Order("Messer", 6,  789)    // 6x  789 = 4734
    );                                  // Summe:   20562 = 205,62€


    /**
     * Static getter method that returns an instance of the {@link Streams} interface.
     * @return instance of the {@link Streams} interface
     */
    static Streams getInstance() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstance()' "
            + "in interface 'Streams'. Create an implementation class and return.");
        // return new StreamsImpl();
    }

    /**
     * Factory method that creates instance of the {@link Runner} interface.
     * @param streams instance of the {@link Streams} interface used by the runner
     * @return instance of the {@link Runner} interface
     */
    static Runner createRunner(Streams streams) {
        return new StreamsRunner(streams);
    }
}