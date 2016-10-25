package com.zhaomeng.mimi.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

import com.zhaomeng.mimi.R;

/**
 * Created by wlk on 2016/10/24.
 */
public class TimeLine extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
    }
}
