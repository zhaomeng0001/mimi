package com.zhaomeng.mimi.activity;

import android.app.ListActivity;
import android.os.Bundle;

import com.zhaomeng.mimi.R;

/**
 * Created by wlk on 2016/10/25.
 */
public class MessageActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
    }
}
