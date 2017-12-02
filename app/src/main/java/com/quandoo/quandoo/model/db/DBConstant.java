package com.quandoo.quandoo.model.db;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Eslam Hussein on 11/30/17.
 */

public interface DBConstant {
    String DB_NAME = "Reservation_db";
    String CUSTOMER_TABLE_NAME = "CUSTOMER";
    String TABLE_TABLE_NAME = "C_TABLE";


    String TABLE_ID = "id";
    String TABLE_AVAILABLE = "available";


    String CUSTOMER_ID = "id";
    String CUSTOMER_FIRST_NAME = "first_name";
    String CUSTOMER_LAST_NAME = "last_name";

}
