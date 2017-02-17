package com.google.slashb410.exgroup.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.slashb410.exgroup.R;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetPlanetActivity extends AppCompatActivity {

    @BindView(R.id.planet_to_kakao)
    Button toKakaoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_planet);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.planet_to_kakao)
    public void toKakao(){
        KakaoLink kakaoLink = null;
        KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder = null;
        String text= "000님이 lv4 행성을 획득하였습니다!\n함께하는 다이어트 친구랑운동에서 확인하세요!+링크";

        try {
            kakaoLink = KakaoLink.getKakaoLink(this);
            kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

            kakaoTalkLinkMessageBuilder.addText(text);
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);


        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }
    }
}
