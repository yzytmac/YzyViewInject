package com.example.yzy.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yzyinject.Click;
import com.example.yzyinject.ContentView;
import com.example.yzyinject.ViewInject;
import com.example.yzyinject.InjectUtils;
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.bt)
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InjectUtils.injectActivity(this);
        bt.setText("按钮1");

        MyFragment vFragment = new MyFragment();
        FragmentManager vManager = getFragmentManager();
        FragmentTransaction vTransaction = vManager.beginTransaction();
        vTransaction.add(R.id.frame,vFragment);
        vTransaction.commit();
    }

    @Click({R.id.bt,R.id.bt2})
    public void clickBt(View pView){
        switch (pView.getId()){
            case R.id.bt:
                Log.e("yzy", "clickBt1: ");
                break;
            case R.id.bt2:
                Log.e("yzy", "clickBt2: ");
                break;
            default:
        }
    }

}
