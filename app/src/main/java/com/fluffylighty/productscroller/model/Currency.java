package com.fluffylighty.productscroller.model;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class Currency {

    private final String symbol;

    /**
     * Dummy constructor as this Object will only be instantiated via gson
     */
    public Currency() {

        symbol = "";
    }

    public String getSymbol() {
        return symbol;
    }
}
