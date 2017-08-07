package com.example.yzy.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yzyinject.Click;
import com.example.yzyinject.ContentView;
import com.example.yzyinject.InjectUtils;
import com.example.yzyinject.ViewInject;

/**
 * Created by yzy on 17-8-7.
 */
@ContentView(R.layout.fragment_layout)
public class MyFragment extends Fragment {
    @ViewInject(value = R.id.fragment_tv,text = "我是fragment")
    TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return InjectUtils.injectFragment(this);
    }

    @Click({R.id.fragment_tv,R.id.fragment_bt})
    public void onClick(View pView){
        switch(pView.getId()){
            case R.id.fragment_tv:
                Log.e("yzy", "onClick: tv----------");
                break;
            case R.id.fragment_bt:
                Log.e("yzy", "onClick: bt----------");
                break;
            default:
        }
    }
}
