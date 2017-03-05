package com.google.slashb410.exgroup.ui.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.slashb410.exgroup.R;

public class DetailInfo extends AppCompatActivity {
    Button submitButton;
    EditText myTextView;
    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            student = bundle.getParcelable("STUDENT");
            Log.i("tag", "bundle is not null");
        }

        myTextView = (EditText)findViewById(R.id.myName);
        submitButton = (Button)findViewById(R.id.submitBtn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            String inputMyname;
            Student student = null;
            @Override
            public void onClick(View v) {
                inputMyname = myTextView.getText().toString();

                student = new Student(inputMyname);

                Intent intent = new Intent(DetailInfo.this, MyHomeActivity.class);
                intent.putExtra("STUDENT", student);
                startActivity(intent);
            }
        });
    }
}
