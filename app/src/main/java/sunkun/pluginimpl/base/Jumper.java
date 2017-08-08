package sunkun.pluginimpl.base;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunkun on 2017/4/6.
 */

public class Jumper {
    public static final String EXTRA_PLUGIN_PACKAGE_NAME = "extra_plugin_package_name";
    public static final String EXTRA_TARGET_CLASS_NAME = "extra_target_class_name";

    private static List<Runnable> mStartActivityActions = new ArrayList<>();
    private static String PLUGIN_PACKAGE_NAME = null;
    private static String PLUGIN_PAGE_NAME = null;

    public static synchronized void startPluginActivity(final Activity context, final Intent intent) {
        if (intent == null)
            return;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                Intent realIntent = new Intent(intent);
                PLUGIN_PACKAGE_NAME = intent.getStringExtra(EXTRA_PLUGIN_PACKAGE_NAME);
                PLUGIN_PAGE_NAME = intent.getStringExtra(EXTRA_TARGET_CLASS_NAME);
                context.startActivity(intent);
            }
        };
        mStartActivityActions.add(runnable);
        pickUpFirstToRun();
    }

//    public static synchronized void startPluginActivity(final Activity activity, final Intent intent) {
//        if (intent == null)
//            return;
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Intent realIntent = new Intent(intent);
//                realIntent.setClassName(info.packageName,intent.get)
//            }
//        }
//
//    }

    static synchronized void resetPackageName() {
        PLUGIN_PACKAGE_NAME = null;
        PLUGIN_PAGE_NAME = null;
        pickUpFirstToRun();
    }

    static String getPluginPackageName() {
        return PLUGIN_PACKAGE_NAME;
    }

    static String getTargetClassName() {
        return PLUGIN_PAGE_NAME;
    }

    private static void pickUpFirstToRun() {
        if (mStartActivityActions.isEmpty())
            return;
        if (PLUGIN_PACKAGE_NAME != null)
            return;
        mStartActivityActions.remove(0).run();
    }
}
