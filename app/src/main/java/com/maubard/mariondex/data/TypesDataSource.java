package com.maubard.mariondex.data;

import android.support.annotation.NonNull;

import com.maubard.mariondex.data.entity.TypeEntity;

import java.util.List;

/**
 * Created by Marion Aubard on 19/02/2018.
 */

public interface TypesDataSource {

    interface LoadTypesCallback {

        void onTypesLoaded(List<TypeEntity> types);

        void onDataNotAvailable();
    }

    interface GetTypeCallback {

        void onTypeLoaded(TypeEntity task);

        void onDataNotAvailable();
    }

    void getTypes(@NonNull LoadTypesCallback callback);

    void saveType(@NonNull TypeEntity type);

    void deleteAllTypes();
}
