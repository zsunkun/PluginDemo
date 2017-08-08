package sunkun.pluginimpl.base;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Constructor;

/**
 * Created by sunkun on 2017/4/5.
 */

public class ProxyHelper {
    private static final String TAG = "ProxyHelper";

    private ProxyActivity mActivity;
    private String mPackageName;
    private String mClassName;
    private PluginInfo mPluginInfo;
    private AssetManager mAssetManager;
    private Resources mResources;


    public ProxyHelper(ProxyActivity activity) {
        mActivity = activity;
    }

    public void init(String packageName, String className) {
        mPackageName = packageName;
        mClassName = className;
        mPluginInfo = PluginUtils.getIns(mActivity).getPluginInfo(mPackageName);
        if (mPluginInfo == null)
            return;
        mAssetManager = mPluginInfo.assetManager;
        mResources = mPluginInfo.resources;
    }

    public ClassLoader getClassLoader() {
        return mPluginInfo.classLoader;
    }

    public AssetManager getAssets() {
        return mAssetManager;
    }

    public Resources getResources() {
        return mResources;
    }

    public PluginInfo getPluginInfo() {
        return mPluginInfo;
    }

    public void handlePluginActivityOnCreate() {
        try {
            Class<?> loadClass = getClassLoader().loadClass(mClassName);
            Constructor<?> localConstructor = loadClass.getConstructor(new Class[]{});
            Object instance = localConstructor.newInstance(new Object[]{});
            PluginAbleActivity pluginAbleActivity = (PluginAbleActivity) instance;
            mActivity.bindPluginActivity(pluginAbleActivity);
            pluginAbleActivity.bindProxyActivity(mActivity, mPluginInfo);
            pluginAbleActivity.onCreate(new Bundle());
        } catch (Exception e) {
            Log.e(TAG, "" + e.getCause() + " " + e.getMessage());
        }
    }
}
