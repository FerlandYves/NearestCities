package com.example.nearestcities;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Common {

    public static void showHelp(Context context,String help) {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context,R.style.Theme_MaterialComponents_Dialog);
        builder.setTitle("Help");
        builder.setIcon(R.drawable.ic_help_outline_24);
        builder.setMessage(help);
        builder.show();


    }
}
