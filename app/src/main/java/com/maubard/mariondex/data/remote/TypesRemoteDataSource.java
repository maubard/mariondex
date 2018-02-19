package com.maubard.mariondex.data.remote;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.maubard.mariondex.data.TypesDataSource;
import com.maubard.mariondex.data.entity.TypeEntity;
import com.maubard.mariondex.data.local.TypesLocalDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marion Aubard on 19/02/2018.
 */

public class TypesRemoteDataSource implements TypesDataSource {

    /***********************************************************
    *  Attributes
    **********************************************************/

    private static TypesRemoteDataSource sInstance;

    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    private final static Map<Integer, TypeEntity> sMapTypes;

    static {
        sMapTypes = new LinkedHashMap<>(2);
        addType("Normal");
        addType("Fly");
    }

    /***********************************************************
    *  Constructors
    **********************************************************/

    // Prevent direct instantiation.
    private TypesRemoteDataSource() {}

    /***********************************************************
    *  Public method
    **********************************************************/

    public static TypesRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new TypesRemoteDataSource();
        }
        return sInstance;
    }

    /***********************************************************
    *  Private method
    **********************************************************/

    private static void addType(String name) {
        TypeEntity newType = new TypeEntity(name);
        sMapTypes.put(newType.getId(), newType);
    }

    /***********************************************************
    *  Implements
    **********************************************************/

    @Override
    public void getTypes(final @NonNull LoadTypesCallback callback) {
        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<TypeEntity> types = new ArrayList<>();
                types.addAll(sMapTypes.values());
                callback.onTypesLoaded(types);
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void saveType(@NonNull TypeEntity type) {
        sMapTypes.put(type.getId(), type);
    }

    @Override
    public void deleteAllTypes() {
        sMapTypes.clear();
    }
}
