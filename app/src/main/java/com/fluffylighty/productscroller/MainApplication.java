package com.fluffylighty.productscroller;

import android.app.Application;

import com.fluffylighty.productscroller.controller.MainController;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Nico Adler on 28.01.17.
 */

public class MainApplication extends Application {

    private MainController mainController;

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
        ImageLoader.getInstance().init(config);

        mainController = new MainController();
    }
}
