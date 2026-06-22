package components;

import datamodel.Article;
import datamodel.Order;
import datamodel.VAT;

/**
 * {@link Calculator} provides methods to perform calculations on objects of type
 * {@link Order} and {@link Order.Item}.
 */
public interface Calculator {

    /**
     * Calculate the total {@link Order} value as sum of values of all
     * ordered {@link Order.Item}s.
     * @param order subject of calculation.
     * @return total {@link Order} value.
     */
    long calculateOrderValue(Order order);

    /**
     * Calculate the {@link Order.Item} value as product of {@link Article}
     * price and the number of units ordered.
     * @param item subject of calculation.
     * @return {@link Order.Item} value.
     */
    long calculateOrderItemValue(Order.Item item);

    /**
     * Calculate the total VAT included in {@link Order.Item}s of an
     * {@link Order}.
     * @param order subject of calculation.
     * @return total VAT included in an {@link Order}.
     */
    long calculateIncludedVAT(Order order);

    /**
     * Calculate the VAT included in an ordered {@link Order.Item}.
     * @param item subject of calculation.
     * @return VAT included in an {@link Order.Item}.
     */
    long calculateIncludedVAT(Order.Item item);

    /**
     * Calculate the VAT included in a gross price.
     * @param grossPrice subject of calculation.
     * @param vatRate tax rate to apply, e.g. a value of 0.19 for a 19% rate
     * @return VAT included in a gross price.
     */
    long calculateIncludedVAT(long grossPrice, double vatRate);

    /**
     * Return the {@link VAT} of an {@link Article}.
     * @param article article for which {@link VAT} is returned.
     * @return {@link VAT} of an {@link Article}.
     */
    VAT vat_Code(Article article);

    /**
     * Return the current tax rate that apply to a {@link VAT} as 1/100-th,
     * e.g. for a tax rate of 19%, value 0.19 is returned.
     * @param vatCode vatCode for which the current tax rate is returned.
     * @return current tax rate.
     */
    double taxRate(VAT vatCode);
}
