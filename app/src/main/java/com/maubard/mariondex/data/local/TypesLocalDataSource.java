package com.maubard.mariondex.data.local;

import android.support.annotation.NonNull;

import com.maubard.mariondex.data.TypesDataSource;
import com.maubard.mariondex.data.entity.TypeEntity;
import com.maubard.mariondex.util.AppExecutors;

import java.util.List;

/**
 * Created by Marion Aubard on 19/02/2018.
 */

public class TypesLocalDataSource implements TypesDataSource {

    /***********************************************************
    *  Attributes
    **********************************************************/

    private static volatile TypesLocalDataSource sInstance;

    private TypesDao mTypesDao;

    private AppExecutors mAppExecutors;

    /***********************************************************
    *  Constructors
    **********************************************************/

    // Prevent direct instantiation.
    private TypesLocalDataSource(@NonNull AppExecutors appExecutors,
                                 @NonNull TypesDao typesDao) {
        mAppExecutors = appExecutors;
        mTypesDao = typesDao;
    }

    public static TypesLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull TypesDao tasksDao) {
        if (sInstance == null) {
            synchronized (TypesLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new TypesLocalDataSource(appExecutors, tasksDao);
                }
            }
        }
        return sInstance;
    }

    /***********************************************************
    *  Implements
    **********************************************************/

    @Override
    public void getTypes(@NonNull final LoadTypesCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TypeEntity> tasks = mTypesDao.findAllTypes();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (tasks.isEmpty()) {
                            // This will be called if the table is new or just empty.
                            callback.onDataNotAvailable();
                        } else {
                            callback.onTypesLoaded(tasks);
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveType(final @NonNull TypeEntity type) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mTypesDao.insertType(type);
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void deleteAllTypes() {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                mTypesDao.deleteTypes();
            }
        };
        mAppExecutors.diskIO().execute(deleteRunnable);
    }
}
