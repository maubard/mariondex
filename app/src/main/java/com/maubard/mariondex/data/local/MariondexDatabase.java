package com.maubard.mariondex.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.maubard.mariondex.data.entity.TypeEntity;

/**
 * Created by Marion Aubard on 16/02/2018.
 */
@Database(entities = {TypeEntity.class}, version = 1)
public abstract class MariondexDatabase extends RoomDatabase {

    /***********************************************************
    *  Attributes
    **********************************************************/

    private static final String DATABASE_NAME = "mariondex";

    private static final Object LOCK = new Object();
    private static volatile MariondexDatabase sInstance;

    /***********************************************************
    *  Public method
    **********************************************************/

    public abstract TypesDao typeDao();

    public static MariondexDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MariondexDatabase.class, MariondexDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }
}
