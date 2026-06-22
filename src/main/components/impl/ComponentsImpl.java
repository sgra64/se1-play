package components.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.experimental.Accessors;

import components.Calculator;
import components.Components;
import components.DataFactory;
import components.Formatters;
import components.Formatters.PriceFormatter;
import components.Formatters.TableFormatterFactory;
import components.Repository;
import datamodel.Article;
import datamodel.Category;
import datamodel.Customer;
import datamodel.Order;
import datamodel.Order.Item;
import datamodel.VAT;

/**
 * Public singleton implementation class of interface {@link Components}
 * that holds other singleton component objects as singleton instances.
 * Class provides public getter methods to component instances.
 */
@Accessors(fluent=true)
public class ComponentsImpl implements Components {

    private static Components instance = null;

    private final ObjectMapper jsonObjectMapper = new ObjectMapper();

    private static final Logger dataFactoryLogger = LogManager.getLogger("datafactory-logger");


    @Getter
    private final DataFactory dataFactory;

    @Getter
    private final Calculator calculator;

    @Getter
    private final Formatters formatters;

    private final PriceFormatter priceFormatter;

    private final TableFormatterFactory tableFormatterFactory;

    private final RepositoryFactory repositoryFactory = new RepositoryFactory(jsonObjectMapper);

    @Getter
    private final Repository<Customer, Integer> customerRepository;

    @Getter
    private final Repository<Category, Integer> categoryRepository;

    @Getter
    private final Repository<Article, Integer> articleRepository;

    @Getter
    private final Repository<Order, Long> orderRepository;

    /**
     * Private constructor.
     */
    private ComponentsImpl() {
        // 
        this.dataFactory = DataFactory.getInstance(jsonObjectMapper, dataFactoryLogger);
        // 
        this.customerRepository = repositoryFactory.createFromJsonFile(
            "data/customers.json", Customer::id, fieldsmap -> dataFactory.createCustomer(fieldsmap)
        );
        // 
        this.categoryRepository = repositoryFactory.createFromJsonFile(
            "data/categories.json", Category::id, fieldsmap -> dataFactory.createCategory(fieldsmap)
        );
        // 
        this.articleRepository = repositoryFactory.createFromJsonFile(
            "data/articles.json", Article::id, fieldsmap -> dataFactory.createArticle(fieldsmap)
        );
        // 
        this.orderRepository = repositoryFactory.createFromJsonFile(
            "data/orders.json", Order::id, fieldsmap -> dataFactory.createOrder(fieldsmap, customerRepository, articleRepository)
        );
        // 
        // create proxy Calculator instance, replace by working implementation
        this.calculator = new Calculator() {

            @Override
            public long calculateOrderValue(Order order) { return 0L; }

            @Override
            public long calculateOrderItemValue(Item item) { return 0L; }

            @Override
            public long calculateIncludedVAT(Order order) { return 0L; }

            @Override
            public long calculateIncludedVAT(Item item) { return 0L; }

            @Override
            public long calculateIncludedVAT(long grossPrice, double vatRate) { return 0L; }

            @Override
            public VAT vat_Code(Article article) { return VAT.A; }

            @Override
            public double taxRate(VAT vatCode) { return 0L; }
        };
        // this.calculator = new CalculatorImpl(categoryRepository);
        // 
        this.priceFormatter = new PriceFormatterImpl();
        this.tableFormatterFactory = new TableFormatterFactoryImpl(calculator, priceFormatter, categoryRepository);
        this.formatters = new Formatters() {

            @Override
            public PriceFormatter priceFormatter() { return priceFormatter; }

            @Override
            public TableFormatterFactory tableFormatterFactory() { return tableFormatterFactory; }
        };
    }

    /**
     * Public static getter of {@link Components} implementation singleton instance.
     * @return {@link Components} implementation singleton instance.
     */
    public static Components getInstance() {
        if(instance==null) {
            instance = new ComponentsImpl();
        }
        return instance;
    }
}
