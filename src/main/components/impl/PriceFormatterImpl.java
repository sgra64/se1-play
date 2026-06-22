package components.impl;

import java.util.Currency;
import java.util.Locale;

import components.Formatters.PriceFormatter;


class PriceFormatterImpl implements PriceFormatter {

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
    @Override
    public String formatPrice(long price, int... style) {
        final int ft = style.length > 0? style[0] : 0;	// 0 is default format
        // var euroFormat = java.text.NumberFormat.getCurrencyInstance(Locale.GERMANY);
        // System.out.println(euroFormat.format(999L));
        Currency euro = Currency.getInstance("EUR");
        switch(ft) {
        case 0: return formatDecimal(price, 2);
        case 1: return String.format("%s EUR", formatDecimal(price, 2));
        case 2: return String.format( "%sEUR", formatDecimal(price, 2));
        case 3: return String.format(  "%s %s", formatDecimal(price, 2), euro.getSymbol(Locale.GERMANY));
        case 4: return String.format(   "%s%s", formatDecimal(price, 2), euro.getSymbol(Locale.GERMANY));
        default: return formatPrice(price, 0);
        }
    }

    /**
     * Format long value to a decimal String with specified digit formatting:
     * <pre>
     *      {      "%,d", 1L },     // no decimal digits:  16,000Y
     *      { "%,d.%01d", 10L },
     *      { "%,d.%02d", 100L },   // double-digit price: 169.99E
     *      { "%,d.%03d", 1000L },  // triple-digit unit:  16.999-
     * </pre>
     * @param value value to format to String in decimal format.
     * @param decimalDigits number of digits.
     * @return decimal value formatted according to specified digit formatting.
     */
    @Override
    public String formatDecimal(long value, int decimalDigits, String... unit) {
        final String unitStr = unit.length > 0? unit[0] : null;
        final Object[][] dec = {
            {      "%,d", 1L },     // no decimal digits:  16,000Y
            { "%,d.%01d", 10L },
            { "%,d.%02d", 100L },   // double-digit price: 169.99E
            { "%,d.%03d", 1000L },  // triple-digit unit:  16.999-
        };
        String result;
        String fmt = (String)dec[decimalDigits][0];
        if(unitStr != null && unitStr.length() > 0) {
            fmt += "%s";	// add "%s" to format for unit string
        }
        int decdigs = Math.max(0, Math.min(dec.length - 1, decimalDigits));
        //
        if(decdigs==0) {
            Object[] args = {value, unitStr};
            result = String.format(fmt, args);
        } else {
            long digs = (long)dec[decdigs][1];
            long frac = Math.abs( value % digs );
            Object[] args = {value/digs, frac, unitStr};
            result = String.format(fmt, args);
        }
        return result;
    }

    // 
    // private final java.text.DecimalFormat decimalFormatter = new java.text.DecimalFormat("#,##0");
    // 
    // /**
    //  * Format price from type {@code long} (in cents) to a printable String format,
    //  * e.g. {@code 1299} is formatted to {@code "12.99"}
    //  * @param price price to format.
    //  * @return formatted price.
    //  */
    // @Override
    // public String formatPrice(long price) {
    //     return String.format("%s.%02d",
    //         decimalFormatter.format(price / 100), price % 100);
    // }
}
