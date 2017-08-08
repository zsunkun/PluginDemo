package sunkun.plugin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import sunkun.pluginimpl.base.PluginAbleActivity;
import sunkun.pluginimpl.test.RProxxTest;


/**
 * Created by sunkun on 2017/4/5.
 */

public class MainActivity extends PluginAbleActivity implements View.OnClickListener {

    private ImageView imageView1;
    private ImageView imageView2;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = (ImageView) findViewById(R.id.image1);
        imageView1.setImageResource(R.drawable.pin_noparking_blue);
        imageView1.setOnClickListener(this);
        mBtn = (Button) findViewById(R.id.btn_get_name);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.image1:
//                startPluginActivity(new Intent(mProxyActivity, SecondActivity.class));
//                break;
//        }
        if (v.getId() == R.id.image1) {
            startPluginActivity(new Intent(mProxyActivity, SecondActivity.class));
        } else if (v.getId() == R.id.btn_get_name) {
            Toast.makeText(mProxyActivity, getHostResources().getString(RProxxTest.string.app_name), Toast.LENGTH_SHORT).show();
        }
    }

    private View generateContentView(final Context context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.pin_noparking_blue);
        layout.addView(imageView, LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(context);
        textView.setText("Hello, World");
        textView.setTextSize(30);
        layout.addView(textView, LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        return layout;
    }
}
