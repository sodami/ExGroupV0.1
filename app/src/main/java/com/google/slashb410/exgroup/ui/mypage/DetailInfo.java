package com.google.slashb410.exgroup.ui.mypage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.slashb410.exgroup.R;

public class DetailInfo extends AppCompatActivity {
    Button submitButton;
    EditText myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
    }
}
