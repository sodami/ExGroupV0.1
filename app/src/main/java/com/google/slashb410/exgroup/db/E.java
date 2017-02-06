package com.google.slashb410.exgroup.db;

/**
 * Created by Tacademy on 2016-12-29.
 */

public class E {
    public static class KEY {
        public static final String USERINFO = "userInfo";
        public static final String MAIN_AD = "app_init_ad_url";
        public static final String STORAGE_KEY = "pref";
        public static final String TODAY_OK_KEY = "today_ok";
        public static final String TODAY_SAVE_DATE_FORMAT = "yyyy-MM-dd";
        public static final String EMERGENCY_KEY = "EMERGENCY_MSG";
        public static final String REAL_DOMAIN_KEY = "REAL_DOMAIN";
        // 테스트 도메인
        public static final String TEST_DOMAIN_KEY = "TEST_DOMAIN";
        // 테스트인지 아닌지
        public static final String TEST_MODE_KEY = "TEST_MODE";
    }

    public static class NET {
        public static String REAL_DOMAIN;
        public static String TEST_DOMAIN;
        public static String USE_DOMAIN;
        public static boolean TEST_MODE;

        public static final String API_GET_EPLLIST = "/page/";
        //서버 주소 => firebase로 대체
    }
}
