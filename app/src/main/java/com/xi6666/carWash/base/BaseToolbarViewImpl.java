package com.xi6666.carWash.base;

import android.view.View;

/**
 * 创建者 sunsun
 * 时间 16/10/31 下午5:08.
 * 个人公众号 ardays
 */
public interface BaseToolbarViewImpl {


    /**
     * 设置主页面
     */
    void setMainView(int resId);

    /**
     * 设置侵入式布局
     */
    void setBackgroundView(int resId);


    /**
     * 设置标题栏左边按钮图片
     * @param resId 按钮图片
     */
    void setToolbarLeftIcon(int resId);

    /**
     * 设置标题栏标题颜色
     */
    void setToolbarTitleColor(int color);

    /**
     * 设置标题栏右边按钮图片
     */
    void setToolbarRightIcon(int resId);

    /**
     * 设置标题栏右边文本
     */
    void setToolbarRightText(String text);
    /**
     * 设置标题栏右边颜色
     */
    void setToolbarRightColor(int resId);


    /**
     * 标题栏左边按钮点击回调事件
     */
    void onToolbarLeftClick(View view);


    /**
     * 标题栏右边按钮点击回调事件
     */
    void onToolbarRightClick(View view);



    /**
     * 设置标题栏颜色透明度
     * @param i 透明度 0 ~ 100  越小越透明
     *
     */
    void setToolbarColorTransparent(int i);



    /**
     * 设置标题栏名字
     */
    void setToolbarTitle(String title);


    /**
     * 设置标题栏右边数字
     */
    void setToolbarRightNumberTitle(String number);

    /**
     * 显示错误页面
     */
    void showErrorView();
    /**
     * 隐藏错误页面
     */
    void hiddenErrorView();


    /**
     * 设置左边字体颜色
     */
    void setToolbarLeftColor(int color);

}
