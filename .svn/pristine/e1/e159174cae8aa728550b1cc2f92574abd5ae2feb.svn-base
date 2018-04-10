package com.xi6666.view.dialog;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.SuperFrgm;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.databean.AddOilPopuBean;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

public class ReceiveSuccessFrg extends SuperFrgm {


    @BindView(R.id.txt_receive_success_content)
    TextView mTxtReceiveSuccessContent;
    @BindView(R.id.txt_receive_success_know)
    TextView mTxtReceiveSuccessKnow;
    private String mPram;

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_receive_success;
    }

    @Override
    protected void init() {
         mTxtReceiveSuccessContent.setText(mPram);
        mTxtReceiveSuccessKnow.setOnClickListener(v -> {
            mActivity.finish();
        });
    }

    public static ReceiveSuccessFrg newInstance(String param1) {
        ReceiveSuccessFrg fragment = new ReceiveSuccessFrg();
        Bundle args = new Bundle();
        args.putString("pram", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPram = getArguments().getString("pram");
        }


    }



}
