package com.zhaomeng.mimi;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wlk on 2016/10/24.
 */
public class Configer {

    public static final String ACTION_GET_CODE = "send_pass";
    public static  final  String KEY_ACTION ="action";
    public static final String ACTION_PHONE = "send_pass";
    public static  final  String KEY_PHONE ="phone";
    public static  final  String KEY_STATUS ="status";
    public static final String  SERVER_URL= "ffffffffffffff";
    public static final int RESULT_STATUS_SUCCESS = 1;
    public static final int RESULT_STATUS_FAIL = 0;
    public static final int RESULT_STATUS_OUTLINE = 2;
    public static final  String APP_ID = "com.zhaomeng.mimi";
    public static  final  String KEY_TOKEN ="token";
    public static final String CHARSET = "utf-8" ;

    public static  String getCacheToken(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_TOKEN,null);

    }

    public static void cacheToken(Context context,String token){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN,token);
        e.commit();

    }
}
