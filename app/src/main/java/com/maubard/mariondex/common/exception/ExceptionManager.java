package com.maubard.mariondex.common.exception;

import com.maubard.mariondex.R;
import com.maubard.mariondex.application.CentralHub;
import com.maubard.mariondex.application.manager.MainManagerIntf;

/**
 * Created by Marion Aubard on 20/02/2018.
 * Implementation of {@link ExceptionManagerIntf}
 */

public class ExceptionManager implements ExceptionManagerIntf {

    /***********************************************************
    *  Tag
    **********************************************************/

    private static final String TAG = "ExceptionManager";

    /***********************************************************
    *  Constructors
    **********************************************************/

    public ExceptionManager(MainManagerIntf mainManager) {
        if (mainManager == null) {
            throw new IllegalArgumentException(CentralHub.getInstance().getAppContext().getString(R.string.warning_dont_instance_this_object));
        }
    }

    /***********************************************************
    *  Implements
    **********************************************************/

    @Override
    public void manage(ManagedException exception) {
        exception.printStackTrace();
    }
}
