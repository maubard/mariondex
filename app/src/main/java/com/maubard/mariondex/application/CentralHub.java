package com.maubard.mariondex.application;

import android.content.Context;

import com.maubard.mariondex.application.manager.MainManager;
import com.maubard.mariondex.application.manager.MainManagerIntf;
import com.maubard.mariondex.common.exception.ManagedException;

/**
 * Created by Marion Aubard on 20/02/2018.
 * This class is a proxy for MyApplication and MainManager
 * Make call method more easy to understand and use.
 */

public class CentralHub {

    /***********************************************************
    *  Attributes
    **********************************************************/

    private static CentralHub sInstance;
    private ApplicationIntf mApplication;
    private MainManagerIntf mMainManager;

    /***********************************************************
    *  Constructors
    **********************************************************/

    CentralHub() {

    }

    /***********************************************************
    *  Static methods
    **********************************************************/

    public static void initializeInstance(ApplicationIntf application,
                                          MainManagerIntf mainManager) {
        sInstance = new CentralHub();
        sInstance.mApplication = application;
        sInstance.mMainManager = mainManager;
    }

    public static CentralHub getInstance() {
        if (sInstance == null) {
            try {
                throw new ManagedException(CentralHub.class, "CentralHub as not been initialized", new IllegalStateException());
            } catch(ManagedException ignored) {

            }
        }
        return sInstance;
    }

    /***********************************************************
     *  Delegated method - ApplicationIntf
     **********************************************************/

    public Context getAppContext() {
        return mApplication.getAppContext();
    }
}
