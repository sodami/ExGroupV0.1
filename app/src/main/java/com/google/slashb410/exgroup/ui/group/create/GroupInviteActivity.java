package com.google.slashb410.exgroup.ui.group.create;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.slashb410.exgroup.R;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupInviteActivity extends AppCompatActivity {

    final String text = "친구랑운동!\n플레이스토어url축약버전\n이슬비님께서 000그룹에 초대하셨습니다.\n그룹id : dasdf";
    String groupId = "임시그룹아이디";
    KakaoLink kakaoLink = null;
    KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_invite);
        ButterKnife.bind(this);


        try {
            kakaoLink = KakaoLink.getKakaoLink(this);
            kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

            kakaoTalkLinkMessageBuilder.addText(text);

        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }



    }

    @OnClick(R.id.kakao_invite)
    public void inviteByKakao(){
        try {
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);
        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.group_id_copyBtn)
    public void copyId(){
        Context context = getApplicationContext();
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("a", groupId);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(context, "그룹 id가 복사되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
