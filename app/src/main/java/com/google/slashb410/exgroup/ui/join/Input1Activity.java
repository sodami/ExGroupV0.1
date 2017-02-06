package com.google.slashb410.exgroup.ui.join;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.util.U;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Input1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input1);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.nextBtn1)
    public void goNext(){
        U.getInstance().goNext(this, Input2Activity.class, true);
        finish();
    }
}
