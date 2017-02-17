package com.google.slashb410.exgroup.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.slashb410.exgroup.db.StorageHelper;

/**
 * FCM 고유 토큰, 아이디를 발급하는 서비스
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService
{
    private static final String TAG = "MyFirebaseIIDService";
    // [START refresh_token]
    // 토큰이 갱신되면 호출된다.
    @Override
    public void onTokenRefresh() {
        // 앱을 최초로 깔앗거나, 삭제후 다시 깔앗거나 이런 경우, 혹은 변경되었거나
        // 1회 발급이 된다. (최초, 변경되면)
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        StorageHelper.getInstance().setString(this, "token", refreshedToken);
        // 로컬에 저장?
        Log.i("FCM", "Refreshed token: " + refreshedToken);
        // 회원가입후
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]
    private void sendRegistrationToServer(final String token) {
        // 회원가입이 되어있다면 서버로 보내서, 수정!! => fb database
        // 별 숫자 변경 및 어던 사람이 눌렀는지 여부 처리
        // 로그아웃을 할경우 회원가입여부를 확인할수 없다.
        // UID는 다로 저장해서 사용하는편이 나을지도..
//        if( FirebaseAuth.getInstance().getCurrentUser()          != null &&
//            FirebaseAuth.getInstance().getCurrentUser().getUid() != null     ){
//            Log.i("FCM", "토큰이 변경되었으니 디비값을 수정해라");
//            DatabaseReference userReference
//                    = FirebaseDatabase.getInstance().getReference()
//                    .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//            // 수정을 위한 트렌젝션
//            userReference.runTransaction(new Transaction.Handler() {
//                // 트렌젝션 수행
//                @Override
//                public Transaction.Result doTransaction(MutableData mutableData) {
//                    User user = mutableData.getValue(User.class);
//                    if( user == null ){
//                        return Transaction.success(mutableData);
//                    }
//                    user.setToken(token);
//                    // 변경 내용을 다시 삽입
//                    mutableData.setValue(user);
//                    Log.i("FCM", "토큰 수정 완료");
//                    return Transaction.success(mutableData);
//                }
//                @Override
//                public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
//                    // 완료
//                }
//            });

        }
}

