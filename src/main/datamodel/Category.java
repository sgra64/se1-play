package datamodel;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Class describes the {@link Category} of an {@link Article} and defines the
 * VAT tax classification (VAT: Value Added Tax).
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
// @lombok.extern.jackson.Jacksonized    // to use fluent methods by Jackson
@Getter
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@Accessors(fluent=true)
public final class Category {

    /**
     * Unique positive 2-digit {@link Category} identifier attribute.
     * -- GETTER --
     * Return 2-digit {@link Category} identifier attribute, e.g. {@code 12}.
     * @return {@link Article} id attribute.
     */
    int id;

    /**
     * Description of {@link Category}.
     * -- GETTER --
     * Return {@link Category} description, e.g. {@code "Küche & Kochen"}.
     * @return {@link Category} description.
     */
    String description;

    /**
     * {@link Category} VAT tax classification code.
     * -- GETTER --
     * Return {@link VAT} that applies to {@link Category}, e.g. {@code "A"}.
     * @return {@link VAT} of {@link Category}.
     */
    // @com.fasterxml.jackson.annotation.JsonProperty("vat_code")
    VAT vatCode;
}
