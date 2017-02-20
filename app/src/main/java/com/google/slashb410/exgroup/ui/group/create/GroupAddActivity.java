package com.google.slashb410.exgroup.ui.group.create;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.util.U;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupAddActivity extends AppCompatActivity {

    final int DIALOG_DATE = 1;
    String[] currentYYmmDD = U.getInstance().currentYYmmDD();

    @BindView(R.id.group_profileImg)
    ImageView groupProfileImg;
    @BindView(R.id.group_name_add)
    EditText groupName;
    int groupTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
        ButterKnife.bind(this);

    }

    private int getTerm(String today, String selectedDay) {

        U.getInstance().myLog(today);
        U.getInstance().myLog(selectedDay);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = formatter.parse(today);
            endDate = formatter.parse(selectedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = endDate.getTime() - beginDate.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        int term = Integer.parseInt(diffDays + "");

        return term;

    }

    @OnClick(R.id.group_add)
<<<<<<< HEAD
    public void goAdd(){
//
//        E.KEY.new_group.setGroupName(groupName.getText().toString());
//        //E.KEY.new_group.setGroupImgPath();
//        E.KEY.new_group.setTerm(groupTerm);
//
//        E.KEY.group_list.add(E.KEY.new_group);
=======
    public void goAdd() {

        E.KEY.new_group.setGroupName(groupName.getText().toString());
        //E.KEY.new_group.setGroupImgPath();
        E.KEY.new_group.setTerm(groupTerm);

        E.KEY.group_list.add(E.KEY.new_group);
>>>>>>> cb8ab04d4484124306665c83b5c2664c33f29ba4

        U.getInstance().goNext(this, WeightCheckActivity.class, false, false);
        finish();
    }

    @OnClick(R.id.group_profileImg)
    public void onSetProfile() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = {"카메라", "갤러리"};

        builder.setTitle("그룹 대표이미지 선택")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: {
                                U.getInstance().onCamera(GroupAddActivity.this, "/group", groupProfileImg);
                                dialog.dismiss();
                            }
                            break;
                            case 1: {
                                U.getInstance().onGallery(GroupAddActivity.this, "/group", groupProfileImg);
                                dialog.dismiss();
                            }
                            break;
                        }
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();

    }

}
