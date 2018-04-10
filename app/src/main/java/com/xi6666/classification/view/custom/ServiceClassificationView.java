package com.xi6666.classification.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/12 上午10:51.
 * 个人公众号 ardays
 */
public class ServiceClassificationView extends LinearLayout implements ServiceClassificationViewImpl {
    /**
     * 上下文
     */
    Context mContext;


    @BindView(R.id.service_classification_tcv)
    TagCloudView mTag;  //标签
    @BindView(R.id.service_classification_title_tv)
    TextView mTitleTv;  //标题名字

    public ServiceClassificationView(Context context) {
        this(context, null);
    }

    public ServiceClassificationView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public ServiceClassificationView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        View view = LinearLayout.inflate(mContext, R.layout.view_service_classification, this);
        ButterKnife.bind(this, view);
    }


    /**
     *  写tag
     */
    public void setTags(List<ServiceClassificationBean.DataBean.ChildBean> datas){
        List<String> tags = new ArrayList<>();
        for (ServiceClassificationBean.DataBean.ChildBean data : datas){
            tags.add(data.cate_name);
        }
        mTag.setTags(tags);
    }

    /**
     * 设置点击事件
     */
    public void setOnTagClick(TagCloudView.OnTagClickListener listener){
        mTag.setOnTagClickListener(listener);
    }


    /**
     * 设置标题
     */
    public void setTitle(String title){
        mTitleTv.setText(title);
    }









}