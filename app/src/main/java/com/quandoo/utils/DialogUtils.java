package com.quandoo.utils;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


public class DialogUtils {

    public static Dialog getOkDialog(Context context, String title, String msg, String okTxt, String cancelTxt,
                                     final Runnable okAction) {
        AlertDialog.Builder builder;

        builder =
                new AlertDialog.Builder(context);

        if (!TextUtils.isEmptyString(title))
            builder.setTitle(title);

        builder.setMessage(msg);


        builder.setPositiveButton(okTxt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (okAction != null)
                    okAction.run();
                dialog.dismiss();

            }
        });

        builder.setNegativeButton(cancelTxt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        return builder.create();
    }

    public static ProgressDialog getProgressDialog(
            Context context, String message, boolean canCancelable,
            boolean canceledOnTouchOutside) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(canCancelable);
        progressDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        progressDialog.setMessage(message);
        return progressDialog;
    }


}
