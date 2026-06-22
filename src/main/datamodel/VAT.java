package datamodel;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * VAT tax classification codes: {@code A}, {@code B} and {@code C}.
 */
@Getter
@Accessors(fluent=true)
public enum VAT {

    /** {@code A}: VAT code for the regular VAT tax rate (19%). */
    A(0.19),

    /** {@code B}: VAT code for food and grocery items with reduced rate (7%). */
    B(0.07),

    /** {@code C}: VAT code for print and media items with reduced rate (7%). */
    C(0.07);

    /**
     * VAT rate that applies to {@link VAT}.
     * -- GETTER --
     * Return VAT rate that applies to {@link VAT}.
     * @return VAT rate that applies to {@link VAT}.
     */
    double rate;

    /**
     * Constructor.
     * @param rate percent rate given as <i>0..1.0<i> value that applies to VAT code
     */
    VAT(double rate) { this.rate = rate; }
}
