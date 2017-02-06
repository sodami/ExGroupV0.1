package com.google.slashb410.exgroup.util;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //common function
    // progress bar define
    private ProgressDialog pd;


    // progress bar show
    public void showProgress(String msg) {

        //1 time object create
        if (pd == null) {
            pd = new ProgressDialog(this);
            pd.setCancelable(false);

        }

        pd.setMessage(msg);

        pd.show();

    }

    // progress bar hidden
    public void hideProgress() {

        if (pd != null && pd.isShowing()) {
            pd.dismiss();

        }
    }

}
