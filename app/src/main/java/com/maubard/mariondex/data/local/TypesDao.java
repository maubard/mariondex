package com.maubard.mariondex.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.maubard.mariondex.data.entity.TypeEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Marion Aubard on 16/02/2018.
 */

@Dao
public interface TypesDao {

    /**
     * Select all types from the types table
     *
     * @return all types.
     */
    @Query("SELECT * FROM types")
    List<TypeEntity> findAllTypes();

    /**
     * Insert a type in the database. If the type already exists, replace it.
     *
     * @param type  the type to be inserted.
     */
    @Insert(onConflict = REPLACE)
    void insertType(TypeEntity type);

    /**
     * Delete all types.
     */
    @Query("DELETE FROM types")
    void deleteTypes();
}
