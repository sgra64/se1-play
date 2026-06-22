package components;

import java.util.Currency;

import datamodel.Article;
import datamodel.Customer;

/**
 * Public accessor interface for various formatters
 */
public interface Formatters {

    /**
     * Getter of the {@link Formatters.PriceFormatter} component reference.
     * @return {@link Formatters.PriceFormatter} component reference.
     */
    PriceFormatter priceFormatter();

    /**
     * Getter of the {@link Formatters.TableFormatterFactory} component reference.
     * @return {@link Formatters.TableFormatterFactory} component reference.
     */
    TableFormatterFactory tableFormatterFactory();


    /**
     * Interface of a component with methods that formats prices.
     */
    interface PriceFormatter {

        /**
         * Format long value to price according to a format (0 is default):
         * <pre>
         * Example: long value: 499
         * Style: 0: "4.99"
         *        1: "4.99 EUR"     3: "4.99 €"
         *        2: "4.99EUR"      4: "4.99€"
         * </pre>
         * @param price long value as price.
         * @param style price formatting style.
         * @return price formatted according to the selcted style.
         */
        String formatPrice(long price, int... style);

        /**
         * Format long value to a decimal String with specified digit formatting:
         * <pre>
         *      {      "%,d", 1L },     // no decimal digits:  16,000Y
         *      { "%,d.%01d", 10L },
         *      { "%,d.%02d", 100L },   // double-digit price: 169.99E
         *      { "%,d.%03d", 1000L },  // triple-digit unit:  16.999-
         * </pre>
         * @param value value to format to String in decimal format.
         * @param decDigits number of decimal digits.
         * @param unit optional added unit, e.g. a {@link Currency}.
         * @return decimal value formatted according to specified digit formatting.
         */
        String formatDecimal(long value, int decDigits, String... unit);
    }


    /**
     * Interface of a component that provides factory methods for various
     * table formatters. A {@link TableFormatter} object is used to format
     * objects of certain types, e.g. {@link Customer}, {@link Article} or
     * {@link datamodel.Order} as a table.
     */
    interface TableFormatterFactory {

        /**
         * Factory method of a {@link TableFormatter} to format a {@link Customer}
         * object into a table entry:
         * <pre>
         * Header:
         * +--------+---------------------+---------------------+-------------------------+
         * |     ID | NAME                | FIRSTNAME           | CONTACT                 |
         * +--------+---------------------+---------------------+-------------------------+
         * 
         * formatted Customer rows:
         * +--------+---------------------+---------------------+-------------------------+
         * | 892474 | Meyer, Eric         |                     | eric98@yahoo.com        |
         * | 825786 | Neumann, Lena       |                     | +49 173-4292109         |
         * | 193850 | Stefan Hoffmann     |                     | stefan.hoffmann@bht-berl|
         * +--------+---------------------+---------------------+-------------------------+
         * </pre>
         * @return {@link TableFormatter} object to format an {@link Customer} as a table entry.
         */
        TableFormatter createCustomerTableFormatter();

        /**
         * Factory method of a {@link TableFormatter} to format a {@link Article}
         * object into a table entry:
         * <pre>
         * Header:
         * +--------+-------------------------------------------+----------+--------------+
         * | ID     | DESCRIPTION                               | CATEGORY |        PRICE |
         * +--------+-------------------------------------------+----------+--------------+
         * 
         * formatted Article rows:
         * +--------+-------------------------------------------+----------+--------------+
         * | 673276 | Teller (Art Deco Collection)              |       12 |         6.49 |
         * | 523473 | Becher (Art Deco Collection)              |       12 |         1.49 |
         * | 354546 | Kanne (Art Deco Collection)               |       12 |        19.99 |
         * | 245262 | Buch "OOP" (Object-Oriented Programming)  |       53 |        79.95 |
         * | 733634 | Tasse (Art Deco Collection)               |       12 |         2.99 |
         * +--------+-------------------------------------------+----------+--------------+
         * </pre>
         * @return {@link TableFormatter} object to format an {@link Article} as a table entry.
         */
        TableFormatter createArticleTableFormatter();

        /**
         * Factory method of a {@link TableFormatter} to format a {@link datamodel.Category}
         * object into a table entry:
         * <pre>
         * Header:
         * +--------+------------------------------------------------------+--------------+
         * | ID     | DESCRIPTION                                          | VAT_CODE     |
         * +--------+------------------------------------------------------+--------------+
         * 
         * formatted Article rows:
         * +--------+------------------------------------------------------+--------------+
         * | 10     | Reinigung und Pflege                                 | A            |
         * | 12     | Küche und Kochen                                     | A            |
         * | 53     | Sachbuch                                             | C            |
         * +--------+------------------------------------------------------+--------------+
         * </pre>
         * @return {@link TableFormatter} object to format an {@link datamodel.Category}
         *      as a table entry.
         */
        TableFormatter createCategoryTableFormatter();

        /**
         * Factory method of a {@link TableFormatter} to format an {@link datamodel.Order}
         * object into a multi-row table entry:
         * <pre>
         * Header:
         * +----------------------------------------+--------+--------+---------+---------+
         * | ORDER                                  |   MwSt |  Preis |    MwSt |   Preis |
         * +----------------------------------------+--------+--------+---------+---------+
         * 
         * multi-row table format of an Order:
         * +----------------------------------------+--------+--------+---------+---------+
         * | 8592356245                             |        |        |         |         |
         * | Meyer, Eric,                           |        |        |         |         |
         * | - 4 Teller, 4x 6.49                    |   4.14 |  25.96 |         |         |
         * | - 8 Becher, 8x 1.49                    |   1.90 |  11.92 |         |         |
         * | - 1 Buch "OOP", 1x 79.95 (C)           |   5.23 |  79.95 |         |         |
         * | - 4 Tasse, 4x 2.99                     |   1.91 |  11.96 |   13.18 |  129.79 |
         * +----------------------------------------+--------+--------+---------+---------+
         * </pre>
         * @return {@link TableFormatter} object to format an {@link datamodel.Order}
         *      as multi-row table entry.
         */
        TableFormatter createOrderTableFormatter();
    }
}
