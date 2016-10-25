package com.zhaomeng.mimi.net;

import com.zhaomeng.mimi.Configer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wlk on 2016/10/25.
 */
public class GetCode {

    public GetCode(String phone, final SuccessCallBack successCallBack, final FailCallBack failCallback){

        new NetConnection(Configer.SERVER_URL, HttpMethod1.POST, new NetConnection.SuccessCallBack() {
            @Override
            public void onSuccess(String result) {

                if(successCallBack != null){
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        switch (jsonObject.getInt(Configer.KEY_STATUS)){
                            case Configer.RESULT_STATUS_SUCCESS:
                                if(successCallBack != null){
                                    successCallBack.onSuccess();
                                }
                                break;
                            default:
                                failCallback.onFail();
                                break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        if(failCallback != null){
                            failCallback.onFail();
                        }
                    }
                }

            }
        },new NetConnection.FailCallback(){
            @Override
            public void onFail() {
                if(failCallback != null){
                    failCallback.onFail();
                }
            }
        },Configer.KEY_ACTION,Configer.ACTION_GET_CODE,Configer.KEY_PHONE,Configer.ACTION_PHONE);
    }

    public static  interface SuccessCallBack{

        void onSuccess();
    }
    public static  interface FailCallBack{

        void onFail();
    }
}
