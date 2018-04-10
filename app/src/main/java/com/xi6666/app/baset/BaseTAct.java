package com.xi6666.app.baset;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.xi6666.app.ImmerseBaseAct;

import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2017/1/16.
 */

public abstract class BaseTAct<P extends BaseTPresenter, M extends BaseTModel> extends ImmerseBaseAct {
    public P mPresenter;
    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.getLayoutId());
        ButterKnife.bind(this);
        mPresenter = InstanceUtil.getInstance(this, 0);
        if (this instanceof BaseTView) {
            mPresenter.setModelAndView(InstanceUtil.getInstance(this, 1), this);
        }
        this.init();
    }

    public abstract int getLayoutId();

    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDetached();
    }

    public void showToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

    public void cancleToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
