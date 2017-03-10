package com.google.slashb410.exgroup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.home.ReqLogin;
import com.google.slashb410.exgroup.model.group.home.ResLogin;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.join.InputInit0Activity;
import com.google.slashb410.exgroup.ui.join.JoinActivity;
import com.google.slashb410.exgroup.util.U;

import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterActivity extends AppCompatActivity {

    @BindView(R.id.email_login)
    EditText email;
    @BindView(R.id.password_login)
    EditText password;
    ReqLogin reqLogin;

    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter);
        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();

        U.getInstance().popSimpleDialog(null, this, null, "로그인 버튼을 누르시면 테스트 계정으로 자동 로그인됩니다.");

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("result", object.toString());
                        callFacebookLogin(loginResult.getAccessToken().toString());
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr", error.toString());
            }
        });
    }

    private void callFacebookLogin(String token) {
        Call<ResStandard> resFacebookLogin = NetSSL.getInstance().getMemberImpFactory().facebookLogin(token);
        resFacebookLogin.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if (response.body() == null) U.getInstance().myLog("resFacebookLogin body is null");
                int resultCode = response.body().getResultCode();
                switch (resultCode) {
                    case 0:
                        U.getInstance().myLog("처음 등록한 사용자");
                        U.getInstance().goNext(getApplicationContext(), InputInit0Activity.class, false, false);
                        break;
                    case 1:
                        U.getInstance().myLog("등록된 사용자");
                        U.getInstance().goNext(getApplicationContext(), Home2Activity.class, false, false);
                        break;

                }
            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                U.getInstance().myLog("facebook 접근실패 : " + t.toString());
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    // 회원가입 이동
    @OnClick(R.id.joinBtn)
    public void onJoin() {
        U.getInstance().goNext(this, JoinActivity.class, false, false);
    }

    // 로그인 처리
    @OnClick(R.id.loginBtn)
    public void onLogin(View view) {
        //최초 로그인 시 튜토리얼 진행
        if (isFirstLogin()) {

        } else {
//            if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
//                Snackbar.make(view, "입력란을 모두 채워주세요.", Snackbar.LENGTH_SHORT).show();
//            } else {
            reqLogin = new ReqLogin();
//                reqLogin.setUsername(email.getText().toString());
//                reqLogin.setPassword(password.getText().toString());

            reqLogin.setUsername("b@naver.com");
            reqLogin.setPassword("b");

            Call<ResLogin> resLoginCall = NetSSL.getInstance().getMemberImpFactory().login(reqLogin);
            resLoginCall.enqueue(new Callback<ResLogin>() {
                @Override
                public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {
                    U.getInstance().goNext(EnterActivity.this, Home2Activity.class, false, false);
//                        U.getInstance().myLog((response.body().toString()+""));
//                        if (response.body().getResultCode() == 1) {
//                            U.getInstance().myLog(response.body().getMessage());
//                            U.getInstance().myLog("activation : " + response.body().getUser().getActivation());
//                            if (response.body().getUser().getActivation() == 1) {
//                                //계정 활성화
//                                U.getInstance().goNext(EnterActivity.this, Home2Activity.class, false, false);
//                                finish();
//                            } else {
//                                //계정 비활성화 -> 정보입력
//                                U.getInstance().goNext(EnterActivity.this, InputInit0Activity.class, false, false);
//                                finish();
//                            }
//                        } else {
//                            //resultCode == 0
//                            AlertDialog.Builder builder = new AlertDialog.Builder(EnterActivity.this);
//                            builder.setTitle("로그인 에러")
//                                    .setMessage("다시 로그인해주시기 바랍니다.")
//                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.dismiss();
//                                        }
//                                    })
//                                    .show();
//
//                        }
                }

                @Override
                public void onFailure(Call<ResLogin> call, Throwable t) {
                    U.getInstance().myLog("접근실패 : " + t.toString());
                }
            });
        }
    }


    public boolean isFirstLogin() {
        return false;
    }
}
