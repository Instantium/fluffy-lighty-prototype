package com.fluffylighty.productscroller.Utilities;

import java.text.NumberFormat;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class Utilities {

    public static String formatPrice(float price) {

        return NumberFormat.getInstance().format(price);
    }
}
