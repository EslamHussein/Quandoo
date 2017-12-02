package com.quandoo.quandoo.model.cloud.dto;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.quandoo.quandoo.model.db.DBConstant;

/**
 * Created by Eslam Hussein on 11/22/17.
 */
@Entity(tableName = DBConstant.CUSTOMER_TABLE_NAME)
public class Customer  implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstant.CUSTOMER_ID)
    @SerializedName("id")
    private int id;

    @SerializedName("customerFirstName")
    @ColumnInfo(name = DBConstant.CUSTOMER_FIRST_NAME)
    private String customerFirstName;

    @SerializedName("customerLastName")
    @ColumnInfo(name = DBConstant.CUSTOMER_LAST_NAME)
    private String customerLastName;

    public Customer() {
    }

    @Ignore
    public Customer(int id, String customerFirstName, String customerLastName) {
        this.id = id;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }

    protected Customer(Parcel in) {
        id = in.readInt();
        customerFirstName = in.readString();
        customerLastName = in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(customerFirstName);
        dest.writeString(customerLastName);
    }
}
