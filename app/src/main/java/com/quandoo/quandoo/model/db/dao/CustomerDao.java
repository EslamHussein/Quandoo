package com.quandoo.quandoo.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.db.DBConstant;

import java.util.List;


@Dao
public interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Customer> customerEntities);

    @Query("SELECT * FROM " + DBConstant.CUSTOMER_TABLE_NAME)
    List<Customer> getAll();

    @Query("DELETE FROM "+ DBConstant.CUSTOMER_TABLE_NAME)
    void deleteAll();

}
