package com.quandoo.quandoo.model.db.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eslam Hussein on 11/24/17.
 */

public class RealmTable extends RealmObject {

    @PrimaryKey
    private int id;
    private boolean available;

    public RealmTable() {
    }

    public RealmTable(int id, boolean available) {
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
