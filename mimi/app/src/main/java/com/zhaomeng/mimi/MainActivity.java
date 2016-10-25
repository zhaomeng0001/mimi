package com.zhaomeng.mimi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mob.commons.SMSSDK;
import com.zhaomeng.mimi.activity.AtyLogin;
import com.zhaomeng.mimi.activity.TimeLine;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tokrn = Configer.getCacheToken(this);
        startActivity(new Intent(MainActivity.this,TimeLine.class));
        if(tokrn != null){
            Intent intent = new Intent(MainActivity.this, TimeLine.class);
            intent.putExtra(Configer.APP_ID,tokrn);
            startActivity(intent);
        }else{
            startActivity(new Intent(this, AtyLogin.class));
        }




    }
}
