package com.quandoo.quandoo.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.model.db.dao.CustomerDao;
import com.quandoo.quandoo.model.db.dao.TableDao;


@Database(entities = {Customer.class, Table.class}, version = 1,exportSchema = false)
public abstract class ReservationDataBase extends RoomDatabase {
    public abstract CustomerDao customerDao();
    public abstract TableDao tableDao();

}
