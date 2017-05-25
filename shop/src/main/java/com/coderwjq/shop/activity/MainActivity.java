package com.coderwjq.shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.coderwjq.shop.R;

public class MainActivity extends AppCompatActivity {

    public static void invoke(Activity srcActivity, boolean finishSelf) {
        srcActivity.startActivity(new Intent(srcActivity, MainActivity.class));
        if (finishSelf) {
            srcActivity.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
