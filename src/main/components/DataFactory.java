package components;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import datamodel.Article;
import datamodel.Category;
import datamodel.Customer;
import datamodel.Order;
import datamodel.VAT;


/**
 * {@link DataFactory} provides factory methods to create objects of data model
 * classes: {@link Customer}, {@link Article} and {@link Category}.
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface DataFactory {

    static DataFactory getInstance(ObjectMapper jsonObjectMapper, Logger logger) {
        return components.impl.DataFactory.getInstance(jsonObjectMapper, logger);
    }

    /**
     * Factory-method that attempts to creates an object of type {@link Customer}
     * from validated arguments.
     * @param id unique 6-digit identifier attribute.
     * @param name {@link Customer} last name attribute.
     * @param firstNames {@link Customer} first name attribute.
     * @param address {@link Customer} address attribute as single-line String.
     * @param contact {@link Customer} contact attribute (email address or phone number).
     * @return object of type {@link Customer} or empty {@link Optional}.
     */
    Optional<Customer> createCustomer(int id, String name, String firstNames, String address, String contact);

    /**
     * Factory-method that attempts to creates an object of type {@link Customer}
     * from key-value entries.
     * @param fieldMap map of named values (key-value pairs).
     * @return object of type {@link Customer} or empty {@link Optional}.
     */
    Optional<Customer> createCustomer(Map<String, Object> fieldMap);

    /**
     * Factory-method that attempts to creates an object of type {@link Article}
     * from validated arguments.
     * @param id unique 6-digit identifier attribute.
     * @param category reference to {@link Article} {@link Category}.
     * @param description description of {@link Article}.
     * @param price {@link Article} price (in cents).
     * @return object of type {@link Article} or empty {@link Optional}.
     */
    Optional<Article> createArticle(int id, int category, String description, int price);

    /**
     * Factory-method that attempts to creates an object of type {@link Article}
     * from key-value entries.
     * @param fieldMap map of named values (key-value pairs).
     * @return object of type {@link Article} or empty {@link Optional}.
     */
    Optional<Article> createArticle(Map<String, Object> fieldMap);

    /**
     * Factory-method that attempts to creates an object of type {@link Category}
     * from validated arguments.
     * @param id unique 2-digit {@link Category} identifier attribute.
     * @param description Description of {@link Category}.
     * @param vatCode {@link Category} VAT tax classification code.
     * @return object of type {@link Category} or empty {@link Optional}.
     */
    Optional<Category> createCategory(int id, String description, VAT vatCode);

    /**
     * Factory-method that attempts to creates an object of type {@link Category}
     * from key-value entries.
     * @param fieldMap map of named values (key-value pairs).
     * @return object of type {@link Category} or empty {@link Optional}.
     */
    Optional<Category> createCategory(Map<String, Object> fieldMap);

    /**
     * Factory-method that attempts to creates an object of type {@link Order}
     * from validated arguments.
     * @param id unique 10-digit identifier attribute.
     * @param customer {@link Customer} owning the {@link Order}.
     * @return object of type {@link Order} or empty {@link Optional}.
     */
    Optional<Order> createOrder(long id, Customer customer);

    /**
     * Factory-method that attempts to creates an object of type {@link Order}
     * from key-value entries.
     * @param fieldMap map of named values (key-value pairs).
     * @return object of type {@link Order} or empty {@link Optional}.
     */
    Optional<Order> createOrder(Map<String, Object> fieldMap,
        Repository<Customer, Integer> customerRepository,
        Repository<Article, Integer> articleRepository);
}
