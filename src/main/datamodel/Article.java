package datamodel;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Class of a {@link Article} that can be ordered by a {@link Customer}.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
@Getter
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@Accessors(fluent=true)
public final class Article {

    /**
     * Unique positive 6-digit identifier attribute.
     * -- GETTER --
     * Return 6-digit {@link Article} identifier attribute, e.g. {@code 673276}.
     * @return {@link Article} id attribute.
     */
    int id;

    /**
     * Reference to {@link Article} {@link Category}.
     * -- GETTER --
     * Return Reference to {@link Article} {@link Category}, e.g. {@code 12}.
     * @return {@link Article} id attribute.
     */
    int category;

    /**
     * Description of {@link Article}.
     * -- GETTER --
     * Return {@link Article} description, e.g. {@code "Teller (Art Deco Collection)"}.
     * @return {@link Article} description.
     */
    String description;

    /**
     * {@link Article} price (in cents).
     * -- GETTER --
     * Return {@link Article} price (in cents), e.g. {@code 649} ({@code 6.49€}).
     * @return {@link Article} price (in cents).
     */
    int price;
}
