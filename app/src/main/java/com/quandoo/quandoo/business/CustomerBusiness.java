package com.quandoo.quandoo.business;

import com.quandoo.quandoo.model.cloud.ReservationsCloudRepo;
import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.db.ReservationsDBRepo;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Eslam Hussein on 11/24/17.
 */

public class CustomerBusiness {

    ReservationsDBRepo dbRepo;
    ReservationsCloudRepo cloudRepo;

    public CustomerBusiness(ReservationsDBRepo dbRepo, ReservationsCloudRepo cloudRepo) {
        this.dbRepo = dbRepo;
        this.cloudRepo = cloudRepo;

    }

    public Observable<List<Customer>> getCustomersDB() {
        return dbRepo.getCustomers().observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io());
    }

    public Observable<List<Customer>> getCustomersCloud() {

        return Observable.fromCallable(new Callable<List<Customer>>() {
            @Override
            public List<Customer> call() throws Exception {
                List<Customer> customers = cloudRepo.getCustomers();
                dbRepo.addCustomers(customers);
                return customers;

            }
        }).observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io());
    }


}
