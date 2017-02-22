package com.google.slashb410.exgroup.util;

import android.app.Application;

import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;

/**
 * Created by Tacademy on 2017-01-19.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RxPaparazzo.register(this);



    }
}
