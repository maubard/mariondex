package com.maubard.mariondex.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;


/**
 * Immutable model class for a TypeEntity.
 */

@Entity(tableName = "types")
public class TypeEntity {

    /***********************************************************
    *  Attributes
    **********************************************************/

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    private Integer mId;

    @NonNull
    @SerializedName("name")
    private String mName;

    /***********************************************************
    *  Constructors
    **********************************************************/

    /**
     * Use this constructor to create a new TypeEntity.
     *
     * @param name  name of the type
     */
    public TypeEntity(@NonNull String name) {
        this.mName = name;
    }

    /***********************************************************
    *  Getters/Setters
    **********************************************************/

    @NonNull
    public Integer getId() {
        return mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setId(@NonNull Integer id) {
        this.mId = id;
    }

    public void setName(@NonNull String name) {
        this.mName = name;
    }

    /***********************************************************
    *  Implements
    **********************************************************/

    @Override
    public String toString() {
        return "TypeEntity with name " + mName;
    }
}
