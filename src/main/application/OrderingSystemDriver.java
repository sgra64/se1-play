package application;

import components.Calculator;
import components.Components;
import components.Formatters.PriceFormatter;
import runtimeSE.Runner;
import runtimeSE.RuntimeSE;
import runtimeSE.Runner.Accessors;

/**
 * Class with {@code main()} method as entry point for the Java VM.
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
@Accessors(priority=10)
public class OrderingSystemDriver implements Runner {

    /**
     * Method invoked by the runtime. Application code starts here.
     * @param runtime reference to the runtime singleton instance ({@link RuntimeSE}).
     * @param args arguments passed from the command line.
     */
    @Override
    public void run(RuntimeSE runtime, String[] args) {
        System.out.println("Hello, Ordering System");
        run(runtime, Components.getInstance(), args);
    }

    void run(RuntimeSE runtime, Components components, String[] args) {
        // 
        components.formatters().tableFormatterFactory().createCustomerTableFormatter()
           .header()
           .row(components.customerRepository().stream().toList())
           .footer()
           .print(System.out);

        // print 'Article' table
        components.formatters().tableFormatterFactory().createArticleTableFormatter()
           .header()
           .row(components.articleRepository().stream().toList())
           .footer()
           .print(System.out);

        // print 'Category' table
        components.formatters().tableFormatterFactory().createCategoryTableFormatter()
            .header()
            .row(components.categoryRepository().stream()
                .sorted(java.util.Comparator.comparing(datamodel.Category::id)).toList())
            .footer()
            .print(System.out);

        Calculator calculator = components.calculator();
        PriceFormatter priceFormatter = components.formatters().priceFormatter();
        // 
        // print 'Order' table
        var orderTableFormatter = components.formatters().tableFormatterFactory().createOrderTableFormatter()
           .header();
        // 
        // fill-in orders
        components.orderRepository().stream()
            .forEach(order -> orderTableFormatter.row(order).line());
        // 
        // append VAT and price totals at the end
        orderTableFormatter
           .row("", "", "", "",
                priceFormatter.formatPrice(
                    components.orderRepository().stream()
                        .mapToLong(order -> calculator.calculateIncludedVAT(order)).sum()),
                // 
                priceFormatter.formatPrice(
                    components.orderRepository().stream()
                        .mapToLong(order -> calculator.calculateOrderValue(order)).sum())
            )
           .line("", "", "", "", "{===}", "{===}")
           .print(System.out);
    }
}
