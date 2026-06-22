package components.impl;

import components.Calculator;
import components.Formatters.PriceFormatter;
import components.Formatters.TableFormatterFactory;
import components.Repository;
import components.TableFormatter;
import datamodel.Article;
import datamodel.Category;
import datamodel.Customer;
import datamodel.Order;
import datamodel.VAT;


class TableFormatterFactoryImpl implements TableFormatterFactory {

    private final Calculator calculator;

    private final PriceFormatter priceFormatter;

    private final Repository<Category, Integer> categoryRepository;

    TableFormatterFactoryImpl(
        Calculator calculator,
        PriceFormatter PriceFormatter,
        Repository<Category, Integer> categoryRepository
    ) {
        this.calculator = calculator;
        this.priceFormatter = PriceFormatter;
        this.categoryRepository = categoryRepository;
    }


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
    @Override
    public TableFormatter createCustomerTableFormatter() {
        // 
        return TableFormatter.builder()
        .columns("| ID | NAME | FIRSTNAME | CONTACT |")
        .widths(8, 21, 21, 25)   // column widths
        .alignments("R")         // column alignments
        // 
        .rowMapper(Customer.class, c ->
            new String[] {Integer.toString(c.id()), c.name(), c.firstNames(), c.contact()})
        // 
        .build();
    }

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
    @Override
    public TableFormatter createArticleTableFormatter() {
        // 
        return TableFormatter.builder()
        .columns("| ID | DESCRIPTION | CATEGORY | PRICE |")
        .widths(8, 43, 10, 14)   // column widths
        .alignments("LLRR")      // column alignments
        // 
        .rowMapper(Article.class, a ->
            new String[] {Integer.toString(a.id()), a.description(), Integer.toString(a.category()), priceFormatter.formatPrice(a.price())})
        // 
        .build();
    }

    /**
     * Factory method of a {@link TableFormatter} to format a {@link Category}
     * object into a table entry:
     * <pre>
     * Header:
     * +--------+------------------------------------------------------+--------------+
     * | ID     | DESCRIPTION                                          | VAT_CODE     |
     * +--------+------------------------------------------------------+--------------+
     * 
     * formatted Article rows:
     * +--------+------------------------------------------------------+--------------+
     * | 10     | Reinigung & Pflege                                   | A            |
     * | 12     | Küche & Kochen                                       | A            |
     * | 53     | Sachbuch                                             | C            |
     * +--------+------------------------------------------------------+--------------+
     * </pre>
     * @return {@link TableFormatter} object to format an {@link Category} as a table entry.
     */
    @Override
    public TableFormatter createCategoryTableFormatter() {
        // 
        return TableFormatter.builder()
                .columns("| ID | DESCRIPTION | VAT_CODE |")
        .widths(8, 54, 14)       // column widths
        .alignments("LLL")       // column alignments
        // 
        .rowMapper(Category.class, c ->
            new String[] {Integer.toString(c.id()), c.description(), c.vatCode().toString()})
        // 
        .build();
    }

    /**
     * Factory method of a {@link TableFormatter} to format an {@link Order}
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
     * @return {@link TableFormatter} object to format an {@link Order} as multi-row table entry.
     */
    @Override
    public TableFormatter createOrderTableFormatter() {
        // 
        return TableFormatter.builder()
            .columns("| ORDER |T| MwSt | Preis | MwSt | Preis |")
            .widths(38, 1, 8, 8, 9, 9)  // column widths
            .alignments("LLRRRR")       // column alignments
            // 
            // register multi-row - mapper for {@link Order} objects
            .multiRowMapper(Order.class, order -> {
                int lines = order.items().size() + 2;
                String[][] rows = new String[lines][6];
                rows[0][0] = String.format("%d", order.id());
                rows[0][1] = "{ }";
                rows[0][2] = rows[0][3] = rows[0][4] = rows[0][5] = " ";
                // 
                rows[1][0] = String.format("%s, %s", order.customer().name(), order.customer().firstNames());
                rows[1][1] = "{ }";
                rows[1][2] = rows[1][3] = rows[1][4] = rows[1][5] = " ";
                // 
                boolean last = false;
                for(int i=0; ! last; i++) {
                    last = i >= order.items().size() - 1;
                    var item = order.items().get(i);
                    int cat = item.article().category();
                    var vatCode = categoryRepository.findById(cat).get().vatCode();
                    // 
                    rows[i+2][0] = String.format("- %d %s, %dx %s",
                        item.unitsOrdered(),
                        item.article().description().replaceAll("\\([^)]*\\)", "").trim(),
                        item.unitsOrdered(),
                        priceFormatter.formatPrice(item.article().price())
                    );
                    rows[i+2][1] = vatCode != VAT.A? String.format("{ }%s", vatCode.toString()) : "{ }";
                    rows[i+2][2] = priceFormatter.formatPrice(calculator.calculateIncludedVAT(item));
                    rows[i+2][3] = priceFormatter.formatPrice(calculator.calculateOrderItemValue(item));
                    rows[i+2][4] = last? priceFormatter.formatPrice(calculator.calculateIncludedVAT(order)) : " ";
                    rows[i+2][5] = last? priceFormatter.formatPrice(calculator.calculateOrderValue(order)) : " ";
                }
                return rows;
            })
        .build();
    }
}
