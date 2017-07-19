package com.sk.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sk.androidlibrarya.UnityPlayerActivityA;
import com.sk.androidlibraryb.UnityPlayerActivityB;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.example);

        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");

        overridePendingTransition(0, 0);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
    }

    private void enterUnity(Class unityActivity, String returnIntentExtraName) {

        Intent intent = new Intent(this, unityActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        Intent returnIntent = new Intent(this, MainActivity.class);
        returnIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra(returnIntentExtraName, returnIntent);

        startActivity(intent);
    }

    //set in the xml layout file
    public void onEnterUnityAButtonClick(View view) {
        Log.d(TAG, "enterUnityA");

        enterUnity(UnityPlayerActivityA.class, UnityPlayerActivityA.INTENT_EXTRA_RETURN_INTENT);
    }

    //set in the xml layout file
    public void onEnterUnityBButtonClick(View view) {
        Log.d(TAG, "enterUnityB");

        enterUnity(UnityPlayerActivityB.class, UnityPlayerActivityB.INTENT_EXTRA_RETURN_INTENT);
    }
}
