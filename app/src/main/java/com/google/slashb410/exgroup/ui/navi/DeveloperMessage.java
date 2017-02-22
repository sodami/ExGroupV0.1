package com.google.slashb410.exgroup.ui.navi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.slashb410.exgroup.R;

public class DeveloperMessage extends AppCompatActivity {

    public static EditText editTextTo,editTextSubject,editTextMessage;
    public static Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_developer_message);

        editTextTo      =(EditText) editTextTo.findViewById(R.id.editText1);
        editTextSubject =(EditText) editTextSubject.findViewById(R.id.editText2);
        editTextMessage =(EditText) editTextMessage.findViewById(R.id.editText3);

        // send            =(Button) send.findViewById(R.id.button1);
    }
}
