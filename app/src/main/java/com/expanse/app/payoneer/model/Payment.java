package com.expanse.app.payoneer.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
/**
 * This class is designed to hold payment information.
 */
@Getter
@Setter
public class Payment {
    /** mandatory */
    private String reference;
    /** mandatory */
    private BigDecimal amount;
    /** mandatory */
    private String currency;
}