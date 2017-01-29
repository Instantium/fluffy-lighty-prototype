package com.fluffylighty.productscroller;

import android.app.Application;

import com.fluffylighty.productscroller.controller.MainController;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
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

        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .showImageOnFail(R.drawable.placeholder_drawable)
                .showImageForEmptyUri(R.drawable.placeholder_drawable)
                .showImageOnLoading(R.drawable.placeholder_drawable)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(displayImageOptions)
                .build();

        ImageLoader.getInstance().init(config);

        mainController = new MainController();
    }
}
