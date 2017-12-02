package com.quandoo.quandoo.injection;

import android.arch.persistence.room.Room;

import com.quandoo.App;
import com.quandoo.quandoo.model.db.DBConstant;
import com.quandoo.quandoo.model.db.ReservationDataBase;
import com.quandoo.quandoo.model.db.ReservationsDBRepo;
import com.quandoo.quandoo.model.db.ReservationsDBRepoImpl;

public class Injection {

    public static ReservationsDBRepo provideDBRepo(ReservationDataBase reservationDataBase) {
        return new ReservationsDBRepoImpl(reservationDataBase);
    }

    public static ReservationDataBase provideReservationDataBase() {
        return Room.databaseBuilder(App.get().getBaseContext(), ReservationDataBase.class, DBConstant.DB_NAME).build();
    }

}
