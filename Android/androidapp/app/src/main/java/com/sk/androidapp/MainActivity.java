package com.sk.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.sk.androidlibrarya.UnityPlayerActivityA;
import com.sk.androidlibraryb.UnityPlayerActivityB;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.example);

        Log.d(TAG, "onCreate");

        try {
            this.getContentRoot(new File(UnityPlayerActivityB.APK_PATH));
        } catch (UnsupportedOperationException ex) {
            Log.e(TAG, ex.getMessage());
        }
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

    private void enterUnity(Class unityActivity, String contentRootExtraName, String contentRoot, String returnIntentExtraName) {

        Intent intent = new Intent(this, unityActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        intent.putExtra(contentRootExtraName, contentRoot);

        Intent returnIntent = new Intent(this, MainActivity.class);
        returnIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra(returnIntentExtraName, returnIntent);

        startActivity(intent);
    }

    //set in the xml layout file
    public void onEnterUnityAButtonClick(View view) {
        Log.d(TAG, "enterUnityA");

        String contentRoot = this.getContentRoot(new File(UnityPlayerActivityA.APK_PATH));
        enterUnity(UnityPlayerActivityA.class, UnityPlayerActivityA.INTENT_EXTRA_CONTENT_ROOT, contentRoot, UnityPlayerActivityA.INTENT_EXTRA_RETURN_INTENT);
    }

    //set in the xml layout file
    public void onEnterUnityBButtonClick(View view) {
        Log.d(TAG, "enterUnityB");

        String contentRoot = this.getContentRoot(new File(UnityPlayerActivityB.APK_PATH));
        enterUnity(UnityPlayerActivityB.class, UnityPlayerActivityB.INTENT_EXTRA_CONTENT_ROOT, contentRoot, UnityPlayerActivityB.INTENT_EXTRA_RETURN_INTENT);
    }

    private String getContentRoot(File requiredFile) {
        File[] contentRoots = ContextCompat.getExternalFilesDirs(this, null);
        Collections.reverse(Arrays.asList(contentRoots)); // HACK: Prefer SD card

        for (File root : contentRoots) {
            if (root.canRead()) {
                File file = new File(root.getAbsoluteFile(), requiredFile.getPath());

                if (file.exists() && file.canRead()) {
                    return root.getAbsolutePath();
                }
            }
        }

        throw new UnsupportedOperationException("Cannot find '" + requiredFile.getPath() + "' in any external files directory, ensure it has been added");
    }
}
