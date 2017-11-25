package com.quandoo.quandoo.model.db;

import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.cloud.dto.Table;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Eslam Hussein on 11/22/17.
 */

public interface ReservationsDBRepo {

    Observable<List<Customer>> getCustomers();

    void addCustomers(List<Customer> customers);

    void addTables(List<Boolean> tables);

    List<Table> getTables();

    Single<Boolean> updateTable(Table tableToUpdate);

    void clearRealmDataBaseReservation();
}
