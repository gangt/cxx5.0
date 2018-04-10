package com.xi6666.evaluate.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.evaluate.fragment.GoodsEvaluateFragment;
import com.xi6666.evaluate.fragment.QuestionAndAnswerFragment;
import com.xi6666.evaluate.fragment.ServersEvaluateFragment;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.order.other.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyEvaluateAndAnswerActivity extends AppCompatActivity {

    @BindView(R.id.iv_back_my_evaluate)
    ImageView    mIvBackMyEvaluate;
    @BindView(R.id.my_evaluate_tb)
    Toolbar      mMyEvaluateTb;
    @BindView(R.id.goods_evaluate_tv)
    TextView     mGoodsEvaluateTv;
    @BindView(R.id.tab_goods_evaluate_ll)
    LinearLayout mTabGoodsEvaluateLl;
    @BindView(R.id.server_evaluate_tv)
    TextView     mServerEvaluateTv;
    @BindView(R.id.tab_server_evaluate_ll)
    LinearLayout mTabServerEvaluateLl;
    @BindView(R.id.answer_tv)
    TextView     mAnswerTv;
    @BindView(R.id.tab_answer_ll)
    LinearLayout mTabAnswerLl;
    @BindView(R.id.tab_line_iv_evaluate)
    ImageView    mTabLineIvEvaluate;
    @BindView(R.id.my_evaluate_vp)
    ViewPager    mMyEvaluateVp;

    /**
     * 屏幕的宽度
     */
    private int screenWidth;

    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;

    private FragmentAdapter mFragmentAdapter;

    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_evaluate_and_answer);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        ToolBarBaseActivity.setMiuiStatusBarDarkMode(this,true);
        init();
        initTabLineWidth();
    }

    private void init() {
        // 给mFragmentList添加fragment
        mFragmentList.add(new GoodsEvaluateFragment());
        mFragmentList.add(new ServersEvaluateFragment());
        mFragmentList.add(new QuestionAndAnswerFragment());

        mMyEvaluateVp.setCurrentItem(0);

        mMyEvaluateVp.setOffscreenPageLimit(2);

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(),mFragmentList);
        mMyEvaluateVp.setAdapter(mFragmentAdapter);

        mMyEvaluateVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIvEvaluate
                        .getLayoutParams();
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - positionOffset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - positionOffset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                }
                mTabLineIvEvaluate.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mGoodsEvaluateTv.setTextColor(getResources().getColor(R.color.themeColor));
                        break;
                    case 1:
                        mServerEvaluateTv.setTextColor(getResources().getColor(R.color.themeColor));
                        break;
                    case 2:
                        mAnswerTv.setTextColor(getResources().getColor(R.color.themeColor));
                        break;
                }
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.iv_back_my_evaluate, R.id.tab_goods_evaluate_ll, R.id.tab_server_evaluate_ll, R.id.tab_answer_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_my_evaluate:
                finish();
                break;
            case R.id.tab_goods_evaluate_ll:
                mMyEvaluateVp.setCurrentItem(0);
                break;
            case R.id.tab_server_evaluate_ll:
                mMyEvaluateVp.setCurrentItem(1);
                break;
            case R.id.tab_answer_ll:
                mMyEvaluateVp.setCurrentItem(2);
                break;
        }
    }

    private void resetTextView() {
        mGoodsEvaluateTv.setTextColor(Color.parseColor("#626262"));
        mServerEvaluateTv.setTextColor(Color.parseColor("#626262"));
        mAnswerTv.setTextColor(Color.parseColor("#626262"));
    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIvEvaluate.getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLineIvEvaluate.setLayoutParams(lp);
    }
}
