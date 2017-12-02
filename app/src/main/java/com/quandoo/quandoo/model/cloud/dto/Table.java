package com.quandoo.quandoo.model.cloud.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.quandoo.quandoo.model.db.DBConstant;

/**
 * Created by Eslam Hussein on 11/24/17.
 */
@Entity(tableName = DBConstant.TABLE_TABLE_NAME)
public class Table {

    @PrimaryKey
    @ColumnInfo(name = DBConstant.TABLE_ID)
    private int id;

    @ColumnInfo(name = DBConstant.TABLE_AVAILABLE)
    private boolean available;

    public Table() {
    }

    public Table(int id, boolean available) {
        this.id = id;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
