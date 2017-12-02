package com.quandoo.quandoo.model.db;

import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.cloud.dto.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;


public class ReservationsDBRepoImpl implements ReservationsDBRepo {

    private ReservationDataBase db;

    public ReservationsDBRepoImpl(ReservationDataBase db) {
        this.db = db;

    }

    @Override
    public Observable<List<Customer>> getCustomers() {


        return Observable.fromCallable(new Callable<List<Customer>>() {
            @Override
            public List<Customer> call() throws Exception {
                List<Customer> customerEntities = db.customerDao().getAll();
                return customerEntities;
            }
        });
    }

    @Override
    public void addCustomers(List<Customer> customers) {

        db.customerDao().insertAll(customers);

    }

    @Override
    public void addTables(List<Boolean> tables) {

        List<Table> realmTables = new ArrayList<>();

        for (int i = 0; i < tables.size(); i++) {
            realmTables.add(new Table(i + 1, tables.get(i)));
        }
        db.tableDao().insertAll(realmTables);

    }

    @Override
    public List<Table> getTables() {


        List<Table> tables = db.tableDao().getAll();

        return tables;

    }

    @Override
    public Single<Boolean> updateTable(final Table tableToUpdate) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Boolean isUpdated;
                Table tableEntity = new Table();
                tableEntity.setId(tableToUpdate.getId());
                tableEntity.setAvailable(tableToUpdate.isAvailable());
                db.tableDao().updateTable(tableEntity);
                isUpdated = true;
                return isUpdated;
            }
        });

    }

    @Override
    public void clearRealmDataBaseReservation() {

        db.tableDao().deleteAll();


    }


}
