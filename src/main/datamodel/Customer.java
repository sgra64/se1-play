package datamodel;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Class of a {@link Customer} who can order {@link Article}s.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
@Getter
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@Accessors(fluent=true)
public final class Customer {

    /**
     * Unique positive 6-digit identifier attribute.
     * -- GETTER --
     * Return 6-digit {@link Customer} identifier attribute, e.g. {@code 892474}.
     * @return {@link Customer} id attribute.
     */
    int id;

    /**
     * {@link Customer} name attribute (last name).
     * -- GETTER --
     * Return the name attribute, e.g. {@code "Meyer"}.
     * @return name attribute.
     */
    String name;

    /**
     * {@link Customer} first names attribute.
     * -- GETTER --
     * Return the first names attribute, e.g. {@code "Eric"}.
     * @return first names attribute.
     */
    String firstNames;

    /**
     * {@link Customer} address attribute as single-line String.
     * -- GETTER --
     * Return the address attribute as single-line String, e.g.
     * {@code "Ahornallee 14b, 14050 Berlin (Charlottenburg)"}.
     * @return address attribute as single-line String.
     */
    String address;

    /**
     * {@link Customer} contact attribute (email address or phone number).
     * -- GETTER --
     * Return the contact attribute (email address or phone number),
     * e.g. {@code "eric98@yahoo.com"}.
     * @return contact attribute (email address or phone number).
     */
    String contact;
}
