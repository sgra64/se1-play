package components.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import components.Repository;
import datamodel.Article;
import datamodel.Category;
import datamodel.Customer;
import datamodel.Order;
import datamodel.VAT;


/**
 * Non-public implementation class of the {@link DataFactory} interface that provides
 * factory methods to create objects of data model classes: {@link Customer},
 * {@link Article}, {@link Category} and {@link Order}.
 */
final class DataFactoryImpl extends DataFactory implements components.DataFactory {

    private final DataValidator dataValidator;

    private final ObjectMapper jsonObjectMapper;

    private final Logger logger;

    private final String[] splitName = new String[] { "", "" };


    DataFactoryImpl(ObjectMapper jsonObjectMapper, Logger logger) {
        this.jsonObjectMapper = jsonObjectMapper;
        this.logger = logger;
        this.dataValidator = new DataValidator();
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
    @Override
    public Optional<Customer> createCustomer(int id, String name, String firstNames, String address, String contact) {
        try {
            // split name to parts, e.g. name: "Meyer, Eric" to name: "Meyer", firstNames: "Eric"
            splitName(splitName, name, firstNames);
            name = splitName[0];
            firstNames = splitName[1];
            address = trim(address);
            contact = trim(contact);
            // 
            // final argument validation, throws AssertionError if any of the arguments violates rules
            dataValidator.validateCustomer(id, name, firstNames, address, contact);
            // 
            if(customerCreator != null) {
                Customer customer = customerCreator.create(id, name, firstNames, address, contact);
                // 
                logger.info(String.format("created customer: [id: %d, name: \"%s, %s\", address: \"%s\", contact: \"%s\"]",
                    customer.id(), customer.name(), customer.firstNames(), customer.address(), customer.contact()));
                // 
                return Optional.of(customer);
            }
        // 
        } catch(AssertionError e) {
            logger.error(String.format("%s while creating 'Customer' object: [%s]", e.getMessage(),
                String.format("id: %d, name: \"%s\", firstNames: \"%s\", address: \"%s\", contact: \"%s\"",
                    id, name, firstNames, address, contact)));
        }
        return Optional.empty();
    }

    private void splitName(String[] splitName, String name, String firstNames) {
        // if lastname contains '[,;]' or firstNames is empty, perform split-name
        // operation, e.g. when 'Meyer, Eric' is found in last name and convert
        // to first name: 'Eric' and name: 'Meyer'
        if(firstNames.trim().isEmpty() || name.matches(".*[,;].*")) {
            String singleStringName = String.format("%s %s", firstNames, name);
            String first="", last="";
            String[] spl1 = singleStringName.split("[,;]");
            if(spl1.length > 1) {
                // two-section name with last name first
                last = spl1[0];
                first = spl1[1];    // ignore higher splitters in first names
            } else {
                // no separator [,;] -> split by white spaces;
                for(String s : singleStringName.split("\\s+")) {
                    if( last.length() > 0 ) {
                        // collect firstNames in order and name as last
                        first += (first.length()==0? "" : " ") + last;
                    }
                    last = s;
                }
            }
            if(splitName != null && splitName.length >= 2) {
                splitName[0] = trim(last);
                splitName[1] = trim(first);
            } else {
                splitName = new String[] { trim(last), trim(first) };
            }
        }
    }

    /**
     * Trim leading and trailing white spaces {@code [\s]}, commata {@code [,;]}
     * and quotes {@code ["']} from a String (used for names and contacts).
     * @param s String to trim
     * @return trimmed String
     */
    private String trim(String s) {
        s = s.replaceAll("^[\\s\"',;]*", "");   // trim leading white spaces[\s], commata[,;] and quotes['"]
        s = s.replaceAll( "[\\s\"',;]*$", "");  // trim trailing accordingly
        return s;
    }

    // // Gemini: "Gib mit Java code, der prüft, ob ein String ein gültiger Name einer Person ist."
    // String[] testNames = {
    //     "Max",                  // Gültig
    //     "Anna-Lena",            // Gültig (Doppelname mit Bindestrich)
    //     "Müller",               // Gültig (Umlaute)
    //     "D'Angelo",             // Gültig (Apostroph)
    //     "René",                 // Gültig (Akzente)
    //     "José Eduardo",         // Gültig (Leerzeichen)
    //     "A",                    // Ungültig (Zu kurz)
    //     "Max123",               // Ungültig (Zahlen)
    //     "Max_Mustermann",       // Ungültig (Unterstrich)
    //     "-Anna-"                // Ungültig (Start/Ende mit Bindestrich)
    // };
    // 
    // for (String name : testNames) {
    //     System.out.printf("Name: '%-16s' -> %s%n", name, isValidName(name) ? "GÜLTIG" : "UNGÜLTIG");
    // }

    /**
     * Factory-method that attempts to creates an object of type {@link Customer}
     * from key-value entries.
     * @param fieldMap map of named values (key-value pairs).
     * @return object of type {@link Customer} or empty {@link Optional}.
     */
    @Override
    public Optional<Customer> createCustomer(Map<String, Object> fieldMap) {
        try {
            int id = (int)assertField(fieldMap, "id", Integer.class);
            String name = (String)assertField(fieldMap, "name", String.class);
            String address = (String)assertField(fieldMap, "address", String.class);
            String contact = (String)assertField(fieldMap, "contact", String.class);
            // 
            return createCustomer(id, name, "", address, contact);
        // 
        } catch(AssertionError e) {
            logger.error(String.format("%s in record: \"%s\"", e.getMessage(), fieldMap));
        }
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
    @Override
    public Optional<Article> createArticle(int id, int category, String description, int price) {
        try {
            // final argument validation, throws AssertionError if any of the arguments violates rules
            dataValidator.validateArticle(id, category, description, price);
            // 
            if(articleCreator != null) {
                Article article = articleCreator.create(id, category, description, price);
                // 
                logger.info(String.format("created article: [id: %d, category: %d, description: \"%s\", price: %d]",
                    article.id(), article.category(), article.description(), article.price()));
                // 
                return Optional.of(article);
            }
        // 
        } catch(AssertionError e) {
            logger.error(String.format("%s while creating 'Article' object: [%s]", e.getMessage(),
                String.format("id: %d, category: %d, description: \"%s\", price: %d",
                    id, category, description, price)));
        }
        return Optional.empty();
    }

    /**
     * Factory-method that attempts to creates an object of type {@link Article}
     * from key-value entries.
     * @param fieldMap map of named values (key-value pairs).
     * @return object of type {@link Article} or empty {@link Optional}.
     */
    @Override
    public Optional<Article> createArticle(Map<String, Object> fieldMap) {
        try {
            int id = (int)assertField(fieldMap, "id", Integer.class);
            int category = (int)assertField(fieldMap, "category", Integer.class);
            String item = (String)assertField(fieldMap, "item", String.class);
            int price = (int)assertField(fieldMap, "price", Integer.class);
            // 
            return createArticle(id, category, item, price);
        // 
        } catch(AssertionError e) {
            logger.error(String.format("%s in record: \"%s\"", e.getMessage(), fieldMap));
        }
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
    @Override
    public Optional<Category> createCategory(int id, String description, VAT vatCode) {
        try {
            // final argument validation, throws AssertionError if any of the arguments violates rules
            dataValidator.validateCategory(id, description, vatCode);
            // 
            if(categoryCreator != null) {
                Category category = categoryCreator.create(id, description, vatCode);
                // 
                logger.info(String.format("created category: [id: %d, description: \"%s\", vatCode: \"%s\"]",
                    category.id(), category.description(), category.vatCode()));
                // 
                return Optional.of(category);
            }
        // 
        } catch(AssertionError e) {
            logger.error(String.format("%s while creating 'Category' object: [%s]", e.getMessage(),
                String.format("category: %d, description: \"%s\", vatCode: \"%s\"",
                    id, description, vatCode.toString())));
        }
        return Optional.empty();
    }

    /**
     * Factory-method that attempts to creates an object of type {@link Category}
     * from key-value entries.
     * @param fieldMap map of named values (key-value pairs).
     * @return object of type {@link Category} or empty {@link Optional}.
     */
    @Override
    public Optional<Category> createCategory(Map<String, Object> fieldMap) {
        try {
            int id = (int)assertField(fieldMap, "id", Integer.class);
            String description = (String)assertField(fieldMap, "description", String.class);
            // throws 'IllegalArgumentException' if enum 'VAT_Code' cannot be created from String
            VAT vatClass = VAT.valueOf(((String)assertField(fieldMap, "vat_code", String.class)).toUpperCase());
            // 
            return createCategory(id, description, vatClass);
        // 
        } catch(IllegalArgumentException | AssertionError e) {
            logger.error(String.format("%s in record: \"%s\"", e.getMessage(), fieldMap));
        }
        return Optional.empty();
    }

    /**
     * Factory-method that attempts to creates an object of type {@link Order}
     * from validated arguments.
     * @param id unique 10-digit identifier attribute.
     * @param customer {@link Customer} owning the {@link Order}.
     * @return object of type {@link Order} or empty {@link Optional}.
     */
    @Override
    public Optional<Order> createOrder(long id, Customer customer) {
        return createOrder(id, customer, new ArrayList<Order.Item>());
    }

    /**
     * Factory-method that attempts to creates an object of type {@link Order}
     * from validated arguments.
     * @param id unique 10-digit identifier attribute.
     * @param customer {@link Customer} owning the {@link Order}.
     * @param items collection of {@link Order.Item}s included in {@link Order}.
     * @return object of type {@link Order} or empty {@link Optional}.
     */
    private Optional<Order> createOrder(long id, Customer customer, List<Order.Item> items) {
        try {
            // final argument validation, throws AssertionError if any of the arguments violates rules
            dataValidator.validateOrder(id, customer, items);
            // 
            if(orderCreator != null) {
                Order order = orderCreator.create(id, customer, items);
                // 
                logger.info(String.format("created order: [id: %d, customer: \"%s, %s\", items: %d]",
                    order.id(), order.customer().name(), order.customer().firstNames(), order.items().size()));
                // 
                return Optional.of(order);
            }
        // 
        } catch(AssertionError e) {
            logger.error(String.format("%s while creating 'Order' object: [%s]", e.getMessage(),
                String.format("id: %d, Customer id: %d, name: \"%s\", firstNames: \"%s\"",
                    id, customer.id(), customer.name(), customer.firstNames())));
        }
        return Optional.empty();
    }

    /**
     * Factory-method that attempts to creates an object of type {@link Order}
     * from key-value entries.
     * @param fieldMap map of named values (key-value pairs).
     * @return object of type {@link Order} or empty {@link Optional}.
     */
    @Override
    public Optional<Order> createOrder(Map<String, Object> fieldMap,
        Repository<Customer, Integer> customerRepository,
        Repository<Article, Integer> articleRepository)
    {
        try {
            if(customerRepository==null)
                throw new AssertionError("customerRepository is null");
            if(articleRepository==null)
                throw new AssertionError("articleRepository is null");
            // 
            long order_id = (long)assertField(fieldMap, "order-id", Long.class);
            int customer_id = (int)assertField(fieldMap, "customer-id", Integer.class);
            // 
            List<Order.Item> items = ((List<?>)assertField(fieldMap, "items", List.class))
                .stream()
                .map(json -> {
                    Map<String, Object> fieldMapItem = jsonObjectMapper.convertValue(json, new TypeReference<Map<String, Object>>(){});
                    // 
                    int item_id = (int)assertField(fieldMapItem, "item-id", Integer.class);
                    Article article = articleRepository.findById(item_id)
                        .orElseThrow(() ->
                            new AssertionError(String.format("Article with id=%d not found", customer_id)));
                    // 
                    int itemCount = (int)assertField(fieldMapItem, "item-count", Integer.class);
                    // 
                    return orderItemCreator.create(article, itemCount);
                })
                .toList();
            // 
            Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() ->
                    new AssertionError(String.format("Customer with id=%d not found", customer_id)));
            // 
            return createOrder(order_id, customer, items);
        // 
        } catch(AssertionError e) {
            logger.error(String.format("%s in record: \"%s\"", e.getMessage(), fieldMap));
        }
        return Optional.empty();
    }

    /**
     * Get value for key and validate its type. Method throws execption when
     * the key is null, not present or in case of value type mismatch.
     * @param fieldMap map to get value from
     * @param key key to get value for
     * @param expectedType expected value type
     * @return value for key
     * @throws AssertionError when key is null, not present or type mismatch
     */
    private Object assertField(Map<String, Object> fieldMap, String key, Class<?> expectedType) throws AssertionError {
        // 
        Object value = fieldMap.get(key);
        if(key==null) {
            throw new AssertionError("key is 'null'");
        }
        if(value==null) {
            throw new AssertionError(String.format("no field '%s'", key));
        }
        if( ! expectedType.isAssignableFrom(value.getClass())) {
            throw new AssertionError(String.format("type mismatch (value): expected type '%s' but got type '%s'",
                expectedType.toString(), value.getClass().toString()));
        }
        return value;
    }
}
