package com.ay27.WirelessDebug;

import android.app.Application;
import android.content.Context;

/**
 * Proudly to user Intellij IDEA.
 * Created by ay27 on 15/6/21.
 */
public class WirelessDebugApplication extends Application {

    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }
}
