package com.bpom.app.utils;

import com.bpom.app.BuildConfig;
import com.pixplicity.easyprefs.library.Prefs;

public class Cons {

    //api
    public static final String BASE_URL = BuildConfig.SERVER_URL;
    public static final String BASE_API = BASE_URL + "index.php/api/";
    public static final String BASE_INDEX = BASE_URL + "index.php/";
    public static final String API_ANDROID = BASE_API + "/android/";
    public static final String API_LOGIN = BASE_API + "android/login";
    public static final String API_AREA = BASE_INDEX + "area?b_id=";
    public static final String API_HEADER = BASE_INDEX + "questioner?b_survey_id=";
    public static final String API_RESPONDEN = BASE_INDEX + "responden";
    public static final String API_QUESTION = BASE_INDEX + "question?b_id_questioner=";
    public static final String API_ANSWER = BASE_INDEX + "option?b_question_id=";

    //api-properties
    public static final String API_HEADER_K = "Content-Type";
    public static final String API_HEADER_V = "application/x-www-form-urlencoded";

    //field
    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_ID = "USER_ID";
    public static final String USER_KODE = "USER_KODE";
    public static final String USER_PERSON = "USER_PERSON";
    public static final String USER_LEVEL = "USER_LEVEL";
    public static final String USER_EMAIL = "USER_EMAIL";
    public static final String NASABAH_NOREK = "no_rekening";
    public static final String NASABAH_ID = "id_nasabah";
    public static final String SELL_ID = "f_kd_penjualan";
    public static final String STOCKS_ID = "f_kd_barang";
    public static final String STOCKS_READY = "f_stocks_ready";
    public static final String STOCKS_NAME = "f_stocks_name";
    public static final String STOCKS_PRICE = "f_stocks_price";

    //get prefs
//    public static final String gID = Prefs.getString(USER_ID,null);
    public static final String gID = "37";

}
