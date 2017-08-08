package sunkun.host;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import sunkun.pluginimpl.base.Jumper;
import sunkun.pluginimpl.base.PluginUtils;
import sunkun.pluginimpl.base.ProxyActivity;
import sunkun.pluginimpl.test.TestThemeActivity;

/**
 * Created by sunkun on 2017/4/5.
 */

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mLoadBtn;
    private Button mStartBtn;
    private Button mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadBtn = (Button) findViewById(R.id.btn_load);
        mStartBtn = (Button) findViewById(R.id.btn_start);
        mLoadBtn.setOnClickListener(this);
        mStartBtn.setOnClickListener(this);
        mTest = (Button) findViewById(R.id.btn_test);
        mTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
                PluginUtils.getIns(this).loadPlugin(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myPatch.apk");
                break;
            case R.id.btn_start:
                Intent intent = new Intent(this, ProxyActivity.class);
                intent.putExtra(Jumper.EXTRA_PLUGIN_PACKAGE_NAME, "sunkun.plugin");
                intent.putExtra(Jumper.EXTRA_TARGET_CLASS_NAME, "sunkun.plugin.MainActivity");
                Jumper.startPluginActivity(this, intent);
                break;
            case R.id.btn_test:
                startActivity(new Intent(this, TestThemeActivity.class));
                break;
        }
    }
}
