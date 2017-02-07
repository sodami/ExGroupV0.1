package com.google.slashb410.exgroup.ui.group.create;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.util.U;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupAddActivity extends AppCompatActivity {

    final int DIALOG_DATE = 1;
    String[] yyMMdd = U.getInstance().currentYYmmDD();
    @BindView(R.id.datepickBtn)
    Button datepickBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.datepickBtn)
    public void pickDate(){
        showDialog(DIALOG_DATE);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        switch(id){
            case DIALOG_DATE :
                DatePickerDialog dpd = new DatePickerDialog
                        (this, // 현재화면의 제어권자
                                new DatePickerDialog.OnDateSetListener() {
                                    public void onDateSet(DatePicker view,
                                                          int year, int monthOfYear,int dayOfMonth) {

                                        ++monthOfYear;
                                        datepickBtn.setText(year+"년 "+monthOfYear+"월 "+dayOfMonth+"일");
                                    }
                                }
                                ,
                                Integer.parseInt(yyMMdd[0]), Integer.parseInt(yyMMdd[1])-1, Integer.parseInt(yyMMdd[2])); // 기본값 연월일
                return dpd;

        }


        return super.onCreateDialog(id);
    }



    @OnClick(R.id.group_add)
    public void goAdd(){
        U.getInstance().goNext(this, WeightCheckActivity.class, false);
    }


}
