package com.wipro.weather.utilities;

import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogUtils {

    public static void getErrorDialog(final Application context){
        Dialog dialog = new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Error")
                .setMessage("An error occur trying to get 5 days forecast")
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                }).create();
        //Do not allow user to touch outside the alert to close it
        dialog.setCanceledOnTouchOutside(false);
        //Show the dialog
        dialog.show();
    }
}
