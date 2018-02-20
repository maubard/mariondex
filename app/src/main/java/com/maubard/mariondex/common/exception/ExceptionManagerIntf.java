package com.maubard.mariondex.common.exception;

/**
 * Created by Marion Aubard on 20/02/2018.
 * A manager for exception.
 */

public interface ExceptionManagerIntf {

    /**
     * Manage exception for type ManagedException
     *
     * @param exception exception to manage
     */
    void manage(ManagedException exception);
}
