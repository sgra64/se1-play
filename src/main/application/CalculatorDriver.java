package application;

import components.Calculator;
import components.Components;
import components.Formatters;
import components.Repository;
import datamodel.Article;
import datamodel.Category;
import datamodel.Customer;
import datamodel.Order;
import datamodel.VAT;
import runtimeSE.Runner;
import runtimeSE.RuntimeSE;
import runtimeSE.Runner.Accessors;

/**
 * Driver-class to develop {@link Calculator} methods:
 * <pre>
 *  1.) double taxRate(VAT vatCode);
 *  2.) VAT vat_Code(Article article);
 *  3.) long calculateIncludedVAT(long grossPrice, double vatRate);
 *  4.) long calculateIncludedVAT(Order.Item item);
 *  5.) long calculateIncludedVAT(Order order);
 *  6.) long calculateOrderItemValue(Order.Item item);
 *  7.) long calculateOrderValue(Order order);
 * </pre>
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
@Accessors(priority=12)
public class CalculatorDriver implements Runner {
    // 
    final Components components = Components.getInstance();
    final Calculator calculator = components.calculator();
    final Formatters.PriceFormatter pfmt = components.formatters().priceFormatter();
    final Repository<Customer, Integer> customerRepository = components.customerRepository();
    final Repository<Category, Integer> categoryRepository = components.categoryRepository();
    final Repository<Article, Integer> articleRepository = components.articleRepository();
    final Repository<Order, Long> orderRepository = components.orderRepository();
    // 
    // Articles from the 'articleRepository':
    final Article teller, becher, kanne, tasse, buch_oop, waescheleine, roggenbrot, book;
    final Category cat_12, cat_13, cat_30, cat_53;
    // 
    // Order '8592356245' with 4 items:
    // +--------------------------------------+-+--------+--------+---------+---------+
    // | 8592356245                             |        |        |         |         |
    // | Meyer, Eric                            |        |        |         |         |
    // | - 4 Teller, 4x 6.49                    |   4.14 |  25.96 |         |         |
    // | - 8 Becher, 8x 1.49                    |   1.90 |  11.92 |         |         |
    // | - 1 Buch "OOP", 1x 79.95              C|   5.23 |  79.95 |         |         |
    // | - 4 Tasse, 4x 2.99                     |   1.91 |  11.96 |   13.18 |  129.79 |
    // +--------------------------------------+-+--------+--------+---------+---------+
    final Order order_8592;
    final Order.Item item_8592_1, item_8592_2, item_8592_3, item_8592_4;
    // 
    // Order '6135735635' with 3 items:
    // +--------------------------------------+-+--------+--------+---------+---------+
    // | 6135735635                             |        |        |         |         |
    // | Blumenfeld, Nadine-Ulla                |        |        |         |         |
    // | - 12 Teller, 12x 6.49                  |  12.43 |  77.88 |         |         |
    // | - 1 Buch "Java", 1x 49.90             C|   3.26 |  49.90 |         |         |
    // | - 1 Buch "OOP", 1x 79.95              C|   5.23 |  79.95 |   20.92 |  207.73 |
    // +--------------------------------------+-+--------+--------+---------+---------+
    final Order order_6135;
    final Order.Item item_6135_1, item_6135_2, item_6135_3;
    //
    // other orders
    final Order order_5234, order_6173, order_3563, order_7372, order_4450;

    /**
     * Constructor initializes member attributes.
     */
    CalculatorDriver() {
        this.teller     = articleRepository.findById(673276).get();
        this.becher     = articleRepository.findById(523473).get();
        this.kanne      = articleRepository.findById(354546).get();
        this.tasse      = articleRepository.findById(733634).get();
        this.buch_oop   = articleRepository.findById(245262).get();
        this.waescheleine = articleRepository.findById(839142).get();
        this.roggenbrot = articleRepository.findById(703146).get();
        this.book       = articleRepository.findById(135948).get();
        // 
        this.cat_12 = categoryRepository.findById(12).get();
        this.cat_13 = categoryRepository.findById(13).get();
        this.cat_30 = categoryRepository.findById(30).get();
        this.cat_53 = categoryRepository.findById(53).get();
        // 
        this.order_8592 = orderRepository.findById(8592356245L).get();
        var items = order_8592.items();
        this.item_8592_1 = items.get(0);
        this.item_8592_2 = items.get(1);
        this.item_8592_3 = items.get(2);
        this.item_8592_4 = items.get(3);
        // 
        this.order_6135 = orderRepository.findById(6135735635L).get();
        items = order_6135.items();
        this.item_6135_1 = items.get(0);
        this.item_6135_2 = items.get(1);
        this.item_6135_3 = items.get(2);
        // 
        this.order_5234 = orderRepository.findById(5234968294L).get();
        this.order_6173 = orderRepository.findById(6173043537L).get();
        this.order_3563 = orderRepository.findById(3563561357L).get();
        this.order_7372 = orderRepository.findById(7372561535L).get();
        this.order_4450 = orderRepository.findById(4450305661L).get();
    }

    /**
     * Method invoked by the runtime. Application code starts here.
     * @param runtime reference to the runtime singleton instance ({@link RuntimeSE}).
     * @param args arguments passed from the command line.
     */
    @Override
    public void run(RuntimeSE runtime, String[] args) {
        System.out.println(String.format("Hello, '%s'", Calculator.class.getSimpleName()));
        run(runtime, Components.getInstance(), args);
    }

    /**
     * Create sample {@link Order} to verify {@link Calculator} methods.
     * <pre>
     * +------------------------------------+--------+--------+---------+---------+
     * | ORDER                              |   MwSt |  Preis |    MwSt |   Preis |
     * +------------------------------------+--------+--------+---------+---------+
     * | 8592356245                         |        |        |         |         |
     * | Meyer, Eric,                       |        |        |         |         |
     * | - 4 Teller, 4x 6.49                |   4.14 |  25.96 |         |         |
     * | - 8 Becher, 8x 1.49                |   1.90 |  11.92 |         |         |
     * | - 1 Buch "OOP", 1x 79.95 (C)       |   5.23 |  79.95 |         |         |
     * | - 4 Tasse, 4x 2.99                 |   1.91 |  11.96 |   13.18 |  129.79 |
     * +------------------------------------+--------+--------+---------+---------+
     * </pre>
     * @param runtime
     * @param components
     * @param args
     */
    void run(RuntimeSE runtime, Components components, String[] args) {

        // 1.) develop method: double taxRate(VAT vatCode);
        double rateA = calculator.taxRate(VAT.A);
        double rateB = calculator.taxRate(VAT.B);
        double rateC = calculator.taxRate(VAT.C);
        // 
        System.out.println(String.format("- taxRate(): rate A: %.2f, rate B: %.2f, rate C: %.2f", rateA, rateB, rateC));

        // // 2.) develop method: VAT vat_Code(Article article);
        // VAT vat1 = calculator.vat_Code(teller);
        // VAT vat2 = calculator.vat_Code(becher);
        // VAT vat3 = calculator.vat_Code(buch_oop);
        // VAT vat4 = calculator.vat_Code(tasse);
        // VAT vat5 = calculator.vat_Code(waescheleine);
        // VAT vat6 = calculator.vat_Code(roggenbrot);
        // VAT vat7 = calculator.vat_Code(book);
        // // 
        // System.out.println(String.format(
        //     "- vat_Code(): teller: %s, becher: %s, buch_oop: %s, tasse: %s, " +
        //     "waescheleine: %s, roggenbrot: %s, book: %s",
        //     vat1, vat2, vat3, vat4, vat5, vat6, vat7));

        // // 3.) develop method: calculateIncludedVAT(long grossPrice, double vatRate);
        // long incl1 = calculator.calculateIncludedVAT( 2596L, 0.19);
        // long incl2 = calculator.calculateIncludedVAT( 1192L, 0.19);
        // long incl3 = calculator.calculateIncludedVAT( 7995L, 0.07);
        // long incl4 = calculator.calculateIncludedVAT( 1196L, 0.19);
        // System.out.println(String.format(
        //     "- calculateIncludedVAT(): 4x Teller: %s, 8x Becher: %s, 1 Buch \"OOP\": %s, 4x Tasse: %s",
        //     pfmt.formatPrice(incl1, 4), pfmt.formatPrice(incl2, 4), pfmt.formatPrice(incl3, 4), pfmt.formatPrice(incl4, 4)));
        // // 
        // long incl5 = calculator.calculateIncludedVAT(10000L, 0.19);
        // long incl6 = calculator.calculateIncludedVAT(11900L, 0.19);
        // System.out.println(String.format(
        //     "- calculateIncludedVAT(): 19%% included VAT in 100€: %s, 19%% included VAT in 119€: %s",
        //     pfmt.formatPrice(incl5, 4), pfmt.formatPrice(incl6, 4)));

        // // 4.) long calculateIncludedVAT(Order.Item item);
        // incl1 = calculator.calculateIncludedVAT(item_8592_1);
        // incl2 = calculator.calculateIncludedVAT(item_8592_2);
        // incl3 = calculator.calculateIncludedVAT(item_8592_3);
        // incl4 = calculator.calculateIncludedVAT(item_8592_4);
        // System.out.println(String.format(
        //     "- calculateIncludedVAT(): in order '8592356245' item-1: %6s, item-2: %s, item-3: %s, item-4: %s",
        //     pfmt.formatPrice(incl1, 4), pfmt.formatPrice(incl2, 4), pfmt.formatPrice(incl3, 4), pfmt.formatPrice(incl4, 4)));
        // // 
        // incl1 = calculator.calculateIncludedVAT(item_6135_1);
        // incl2 = calculator.calculateIncludedVAT(item_6135_2);
        // incl3 = calculator.calculateIncludedVAT(item_6135_3);
        // System.out.println(String.format(
        //     "- calculateIncludedVAT(): in order '6135735635' item-1: %6s, item-2: %s, item-3: %s",
        //     pfmt.formatPrice(incl1, 4), pfmt.formatPrice(incl2, 4), pfmt.formatPrice(incl3, 4)));

        // // 5.) long calculateIncludedVAT(Order order);
        // incl1 = calculator.calculateIncludedVAT(order_5234);
        // incl2 = calculator.calculateIncludedVAT(order_6173);
        // incl3 = calculator.calculateIncludedVAT(order_8592);
        // incl4 = calculator.calculateIncludedVAT(order_6135);
        // System.out.println(String.format(
        //     "- calculateIncludedVAT(): order '5234': %s, order '6173': %6s, order '8592': %6s, order '6135': %s",
        //     pfmt.formatPrice(incl1, 4), pfmt.formatPrice(incl2, 4), pfmt.formatPrice(incl3, 4), pfmt.formatPrice(incl4, 4)));
        // // 
        // incl1 = calculator.calculateIncludedVAT(order_3563);
        // incl2 = calculator.calculateIncludedVAT(order_7372);
        // incl3 = calculator.calculateIncludedVAT(order_4450);
        // System.out.println(String.format(
        //     "- calculateIncludedVAT(): order '3563': %s, order '7372': %6s, order '4450': %6s",
        //     pfmt.formatPrice(incl1, 4), pfmt.formatPrice(incl2, 4), pfmt.formatPrice(incl3, 4)));

        // // 6.) long calculateOrderItemValue(Order.Item item);
        // long val1 = calculator.calculateOrderItemValue(item_8592_1);
        // long val2 = calculator.calculateOrderItemValue(item_8592_2);
        // long val3 = calculator.calculateOrderItemValue(item_8592_3);
        // long val4 = calculator.calculateOrderItemValue(item_8592_4);
        // System.out.println(String.format(
        //     "- calculateOrderItemValue(): in order '6135735635' item-1: %6s, item-2: %s, item-3: %s",
        //     pfmt.formatPrice(val1, 4), pfmt.formatPrice(val2, 4), pfmt.formatPrice(val3, 4)));
        // // 
        // val1 = calculator.calculateOrderItemValue(item_6135_1);
        // val2 = calculator.calculateOrderItemValue(item_6135_2);
        // val3 = calculator.calculateOrderItemValue(item_6135_3);
        // System.out.println(String.format(
        //     "- calculateOrderItemValue(): in order '6135735635' item-1: %6s, item-2: %s, item-3: %s",
        //     pfmt.formatPrice(val1, 4), pfmt.formatPrice(val2, 4), pfmt.formatPrice(val3, 4)));

        // // 7.) long calculateOrderValue(Order order);
        // val1 = calculator.calculateOrderValue(order_5234);
        // val2 = calculator.calculateOrderValue(order_6173);
        // val3 = calculator.calculateOrderValue(order_8592);
        // val4 = calculator.calculateOrderValue(order_6135);
        // System.out.println(String.format(
        //     "- calculateOrderValue(): order '5234': %s, order '6173': %8s, order '8592': %8s, order '6135': %s",
        //     pfmt.formatPrice(val1, 4), pfmt.formatPrice(val2, 4), pfmt.formatPrice(val3, 4), pfmt.formatPrice(val4, 4)));
        // // 
        // val1 = calculator.calculateOrderValue(order_3563);
        // val2 = calculator.calculateOrderValue(order_7372);
        // val3 = calculator.calculateOrderValue(order_4450);
        // System.out.println(String.format(
        //     "- calculateOrderValue(): order '3563': %s, order '7372': %8s, order '4450': %8s",
        //     pfmt.formatPrice(val1, 4), pfmt.formatPrice(val2, 4), pfmt.formatPrice(val3, 4)));

        // /**
        //  * When done, print the whole 'Order'-table:
        //  */
        // System.out.println();
        // printOrderTable();
    }

    void printOrderTable() {
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
                pfmt.formatPrice(
                    components.orderRepository().stream()
                        .mapToLong(order -> calculator.calculateIncludedVAT(order)).sum()),
                // 
                pfmt.formatPrice(
                    components.orderRepository().stream()
                        .mapToLong(order -> calculator.calculateOrderValue(order)).sum())
            )
           .line("", "", "", "", "{===}", "{===}")
           .print(System.out);
    }
}
