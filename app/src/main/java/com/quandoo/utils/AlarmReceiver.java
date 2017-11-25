package com.quandoo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Eslam Hussein on 11/25/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent clearTablesServiceIntent = new Intent(context, ClearTablesService.class);
        context.startService(clearTablesServiceIntent);


    }
}
