package com.xi6666.classification.view.custom;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBrandBean;
import com.xi6666.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/12 下午1:37.
 * 个人公众号 ardays
 */
public class ItemServiceClassificationBrandView extends LinearLayout {
    /**
     * 上下文
     */
    Context mContext;

    public ItemServiceClassificationBrandView(Context context) {
        this(context, null);
    }

    public ItemServiceClassificationBrandView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public ItemServiceClassificationBrandView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }


    @BindView(R.id.item_service_classification_brand_title_tv)
    TextView mBrandTitleTv; //标题
    @BindView(R.id.item_service_classification_brand_rv)
    RecyclerView mBrandRv;  //列表


    BrandAdapter mBrandAdapter; //适配器

    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.item_service_classification_brand, this);
        ButterKnife.bind(this, view);
        initItem();
    }

    /**
     * 初始化条目
     */
    private void initItem() {
        //设置一行显示三个
        mBrandRv.setLayoutManager(new GridLayoutManager(mContext, 3));
//        mBrandRv.setLayoutManager(new LinearLayoutManager(mContext));
        //设置adapter
        mBrandAdapter = new BrandAdapter(mContext);
        mBrandRv.setAdapter(mBrandAdapter);
    }

    public void setData(ServiceClassificationBrandBean.DataBean data) {
        //设置标题
        mBrandTitleTv.setText(data.title);
        if(data.list == null){
            return;
        }
        //添加条目图片
        mBrandAdapter.addAll(data.list);
    }


    /**
     * 监听图片点击
     */
    public void setOnImageItemClick(BrandAdapter.BrandAdapterListener listener) {
        mBrandAdapter.setBrandAdapterListener(listener);
    }


    public static class BrandAdapter extends RecyclerView.Adapter {

        public List<ServiceClassificationBrandBean.DataBean.ListBean> mData;  //
        public Context mContext;

        public BrandAdapter(Context context) {
            mData = new ArrayList<>();
            this.mContext = context;
//            //测试数据
//            mData.add("http://v3.qzone.cc/manage/201611/12/12/01/5826942aeacc0538.jpg");
//            mData.add("http://v3.qzone.cc/manage/201611/12/12/01/5826942c96838484.jpg");
//            mData.add("http://v3.qzone.cc/manage/201611/12/12/01/5826942f61165217.jpg");
//            mData.add("http://v3.qzone.cc/manage/201611/12/12/01/5826943172687888.jpg");
//            mData.add("http://v3.qzone.cc/manage/201611/12/12/01/5826943389f43413.jpg");
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_image_three, null);
            return new BrandViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            BrandViewHolder h = (BrandViewHolder) holder;
            //加载图片
            Glide.with(BaseApplication.getmAppContext())
                    .load(mData.get(position).img)
                    .placeholder(R.drawable.bg_image_default)
                    .error(R.drawable.bg_image_default)
                    .fitCenter()
                    .into(h.itemIv);

            h.itemIv.setOnClickListener(v -> {
                if (mBrandAdapterListener != null) mBrandAdapterListener.onImageItemClick(position);
            });
        }

        public void addAll(List<ServiceClassificationBrandBean.DataBean.ListBean> data) {
            this.mData.addAll(data);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }


        class BrandViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.iv)
            ImageView itemIv;

            public BrandViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        BrandAdapterListener mBrandAdapterListener;

        public void setBrandAdapterListener(BrandAdapterListener listener) {
            this.mBrandAdapterListener = listener;
        }

        public interface BrandAdapterListener {
            void onImageItemClick(int position);
        }
    }
}