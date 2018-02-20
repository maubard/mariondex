package com.maubard.mariondex.application;

import android.app.Application;
import android.content.Context;

import com.maubard.mariondex.application.manager.MainManager;

/**
 * Created by Marion Aubard on 16/02/2018.
 */

public class MariondexApplication extends Application implements ApplicationIntf {

    @Override
    public void onCreate() {
        super.onCreate();
        CentralHub.initializeInstance(this, new MainManager());
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }
}
