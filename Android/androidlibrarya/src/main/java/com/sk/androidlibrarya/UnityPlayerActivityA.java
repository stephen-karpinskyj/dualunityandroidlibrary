package com.sk.androidlibrarya;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

import com.unity3d.player.UnityPlayer;

import java.io.File;

/**
 * Created by stephenkarpinskyj on 15/02/17.
 */

public class UnityPlayerActivityA extends Activity {
    private static final String TAG = "UnityPlayerActivityA";

    public static final String APK_PATH = "UnityProjectA.apk";
    public static final String INTENT_EXTRA_CONTENT_ROOT = "EXTRA_CONTENT_ROOT";
    public static final String INTENT_EXTRA_RETURN_INTENT = "EXTRA_RETURN_INTENT";

    public static Activity currentActivity; // Accessed by Unity script

    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code

    private CustomContextWrapper customContext;

    // Setup activity layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        currentActivity = this;

        getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy

        String contentRoot = getIntent().getStringExtra(INTENT_EXTRA_CONTENT_ROOT);
        File apkFile = new File(contentRoot, APK_PATH);

        customContext = new CustomContextWrapper(this, apkFile.getAbsolutePath()); // HACK: Hard-coded apk with Unity data in content folder

        mUnityPlayer = new UnityPlayer(customContext); // Override context
        setContentView(mUnityPlayer);
        mUnityPlayer.requestFocus();

        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }

    // Quit Unity
    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");

        mUnityPlayer.quit();
        super.onDestroy();
    }

    // Pause Unity
    @Override
    protected void onPause() {
        super.onPause();
        mUnityPlayer.pause();

        Log.d(TAG, "onPause");
    }

    // Resume Unity
    @Override
    protected void onResume() {
        super.onResume();
        mUnityPlayer.resume();

        UnityPlayer.UnitySendMessage("UI", "Android_SetData", "DataA");

        overridePendingTransition(0, 0);

        Log.d(TAG, "onResume");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
    }

    // This ensures the layout will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    /*API12*/
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    // Called by Unity script
    private void unity_exit(int count) {
        Log.d(TAG, "unity_exit, count=" + count);

        runOnUiThread(new Runnable() {
            public void run() {
                Intent returnIntent = getIntent().getParcelableExtra(INTENT_EXTRA_RETURN_INTENT);
                startActivity(returnIntent);
            }
        });
    }
}
