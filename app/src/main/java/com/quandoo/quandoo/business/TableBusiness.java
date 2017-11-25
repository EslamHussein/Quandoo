package com.quandoo.quandoo.business;

import com.quandoo.quandoo.model.cloud.ReservationsCloudRepo;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.model.db.ReservationsDBRepo;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Eslam Hussein on 11/25/17.
 */

public class TableBusiness {

    ReservationsDBRepo dbRepo;
    ReservationsCloudRepo cloudRepo;

    public TableBusiness(ReservationsDBRepo dbRepo, ReservationsCloudRepo cloudRepo) {
        this.dbRepo = dbRepo;
        this.cloudRepo = cloudRepo;
    }

    public Observable<List<Table>> getTables() {

        return Observable.fromCallable(new Callable<List<Table>>() {
            @Override
            public List<Table> call() throws Exception {
                List<Table> tables = dbRepo.getTables();
                if (tables != null && !tables.isEmpty())
                    return tables;
                List<Boolean> apiTables = cloudRepo.getTables();

                dbRepo.addTables(apiTables);

                tables = dbRepo.getTables();
                return tables;
            }
        });

    }

    public Single<Boolean> bookTable(Table table) {

        return dbRepo.updateTable(table).observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io());
    }

}
