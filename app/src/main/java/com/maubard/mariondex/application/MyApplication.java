package com.maubard.mariondex.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by Marion Aubard on 16/02/2018.
 */

public class MyApplication extends Application implements ApplicationIntf {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }
}
