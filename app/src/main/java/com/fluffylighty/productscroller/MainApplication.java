package com.fluffylighty.productscroller;

import android.app.Application;

import com.fluffylighty.productscroller.controller.MainController;

/**
 * Created by Nico Adler on 28.01.17.
 */

public class MainApplication extends Application {

    private MainController mainController;

    @Override
    public void onCreate() {
        super.onCreate();

        mainController = new MainController();
    }
}
