package com.google.slashb410.exgroup.db;

/**
 * Created by Tacademy on 2017-02-09.
 */

public class E {

    public static class KEY {
        public static final int GROUP_MAX = 5;
        public static final String STORAGE_KEY = "pref";
        public static final int GROUP_TERM_MIN = 7;
        public static final int GROUP_TERM_MAX = 30;

        //----fake data
        public static final String[] GROUPS ={"소담이네", "혜원이네", "연정이네", "승옥이네"};
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
