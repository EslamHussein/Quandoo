package com.quandoo.quandoo.model.db;

import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.model.db.entity.RealmCustomer;
import com.quandoo.quandoo.model.db.entity.RealmTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.realm.Realm;

/**
 * Created by Eslam Hussein on 10/27/17.
 */

public class ReservationsDBRepoImpl implements ReservationsDBRepo {

    @Override
    public Observable<List<Customer>> getCustomers() {
        Realm realm = Realm.getDefaultInstance();
        List<RealmCustomer> realmCustomers = new ArrayList<RealmCustomer>(realm.where(RealmCustomer.class).findAll());
        List<Customer> customers = new ArrayList<>();

        for (RealmCustomer realmCustomer : realmCustomers) {
            customers.add(new Customer(realmCustomer.getId(), realmCustomer.getCustomerFirstName(),
                    realmCustomer.getCustomerLastName()));
        }

        return Observable.fromArray(customers);
    }

    @Override
    public void addCustomers(List<Customer> customers) {


        Realm realm = Realm.getDefaultInstance();
        List<RealmCustomer> realmCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            realmCustomers.add(new RealmCustomer(customer.getId(),
                    customer.getCustomerFirstName(), customer.getCustomerLastName()));
        }
        try {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(realmCustomers);
            realm.commitTransaction();

        } catch (Exception e) {

            realm.cancelTransaction();
        } finally {
            realm.close();

        }
    }

    @Override
    public void addTables(List<Boolean> tables) {

        Realm realm = Realm.getDefaultInstance();
        List<RealmTable> realmTables = new ArrayList<>();

        for (int i = 0; i < tables.size(); i++) {
            realmTables.add(new RealmTable(i + 1, tables.get(i)));
        }
        try {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(realmTables);
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        } finally {
            realm.close();
        }

    }

    @Override
    public List<Table> getTables() {


        Realm realm = Realm.getDefaultInstance();
        realm.refresh();
        List<RealmTable> realmTables = new ArrayList<>(realm.where(RealmTable.class).findAll());
        List<Table> tables = new ArrayList<>();

        for (RealmTable realmTable : realmTables) {
            tables.add(new Table(realmTable.getId(), realmTable.isAvailable()));
        }
        if (tables.isEmpty()) {
            return null;
        }
        return tables;

    }

    @Override
    public Single<Boolean> updateTable(final Table tableToUpdate) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Boolean isUpdated = false;
                Realm realm = Realm.getDefaultInstance();
                realm.refresh();
                RealmTable realmTable = new RealmTable();
                realmTable.setId(tableToUpdate.getId());
                realmTable.setAvailable(tableToUpdate.isAvailable());
                try {
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(realmTable);
                    realm.commitTransaction();
                    isUpdated = true;
                } catch (Exception e) {
                    realm.cancelTransaction();
                    isUpdated = false;
                } finally {
                    realm.close();
                }
                return isUpdated;
            }
        });

    }

    @Override
    public void clearRealmDataBaseReservation() {


        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.clear(RealmTable.class);
            }
        });


    }


}
