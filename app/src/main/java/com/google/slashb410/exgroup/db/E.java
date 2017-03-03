package com.google.slashb410.exgroup.db;

import com.google.slashb410.exgroup.model.group.group.ShotData;
import com.google.slashb410.exgroup.model.group.WriteData;

/**
 * Created by Tacademy on 2017-02-09.
 */

public class E {

    public static class KEY {
        public static final int GROUP_MAX = 5;
        public static final String STORAGE_KEY = "pref";
        public static final int GROUP_TERM_MIN = 7;
        public static final int GROUP_TERM_MAX = 30;

        public static final String LASTDATE_KEY = "lastdate";
        public static final String SERVER_KEY = "SERVER_ADDRESS";
        public static final String PEM_KEY = "PEM_ADDRESS";

        public static int USER_ID = 0;
        public static String USER_NAME = "";
        public static String USER_NICKNAME = "";

        //----fake data
        public static final WriteData new_write = new WriteData();
        public static ShotData shotData = new ShotData();
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
