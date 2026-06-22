package components.impl;

import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

import datamodel.Accessors;
import datamodel.Article;
import datamodel.Category;
import datamodel.Customer;
import datamodel.Order;


/**
 * Public part of the implementation class of the {@link DataFactory} interface
 * that provides creator functions of {@link datamodel} objects that are
 * exclusively exposed to the {@link DataFactory}.
 * <p>
 * Non-public {@link DataFactory} implementation class inherits from this class.
 */
public abstract class DataFactory {

    private static components.DataFactory dataFactory = null;

    protected Accessors.CustomerCreator customerCreator = null;
    protected Accessors.ArticleCreator articleCreator = null;
    protected Accessors.CategoryCreator categoryCreator = null;
    protected Accessors.OrderCreator orderCreator = null;
    protected Accessors.OrderItemCreator orderItemCreator = null;

    protected DataFactory() {
        Accessors.setFactoryCreators(this);
    }

    public static components.DataFactory getInstance(ObjectMapper jsonObjectMapper, Logger logger) {
        if(dataFactory==null) {
            dataFactory = new DataFactoryImpl(jsonObjectMapper, logger);
            logger.trace(String.format("%s DataFactory logger started.", "-".repeat(24)));
        }
        return dataFactory;
    }

    /**
     * Setter method exposed to {@link datamodel.Accessors} the
     * {@code setFactoryCreators(DataFactoryImpl factory)} method that passes
     * creator functions to {@link DataFactory}.
     * @param customerCreator creator function of {@link Customer} objects.
     * @param articleCreator creator function of {@link Article} objects.
     * @param categoryCreator creator function of {@link Category} objects.
     * @param orderCreator creator function of {@link Order} objects.
     * @param orderItemCreator creator function of {@link Order.Item} objects.
     */
    public void setCreators(
        Accessors.CustomerCreator customerCreator,
        Accessors.ArticleCreator articleCreator,
        Accessors.CategoryCreator categoryCreator,
        Accessors.OrderCreator orderCreator,
        Accessors.OrderItemCreator orderItemCreator
    ) {
        this.customerCreator = this.customerCreator==null? customerCreator : this.customerCreator;
        this.articleCreator = this.articleCreator==null? articleCreator : this.articleCreator;
        this.categoryCreator = this.categoryCreator==null? categoryCreator : this.categoryCreator;
        this.orderCreator = this.orderCreator==null? orderCreator : this.orderCreator;
        this.orderItemCreator = this.orderItemCreator==null? orderItemCreator : this.orderItemCreator;
    }
}
