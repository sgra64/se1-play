package components.impl;

import java.util.List;
import java.util.regex.Pattern;

import components.DataFactory;
import datamodel.Article;
import datamodel.Category;
import datamodel.Customer;
import datamodel.Order;
import datamodel.VAT;
import datamodel.Order.Item;

/**
 * Non-public class that validates arguments for object creation in {@link DataFactory}.
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
final class DataValidator {

    // allow Unicode-letters, spaces, hyphens and apostrophes in names of length 2-50 characters,
    // no digits or other special characters are allowed in names
    private static final String NAME_REGEX = "^[\\p{L}'][\\p{L}'\\s-]{0,48}[\\p{L}']$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    // OWASP (Open Web Application Security Project) recommended pattern to validate e-mail-adresses
    private static final String EMAIL_REGEX = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // E.164-standard to validate international phone numbers starting with '+' followed by the
    // country code and calling number (at most 15 digits), tolerates white spaces and hyphens '-'
    private static final String PHONE_REGEX = "^\\+(?:[0-9-]\\s*){6,14}[0-9]$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);


    /**
     * Validate arguments for creating {@link Customer} objects in {@link DataFactory}.
     * @param id unique positive 6-digit identifier attribute.
     * @param name {@link Customer} last name attribute.
     * @param firstNames {@link Customer} first name attribute.
     * @param address {@link Customer} address attribute as single-line String.
     * @param contact {@link Customer} contact attribute (email address or phone number).
     * @throws AssertionError when any of the arguments is invalid.
     */
    public void validateCustomer(int id, String name, String firstNames, String address, String contact) throws AssertionError {
        // 
        if(id < 100000 || id > 999999)
            throw new AssertionError(String.format("id out of valid 6-digit range (100000-999999): %d", id));
        if(name==null)
            throw new AssertionError("name attribute is 'null'");
        if(firstNames==null)
            throw new AssertionError("firstNames attribute is 'null'");
        // 
        if( ! NAME_PATTERN.matcher(name).matches())
            throw new AssertionError(String.format("name attribute: \"%s\" violates naming rules", name));
        
        if( ! NAME_PATTERN.matcher(firstNames).matches())
            throw new AssertionError(String.format("firstNames attribute: \"%s\" violates naming rules", firstNames));
        // 
        if(address==null || address.length() <= 2)
            throw new AssertionError(String.format("address \"%s\" is 'null' or too short", address));
        // 
        if(contact==null) {
            contact = "";   // accept null contacts and turn them to ""
        } else {
            boolean validEmail = EMAIL_PATTERN.matcher(contact).matches();
            boolean validPhone = PHONE_PATTERN.matcher(contact).matches();
            if( ! validEmail && ! validPhone)
                throw new AssertionError(String.format("contact: \"%s\" is neither a valid email address nor a valid phone number", contact));
        }
    }

    /**
     * Validate arguments for creating {@link Article} objects in {@link DataFactory}.
     * @param id unique positive 6-digit identifier attribute.
     * @param category
     * @param description
     * @param price
     * @throws AssertionError when any of the arguments is invalid.
     */
    public void validateArticle(int id, int category, String description, int price) throws AssertionError {
        // 
        if(id < 100000 || id > 999999)
            throw new AssertionError(String.format("id out of valid 6-digit range (100000-999999): %d", id));
    }

    /**
     * Validate arguments for creating {@link Category} objects in {@link DataFactory}.
     * @param id unique positive 2-digit identifier attribute.
     * @param description description of the {@link Article}, e.g. {@code "Küche & Kochen"}.
     * @param vatCode VAT tax classification code.
     * @throws AssertionError when any of the arguments is invalid.
     */
    public void validateCategory(int id, String description, VAT vatCode) throws AssertionError {
        // 
        if(id < 10 || id > 99)
            throw new AssertionError(String.format("id out of valid 2-digit range (10-99): %d", id));
    }

    /**
     * Validate arguments for creating {@link Order} objects in {@link DataFactory}.
     * @param id unique positive 10-digit identifier attribute.
     * @param customer reference to the owning {@link Customer}.
     * @param items list of {@link Item}s contained in this {@link Order}.
     * @throws AssertionError when any of the arguments is invalid.
     */
    public void validateOrder(long id, Customer customer, List<Order.Item> items) throws AssertionError {
        // 
        if(id < 1000000000L || id > 9999999999L)
            throw new AssertionError(String.format("id out of valid 10-digit range (1000000000-9999999999): %d", id));
    }
}
