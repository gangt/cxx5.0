package com.xi6666.carWash.view.custom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;

/**
 * 创建者 sunsun
 * 时间 16/11/1 上午11:45.
 * 个人公众号 ardays
 * tab
 */

public class SortTabView extends LinearLayout implements SortTabViewImpl {

    /**
     * 当前类的View
     */
    public View mView;
    /**
     * tab文本
     */
    public TextView mTitleTv;
    /**
     * tab右边的按钮
     */
    public ImageView mRightIconIv;
    /**
     * 箭头朝上(升序) 代表true (默认)
     * 箭头朝下(降序) 代表false
     */
    private boolean isSwitch = true;
    /**
     * 旋转的角度
     */
    private float mRotationX = 0f;
    /**
     * 是否选中
     */
    private boolean mIsSelected = true;
    /**
     * 选中颜色
     */
    private int mSelectTitleColor = R.color.text_green;
    /**
     * 取消选中的颜色
     */
    private int mDefaultTitleColor = R.color.text_gray;


    public SortTabView(Context context) {
        this(context, null);
    }

    public SortTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SortTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //初始化布局
        mView = LinearLayout.inflate(context, R.layout.view_sort_tab, this);
        initView(mView);
        initXml(context, attrs);
        initClick();
    }

    /**
     * 初始化控件
     */
    private void initView(View view) {
        mTitleTv = (TextView) view.findViewById(R.id.view_sort_tab_title_tv);
        mRightIconIv = (ImageView) view.findViewById(R.id.view_sort_tab_right_icon_iv);
    }


    /**
     * 初始化xml
     */
    private void initXml(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SortTabView);
        //取出标题并且写入到文件上面
        setTitle(a.getString(R.styleable.SortTabView_tab_title));
        //判断是升序还是降序
        initSort(a.getBoolean(R.styleable.SortTabView_tab_sort, true));
        //设置颜色(默认灰色)
        setTitleColor(a.getColor(R.styleable.SortTabView_tab_color, mDefaultTitleColor));
        //隐藏按钮
        if (a.getBoolean(R.styleable.SortTabView_tab_icon_hidden, false)) hiddenIcon();
        //选中按钮
        if (a.getBoolean(R.styleable.SortTabView_tab_select, false)) selectedTab();
        else unSelectTab();
        //回收
        a.recycle();
    }

    /**
     * 降序 and 升序
     *
     * @param sort 规则看上面
     */
    private void initSort(boolean sort) {
        this.isSwitch = sort;
    }


    /**
     * 初始化
     */
    private void initClick() {
        mView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 判断是否选中
                 */
                if (!mIsSelected) {   //没选中
                    selectedTab();
                    //代理事件
                    if (mSortTabListener != null)
                        mSortTabListener.onSelectedSortTab(mView, isSwitch);
                } else {    //选中状态
                    //动画
                    ObjectAnimator.ofFloat(mRightIconIv, "rotationX", mRotationX, mRotationX += 180f).start();
                    // 当旋转的角度大于360让他恢复到0重新开始
                    if (mRotationX == 360) {
                        mRotationX = 0;
                    }
                    isSwitch = !isSwitch;
                    //代理事件
                    if (mSortTabListener != null) {
                        mSortTabListener.onSortTabSwitch(mView, isSwitch);
                    }
                }

            }
        });
    }


    /*              #方法             */
    @Override
    public void setTitle(String title) {
        mTitleTv.setText(title);
    }

    @Override
    public void setTitleColor(int color) {
        mTitleTv.setTextColor(getResources().getColor(color));
    }

    @Override
    public void unSelectTab() {
        mIsSelected = false;
        setTitleColor(mDefaultTitleColor);
        mRightIconIv.setImageResource(R.mipmap.ic_sort_not_gray);
    }

    @Override
    public void selectedTab() {
        mIsSelected = true;
        setTitleColor(mSelectTitleColor);
        mRightIconIv.setImageResource(R.mipmap.ic_sort_gray);
    }

    @Override
    public void hiddenIcon() {
        mRightIconIv.setVisibility(GONE);
    }


    /*              #代理方法设置             */
    public OnSortTabListener mSortTabListener;

    public void setOnSortTabListener(OnSortTabListener sortTabListener) {
        this.mSortTabListener = sortTabListener;
    }

    /**
     * 代理设置
     */
    public interface OnSortTabListener {
        /**
         * 点击Tab版切换
         */
        void onSortTabSwitch(View view, boolean bol);

        /**
         * 第一次选中Tab的时候调用
         */
        void onSelectedSortTab(View view, boolean bol);

    }


}
