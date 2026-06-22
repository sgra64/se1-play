package datamodel;

import java.util.List;

import components.DataFactory;
import datamodel.Order.Item;

/**
 * Non-instantiable class that supplies creator functions of {@link datamodel}
 * classes to the {@link DataFactory}. Constructors of {@link datamodel} classes
 * are protected (only visible in this package).
 * <p>
 * Since {@link DataFactory} is outside the {@link datamodel} package, it must
 * be provided with creator functions that construct {@link datamodel} objects.
 * 
 * Class implements a variation of the <i>"Friend Accessor Pattern"</i>,
 * @see <a href="https://stackoverflow.com/questions/182278/is-there-a-way-to-simulate-the-c-friend-concept-in-java">article (stackoverflow)</a>.
 */
public abstract class Accessors {

    /**
     * Private constructor disables object creation of this class.
     */
    private Accessors() { }


    /**
     * {@link FunctionalInterface} of the {@link Customer} creator.
     */
    @FunctionalInterface
    public interface CustomerCreator{
        Customer create(int id, String name, String firstNames, String address, String contact);
    }

    /**
     * {@link FunctionalInterface} of the {@link Article} creator.
     */
    @FunctionalInterface
    public interface ArticleCreator{
         Article create(int id, int category, String description, int price);
    }

    /**
     * {@link FunctionalInterface} of the {@link Category} creator.
     */
    @FunctionalInterface
    public interface CategoryCreator{
         Category create(int id, String description, VAT vatCode);
    }

    /**
     * {@link FunctionalInterface} of the {@link Order} creator.
     */
    @FunctionalInterface
    public interface OrderCreator{
         Order create(long id, Customer customer, List<Item> items);
    }

    /**
     * {@link FunctionalInterface} of the {@link Order.Item} creator.
     */
    @FunctionalInterface
    public interface OrderItemCreator{
         Order.Item create(Article article, int unitsOrdered);
    }


    /**
     * Public method to set creator functions of {@link datamodel} classes
     * to the {@link DataFactory}. Method is idempotent.
     * @param factory reference to singleton instance {@link DataFactory} class.
     */
    public static void setFactoryCreators(components.impl.DataFactory factory) {
        factory.setCreators(
            (id, name, firstNames, address, contact) -> new Customer(id, name, firstNames, address, contact),
            (int id, int category, String description, int price) -> new Article(id, category, description, price),
            (int id, String description, VAT vatCode) -> new Category(id, description, vatCode),
            (long id, Customer customer, List<Item> items) -> new Order(id, customer, items),
            (Article article, int unitsOrdered) -> new Order.Item(article, unitsOrdered)
        );
    }
}
