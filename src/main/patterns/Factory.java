package patterns;

import java.util.List;
import java.util.Optional;

import datamodel.Article;
import datamodel.Category;
import datamodel.VAT;
import datamodel.Customer;
import datamodel.Order;

/**
 * A {@link Factory} class provides {@code create()}-methods of objects of
 * various types. Goal of a {@link Factory} class is to centralize object
 * creation in a system, e.g. to validate arguments. Objects are created
 * only from valid arguments. Modern {@link Factory} classes hence return
 * objects as {@link Optional}.
 * <img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/patterns/factory.png" width="360"/>
 * <p>
 * The implementation of the <i>strict Factory pattern</i>:
 * <pre>public class Factory {
 * 
 *     Optional&lt;Customer&gt; <b><i>createCustomer</i></b>(int id, String lastName, String firstName, String address, String contact) {
 *         // ...
 *         Customer customer = null;
 *         // 
 *         // object is created only from valid arguments:
 *         if(validate(id, lastName, firstName, address, contact)) {
 *             customer = new Customer(id, lastName, firstName, address, contact);
 *         }
 *         // ...
 *         return Optional.ofNullable(customer);
 *     }
 * 
 *     Optional&lt;Article&gt; <b><i>createArticle</i></b>(int id, int category, String description, int price) {
 *         // ...
 *     }
 * 
 *     Optional&lt;Category&gt; <b><i>createCategory</i></b>(int id, String description, VAT_Code vatCode) {
 *         // ...
 *     }
 * 
 *     Optional&lt;Order&gt; <b><i>createOrder</i></b>(long id, Customer customer, List&lt;Order.Item&gt; items) {
 *         // ...
 *     }
 * }</pre>
 */
public class Factory {

    /**
     * Factory-method that attempts to creates an object of type {@link Customer}
     * from validated arguments.
     * @param id unique 6-digit identifier attribute.
     * @param lastName {@link Customer} last name attribute.
     * @param firstName {@link Customer} first name attribute.
     * @param address {@link Customer} address attribute as single-line String.
     * @param contact {@link Customer} contact attribute (email address or phone number).
     * @return object of type {@link Customer} or empty {@link Optional}.
     */
    Optional<Customer> createCustomer(int id, String lastName, String firstName, String address, String contact) {
        // ...
        return Optional.empty();
    }

    /**
     * Factory-method that attempts to creates an object of type {@link Article}
     * from validated arguments.
     * @param id unique 6-digit identifier attribute.
     * @param category reference to {@link Article} {@link Category}.
     * @param description description of {@link Article}.
     * @param price {@link Article} price (in cents).
     * @return object of type {@link Article} or empty {@link Optional}.
     */
    Optional<Article> createArticle(int id, int category, String description, int price) {
        // ...
        return Optional.empty();
    }

    /**
     * Factory-method that attempts to creates an object of type {@link Category}
     * from validated arguments.
     * @param id unique 2-digit {@link Category} identifier attribute.
     * @param description Description of {@link Category}.
     * @param vatCode {@link Category} VAT tax classification code.
     * @return object of type {@link Category} or empty {@link Optional}.
     */
    Optional<Category> createCategory(int id, String description, VAT vatCode) {
        // ...
        return Optional.empty();
    }

    /**
     * Factory-method that attempts to creates an object of type {@link Order}
     * from validated arguments.
     * @param id unique 10-digit identifier attribute.
     * @param customer {@link Customer} owning the {@link Order}.
     * @return object of type {@link Order} or empty {@link Optional}.
     */
    Optional<Order> createOrder(long id, Customer customer, List<Order.Item> items) {
        // ...
        return Optional.empty();
    }
}
