package com.sk.androidlibrarya;


import android.content.Context;
import android.content.ContextWrapper;

/**
 * Created by tksh on 19/07/17.
 */
public class CustomContextWrapper extends ContextWrapper {

    private String packageCodePath;

    public CustomContextWrapper(Context base, String packageCodePath) {
        super(base);

        this.packageCodePath = packageCodePath;
    }

    @Override
    public String getPackageCodePath() {
        return packageCodePath;
    }
}
