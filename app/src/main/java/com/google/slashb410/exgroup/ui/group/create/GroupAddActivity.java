package com.google.slashb410.exgroup.ui.group.create;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupAddActivity extends AppCompatActivity {

    @BindView(R.id.group_profileImg)
    ImageView groupProfileImg;
    @BindView(R.id.group_name_add)
    EditText groupTitle;
    @BindView(R.id.memberSpinner)
    Spinner memSpinner;
    @BindView(R.id.title_length)
    TextView titleLenght;
    @BindView(R.id.period)
    Button periodBtn;

    int pickPeriod = 0;
    String profilePath ="";
    String numMem ="";
    View peroidPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
        ButterKnife.bind(this);


        //1. 타이틀 입력
        // 1-1.길이 출력
        groupTitle.addTextChangedListener(new TextWatcher() {
//            String curString;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                curString = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleLenght.setText(String.valueOf(s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //2. 멤버수 입력
        memSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                numMem = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                                U.getInstance().onCamera(GroupAddActivity.this, groupProfileImg);

                                dialog.dismiss();
                            }
                            break;
                            case 1: {
                                U.getInstance().onGallery(GroupAddActivity.this, groupProfileImg);
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

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @OnClick(R.id.period)
    public void setPeriod(View view) {


        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        peroidPickerView = inflater.inflate(R.layout.dialog_period_picker, null);

        NumberPicker periodPicker = (NumberPicker) peroidPickerView.findViewById(R.id.period_picker);
        periodPicker.setMinValue(7);
        periodPicker.setMaxValue(30);
        periodPicker.setWrapSelectorWheel(false);
        periodPicker.setBackgroundColor(getResources().getColor(R.color.babyPink));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("활동기간 선택")
                .setMessage("최소 7일, 최대 30일")
                .setView(peroidPickerView)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pickPeriod = periodPicker.getValue();
                U.getInstance().myLog("pickPeriod : "+pickPeriod);
                periodBtn.setText(pickPeriod + "일");
                dialog.dismiss();
            }
        })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
        .show();
    }

    @OnClick(R.id.group_add)
    public void goAdd(View view) {
        Map<String, RequestBody> bodyMap = new HashMap<>();
        profilePath = E.KEY.TEMP_PIC_URI;
        U.getInstance().myLog("profilePath : "+profilePath);

//        bodyMap.put("groupTitle", RequestBody.create(groupTitle.getText().toString(), ))
        if (profilePath.equals("")||groupTitle.getText().toString().equals("") || pickPeriod==0) {
            U.getInstance().myLog(profilePath+"/"+groupTitle.getText().toString()+"/"+pickPeriod);
            Snackbar.make(view, "입력란을 모두 채워주세요.", Snackbar.LENGTH_SHORT).show();
            return;
        }

        Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("groupTitle", RequestBody.create(MediaType.parse("multipart/form-data"), groupTitle.getText().toString()));
        map.put("maxNum", RequestBody.create(MediaType.parse("multipart/form-data"), numMem));
        map.put("exPeriod", RequestBody.create(MediaType.parse("multipart/form-data"), pickPeriod+""));


        File file = new File(profilePath);
        U.getInstance().myLog(file.getAbsolutePath() + "+" + file.canRead());

        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        map.put("photo", fileBody);


        Call<ResStandard> resMakeGroup = NetSSL.getInstance().getGroupImpFactory().makeGroup(map);
        resMakeGroup.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if (response.body() == null) {
                    U.getInstance().myLog("resMakeGroup Body is NULL");
                } else {
                    U.getInstance().myLog("그룹만들기 성공");
                    E.KEY.TEMP_PIC_URI = "";
                    U.getInstance().myLog(response.body().getResult());

                }
            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                U.getInstance().myLog("접근실패 : " + t.toString());
            }
        });
    }
}