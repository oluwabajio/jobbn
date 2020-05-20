package com.example.jobbn.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.jobbn.R;

public class AppUtils {

    public static final String JOBBN_TAG = "JOBBN_TAG";
    public static ProgressDialog progressDialog;

    public static void initLoadingDialog(Context context, String message) {
        progressDialog = new ProgressDialog(context,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message + "...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static void dismissLoadingDialog() {
        progressDialog.dismiss();
    }
}
