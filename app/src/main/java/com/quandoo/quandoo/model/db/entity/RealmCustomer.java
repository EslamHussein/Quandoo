package com.quandoo.quandoo.model.db.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eslam Hussein on 11/25/17.
 */

public class RealmCustomer extends RealmObject {
    @PrimaryKey
    private int id;
    private String customerFirstName;
    private String customerLastName;

    public RealmCustomer() {
    }

    public RealmCustomer(int id, String customerFirstName, String customerLastName) {
        this.id = id;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }

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
}
