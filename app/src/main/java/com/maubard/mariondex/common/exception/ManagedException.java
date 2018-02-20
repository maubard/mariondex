package com.maubard.mariondex.common.exception;

import android.support.annotation.StringRes;

import com.maubard.mariondex.application.CentralHub;

/**
 * Created by Marion Aubard on 20/02/2018.
 * A simple exception to be managed.
 */

public class ManagedException extends Exception {

    /***********************************************************
     *  Attributes
     **********************************************************/

    private String errorMessage;
    private Class rootClass;


    /***********************************************************
     *  Constructors
     **********************************************************/

    public ManagedException(Class rootClass, @StringRes int errorMessage, Exception exception) {
        this(rootClass, CentralHub.getInstance().getAppContext().getString(errorMessage), exception);
    }

    public ManagedException(Class rootClass, String errorMessage, Exception exception) {
        super(errorMessage, exception);
        this.errorMessage = errorMessage;
        this.rootClass = rootClass;
    }


    /***********************************************************
     *  Getters/Setters
     **********************************************************/

    public String getErrorMessage() {
        return errorMessage;
    }

    public Class getRootClass() {
        return rootClass;
    }
}
