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
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.util.U;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class InputInit0Activity extends AppCompatActivity {

    @BindView(R.id.nickname_join)
    EditText nickname;
    @BindView(R.id.phone_number_join)
    EditText phone;
    @BindView(R.id.profile_join)
    CircleImageView profile;
    String picUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_init0);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.nextBtn_init0)
    public void goNext(View view){
        if(nickname.getText().toString().equals("")||phone.getText().toString().equals("")){
            Snackbar.make(view, "입력란을 모두 채워주세요", Snackbar.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(InputInit0Activity.this, InputInit1Activity.class);
            intent.putExtra("nickname", nickname.getText().toString());
            intent.putExtra("phone", phone.getText().toString());
            if(picUrl.equals("")){
                U.getInstance().myLog("picUrl is null");
//                picUrl = "https://firebasestorage.googleapis.com/v0/b/exgroup-1faeb.appspot.com/o/default%2F%EC%82%AC%EC%A7%84%20%EB%94%94%ED%8F%B4%ED%8A%B8_xhdpi(96x96).png?alt=media&token=b21b330d-9e25-4254-ab8a-aadec8d30c86";
            }
            intent.putExtra("picUrl", picUrl);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.profile_join)
    public void setProfile(){

        String[] items = {"카메라", "갤러리"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("프로필사진 선택")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                U.getInstance().onCamera(InputInit0Activity.this, profile);
                                picUrl = E.KEY.TEMP_PIC_URI;
                                U.getInstance().myLog("picUrl : "+E.KEY.TEMP_PIC_URI);
                                break;
                            case 1:
                                U.getInstance().onGallery(InputInit0Activity.this, profile);
                                picUrl = E.KEY.TEMP_PIC_URI;
                                U.getInstance().myLog("picUrl : "+E.KEY.TEMP_PIC_URI);
                                break;
                        }
                    }
                })
                .show();
    }
}
