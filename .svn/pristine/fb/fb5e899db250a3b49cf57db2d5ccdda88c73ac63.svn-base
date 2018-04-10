package com.xi6666.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * Created by Mr_yang on 2016/10/18.
 */

/**
 * @author peng
 * @data 创建时间:2016/10/21
 * @desc fragment的上帝类
 */
public abstract class SuperFrgm extends Fragment {
    protected Activity mActivity;
    protected View mViewContent;

    /**
     * 获得全局的，防止使用getActivity()为空
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //防止fragment被多次加载
        if (mViewContent == null) {
            mViewContent = inflater.inflate(getLayoutResId(), container, false);
            ButterKnife.bind(this, mViewContent);
            init();
        }
        ViewGroup parent = (ViewGroup) mViewContent.getParent();
        if (parent != null) {
            parent.removeView(mViewContent);
        }
        return mViewContent;
    }

    public View getViewContent() {
        return mViewContent;
    }

    protected abstract int getLayoutResId();

    protected abstract void init();

}
