package com.fluffylighty.productscroller.Utilities;

import android.graphics.Color;
import android.util.Log;

import java.text.NumberFormat;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class Utilities {

    private static final String LOG_TAG = Utilities.class.getSimpleName();

    public static String formatPrice(float price) {

        return NumberFormat.getInstance().format(price);
    }

    public static int parseColorString(String colorString) {

        if (!colorString.startsWith("#")) {
            colorString = "#" + colorString;
        }

        int color = 0;

        try {

            color = Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {

            Log.e(LOG_TAG, "Color string could not be parsed: ", e);
        }

        return color;
    }
}
