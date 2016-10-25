package com.zhaomeng.mimi.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhaomeng.mimi.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by wlk on 2016/10/24.
 */
public class AtyLogin extends Activity implements View.OnClickListener {

    private static String APP_KEY = "10900ea2c087c";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String APP_SECRET = "129d8b5cbc75678b87f3f90d55869b38";


    private EditText phone_text ;
    private EditText code_text;

    private Button get_code;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        init();
//        cn.smssdk.SMSSDK.getVerificationCode("86","13152089852");

    }

    private void init() {
        phone_text = (EditText) findViewById(R.id.edittext_phone);
        code_text = (EditText) findViewById(R.id.edittext_code);
        get_code = (Button) findViewById(R.id.btn_get_sms_code);
        login = (Button) findViewById(R.id.btn_login_in);

        get_code.setOnClickListener(this);
        login.setOnClickListener(this);

        //初始化init
        cn.smssdk.SMSSDK.initSDK(this,APP_KEY,APP_SECRET);
        //创建eventhandler
        EventHandler eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int i, int i1, Object o) {
                Message msg = new Message();
                msg.arg1 = i;
                msg.arg2 = i1;
                msg.obj = o;
                handler.sendMessage(msg);

            }
        };
        //注册回调接口
        cn.smssdk.SMSSDK.registerEventHandler(eventHandler);
    }

    int i = 30;
    @Override
    public void onClick(View v) {
        String phoneNum = phone_text.getText().toString();
        String code = code_text.getText().toString();
        switch (v.getId()){
            case R.id.btn_get_sms_code:
//                if(!judgePhoneNums(phoneNum)){
//
//                    return;
//                }
                cn.smssdk.SMSSDK.getVerificationCode("86",phoneNum);
                get_code.setClickable(false);
                get_code.setText("重新发送"+i+"s");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(;i>0;i--){
                        handler.sendEmptyMessage(-9);
                        if(i <= 0){
                            break;
                        }
                        try{
                           Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    handler.sendEmptyMessage(-8);
                }
            }).start();
                break;
            case R.id.btn_login_in:
                SMSSDK.submitVerificationCode("86",phoneNum,code);
                break;
        }

    }

    private boolean judgePhoneNums(String phoneNum) {
        if(phoneNum.length() == 11   ){
            return true;
        }
        return false;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == -9){
                get_code.setText("重新发送"+i+"s");
            }else if(msg.what == -8){
                get_code.setText("获取验证码");
                get_code.setClickable(true);
                i = 30;
            }else{
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;

                Log.i("haha",event+"--"+result);
                Log.i("haha",SMSSDK.RESULT_COMPLETE+"");
                Log.i("haha",SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE+"");
                if(result == SMSSDK.RESULT_COMPLETE){
                    if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }
    };

    @Override
    protected void onDestroy() {
        cn.smssdk.SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }
}
