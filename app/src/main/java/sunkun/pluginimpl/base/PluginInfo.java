package sunkun.pluginimpl.base;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * Created by sunkun on 2017/4/5.
 */

public class PluginInfo {
    public String packageName;
    public String defaultActivity;
    public DexClassLoader classLoader;
    public AssetManager assetManager;
    public Resources resources;
    public PackageInfo packageInfo;

    public PluginInfo(DexClassLoader loader, Resources resources, PackageInfo packageInfo) {
        this.packageName = packageInfo.packageName;
        this.classLoader = loader;
        this.assetManager = resources.getAssets();
        this.resources = resources;
        this.packageInfo = packageInfo;

        defaultActivity = getDefaultActivity();
    }

    private final String getDefaultActivity() {
        if (packageInfo.activities != null && packageInfo.activities.length > 0) {
            return packageInfo.activities[0].name;
        }
        return "";
    }
}
