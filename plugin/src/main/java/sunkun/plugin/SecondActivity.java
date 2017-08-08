package sunkun.plugin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sunkun.pluginimpl.base.PluginAbleActivity;

/**
 * Created by sunkun on 2017/4/6.
 */

public class SecondActivity extends PluginAbleActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button = (Button) findViewById(R.id.btn_finish);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_finish:
                finish();
                break;
        }
    }
}
