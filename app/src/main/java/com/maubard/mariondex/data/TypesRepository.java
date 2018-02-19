package com.maubard.mariondex.data;

import android.support.annotation.NonNull;

import com.maubard.mariondex.data.entity.TypeEntity;
import com.maubard.mariondex.data.remote.TypesRemoteDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marion Aubard on 19/02/2018.
 */

public class TypesRepository implements TypesDataSource {

    /***********************************************************
    *  Attributes
    **********************************************************/

    private static TypesRepository sInstance = null;

    private final TypesDataSource mTypesRemoteDataSource;

    private final TypesDataSource mTypesLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<Integer, TypeEntity> mCachedTypes;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    /***********************************************************
    *  Constructors
    **********************************************************/

    // Prevent direct instantiation.
    private TypesRepository(@NonNull TypesDataSource tasksRemoteDataSource,
                            @NonNull TypesDataSource tasksLocalDataSource) {
        mTypesRemoteDataSource = tasksRemoteDataSource;
        mTypesLocalDataSource = tasksLocalDataSource;
    }

    /***********************************************************
    *  Public method
    **********************************************************/

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param typesRemoteDataSource the backend data source
     * @param typesLocalDataSource  the device storage data source
     * @return the {@link TypesRepository} instance
     */
    public static TypesRepository getInstance(TypesDataSource typesRemoteDataSource,
                                              TypesDataSource typesLocalDataSource) {
        if (sInstance == null) {
            sInstance = new TypesRepository(typesRemoteDataSource, typesLocalDataSource);
        }
        return sInstance;
    }

    /**
     * Used to force {@link #getInstance(TypesDataSource, TypesDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        sInstance = null;
    }

    /***********************************************************
    *  Private method
    **********************************************************/

    private void getTypesFromRemoteDataSource(@NonNull final LoadTypesCallback callback) {
        mTypesRemoteDataSource.getTypes(new LoadTypesCallback() {
            @Override
            public void onTypesLoaded(List<TypeEntity> types) {
                refreshCache(types);
                refreshLocalDataSource(types);
                callback.onTypesLoaded(new ArrayList<>(mCachedTypes.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<TypeEntity> types) {
        if (mCachedTypes == null) {
            mCachedTypes = new LinkedHashMap<>();
        }
        mCachedTypes.clear();
        for (TypeEntity type : types) {
            mCachedTypes.put(type.getId(), type);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<TypeEntity> types) {
        mTypesLocalDataSource.deleteAllTypes();
        for (TypeEntity type : types) {
            mTypesLocalDataSource.saveType(type);
        }
    }

    /***********************************************************
    *  Implements
    **********************************************************/

    @Override
    public void getTypes(final @NonNull LoadTypesCallback callback) {
        // Respond immediately with cache if available and not dirty
        if (mCachedTypes != null && !mCacheIsDirty) {
            callback.onTypesLoaded(new ArrayList<>(mCachedTypes.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getTypesFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mTypesLocalDataSource.getTypes(new LoadTypesCallback() {
                @Override
                public void onTypesLoaded(List<TypeEntity> types) {
                    refreshCache(types);
                    callback.onTypesLoaded(new ArrayList<>(mCachedTypes.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    getTypesFromRemoteDataSource(callback);
                }
            });
        }
    }

    @Override
    public void saveType(@NonNull TypeEntity type) {
        mTypesRemoteDataSource.saveType(type);
        mTypesLocalDataSource.saveType(type);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedTypes == null) {
            mCachedTypes = new LinkedHashMap<>();
        }
        mCachedTypes.put(type.getId(), type);
    }

    @Override
    public void deleteAllTypes() {
        mTypesRemoteDataSource.deleteAllTypes();
        mTypesLocalDataSource.deleteAllTypes();

        if (mCachedTypes == null) {
            mCachedTypes = new LinkedHashMap<>();
        }
        mCachedTypes.clear();
    }
}
