package sunkun.pluginimpl.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by sunkun on 2017/4/5.
 */
public class ProxyActivity extends Activity {

    private ProxyHelper mProxyHelper = new ProxyHelper(this);
    private PluginAbleActivity mPluginActivity;
    private Resources mHostResources;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        mHostResources = newBase.getResources();
        mProxyHelper.init(Jumper.getPluginPackageName(), Jumper.getTargetClassName());
        replaceResources(newBase);
        Jumper.resetPackageName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProxyHelper.handlePluginActivityOnCreate();
    }

    public void bindPluginActivity(PluginAbleActivity activity) {
        mPluginActivity = activity;
    }

    private void replaceResources(Context oldContext) {
        try {
            Field filed = oldContext.getClass().getDeclaredField("mResources");
            filed.setAccessible(true);
            filed.set(oldContext, getResources());
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }

    @Override
    public AssetManager getAssets() {
        return mProxyHelper.getAssets() == null ? super.getAssets() : mProxyHelper.getAssets();
    }

    @Override
    public Resources getResources() {
        return mProxyHelper.getResources() == null ? super.getResources() : mProxyHelper.getResources();
    }

    public Resources getHostResources() {
        return mHostResources;
    }

    @Override
    public ClassLoader getClassLoader() {
        return mProxyHelper.getClassLoader();
    }


    /****************************** 重载Activity相关方法，以满足Plugin的调用 ********************************/

    /**
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).
                inflate(getResources().getLayout(layoutResID), null);
        setContentView(view);
    }


    /******************* 复写Activity相关事件，将事件通知给Plugin，使Plugin具有生命周期*********************/


    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPluginActivity.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        mPluginActivity.onStart();
        super.onStart();
    }

    @Override
    protected void onRestart() {
        mPluginActivity.onRestart();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        mPluginActivity.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mPluginActivity.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mPluginActivity.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mPluginActivity.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mPluginActivity.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mPluginActivity.onRestoreInstanceState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        mPluginActivity.onNewIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    public void onBackPressed() {
        mPluginActivity.onBackPressed();
        super.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return mPluginActivity.onTouchEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        return mPluginActivity.onKeyUp(keyCode, event);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
        mPluginActivity.onWindowAttributesChanged(params);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mPluginActivity.onWindowFocusChanged(hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mPluginActivity.onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mPluginActivity.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}
