package components;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import datamodel.Article;
import datamodel.Category;
import datamodel.Customer;
import datamodel.Order;

/**
 * Setup for {@link Calculator} unit tests.
 * 
 * Tested methods:
 * - 010: test Repository setup (data loaded from .json files)
 * - 100: double taxRate(VAT vatCode);
 * - 200: VAT vat_Code(Article article);
 * - 300: long calculateIncludedVAT(long grossPrice, double vatRate);
 * - 400: long calculateIncludedVAT(Order.Item item);
 * - 500: long calculateIncludedVAT(Order order);
 * - 600: long calculateOrderItemValue(Order.Item item);
 * - 700: long calculateOrderValue(Order order);
 * 
 * Tests reflect results from the 'Order' table:
 * <pre>
 * +--------------------------------------+-+--------+--------+---------+---------+
 * | ORDER                                |T|   MwSt |  Preis |    MwSt |   Preis |
 * +--------------------------------------+-+--------+--------+---------+---------+
 * | 5234968294                             |        |        |         |         |
 * | Meyer, Eric                            |        |        |         |         |
 * | - 1 Kanne, 1x 19.99                    |   3.19 |  19.99 |    3.19 |   19.99 |
 * +--------------------------------------+-+--------+--------+---------+---------+
 * | 6173043537                             |        |        |         |         |
 * | Neumann, Lena                          |        |        |         |         |
 * | - 1 Buch "Java", 1x 49.90             C|   3.26 |  49.90 |         |         |
 * | - 1 Fahrradkarte Berlin, 1x 6.95      C|   0.45 |   6.95 |    3.71 |   56.85 |
 * +--------------------------------------+-+--------+--------+---------+---------+
 * | 8592356245                             |        |        |         |         |
 * | Meyer, Eric                            |        |        |         |         |
 * | - 4 Teller, 4x 6.49                    |   4.14 |  25.96 |         |         |
 * | - 8 Becher, 8x 1.49                    |   1.90 |  11.92 |         |         |
 * | - 1 Buch "OOP", 1x 79.95              C|   5.23 |  79.95 |         |         |
 * | - 4 Tasse, 4x 2.99                     |   1.91 |  11.96 |   13.18 |  129.79 |
 * +--------------------------------------+-+--------+--------+---------+---------+
 * | 6135735635                             |        |        |         |         |
 * | Blumenfeld, Nadine-Ulla                |        |        |         |         |
 * | - 12 Teller, 12x 6.49                  |  12.43 |  77.88 |         |         |
 * | - 1 Buch "Java", 1x 49.90             C|   3.26 |  49.90 |         |         |
 * | - 1 Buch "OOP", 1x 79.95              C|   5.23 |  79.95 |   20.92 |  207.73 |
 * +--------------------------------------+-+--------+--------+---------+---------+
 * | 3563561357                             |        |        |         |         |
 * | Bayer, Anne                            |        |        |         |         |
 * | - 2 Teller, 2x 6.49                    |   2.07 |  12.98 |         |         |
 * | - 2 Tasse, 2x 2.99                     |   0.95 |   5.98 |    3.02 |   18.96 |
 * +--------------------------------------+-+--------+--------+---------+---------+
 * | 7372561535                             |        |        |         |         |
 * | Meyer, Eric                            |        |        |         |         |
 * | - 1 Fahrradhelm, 1x 169.00             |  26.98 | 169.00 |         |         |
 * | - 1 Fahrradkarte Berlin, 1x 6.95      C|   0.45 |   6.95 |   27.43 |  175.95 |
 * +--------------------------------------+-+--------+--------+---------+---------+
 * | 4450305661                             |        |        |         |         |
 * | Meyer, Eric                            |        |        |         |         |
 * | - 3 Tasse, 3x 2.99                     |   1.43 |   8.97 |         |         |
 * | - 3 Becher, 3x 1.49                    |   0.71 |   4.47 |         |         |
 * | - 1 Kanne, 1x 19.99                    |   3.19 |  19.99 |    5.33 |   33.43 |
 * +--------------------------------------+-+--------+--------+---------+---------+
 *                                                            |   76.78 |  642.70 |
 *                                                            +=========+=========+
 * </pre>
 */
public class Calculator_010_setup_Tests {

    /**
     * Inner class with shared test data.
     */
    static class TestData {

        /***
         * Unit under test.
         */
        final Calculator calculator;

        /**
         * Test data shared between tests.
         */
        final Repository<Customer, Integer> customerRepository;
        final Repository<Category, Integer> categoryRepository;
        final Repository<Article, Integer> articleRepository;
        final Repository<Order, Long> orderRepository;

        final Article teller;
        final Article becher;
        final Article kanne;
        final Article tasse;
        final Article buch_oop;
        final Article waescheleine;
        final Article roggenbrot;
        final Article book;
        final Category cat_12;
        final Category cat_13;
        final Category cat_30;
        final Category cat_53;
        // 
        final Order order_8592;
        final Order.Item item_8592_1;
        final Order.Item item_8592_2;
        final Order.Item item_8592_3;
        final Order.Item item_8592_4;
        // 
        final Order order_6135;
        final Order.Item item_6135_1;
        final Order.Item item_6135_2;
        final Order.Item item_6135_3;
        //
        final Order order_5234;
        final Order order_6173;
        final Order order_3563;
        final Order order_7372;
        final Order order_4450;

        final int max_int = 2147483647;

        private TestData() {
            var components = Components.getInstance();
            calculator = components.calculator();
            // 
            customerRepository = components.customerRepository();
            categoryRepository = components.categoryRepository();
            articleRepository = components.articleRepository();
            orderRepository = components.orderRepository();
            // 
            teller =   articleRepository.findById(673276).get();
            becher =   articleRepository.findById(523473).get();
            kanne =    articleRepository.findById(354546).get();
            tasse =    articleRepository.findById(733634).get();
            buch_oop = articleRepository.findById(245262).get();
            waescheleine = articleRepository.findById(839142).get();
            roggenbrot = articleRepository.findById(703146).get();
            book = articleRepository.findById(135948).get();
            // 
            cat_12 = categoryRepository.findById(12).get();
            cat_13 = categoryRepository.findById(13).get();
            cat_30 = categoryRepository.findById(30).get();
            cat_53 = categoryRepository.findById(53).get();
            // 
            order_8592 = orderRepository.findById(8592356245L).get();
            var items = order_8592.items();
            item_8592_1 = items.get(0);
            item_8592_2 = items.get(1);
            item_8592_3 = items.get(2);
            item_8592_4 = items.get(3);
            // 
            order_6135 = orderRepository.findById(6135735635L).get();
            items = order_6135.items();
            item_6135_1 = items.get(0);
            item_6135_2 = items.get(1);
            item_6135_3 = items.get(2);
            // 
            order_5234 = orderRepository.findById(5234968294L).get();
            order_6173 = orderRepository.findById(6173043537L).get();
            order_3563 = orderRepository.findById(3563561357L).get();
            order_7372 = orderRepository.findById(7372561535L).get();
            order_4450 = orderRepository.findById(4450305661L).get();
        }
    }

    /**
     * Static test data shared between {@link Calculator} test classes.
     */
    static final TestData testData = new TestData();


    /**
     * Verify repositories have loaded data from 'data/*.json' files.
     */
    @Test
    void test_010_RepositorySetup() {
        assertTrue(testData.customerRepository.count() > 0L);
        assertTrue(testData.categoryRepository.count() > 0L);
        assertTrue(testData.articleRepository.count() > 0L);
        assertTrue(testData.orderRepository.count() > 0L);
        // 
        // assertEquals(testData.customerRepository.count(), 26L);
        // assertEquals(testData.categoryRepository.count(), 19L);
        // assertEquals(testData.articleRepository.count(), 108L);
        // assertEquals(testData.orderRepository.count(), 7L);
    }
}
