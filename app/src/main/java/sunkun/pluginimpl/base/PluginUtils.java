package sunkun.pluginimpl.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

/**
 * Created by sunkun on 2017/4/5.
 */

public class PluginUtils {
    private static volatile PluginUtils mIns;

    private Context mContext;
    private HashMap<String, PluginInfo> mAllPluginInfo = new HashMap<>();

    private File mLibPath;

    private PluginUtils(Context context) {
        mContext = context.getApplicationContext();
        mLibPath = mContext.getDir("pluginLibs", Context.MODE_PRIVATE);
    }

    public static PluginUtils getIns(Context context) {
        if (mIns == null) {
            synchronized (PluginUtils.class) {
                if (mIns == null)
                    mIns = new PluginUtils(context);
            }
        }
        return mIns;
    }

    public PluginInfo loadPlugin(String pluginPath) {
        PackageInfo packageInfo = mContext.getPackageManager().
                getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);
        if (packageInfo == null)
            return null;
        PluginInfo info = mAllPluginInfo.get(packageInfo.packageName);
        if (info == null) {
            info = initPluginInfo(packageInfo, pluginPath);
            mAllPluginInfo.put(packageInfo.packageName, info);
        }
        return info;
    }

    public PluginInfo getPluginInfo(String pkgName) {
        return mAllPluginInfo.get(pkgName);
    }

    private PluginInfo initPluginInfo(PackageInfo packageInfo, String path) {
        DexClassLoader loader = getDexClassLoader(path);
        AssetManager assetManager = getAssertManager(path);
        Resources resources = getResources(assetManager);
        PluginInfo info = new PluginInfo(loader, resources, packageInfo);
        return info;
    }

    private AssetManager getAssertManager(String path) {
        try {
            AssetManager manager = AssetManager.class.newInstance();
            Method addAssetPath = manager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.invoke(manager, path);
            return manager;
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
        }
        return null;
    }

    private Resources getResources(AssetManager assetManager) {
        Resources originResources = mContext.getResources();
        return new Resources(assetManager, originResources.getDisplayMetrics(), originResources.getConfiguration());
    }

    private DexClassLoader getDexClassLoader(String path) {
        File optFile = mContext.getDir("dex", Context.MODE_PRIVATE);
        DexClassLoader loader = new DexClassLoader(path, optFile.getAbsolutePath(), mLibPath.getAbsolutePath(), mContext.getClassLoader());
        return loader;
    }
}
