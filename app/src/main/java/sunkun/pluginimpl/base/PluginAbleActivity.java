package sunkun.pluginimpl.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by sunkun on 2017/4/5.
 */

public class PluginAbleActivity extends Activity {

    protected ProxyActivity mProxyActivity;
    private PluginInfo mPluginInfo;

    public void bindProxyActivity(ProxyActivity activity, PluginInfo info) {
        mProxyActivity = activity;
        mPluginInfo = info;
    }

    protected void startPluginActivity(Intent intent) {
        intent.putExtra(Jumper.EXTRA_PLUGIN_PACKAGE_NAME, getPackageName());
        intent.putExtra(Jumper.EXTRA_TARGET_CLASS_NAME, intent.getComponent().getClassName());
        intent.setComponent(new ComponentName(mProxyActivity.getPackageName(), ProxyActivity.class.getName()));
        Jumper.startPluginActivity(mProxyActivity == null ? this : mProxyActivity, intent);
    }

    @Override
    public void setContentView(View view) {
        if (mProxyActivity == null) {
            super.setContentView(view);
        } else {
            mProxyActivity.setContentView(view);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (mProxyActivity == null) {
            super.setContentView(view, params);
        } else {
            mProxyActivity.setContentView(view, params);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mProxyActivity == null) {
            super.setContentView(layoutResID);
        } else {
            mProxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        if (mProxyActivity == null) {
            super.addContentView(view, params);
        } else {
            mProxyActivity.addContentView(view, params);
        }
    }

    @Override
    public View findViewById(int id) {
        if (mProxyActivity == null) {
            return super.findViewById(id);
        } else {
            return mProxyActivity.findViewById(id);
        }
    }

    @Override
    public Intent getIntent() {
        if (mProxyActivity == null) {
            return super.getIntent();
        } else {
            return mProxyActivity.getIntent();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (mProxyActivity == null) {
            return super.getClassLoader();
        } else {
            return mProxyActivity.getClassLoader();
        }
    }

    @Override
    public Resources getResources() {
        if (mProxyActivity == null) {
            return super.getResources();
        } else {
            return mProxyActivity.getResources();
        }
    }

    public Resources getHostResources() {
        if (mProxyActivity == null) {
            return super.getResources();
        } else {
            return mProxyActivity.getHostResources();
        }
    }

    @Override
    public String getPackageName() {
        if (mProxyActivity == null) {
            return super.getPackageName();
        } else {
            return mPluginInfo.packageName;
        }
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        if (mProxyActivity == null) {
            return super.getLayoutInflater();
        } else {
            return mProxyActivity.getLayoutInflater();
        }
    }

    @Override
    public MenuInflater getMenuInflater() {
        if (mProxyActivity == null) {
            return super.getMenuInflater();
        } else {
            return mProxyActivity.getMenuInflater();
        }
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        if (mProxyActivity == null) {
            return super.getSharedPreferences(name, mode);
        } else {
            return mProxyActivity.getSharedPreferences(name, mode);
        }
    }

    @Override
    public Context getApplicationContext() {
        if (mProxyActivity == null) {
            return super.getApplicationContext();
        } else {
            return mProxyActivity.getApplicationContext();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (mProxyActivity == null) {
            return super.getWindowManager();
        } else {
            return mProxyActivity.getWindowManager();
        }
    }

    @Override
    public Window getWindow() {
        if (mProxyActivity == null) {
            return super.getWindow();
        } else {
            return mProxyActivity.getWindow();
        }
    }

    @Override
    public Object getSystemService(String name) {
        if (mProxyActivity == null) {
            return super.getSystemService(name);
        } else {
            return mProxyActivity.getSystemService(name);
        }
    }

    @Override
    public void finish() {
        if (mProxyActivity == null) {
            super.finish();
        } else {
            mProxyActivity.finish();
        }
    }

//    @Override
//    public void onPluginCreate(Bundle savedInstanceState) {
//
//    }
//
//    @Override
//    public void onPluginStart() {
//
//    }
//
//    @Override
//    public void onPluginRestart() {
//
//    }
//
//    @Override
//    public void onPluginActivityResult(int requestCode, int resultCode, Intent data) {
//
//    }
//
//    @Override
//    public void onPluginResume() {
//
//    }
//
//    @Override
//    public void onPluginPause() {
//
//    }
//
//    @Override
//    public void onPluginStop() {
//
//    }
//
//    @Override
//    public void onPluginDestroy() {
//
//    }
//
//    @Override
//    public void onPluginSaveInstanceState(Bundle outState) {
//
//    }
//
//    @Override
//    public void onPluginNewIntent(Intent intent) {
//
//    }
//
//    @Override
//    public void onPluginRestoreInstanceState(Bundle savedInstanceState) {
//
//    }
//
//    @Override
//    public boolean onPluginTouchEvent(MotionEvent event) {
//        return false;
//    }
//
//    @Override
//    public boolean onPluginKeyUp(int keyCode, KeyEvent event) {
//        return false;
//    }
//
//    @Override
//    public void onPluginWindowFocusChanged(boolean hasFocus) {
//
//    }
//
//    @Override
//    public void onPluginBackPressed() {
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mProxyActivity == null)
            super.onCreate(savedInstanceState);
//        onPluginCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        if (mProxyActivity == null)
            super.onStart();
//        onPluginStart();
    }

    @Override
    protected void onRestart() {
        if (mProxyActivity == null)
            super.onRestart();
//        onPluginRestart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mProxyActivity == null)
            super.onActivityResult(requestCode, resultCode, data);
//        onPluginActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        if (mProxyActivity == null)
            super.onResume();
//        onPluginResume();
    }

    @Override
    protected void onPause() {
        if (mProxyActivity == null)
            super.onPause();
//        onPluginPause();
    }

    @Override
    protected void onStop() {
        if (mProxyActivity == null)
            super.onStop();
//        onPluginStop();
    }

    @Override
    protected void onDestroy() {
        if (mProxyActivity == null)
            super.onDestroy();
//        onPluginDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mProxyActivity == null)
            super.onSaveInstanceState(outState);
//        onPluginSaveInstanceState(outState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (mProxyActivity == null)
            super.onNewIntent(intent);
//        onPluginNewIntent(intent);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (mProxyActivity == null)
            super.onRestoreInstanceState(savedInstanceState);
//        onPluginRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mProxyActivity == null)
            return super.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mProxyActivity == null)
            return super.onKeyUp(keyCode, event);
        return false;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (mProxyActivity == null)
            super.onWindowFocusChanged(hasFocus);
//        onPluginWindowFocusChanged(hasFocus);
    }

    @Override
    public void onBackPressed() {
        if (mProxyActivity == null)
            super.onBackPressed();
//        onPluginBackPressed();
    }
}
