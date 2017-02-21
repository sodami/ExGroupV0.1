package com.google.slashb410.exgroup.ui.join;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.home.ReqInitInfo;
import com.google.slashb410.exgroup.model.group.home.ResInitInfo;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.Home2Activity;
import com.google.slashb410.exgroup.util.U;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputInit1Activity extends AppCompatActivity {

    @BindView(R.id.age_join)
    EditText age;
    @BindView(R.id.height_join)
    EditText height;
    @BindView(R.id.weight_join)
    EditText weight;

    String nickname;
    String phone;
    String picUrl;

    ReqInitInfo reqInitInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_init1);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        phone = intent.getStringExtra("phone");
        picUrl = intent.getStringExtra("picUrl");

    }

    @OnClick(R.id.nextBtn3)
    public void goNext(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (age.getText().toString().equals("") || height.getText().toString().equals("") || weight.getText().toString().equals("")) {
            Snackbar.make(view, "입력란을 모두 채워주세요.", Snackbar.LENGTH_SHORT).show();
        } else {
            reqInitInfo = new ReqInitInfo();
            reqInitInfo.setNickname(nickname);
            reqInitInfo.setPhone(phone);
            reqInitInfo.setPhoto(picUrl);
            reqInitInfo.setAge(age.getText().toString());
            reqInitInfo.setHeight(height.getText().toString());
            reqInitInfo.setWeight(weight.getText().toString());

            Call<ResInitInfo> resInitInfoCall = NetSSL.getInstance().getMemberImpFactory().initInfo(reqInitInfo);
            resInitInfoCall.enqueue(new Callback<ResInitInfo>() {
                @Override
                public void onResponse(Call<ResInitInfo> call, Response<ResInitInfo> response) {
                    if (response.body() == null) {
                        U.getInstance().myLog("body is null");
                        return;
                    } else {
                        if (response.body().getResultCode() == 1) {
                            U.getInstance().myLog(response.body().getResult());

                            Intent intent2 = new Intent(InputInit1Activity.this, Home2Activity.class);
                            startActivity(intent2);
                            finish();

                        } else {
                            //resultCode == 0
                            builder.setTitle("기본 정보 입력 에러")
                                    .setMessage("다시 시도해 주세요.")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResInitInfo> call, Throwable t) {

                }
            });


        }
    }
}


