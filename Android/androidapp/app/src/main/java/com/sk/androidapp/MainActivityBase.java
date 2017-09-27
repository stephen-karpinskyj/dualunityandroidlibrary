package com.sk.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sk.androidlibraryb.UnityPlayerActivityB;

import java.io.File;

/**
 * Created by jeff on 28/09/17.
 */

public class MainActivityBase extends Activity {

    private static final String TAG = "MainActivityBase";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.example);

        Log.d(TAG, "onCreate");

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(intent);

    }
}
