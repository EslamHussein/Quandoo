package com.quandoo;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.quandoo.utils.AlarmReceiver;


/**
 * Created by Eslam Hussein on 10/27/17.
 */

public class App extends Application {

    public static final int REQUEST_CODE = 0;

    private static App instance;

    public static App get() {
        if (instance == null)
            throw new IllegalStateException("Something went horribly wrong!!, no application attached!");
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        registerAlarmManger();
    }


    private void registerAlarmManger() {
        long TIME_TO_CLEAR = 15 * 60 * 1000;

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        int alarmType = AlarmManager.ELAPSED_REALTIME;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(alarmType, SystemClock.elapsedRealtime() + TIME_TO_CLEAR,
                TIME_TO_CLEAR, pendingIntent);

    }

}
