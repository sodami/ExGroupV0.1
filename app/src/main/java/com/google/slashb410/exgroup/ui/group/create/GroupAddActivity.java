package com.google.slashb410.exgroup.ui.group.create;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.group.ReqMakeGroup;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;

public class GroupAddActivity extends AppCompatActivity {

    final int DIALOG_DATE = 1;

    @BindView(R.id.group_profileImg)
    ImageView groupProfileImg;
    @BindView(R.id.group_name_add)
    EditText groupTitle;
    @BindView(R.id.mem_numpicker)
    NumberPicker memPicker;

    @Nullable
    @BindView(R.id.period_picker)
    NumberPicker periodPicker;
    @BindView(R.id.period)
    Button period;

    String profilePath;
    ReqMakeGroup reqMakeGroup;
    View peroidPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
        ButterKnife.bind(this);

        memPicker.setMinValue(2);
        memPicker.setMaxValue(5);

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
//                            case 0: {
//                                profilePath = U.getInstance().onCamera(GroupAddActivity.this, "/group", groupProfileImg);
//                                dialog.dismiss();
//                            }
//                            break;
                            case 1: {
                                // U.getInstance().onGallery(GroupAddActivity.this, "/group", groupProfileImg);
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

    @OnClick(R.id.period)
    public void setPeriod(){
        periodPicker = new NumberPicker(this);

        periodPicker.setMinValue(7);
        periodPicker.setMaxValue(30);

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        peroidPickerView = inflater.inflate(R.layout.dialog_period_picker, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("활동기간 선택")
                .setView(peroidPickerView)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reqMakeGroup.setExPeriod(which);
                        period.setText(which+"일");
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    @OnClick(R.id.group_add)
    public void goAdd(View view) {
        Map<String, RequestBody> bodyMap = new HashMap<>();
//        bodyMap.put("groupTitle", RequestBody.create(groupTitle.getText().toString(), ))
        if(groupTitle.getText().equals("")||period.getText().equals("")){
            Snackbar.make(view, "입력란을 모두 채워주세요.", Snackbar.LENGTH_SHORT).show();
            return;
        }
        reqMakeGroup = new ReqMakeGroup();
        reqMakeGroup.setGroupTitle(groupTitle.getText().toString());
        reqMakeGroup.setMaxNum(memPicker.getValue());
        reqMakeGroup.setPhoto(profilePath);
//
//        File file = FileUtils.getPath(this, profilePath);
//        Requestb
//        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(), )

//        Call<ResStandard> resMakeGroup = NetSSL.getInstance().getGroupImpFactory().makeGroup(reqMakeGroup);
//        resMakeGroup.enqueue(new Callback<ResStandard>() {
//            @Override
//            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
//                if (response.body()==null) {
//                    U.getInstance().myLog("setProfileBox Body is NULL");
//                } else {
//                    U.getInstance().myLog(response.body().toString());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResStandard> call, Throwable t) {
//                U.getInstance().myLog("접근실패 : " + t.toString());
//            }
//        });
    }
}