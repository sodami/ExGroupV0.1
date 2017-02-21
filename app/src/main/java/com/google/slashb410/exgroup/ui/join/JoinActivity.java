package com.google.slashb410.exgroup.ui.join;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.home.ReqJoin;
import com.google.slashb410.exgroup.model.group.home.ResJoin;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    @BindView(R.id.email_join)
    EditText email;
    @BindView(R.id.password_join)
    EditText password;
    @BindView(R.id.password_check_join)
    EditText chkPassword;

    ReqJoin reqJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.nextBtn1)
    public void goNext(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);

        if(email.getText().toString().equals("")||password.getText().toString().equals("")||chkPassword.getText().toString().equals("")){
            Snackbar.make(view, "입력란을 모두 채워주세요.", Snackbar.LENGTH_SHORT).show();
        } else if(password.getText().toString().equals(chkPassword.getText().toString())){
            reqJoin = new ReqJoin();
            reqJoin.setUsername(email.getText().toString());
            reqJoin.setPassword(password.getText().toString());

            Call<ResJoin> resJoinCall = NetSSL.getInstance().getMemberImpFactory().join(reqJoin);
            resJoinCall.enqueue(new Callback<ResJoin>() {
                @Override
                public void onResponse(Call<ResJoin> call, Response<ResJoin> response) {
                    if (response.body() == null) {
                        U.getInstance().myLog("body is null");
                        return;
                    }
                    if (response.body().getResultCode() == 1) {
                        U.getInstance().myLog(response.body().toString());

                        builder.setTitle("회원 가입 성공!")
                                .setMessage("친구랑운동 가입을 축하드립니다!")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        U.getInstance().goNext(JoinActivity.this, InputInit0Activity.class, false, false);
                                        finish();
                                    }
                                }).show();

                    } else {
                        //resultCode == 0
                        builder.setTitle("회원가입 에러")
                                .setMessage("이미 존재하는 이메일 주소입니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();

                    }
                }

                @Override
                public void onFailure(Call<ResJoin> call, Throwable t) {
                    U.getInstance().myLog("접근실패 : "+t.toString());
                }
            });

        } else {
            Snackbar.make(view, "비밀번호가 일치하지 않습니다.", Snackbar.LENGTH_SHORT).show();

        }
    }
}
