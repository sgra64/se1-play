package datamodel;

import java.util.List;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Class of what a {@link Customer} can order.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
@Getter
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@Accessors(fluent=true)
public final class Order {

    @Getter
    @AllArgsConstructor(access=AccessLevel.PROTECTED)
    @Accessors(fluent=true)
    public static class Item {

        /**
         * Reference to {@link Article} ordered as part of an {@link Order}.
         * -- GETTER --
         * Return {@link Article} ordered.
         * @return {@link Article} ordered.
         */
        private Article article;

        /**
         * Amount of {@link Article}s ordered.
         * -- GETTER --
         * Return amount of {@link Article}s ordered.
         * @return amount of {@link Article}s ordered.
         */
        private int unitsOrdered;
    }

    /**
     * Unique positive 10-digit identifier attribute.
     * -- GETTER --
     * Return 10-digit {@link Order} identifier attribute, e.g. {@code 4450305661}.
     * @return {@link Order} id attribute.
     */
    private final long id;

    /**
     * Reference to the owning {@link Customer}.
     * -- GETTER --
     * Return Reference to owning {@link Customer}.
     * @return {@link Customer} owning the {@link Order}.
     */
    private final Customer customer;

    /**
     * List of {@link Item}s contained in this {@link Order}.
     * -- GETTER --
     * Return unmodifyable list of {@link Item}s contained in this {@link Order}.
     * @return unmodifyable list of {@link Item}s contained in this {@link Order}.
     */
    private final List<Item> items;

    /**
     * Create and add new {@link Item} to {@link Order}.
     * @param article {@link Article} ordered.
     * @param unitsOrdered units of the {@link Article} ordered.
     * @return chainable sef-reference.
     */
    public Order addItem(Article article, int unitsOrdered) {
        if(article != null && unitsOrdered > 0) {
            var item = new Item(article, unitsOrdered);
            items.add(item);
        }
        return this;
    }
}
