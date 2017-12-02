package com.quandoo.quandoo.model.db.dao;

/**
 * Created by Eslam Hussein on 11/30/17.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.model.db.DBConstant;

import java.util.List;

@Dao
public interface TableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Table> tableEntities);

    @Query("SELECT * FROM "+ DBConstant.TABLE_TABLE_NAME)
    List<Table> getAll();

    @Update
    void updateTable(Table tableEntity);

    @Query("DELETE FROM "+ DBConstant.TABLE_TABLE_NAME)
    void deleteAll();

}
