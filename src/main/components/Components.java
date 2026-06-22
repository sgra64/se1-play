package components;

import datamodel.Customer;
import datamodel.Order;
import datamodel.Article;
import datamodel.Category;

/**
 * Public interface of system singleton components.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Components {

    /**
     * Getter of singleton instance of {@link Components} implementation class.
     * @return singleton instance of {@link Components} implementation class
     */
    public static Components getInstance() {
        return components.impl.ComponentsImpl.getInstance();
    }

    /**
     * Getter of {@link DataFactory} instance.
     * @return {@link DataFactory} instance.
     */
    DataFactory dataFactory();

    /**
     * Getter of {@link Calculator} instance.
     * @return {@link Calculator} instance.
     */
    Calculator calculator();

    /**
     * Getter of {@link Formatters} instance.
     * @return
     */
    Formatters formatters();

    /**
     * Getter of {@link Repository} instance of {@link Customer} objects.
     * @return {@link Repository} instance of {@link Customer} objects.
     */
    Repository<Customer, Integer> customerRepository();

    /**
     * Getter of {@link Repository} instance of {@link Article} objects.
     * @return {@link Repository} instance of {@link Article} objects.
     */
    Repository<Article, Integer> articleRepository();

    /**
     * Getter of {@link Repository} instance of {@link Category} objects.
     * @return {@link Repository} instance of {@link Category} objects.
     */
    Repository<Category, Integer> categoryRepository();

    /**
     * Getter of {@link Repository} instance of {@link Order} objects.
     * @return {@link Repository} instance of {@link Order} objects.
     */
    Repository<Order, Long> orderRepository();
}
