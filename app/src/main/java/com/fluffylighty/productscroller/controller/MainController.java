package com.fluffylighty.productscroller.controller;

import android.util.Log;

import com.fluffylighty.productscroller.events.InitialisationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Nico Adler on 28.01.17.
 */

public class MainController {

    private static final String LOG_TAG = MainController.class.getSimpleName();

    public MainController() {

        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new InitialisationEvent());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageEvent(InitialisationEvent initialisationEvent) {

        Log.d(LOG_TAG, "Asynchronous initialisation started.");

        //TODO fetch Data
        //TODO notify views when data arrives
    }
}
