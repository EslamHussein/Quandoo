package com.quandoo.quandoo.model.cloud.dto;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eslam Hussein on 11/22/17.
 */

public class Customer  implements Parcelable {
    private int id;
    private String customerFirstName;
    private String customerLastName;

    public Customer() {
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
