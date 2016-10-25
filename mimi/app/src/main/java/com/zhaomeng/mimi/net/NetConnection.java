package com.zhaomeng.mimi.net;

import android.database.CursorJoiner;
import android.os.AsyncTask;
import android.util.Log;

import com.zhaomeng.mimi.Configer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by wlk on 2016/10/24.
 */
public class NetConnection {
    public NetConnection(final String url,
                         final HttpMethod1 method,
                         final SuccessCallBack successCallBack,
                         final FailCallback failCallback,
                         final String...kvs){

        new AsyncTask<Void,Void,String>(){


            @Override
            protected String doInBackground(Void... params) {
                StringBuffer paramStr = new StringBuffer();
                for(int i =0 ;i < kvs.length ;i+=2){
                    paramStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                }
                try {
                    URLConnection uc ;
                    switch (method){
                        case POST :
                            uc = new URL(url).openConnection();
                            uc.setDoOutput(true);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Configer.CHARSET));
                            bw.write(paramStr.toString());
                            break;
                        default:
                            uc = new URL(url+"?"+paramStr.toString()).openConnection();
                            break;
                    }

                    Log.i("haha",uc.getURL()+"");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream(),Configer.CHARSET));
                    String  line = null;
                    StringBuffer result = new StringBuffer();
                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    return result.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s != null){
                    if(successCallBack != null) {
                        successCallBack.onSuccess(s);
                    }
                }else{
                    if(failCallback != null){
                        failCallback.onFail();
                    }
                }
                super.onPostExecute(s);
            }
        };
    }
    public static  interface  SuccessCallBack{
        void onSuccess(String result);
    }
    public static interface  FailCallback{
        void onFail();
    }
}
